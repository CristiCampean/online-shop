package org.fasttrackit.onlineshop.web;

import org.fasttrackit.onlineshop.domain.Cart;
import org.fasttrackit.onlineshop.service.CartService;
import org.fasttrackit.onlineshop.transfer.AddProdactToCartRequest;
import org.fasttrackit.onlineshop.transfer.cart.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping( "/carts")
public class CartController {

    private final CartService cartService;
@Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    public CartService getCartService() {
        return cartService;
    }
    @PutMapping
    public ResponseEntity<Cart>addProductToCar( @RequestBody @Valid AddProdactToCartRequest request){
        Cart cart = cartService.addProductToCart(request);
       return ResponseEntity.ok(cart);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<CartResponse>getCart(@PathVariable long userId){
        CartResponse cart = cartService.getCart(userId);
        return ResponseEntity.ok((cart));
    }

}

