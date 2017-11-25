package entity;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

/**
 * 购物车实体类
 * @author Administrator
 *
 */
public class Cart {
	private int cart_id;
	private int goods_id;
	private int number;
	private int user_id;
	private String goods_name;
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	@Override
	public String toString() {
		return "Cart [cart_id=" + cart_id + ", goods_id=" + goods_id + ", number=" + number + ", user_id=" + user_id
				+ ", goods_name=" + goods_name + "]";
	}
	public Cart(int cart_id, int goods_id, int number, int user_id, String goods_name) {
		super();
		this.cart_id = cart_id;
		this.goods_id = goods_id;
		this.number = number;
		this.user_id = user_id;
		this.goods_name = goods_name;
	}
	public Cart() {
		super();
		System.out.println();
	}
	
}
