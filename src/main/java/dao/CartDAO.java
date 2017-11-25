package dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import entity.Cart;



/**
 * ���ﳵDAO
 * @author Administrator
 *
 */
public class CartDAO {
	//ʹ��DBUtils�ĸ��������������ݿ�
		private QueryRunner query=new QueryRunner();
	/**
	 * ���һ�����ﳵ��¼
	 */
	public boolean addCart(Cart u){
		Connection conn=null;
		//�������������Ĭ��ʧ��
		boolean flag=false;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
			int row=query.update(conn,"insert into cart(goods_id,number,user_id,goods_name) values(?,?,?,?)",u.getGoods_id(),u.getNumber(),u.getUser_id(),u.getGoods_name());
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
	 * �޸Ĺ��ﳵ�������Ʒ������
	 */
	public boolean updateGoods(int number,int goods_id,int user_id){
		Connection conn=null;
		//�������������Ĭ��ʧ��
		boolean flag=false;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
			int row=query.update(conn,"update cart set number=?  where goods_id=? and user_id=?",number,goods_id,user_id);
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
	 * ��Ʒid�û�id
	 * ɾ�����ﳵ��Ʒ
	 */
	public boolean deleteGoods(int goods_id,int user_id){
		Connection conn=null;
		//�������������Ĭ��ʧ��
		boolean flag=false;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
			int row=query.update(conn,"delete from cart where goods_id=? and user_id=?",goods_id,user_id);
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
	 * ��չ��ﳵ
	 * 
	 */
	public boolean deleteGoodsAll(int user_id){
		Connection conn=null;
		//�������������Ĭ��ʧ��
		boolean flag=false;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
			int row=query.update(conn,"delete from cart where  user_id=?",user_id);
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
	 * ����goods_id��user_id����ѯ
	 * �����鹺�ﳵ�����޼�¼
	 * @param goods_id
	 * @param 
	 * @return
	 */
	public Cart queryByGoods_idAndUser_id(int goods_id,int user_id){
		Connection conn=null;
		Cart cart=null;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
			 cart=query.query(conn,"select * from cart where goods_id=? and user_id=?",new BeanHandler<Cart>(Cart.class),goods_id,user_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return cart;
	}
	/**
	 * ����user_id����ѯ
	 * ����JSP������ﳵ
	 * @param goods_id
	 * @param 
	 * @return
	 */
	public List<Cart> queryByUser_id(int user_id){
		Connection conn=null;
		List<Cart> cartlist=null;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
			 cartlist=query.query(conn,"select * from cart where user_id=?",new BeanListHandler<Cart>(Cart.class),user_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return cartlist;
	}
}
