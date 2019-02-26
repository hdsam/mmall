package com.mmall.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CartMapper;
import com.mmall.pojo.Cart;
import com.mmall.service.ICartService;
import com.mmall.vo.CartProductVo;
import com.mmall.vo.CartVo;

@Service("iCartService")
public class CartServiceImpl implements ICartService {

	@Autowired
	private CartMapper cartMapper;
	
	public ServerResponse add(Integer userId,Integer productId, Integer count) {
		Cart cart = cartMapper.selectCartByUserIdProductId(userId, productId);
		if(cart == null) {
			//此产品不在购物车里，需要新增一个这个产品的记录
			Cart cartItem = new Cart();
			cartItem.setQuantity(count);
			cartItem.setChecked(Const.Cart.CHECKED);
			cartItem.setProductId(productId);
			cartItem.setUserId(userId);
			cartMapper.insert(cartItem);
		}else {
			//这个产品已经存在，数量增加
			count=cart.getQuantity()+count;
			cart.setQuantity(count);
			cartMapper.updateByPrimaryKey(cart);
		}
		return null;
	}
	
	private CartVo getCartVoLimit(Integer userId) {
		CartVo cartVo = new CartVo();
		List<Cart> cartList= cartMapper.selectCartByUserId(userId);
		List<CartProductVo> cartProcutVoList = Lists.newArrayList();
		
		BigDecimal cartTotalPrice = new BigDecimal("0");
		//TODO
		return null;
	}
	
}
