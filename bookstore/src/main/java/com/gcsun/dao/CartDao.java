package com.gcsun.dao;

import com.gcsun.entity.Cart;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 11981 on 2017/11/29.
 */
@Component
public interface CartDao {
    /**
     * 添加购物车商品
     * @param cart
     */
    void addProduct(Cart cart);


    /**
     * 根据用户ID查询购物车内容
     * @param userId
     * @return
     */
    List<Cart> findAddCart(String userId);

    /**
     * 根据购物车ID删除商品
     * @param cartId
     */
    void deleteProduct(String cartId);
}
