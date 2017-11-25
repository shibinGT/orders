package entity;
/**
 * 订单商品实体类
 * @author Administrator
 *
 */
public class Orders_goods {
	private int og_id;
	private int goods_id;
	private int orders_id;
	private int number;
	private float price;
	private String goods_name;
	public int getOg_id() {
		return og_id;
	}
	public void setOg_id(int og_id) {
		this.og_id = og_id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public int getOrders_id() {
		return orders_id;
	}
	public void setOrders_id(int orders_id) {
		this.orders_id = orders_id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	@Override
	public String toString() {
		return "Orders_goods [og_id=" + og_id + ", goods_id=" + goods_id + ", orders_id=" + orders_id + ", number="
				+ number + ", price=" + price + ", goods_name=" + goods_name + "]";
	}
	public Orders_goods(int og_id, int goods_id, int orders_id, int number, float price, String goods_name) {
		super();
		this.og_id = og_id;
		this.goods_id = goods_id;
		this.orders_id = orders_id;
		this.number = number;
		this.price = price;
		this.goods_name = goods_name;
	}
	public Orders_goods() {
		super();
	}
	
}
