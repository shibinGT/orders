package dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import entity.Cart;
import entity.Orders;



/**
 * 订单DAO
 * @author Administrator
 *
 */
public class OrdersDAO {
	//使用DBUtils的辅助类来操作数据库
		private QueryRunner query=new QueryRunner();
	/**
	 * 用户ID
	 * 金额
	 * 创建时间
	 * 添加订单
	 */
	public boolean addOrders(Orders u){
		Connection conn=null;
		//声明操作结果，默认失败
		boolean flag=false;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			int row=query.update(conn,"insert into orders(user_id,money,createtime,user_name) values(?,?,?,?)",u.getUser_id(),u.getMoney(),u.getCreatetime(),u.getUser_name());
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
	 * 订单id
	 * 删除订单
	 */
	public boolean deleteOrders(int orders_id){
		Connection conn=null;
		//声明操作结果，默认失败
		boolean flag=false;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			int row=query.update(conn,"delete from orders where orders_id=? ",orders_id);
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
	 * 
	 * 查询个人订单
	 * @param user_id
	 * @param 
	 * @return
	 */
	public List<Orders> queryByUser_id(int user_id){
		Connection conn=null;
		List<Orders> myorderslist=null;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			 myorderslist=query.query(conn,"select * from orders where user_id=? ",new BeanListHandler<>(Orders.class),user_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return myorderslist;
	}
	/**
	 * 
	 * 查询个人订单的某个订单
	 * @param user_id
	 * @param 
	 * @return
	 */
	public Orders queryByUser_idAndCreatetime(int user_id,String createtime){
		Connection conn=null;
		Orders myorders=null;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			 myorders=query.query(conn,"select * from orders where user_id=? and createtime=?",new BeanHandler<>(Orders.class),user_id,createtime);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return myorders;
	}
	/**
	 * 
	 * 查询个人订单的某个订单
	 * @param user_id
	 * @param 
	 * @return
	 */
	public Orders queryByOrders_id(int orders_id){
		Connection conn=null;
		Orders myorders=null;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			 myorders=query.query(conn,"select * from orders where orders_id=?" ,new BeanHandler<>(Orders.class),orders_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return myorders;
	}
	/**
	 * 根据用户名查
	 * 查询已付款全部订单
	 * 
	 * @param user_name
	 * @param 
	 * @return
	 */
	public List<Orders> queryPayByUser_name(String user_name,int pageNo,int pageSize){
		Connection conn=null;
		List<Orders> orderslist=null;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			 orderslist=query.query(conn,"select * from orders where user_name like? and status=2 limit ?,?",new BeanListHandler<>(Orders.class),'%'+user_name+'%',(pageNo-1)*pageSize,pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return orderslist;
	}
	/**
	 * 根据用户名查
	 * 查询未付款全部订单
	 * 
	 * @param user_name
	 * @param 
	 * @return
	 */
	public List<Orders> queryNoPayByUser_name(String user_name,int pageNo,int pageSize){
		Connection conn=null;
		List<Orders> orderslist=null;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			 orderslist=query.query(conn,"select * from orders where user_name like? and status=1 limit ?,?",new BeanListHandler<>(Orders.class),'%'+user_name+'%',(pageNo-1)*pageSize,pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return orderslist;
	}
	/**
	 * 根据用户名查
	 * 查询全部订单
	 * 
	 * @param user_name
	 * @param 
	 * @return
	 */
	public List<Orders> queryByUser_name(String user_name,int pageNo,int pageSize){
		Connection conn=null;
		List<Orders> orderslist=null;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			 orderslist=query.query(conn,"select * from orders where user_name like? limit ?,?",new BeanListHandler<>(Orders.class),'%'+user_name+'%',(pageNo-1)*pageSize,pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return orderslist;
	}
	/**
	 * 根据订单id
	 * 修改订单状态 
	 */
	public boolean updateGoods(String paymenttime,int orders_id){
		Connection conn=null;
		//声明操作结果，默认失败
		boolean flag=false;
		try {
			//获取连接
			 conn=JDBCUtils.getConnection();
			 //执行SQL语句
			int row=query.update(conn,"update orders set status=2,paymenttime=?  where orders_id=?",paymenttime,orders_id);
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
	 * 分页用，查列
	 */
	public int queryCount(String name) {
		Connection conn = null;
		long count = 0;
		try {
			//
			conn = JDBCUtils.getConnection();
			//
			count=(Long)query.query(conn, "select count(1) from orders where user_name like ?", new ScalarHandler(),'%'+name+'%');
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//
			JDBCUtils.closeConnection(conn);
		}
		return new Long(count).intValue();
	}
	/**
	 * 未支付
	 * @param name
	 * @return
	 */
	public int queryCountNo(String name) {
		Connection conn = null;
		long count = 0;
		try {
			//
			conn = JDBCUtils.getConnection();
			//
			count=(Long)query.query(conn, "select count(1) from orders where user_name like ? and status=1", new ScalarHandler(),'%'+name+'%');
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//
			JDBCUtils.closeConnection(conn);
		}
		return new Long(count).intValue();
	}
	/**
	 * 已支付
	 * @param name
	 * @return
	 */
	public int queryCountYes(String name) {
		Connection conn = null;
		long count = 0;
		try {
			//
			conn = JDBCUtils.getConnection();
			//
			count=(Long)query.query(conn, "select count(1) from orders where user_name like ? and status=2", new ScalarHandler(),'%'+name+'%');
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//
			JDBCUtils.closeConnection(conn);
		}
		return new Long(count).intValue();
	}
}
