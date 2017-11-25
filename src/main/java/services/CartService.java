package services;


import java.util.List;

import dao.CartDAO;

import entity.Cart;

public class CartService {
	private CartDAO dao = new CartDAO();
	/**
	 * 添加
	 * @param u
	 * @return
	 */
	public boolean addCart(Cart u){
		return dao.addCart(u);
	}
	/**
	 * 根据商品id，用户id来增加商品或减少商品数量
	 * @param number
	 * @param goods_id
	 * @return
	 */
	public boolean updateGoods(int number,int goods_id,int user_id){
		return dao.updateGoods(number, goods_id,user_id);
	}
	/**
	 * 根据商品id，用户id来删除购物车记录
	 * @param goods_id
	 * @param user_id
	 * @return
	 */
	public boolean deleteGoods(int goods_id,int user_id){
		return dao.deleteGoods(goods_id,user_id);
	}
	/**
	 * 根据商品id，用户id来查询有无该商品记录
	 * @param goods_id
	 * @param user_id
	 * @return
	 */
	public Cart queryByGoods_idAndUser_id(int goods_id,int user_id){
		return dao.queryByGoods_idAndUser_id(goods_id, user_id);
	}
	/**
	 * 查询该用户的购物车
	 * @param user_id
	 * @return
	 */
	public List<Cart> queryByUser_id(int user_id){
		return dao.queryByUser_id(user_id);
	}
	/**
	 * 清空购物车
	 * 
	 */
	public boolean deleteGoodsAll(int user_id){
		return dao.deleteGoodsAll(user_id);
	}
}
