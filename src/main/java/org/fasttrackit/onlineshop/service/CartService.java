package org.fasttrackit.onlineshop.service;


import org.fasttrackit.onlineshop.domain.Cart;
import org.fasttrackit.onlineshop.domain.Product;
import org.fasttrackit.onlineshop.domain.User;
import org.fasttrackit.onlineshop.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshop.persistence.CartRepository;
import org.fasttrackit.onlineshop.transfer.cart.AddProdactToCartRequest;
import org.fasttrackit.onlineshop.transfer.cart.CartResponse;
import org.fasttrackit.onlineshop.transfer.cart.ProdactInCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public CartService(CartRepository cartRepository, UserService userService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Transactional
    public void addProductToCart(AddProdactToCartRequest request) {

        LOGGER.info("Adding product to cart: {}", request);
        Cart cart = cartRepository.findById(request.getUserId())
                .orElse(new Cart());

        if (cart.getUser() == null) {
            User user = userService.getUser(request.getUserId());
            cart.setUser(user);
        }
        Product product = productService.getProduct((request.getProductId()));
             cart.addProduct(product);
             cartRepository.save(cart);
        }


       @Transactional
        public CartResponse getCart (long userId){
        LOGGER.info("Retrieving cart for user{}",userId);
//            Optional<Cart> optional = cartRepository.findById(userId);
//            if(optional.isPresent()){
//                return optional.get();
//            }else{
//                throw new ResourceNotFoundException("Cart"+userId+ "does not exist");
//            }
            Cart cart = cartRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("Cart" + userId + "does not exist"));
            CartResponse cartResponse = new CartResponse();
            cartResponse.setId(cart.getId());

           Set<ProdactInCart> prodacts = new HashSet<>();
           for(Product product : cart.getProducts()){

               ProdactInCart prodactInCart = new ProdactInCart();
               prodactInCart.setId(product.getId());
               prodactInCart.setName(product.getName());
               prodactInCart.setPrice(product.getPrice());
               prodactInCart.setImageUrl(product.getImageUrl());
               prodacts.add(prodactInCart);
           }

           cartResponse.setProducts(prodacts);
            return cartResponse;
        }

    }

