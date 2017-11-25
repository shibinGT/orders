package services;


import java.util.List;

import dao.CartDAO;

import entity.Cart;

public class CartService {
	private CartDAO dao = new CartDAO();
	/**
	 * ���
	 * @param u
	 * @return
	 */
	public boolean addCart(Cart u){
		return dao.addCart(u);
	}
	/**
	 * ������Ʒid���û�id��������Ʒ�������Ʒ����
	 * @param number
	 * @param goods_id
	 * @return
	 */
	public boolean updateGoods(int number,int goods_id,int user_id){
		return dao.updateGoods(number, goods_id,user_id);
	}
	/**
	 * ������Ʒid���û�id��ɾ�����ﳵ��¼
	 * @param goods_id
	 * @param user_id
	 * @return
	 */
	public boolean deleteGoods(int goods_id,int user_id){
		return dao.deleteGoods(goods_id,user_id);
	}
	/**
	 * ������Ʒid���û�id����ѯ���޸���Ʒ��¼
	 * @param goods_id
	 * @param user_id
	 * @return
	 */
	public Cart queryByGoods_idAndUser_id(int goods_id,int user_id){
		return dao.queryByGoods_idAndUser_id(goods_id, user_id);
	}
	/**
	 * ��ѯ���û��Ĺ��ﳵ
	 * @param user_id
	 * @return
	 */
	public List<Cart> queryByUser_id(int user_id){
		return dao.queryByUser_id(user_id);
	}
	/**
	 * ��չ��ﳵ
	 * 
	 */
	public boolean deleteGoodsAll(int user_id){
		return dao.deleteGoodsAll(user_id);
	}
}
