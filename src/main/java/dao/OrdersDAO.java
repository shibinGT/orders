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
 * ����DAO
 * @author Administrator
 *
 */
public class OrdersDAO {
	//ʹ��DBUtils�ĸ��������������ݿ�
		private QueryRunner query=new QueryRunner();
	/**
	 * �û�ID
	 * ���
	 * ����ʱ��
	 * ��Ӷ���
	 */
	public boolean addOrders(Orders u){
		Connection conn=null;
		//�������������Ĭ��ʧ��
		boolean flag=false;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
			int row=query.update(conn,"insert into orders(user_id,money,createtime,user_name) values(?,?,?,?)",u.getUser_id(),u.getMoney(),u.getCreatetime(),u.getUser_name());
			//���SQL���ؽ������1��˵�������ɹ�
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
	 * ����id
	 * ɾ������
	 */
	public boolean deleteOrders(int orders_id){
		Connection conn=null;
		//�������������Ĭ��ʧ��
		boolean flag=false;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
			int row=query.update(conn,"delete from orders where orders_id=? ",orders_id);
			//���SQL���ؽ������1��˵�������ɹ�
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
	 * ��ѯ���˶���
	 * @param user_id
	 * @param 
	 * @return
	 */
	public List<Orders> queryByUser_id(int user_id){
		Connection conn=null;
		List<Orders> myorderslist=null;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
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
	 * ��ѯ���˶�����ĳ������
	 * @param user_id
	 * @param 
	 * @return
	 */
	public Orders queryByUser_idAndCreatetime(int user_id,String createtime){
		Connection conn=null;
		Orders myorders=null;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
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
	 * ��ѯ���˶�����ĳ������
	 * @param user_id
	 * @param 
	 * @return
	 */
	public Orders queryByOrders_id(int orders_id){
		Connection conn=null;
		Orders myorders=null;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
			 myorders=query.query(conn,"select * from orders where orders_id=?" ,new BeanHandler<>(Orders.class),orders_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return myorders;
	}
	/**
	 * �����û�����
	 * ��ѯ�Ѹ���ȫ������
	 * 
	 * @param user_name
	 * @param 
	 * @return
	 */
	public List<Orders> queryPayByUser_name(String user_name,int pageNo,int pageSize){
		Connection conn=null;
		List<Orders> orderslist=null;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
			 orderslist=query.query(conn,"select * from orders where user_name like? and status=2 limit ?,?",new BeanListHandler<>(Orders.class),'%'+user_name+'%',(pageNo-1)*pageSize,pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return orderslist;
	}
	/**
	 * �����û�����
	 * ��ѯδ����ȫ������
	 * 
	 * @param user_name
	 * @param 
	 * @return
	 */
	public List<Orders> queryNoPayByUser_name(String user_name,int pageNo,int pageSize){
		Connection conn=null;
		List<Orders> orderslist=null;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
			 orderslist=query.query(conn,"select * from orders where user_name like? and status=1 limit ?,?",new BeanListHandler<>(Orders.class),'%'+user_name+'%',(pageNo-1)*pageSize,pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return orderslist;
	}
	/**
	 * �����û�����
	 * ��ѯȫ������
	 * 
	 * @param user_name
	 * @param 
	 * @return
	 */
	public List<Orders> queryByUser_name(String user_name,int pageNo,int pageSize){
		Connection conn=null;
		List<Orders> orderslist=null;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
			 orderslist=query.query(conn,"select * from orders where user_name like? limit ?,?",new BeanListHandler<>(Orders.class),'%'+user_name+'%',(pageNo-1)*pageSize,pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return orderslist;
	}
	/**
	 * ���ݶ���id
	 * �޸Ķ���״̬ 
	 */
	public boolean updateGoods(String paymenttime,int orders_id){
		Connection conn=null;
		//�������������Ĭ��ʧ��
		boolean flag=false;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
			int row=query.update(conn,"update orders set status=2,paymenttime=?  where orders_id=?",paymenttime,orders_id);
			//���SQL���ؽ������1��˵�������ɹ�
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
	 * ��ҳ�ã�����
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
	 * δ֧��
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
	 * ��֧��
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
