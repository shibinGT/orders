package entity;

import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * 订单实体类
 * @author Administrator
 *
 */
public class Orders {
	private int orders_id;
	private int user_id;
	private String user_name;
	private int status;
	private float money;
	private String  createtime;
	private String paymenttime;
	
	public int getOrders_id() {
		return orders_id;
	}
	public void setOrders_id(int orders_id) {
		this.orders_id = orders_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getPaymenttime() {
		return paymenttime;
	}
	public void setPaymenttime(String paymenttime) {
		this.paymenttime = paymenttime;
	}

	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public Orders(int orders_id, int user_id, String user_name, int status, float money, String createtime,
			String paymenttime) {
		super();
		this.orders_id = orders_id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.status = status;
		this.money = money;
		this.createtime = createtime;
		this.paymenttime = paymenttime;
	}
	@Override
	public String toString() {
		return "Orders [orders_id=" + orders_id + ", user_id=" + user_id + ", user_name=" + user_name + ", status="
				+ status + ", money=" + money + ", createtime=" + createtime + ", paymenttime=" + paymenttime + "]";
	}
	public Orders() {
		super();
	}
	
}
