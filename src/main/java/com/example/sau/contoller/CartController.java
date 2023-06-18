package com.example.sau.contoller;

import com.example.sau.global.GlobalData;
import com.example.sau.model.CartItem;
import com.example.sau.model.CustomerForm;
import com.example.sau.model.Product;
import com.example.sau.service.CartItemServiceImpl;
import com.example.sau.service.CustomerFormServiceImpl;
import com.example.sau.service.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductServiceImpl productServiceImpl;
    private final CartItemServiceImpl cartItemService;
    private final CustomerFormServiceImpl customerFormService;

    public CartController(ProductServiceImpl productServiceImpl, CartItemServiceImpl cartItemService, CustomerFormServiceImpl customerFormService) {
        this.productServiceImpl = productServiceImpl;
        this.cartItemService = cartItemService;
        this.customerFormService = customerFormService;
    }

    @GetMapping("")
        public String cartGet(Model model) {
            int totalItems = calculateTotalItems();
            model.addAttribute("cartCounter", totalItems);
            model.addAttribute("total", calculateTotal());
            model.addAttribute("cart", GlobalData.cart);

            if (totalItems == 0) {
                return "cart/empty-cart";
            } else {
                return "cart/cart";
            }
        }

    private int calculateTotalItems() {
        return GlobalData.cart.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

        @GetMapping("/add/{id}")
        public String addToCart(@PathVariable long id) {
            try {
                Optional<Product> productOptional = productServiceImpl.getProductById(id);
                if (productOptional.isPresent()) {
                    Product product = productOptional.get();

                    CartItem cartItem = new CartItem();
                    cartItem.setProduct(product);
                    cartItem.setQuantity(1);
                    cartItem.setSubTotal(product.getPrice());
                    cartItem.setImageName(product.getImageName());
                    List<CartItem> cartItems = new ArrayList<>();
                    cartItems.add(cartItem);
                    GlobalData.cart = cartItems;
                }
            } catch (Exception e) {
                return "redirect:/shop?error=addToCartError";
            }

            return "redirect:/home/shop";
        }

        @PostMapping("/add")
        public String addItemToCart(@RequestParam("productId") long productId,
                                    RedirectAttributes redirectAttributes, Model model) {
            try {
                Optional<Product> productOptional = productServiceImpl.getProductById(productId);
                model.addAttribute("cartSize", GlobalData.cart.size());
                if (productOptional.isPresent()) {
                    Product product = productOptional.get();

                    CartItem cartItem = new CartItem();
                    cartItem.setProduct(product);
                    cartItem.setQuantity(1);
                    cartItem.setSubTotal(product.getPrice());
                    cartItem.setImageName(product.getImageName());

                    GlobalData.cart.add(cartItem);
                } else {
                    redirectAttributes.addAttribute("errorMessage", "Error adding item to cart. Product not found.");
                }
            } catch (Exception e) {
                redirectAttributes.addAttribute("errorMessage", "Error adding item to cart. Please try again.");
            }

            return "redirect:/home/shop";
        }


        @PostMapping("/updateQuantity")
        public String updateCartItemQuantity(@RequestParam("productId") long productId, @RequestParam("quantity") int quantity) {
            Optional<CartItem> cartItemOptional = cartItemService.getCartItemByProductId(productId);

            if (cartItemOptional.isPresent()) {
                CartItem cartItem = cartItemOptional.get();

                cartItem.setQuantity(quantity);

                cartItem.setSubTotal(cartItem.getProduct().getPrice() * quantity);
            }

            return "redirect:/cart";
        }

        @GetMapping("/removeProduct/{index}")
        public String cartItemRemove(@PathVariable int index, RedirectAttributes redirectAttributes) {
            try {
                if (index >= 0 && index < GlobalData.cart.size()) {
                    GlobalData.cart.remove(index);
                } else {
                    redirectAttributes.addAttribute("errorMessage", "Invalid index. Product not removed.");
                }
            } catch (Exception e) {
                redirectAttributes.addAttribute("errorMessage", "Error removing product. Please try again.");
            }

            return "redirect:/cart";
        }

            @GetMapping("/checkout")
            public String checkout(Model model) {
                model.addAttribute("customerForm", new CustomerForm());
                model.addAttribute("total", calculateTotal());
                return "cart/checkout";
            }

            @PostMapping("/checkout")
            public String checkoutSend(@ModelAttribute("customerForm") CustomerForm customerForm){
                customerFormService.addCustomerForm(customerForm);
                return "cart/successfulPage";
            }

            private double calculateTotal() {
                return GlobalData.cart.stream().mapToDouble(CartItem::getSubTotal).sum();
            }

    }
