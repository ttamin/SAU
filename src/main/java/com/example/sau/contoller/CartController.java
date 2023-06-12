//package com.example.sau.contoller;
////
//import com.example.sau.form.CustomerForm;
//import com.example.sau.global.GlobalData;
//import com.example.sau.info.CartInfo;
//import com.example.sau.info.CustomerInfo;
//import com.example.sau.info.ProductInfo;
//import com.example.sau.model.Product;
//import com.example.sau.service.ProductService;
//import com.example.sau.utils.Utils;
//import org.springframework.beans.factory.annotation.Autowired;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.util.Optional;
//
////
//@Controller
//public class CartController {
//    @Autowired
//    ProductService productService;
//    @GetMapping("/addToCart/{id}")
//    public String addToCart(@PathVariable long id){
//        GlobalData.cart.add(productService.getProductById(id).get());
//        return "redirect:/shop";
//    }
//    @GetMapping("/cart")
//    public String cartGet(Model model){
//        model.addAttribute("cartCounter", GlobalData.cart.size());
//        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
//        model.addAttribute("cart", GlobalData.cart);
//        return "cart";
//    }
//
//    @GetMapping("/cart/removeProduct/{index}")
//    public String cartItemRemove(@PathVariable int index){
//        GlobalData.cart.remove(index);
//        return "redirect:/cart";
//    }
//
//    @GetMapping("checkout")
//    public String checkout(Model model){
//        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
//        return "checkout";
//    }
//}
//
//
//
//    @RequestMapping({"/buyProduct"})
//    public String listProductHandler(HttpServletRequest request, Model model, //
//                                     @RequestParam(value = "id", defaultValue = "") Long id) {
//
//        Optional<Product> product = null;
//        if (id != null) {
//            product = productService.getProductById(id);
//        }
//        if (product != null) {
//
//            //
//            CartInfo cartInfo = Utils.getCartInSession(request);
//
//            ProductInfo productInfo = new ProductInfo(product);
//
//            cartInfo.addProduct(productInfo, 1);
//        }
//
//        return "redirect:/shoppingCart";
//    }
//
//    @RequestMapping({"/shoppingCartRemoveProduct"})
//    public String removeProductHandler(HttpServletRequest request, Model model, //
//                                       @RequestParam(value = "id", defaultValue = "") Long id) {
//
//        Optional<Product> product = null;
//        if (id != null) {
//            product = productService.getProductById(id);
//        }
//        if (product != null) {
//
//            CartInfo cartInfo = Utils.getCartInSession(request);
//
//            ProductInfo productInfo = new ProductInfo(product);
//
//            cartInfo.removeProduct(productInfo);
//
//        }
//
//        return "redirect:/shoppingCart";
//    }
//
//    // POST: Update quantity for product in cart
//    @RequestMapping(value = {"/shoppingCart"}, method = RequestMethod.POST)
//    public String shoppingCartUpdateQty(HttpServletRequest request, //
//                                        Model model, //
//                                        @ModelAttribute("cartForm") CartInfo cartForm) {
//
//        CartInfo cartInfo = Utils.getCartInSession(request);
//        cartInfo.updateQuantity(cartForm);
//
//        return "redirect:/shoppingCart";
//    }
//
//    // GET: Show cart.
//    @RequestMapping(value = {"/shoppingCart"}, method = RequestMethod.GET)
//    public String shoppingCartHandler(HttpServletRequest request, Model model) {
//        CartInfo myCart = Utils.getCartInSession(request);
//        CartInfo cartInfo = Utils.getCartInSession(request);
//
//        model.addAttribute("cartForm", myCart);
//        model.addAttribute("myCart", cartInfo);
//        return "shoppingCart";
//    }
//
//    // GET: Enter customer information.
//    @RequestMapping(value = {"/shoppingCartCustomer"}, method = RequestMethod.GET)
//    public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
//
//        CartInfo cartInfo = Utils.getCartInSession(request);
//
//        if (cartInfo.isEmpty()) {
//
//            return "redirect:/shoppingCart";
//        }
//        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
//
//        CustomerForm customerForm = new CustomerForm(customerInfo);
//
//        model.addAttribute("customerForm", customerForm);
//
//        return "shoppingCartCustomer";
//    }
//
//    // POST: Save customer information.
//    @RequestMapping(value = {"/shoppingCartCustomer"}, method = RequestMethod.POST)
//    public String shoppingCartCustomerSave(HttpServletRequest request, //
//                                           Model model, //
//                                           @ModelAttribute("customerForm") @Validated CustomerForm customerForm, //
//                                           BindingResult result, //
//                                           final RedirectAttributes redirectAttributes) {
//
//        if (result.hasErrors()) {
//            customerForm.setValid(false);
//            // Forward to reenter customer info.
//            return "shoppingCartCustomer";
//        }
//
//        customerForm.setValid(true);
//        CartInfo cartInfo = Utils.getCartInSession(request);
//        CustomerInfo customerInfo = new CustomerInfo(customerForm);
//        cartInfo.setCustomerInfo(customerInfo);
//
//        return "redirect:/shoppingCartConfirmation";
//    }
//
//    // GET: Show information to confirm.
//    @RequestMapping(value = {"/shoppingCartConfirmation"}, method = RequestMethod.GET)
//    public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
//        CartInfo cartInfo = Utils.getCartInSession(request);
//
//        if (cartInfo == null || cartInfo.isEmpty()) {
//
//            return "redirect:/shoppingCart";
//        } else if (!cartInfo.isValidCustomer()) {
//
//            return "redirect:/shoppingCartCustomer";
//        }
//        model.addAttribute("myCart", cartInfo);
//
//        return "shoppingCartConfirmation";
//    }
//
//    // POST: Submit Cart (Save)
//    @RequestMapping(value = {"/shoppingCartConfirmation"}, method = RequestMethod.POST)
//
//    public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
//        CartInfo cartInfo = Utils.getCartInSession(request);
//
//        if (cartInfo.isEmpty()) {
//
//            return "redirect:/shoppingCart";
//        } else if (!cartInfo.isValidCustomer()) {
//
//            return "redirect:/shoppingCartCustomer";
//        }
//        try {
//            orderDAO.saveOrder(cartInfo);
//        } catch (Exception e) {
//
//            return "shoppingCartConfirmation";
//        }
//
//        // Remove Cart from Session.
//        Utils.removeCartInSession(request);
//
//        // Store last cart.
//        Utils.storeLastOrderedCartInSession(request, cartInfo);
//
//        return "redirect:/shoppingCartFinalize";
//    }
//
//    @RequestMapping(value = {"/shoppingCartFinalize"}, method = RequestMethod.GET)
//    public String shoppingCartFinalize(HttpServletRequest request, Model model) {
//
//        CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);
//
//        if (lastOrderedCart == null) {
//            return "redirect:/shoppingCart";
//        }
//        model.addAttribute("lastOrderedCart", lastOrderedCart);
//        return "shoppingCartFinalize";
//    }
//}
