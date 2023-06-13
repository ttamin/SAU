package com.example.sau.contoller;

import com.example.sau.global.GlobalData;
import com.example.sau.model.CartItem;
import com.example.sau.model.Product;
import com.example.sau.service.CartItemService;
import com.example.sau.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ShoppingCartController {

        @Autowired
        ProductService productService;

        @Autowired
        CartItemService cartItemService;
        @GetMapping("/cart")
        public String cartGet(Model model) {
            int totalItems = calculateTotalItems();
            model.addAttribute("cartCounter", totalItems);
//            model.addAttribute("cartCounter", GlobalData.cart.size());
            model.addAttribute("total", calculateTotal());
            model.addAttribute("cart", GlobalData.cart);
            return "cart";
        }

    private int calculateTotalItems() {
        return GlobalData.cart.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

        @GetMapping("/cart/addT/{id}")
        public String addToCart(@PathVariable long id) {
            Optional<Product> productOptional = productService.getProductById(id);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();

                // Создание нового элемента корзины
                CartItem cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setQuantity(1); // Установка начального количества товара
                cartItem.setSubTotal(product.getPrice());
                cartItem.setImageName(product.getImageName());
                // Добавление элемента корзины в корзину
                List<CartItem> cartItems = new ArrayList<>();
                cartItems.add(cartItem);
                GlobalData.cart = cartItems;
            }

            return "redirect:/shop";
        }

    @PostMapping("/cart/add")
    public String addItemToCart(@RequestParam("productId") long productId) {
        Optional<Product> productOptional = productService.getProductById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            // Create a new cart item
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItem.setSubTotal(product.getPrice());
            cartItem.setImageName(product.getImageName());

            // Add the cart item to the cart
            GlobalData.cart.add(cartItem);
        }

        return "redirect:/shop";
    }


    @PostMapping("/cart/updateQuantity")
    public String updateCartItemQuantity(@RequestParam("productId") long productId, @RequestParam("quantity") int quantity) {
        Optional<CartItem> cartItemOptional = cartItemService.getCartItemByProductId(productId);

        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();

            // Обновление количества товара
            cartItem.setQuantity(quantity);

            // Обновление цены элемента корзины
            cartItem.setSubTotal(cartItem.getProduct().getPrice() * quantity);
        }

        return "redirect:/cart";
    }


        @GetMapping("/cart/removeProduct/{index}")
        public String cartItemRemove(@PathVariable int index) {
            GlobalData.cart.remove(index);
            return "redirect:/cart";
        }

        @GetMapping("/checkout")
        public String checkout(Model model) {
            model.addAttribute("total", calculateTotal());
            return "checkout";
        }


        private double calculateTotal() {
            return GlobalData.cart.stream().mapToDouble(CartItem::getSubTotal).sum();
        }
            // Остальные методы контроллера


}
