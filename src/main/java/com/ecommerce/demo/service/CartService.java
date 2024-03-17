package com.ecommerce.demo.service;

import com.ecommerce.demo.dto.AddToCartDto;
import com.ecommerce.demo.dto.CartDto;
import com.ecommerce.demo.dto.CartItemDto;
import com.ecommerce.demo.exception.CartItemNotExistException;
import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.model.User;
import com.ecommerce.demo.repository.CartRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CartService {

    @Autowired
    private CartRepo cartRepository;

    public CartService(){}

    public CartService(CartRepo cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addToCart(AddToCartDto addToCartDto, Product product, User user){
        Cart cart = new Cart(product, addToCartDto.getQuantity(), user);
        cartRepository.save(cart);
    }


    public CartDto listCartItems(User user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart:cartList){
            CartItemDto cartItemDto = new CartItemDto(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for (CartItemDto cartItemDto :cartItems){
            totalCost += (cartItemDto.getProduct().getPrice()* cartItemDto.getQuantity());
        }
        return new CartDto(cartItems,totalCost);
    }


//    public static CartItemDto getDtoFromCart(Cart cart) {
//        return new CartItemDto(cart);
//    }
//
//
//    public void updateCartItem(AddToCartDto cartDto, User user,Product product){
//        Cart cart = cartRepository.getOne(cartDto.getId());
//        cart.setQuantity(cartDto.getQuantity());
//        cart.setCreatedDate(new Date());
//        cartRepository.save(cart);
//    }
//
    public void deleteCartItem(int id,int userId) throws CartItemNotExistException {
        if (!cartRepository.existsById(id))
            throw new CartItemNotExistException("Cart id is invalid : " + id);
        cartRepository.deleteById(id);

    }

//    public void deleteCartItems(int userId) {
//        cartRepository.deleteAll();
//    }
//
//
//    public void deleteUserCartItems(User user) {
//        cartRepository.deleteByUser(user);
//    }
}