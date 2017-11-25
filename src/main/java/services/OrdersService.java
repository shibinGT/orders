package services;

import java.util.List;

import dao.OrdersDAO;
import entity.Orders;

public class OrdersService {
	private OrdersDAO orders=new OrdersDAO();
	
	//添加
	public boolean addOrders(Orders u){
		return	orders.addOrders(u);
	}
	//删除
	public boolean deleteOrders(int orders_id){
		return orders.deleteOrders(orders_id);
	}
	//查个人全部订单
	public List<Orders> queryByUser_id(int user_id){
		return orders.queryByUser_id(user_id);
	}
	//查询特定订单
	public Orders queryByUser_idAndCreatetime(int user_id,String createtime){
		return orders.queryByUser_idAndCreatetime(user_id, createtime);
	}
	public Orders queryByOrders_id(int orders_id){
		return orders.queryByOrders_id(orders_id);
	}
	//查全部人订单
	//付款
	public List<Orders> queryPayByUser_name(String user_name,int pageNo,int pageSize){
		return orders.queryPayByUser_name(user_name,pageNo,pageSize);
	}
	//未付款
	public List<Orders> queryNoPayByUser_name(String user_name,int pageNo,int pageSize){
		return orders.queryNoPayByUser_name(user_name,pageNo,pageSize);
	}
	//全部
	public List<Orders> queryByUser_name(String user_name,int pageNo,int pageSize){
		return orders.queryByUser_name(user_name,pageNo,pageSize);
	}
	//修改订单状态
	public boolean updateGoods(String paymenttime,int orders_id){
		return orders.updateGoods( paymenttime,orders_id);
	}
	public int queryCount(String name) {
		return orders.queryCount(name);
	}
	public int queryCountNo(String name) {
		return orders.queryCountNo(name);
	}
	public int queryCountYes(String name) {
		return orders.queryCountYes(name);
	}
}
