package entity;
/**
 * 商品实体类
 * @author Administrator
 *
 */
public class Goods {
	private int goods_id;
	private String goods_name;
	private float price;
	private int stock;
	private String picture;
	private String describes;
	
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getDescribes() {
		return describes;
	}
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	@Override
	public String toString() {
		return "Goods [goods_id=" + goods_id + ", goods_name=" + goods_name + ", price=" + price + ", stock=" + stock
				+ ", picture=" + picture + ", describes=" + describes + "]";
	}
	public Goods(int goods_id, String goods_name, float price, int stock, String picture, String describes) {
		super();
		this.goods_id = goods_id;
		this.goods_name = goods_name;
		this.price = price;
		this.stock = stock;
		this.picture = picture;
		this.describes = describes;
	}
	public Goods() {
		super();
	}
	
}
