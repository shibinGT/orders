package services;

import java.util.List;

import dao.GoodsDAO;




import entity.Goods;

/**
 * 商品服务
 * 
 * @author Administrator
 *
 */
public class GoodsService {

	private GoodsDAO dao = new GoodsDAO();
	
	public boolean updateGoods(int stock, int goods_id){
		return dao.updateGoods(stock, goods_id);
	}

	public boolean addGoods(Goods u) {

		return dao.addGoods(u);
	}

	public boolean deleteGoods(int id) {

		return dao.deleteGoods(id);
	}

	public Goods queryByGoods_id(int id) {

		return dao.queryByGoods_id(id);
	}

	public List<Goods> queryList(String goods_name,int pageNo,int pageSize) {

		return dao.queryList(goods_name,pageNo, pageSize);
	}
	public boolean updateGoods(String goods_name,float price,int stock,String picture,String describes, int goods_id){
		return dao.updateGoods(goods_name,price, stock,picture,describes,goods_id);
	}
	public int queryCount(String name){
		return dao.queryCount(name);
	}
}
