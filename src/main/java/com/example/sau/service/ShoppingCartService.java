//package com.example.sau.service;
//
//import com.example.sau.exception.NotEnoughProductsInStockException;
//import com.example.sau.model.Product;
//import com.example.sau.repository.ProductRepo;
//import com.example.sau.service.impl.IShoppingCartService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.math.BigDecimal;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Transactional
//public class ShoppingCartService implements IShoppingCartService {
//
//    private ProductRepo productRepo;
//
//    private Map<Product, Integer> products = new HashMap<>();
//
//    @Autowired
//    public ShoppingCartService(ProductRepo productRepo)
//    {
//        this.productRepo = productRepo;
//    }
//
//    /**
//     * If product is in the map just increment quantity by 1.
//     * If product is not in the map with, add it with quantity 1
//     *
//     * @param product
//     */
//    @Override
//    public void addProduct(Product product) {
//        if (products.containsKey(product)) {
//            products.replace(product, products.get(product) + 1);
//        } else {
//            products.put(product, 1);
//        }
//    }
//
//    /**
//     * If product is in the map with quantity > 1, just decrement quantity by 1.
//     * If product is in the map with quantity 1, remove it from map
//     *
//     * @param product
//     */
//    @Override
//    public void removeProduct(Product product) {
//        if (products.containsKey(product)) {
//            if (products.get(product) > 1)
//                products.replace(product, products.get(product) - 1);
//            else if (products.get(product) == 1) {
//                products.remove(product);
//            }
//        }
//    }
//
//    /**
//     * @return unmodifiable copy of the map
//     */
//    @Override
//    public Map<Product, Integer> getProductsInCart() {
//        return Collections.unmodifiableMap(products);
//    }
//
//
//    @Override
//    public void checkout() throws NotEnoughProductsInStockException {
//        Product product;
//        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
//            // Refresh quantity for every product before checking
//            product = productRepo.findOne(entry.getKey().getId());
//            if (product.getCurrentQuantity() < entry.getValue())
//                throw new NotEnoughProductsInStockException(product);
//            entry.getKey().setCurrentQuantity(product.getCurrentQuantity() - entry.getValue());
//        }
//        productRepo.save(products.keySet(), );
//        productRepo.flush();
//        products.clear();
//    }
//
//    @Override
//    public BigDecimal getTotal() {
//        return products.entrySet().stream()
//                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
//                .reduce(BigDecimal::add)
//                .orElse(BigDecimal.ZERO);
//    }
//}
