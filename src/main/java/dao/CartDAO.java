package dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import entity.Cart;



/**
 * 购物车DAO
 * @author Administrator
 *
 */
public class CartDAO {
	//使用DBUtils的辅助类来操作数据库
		private QueryRunner query=new QueryRunner();
	/**
	 * 添加一条购物车记录
	 */
	public boolean addCart(Cart u){
		Connection conn=null;
		//声明操作结果，默认失败
		boolean flag=false;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			int row=query.update(conn,"insert into cart(goods_id,number,user_id,goods_name) values(?,?,?,?)",u.getGoods_id(),u.getNumber(),u.getUser_id(),u.getGoods_name());
			//如果SQL返回结果大于1，说明操作成功
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
	 * 修改购物车（添加商品数量）
	 */
	public boolean updateGoods(int number,int goods_id,int user_id){
		Connection conn=null;
		//声明操作结果，默认失败
		boolean flag=false;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			int row=query.update(conn,"update cart set number=?  where goods_id=? and user_id=?",number,goods_id,user_id);
			//如果SQL返回结果大于1，说明操作成功
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
	 * 商品id用户id
	 * 删除购物车商品
	 */
	public boolean deleteGoods(int goods_id,int user_id){
		Connection conn=null;
		//声明操作结果，默认失败
		boolean flag=false;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			int row=query.update(conn,"delete from cart where goods_id=? and user_id=?",goods_id,user_id);
			//如果SQL返回结果大于1，说明操作成功
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
	 * 清空购物车
	 * 
	 */
	public boolean deleteGoodsAll(int user_id){
		Connection conn=null;
		//声明操作结果，默认失败
		boolean flag=false;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			int row=query.update(conn,"delete from cart where  user_id=?",user_id);
			//如果SQL返回结果大于1，说明操作成功
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
	 * 根据goods_id和user_id来查询
	 * 用来查购物车里有无记录
	 * @param goods_id
	 * @param 
	 * @return
	 */
	public Cart queryByGoods_idAndUser_id(int goods_id,int user_id){
		Connection conn=null;
		Cart cart=null;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			 cart=query.query(conn,"select * from cart where goods_id=? and user_id=?",new BeanHandler<Cart>(Cart.class),goods_id,user_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return cart;
	}
	/**
	 * 根据user_id来查询
	 * 用来JSP输出购物车
	 * @param goods_id
	 * @param 
	 * @return
	 */
	public List<Cart> queryByUser_id(int user_id){
		Connection conn=null;
		List<Cart> cartlist=null;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			 cartlist=query.query(conn,"select * from cart where user_id=?",new BeanListHandler<Cart>(Cart.class),user_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return cartlist;
	}
}
