package services;

import java.util.List;

import dao.OrderGoodsDAO;
import entity.Orders_goods;

public class Orders_goodsService {
	private OrderGoodsDAO dao=new OrderGoodsDAO();
	/**
	 * ��Ӷ�����Ʒ
	 */
	public boolean addOrders_goods(Orders_goods u){
		return dao.addOrders_goods(u);
	}
	//�鿴������Ʒ
	public List<Orders_goods> queryByOrders_id(int orders_id){
		return dao.queryByOrders_id(orders_id);
	}
}
