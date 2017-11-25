package dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import org.apache.commons.dbutils.handlers.BeanListHandler;



import entity.Orders_goods;



/**
 * 订单商品DAO
 * @author Administrator
 *
 */
public class OrderGoodsDAO {
	//使用DBUtils的辅助类来操作数据库
		private QueryRunner query=new QueryRunner();

	/**
	 * 添加订单商品
	 */
	public boolean addOrders_goods(Orders_goods u){
		Connection conn=null;
		boolean flag=false;
		try {
			conn=JDBCUtils.getConnection();
			int row=query.update(conn,"insert into orders_goods(goods_id,number,price,orders_id,goods_name) values(?,?,?,?,?)",u.getGoods_id(),u.getNumber(),u.getPrice(),u.getOrders_id(),u.getGoods_name());
			if(row>0){
				flag=true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		return flag;
	}
	/**
	 * 根据orders_id来查询
	 * 用来JSP输出订单商品
	 * @param orders_id
	 * @param 
	 * @return
	 */
	public List<Orders_goods> queryByOrders_id(int orders_id){
		Connection conn=null;
		List<Orders_goods> orders_goodslist=null;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			 orders_goodslist=query.query(conn,"select * from orders_goods where orders_id=?",new BeanListHandler<>(Orders_goods.class),orders_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return orders_goodslist;
	}
}
