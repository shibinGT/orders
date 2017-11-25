package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import entity.Goods;

/**
 * 商品信息DAO
 * @author Administrator
 *
 */
public class GoodsDAO {
	//使用DBUtils的辅助类来操作数据库
	private QueryRunner query=new QueryRunner();
	/**
	 * 添加商品
	 * @param u 商品
	 */
	public boolean addGoods(Goods u){
		Connection conn=null;
		//声明操作结果，默认失败
		boolean flag=false;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			int row=query.update(conn,"insert into goods(goods_name,price,stock,picture,describes) values(?,?,?,?,?)",u.getGoods_name(),u.getPrice(),u.getStock(),u.getPicture(),u.getDescribes());
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
	 * 删除商品
	 * @param u 商品
	 */
	public boolean deleteGoods(int goods_id){
		Connection conn=null;
		//声明操作结果，默认失败
		boolean flag=false;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			int row=query.update(conn,"delete from goods where goods_id=?",goods_id);
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
	 * 修改商品
	 * @param u 商品
	 */
	public boolean updateGoods(String goods_name,float price,int stock,String picture,String describes, int goods_id){
		Connection conn=null;
		//声明操作结果，默认失败
		boolean flag=false;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			int row=query.update(conn,"update goods set goods_name=? ,price=?, stock=?,picture=?,describes=? where goods_id=? ",goods_name,price,stock,picture,describes,goods_id);
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
	 * 修改商品库存
	 * @param u 商品
	 */
	public boolean updateGoods(int stock, int goods_id){
		Connection conn=null;
		//声明操作结果，默认失败
		boolean flag=false;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			int row=query.update(conn,"update goods set  stock=? where goods_id=? ",stock,goods_id);
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
	 * 根据goods_id来查询
	 * @param goods_id
	 * @param 
	 * @return
	 */
	public Goods queryByGoods_id(int goods_id){
		Connection conn=null;
		Goods goods=null;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			 goods=query.query(conn,"select * from goods where goods_id=? ",new BeanHandler<>(Goods.class),goods_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return goods;
	}
	
	/**
	 * 根据名字模糊查询列表
	 * @param goods_id
	 * @param 
	 * @return
	 */
	public List<Goods> queryList(String goods_name,int pageNo,int pageSize){
		Connection conn=null;
		List<Goods> goodslist=null;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			 goodslist=query.query(conn,"select * from goods where goods_name like ? limit ?,?",new BeanListHandler<>(Goods.class),'%'+goods_name+'%',(pageNo-1)*pageSize,pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return goodslist;
	}
	/**
	 * 分页用
	 * @param 
	 * @return
	 */
	public int queryCount(String name) {
		Connection conn = null;
		long count = 0;
		try {
			//
			conn = JDBCUtils.getConnection();
			//
			count=(Long)query.query(conn, "select count(1) from goods where goods_name like ?", new ScalarHandler(),'%'+name+'%');
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//
			JDBCUtils.closeConnection(conn);
		}
		return new Long(count).intValue();
	}

}
