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
 * ��Ʒ��ϢDAO
 * @author Administrator
 *
 */
public class GoodsDAO {
	//ʹ��DBUtils�ĸ��������������ݿ�
	private QueryRunner query=new QueryRunner();
	/**
	 * �����Ʒ
	 * @param u ��Ʒ
	 */
	public boolean addGoods(Goods u){
		Connection conn=null;
		//�������������Ĭ��ʧ��
		boolean flag=false;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
			int row=query.update(conn,"insert into goods(goods_name,price,stock,picture,describes) values(?,?,?,?,?)",u.getGoods_name(),u.getPrice(),u.getStock(),u.getPicture(),u.getDescribes());
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
	 * ɾ����Ʒ
	 * @param u ��Ʒ
	 */
	public boolean deleteGoods(int goods_id){
		Connection conn=null;
		//�������������Ĭ��ʧ��
		boolean flag=false;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
			int row=query.update(conn,"delete from goods where goods_id=?",goods_id);
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
	 * �޸���Ʒ
	 * @param u ��Ʒ
	 */
	public boolean updateGoods(String goods_name,float price,int stock,String picture,String describes, int goods_id){
		Connection conn=null;
		//�������������Ĭ��ʧ��
		boolean flag=false;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
			int row=query.update(conn,"update goods set goods_name=? ,price=?, stock=?,picture=?,describes=? where goods_id=? ",goods_name,price,stock,picture,describes,goods_id);
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
	 * �޸���Ʒ���
	 * @param u ��Ʒ
	 */
	public boolean updateGoods(int stock, int goods_id){
		Connection conn=null;
		//�������������Ĭ��ʧ��
		boolean flag=false;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
			int row=query.update(conn,"update goods set  stock=? where goods_id=? ",stock,goods_id);
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
	 * ����goods_id����ѯ
	 * @param goods_id
	 * @param 
	 * @return
	 */
	public Goods queryByGoods_id(int goods_id){
		Connection conn=null;
		Goods goods=null;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
			 goods=query.query(conn,"select * from goods where goods_id=? ",new BeanHandler<>(Goods.class),goods_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return goods;
	}
	
	/**
	 * ��������ģ����ѯ�б�
	 * @param goods_id
	 * @param 
	 * @return
	 */
	public List<Goods> queryList(String goods_name,int pageNo,int pageSize){
		Connection conn=null;
		List<Goods> goodslist=null;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
			 goodslist=query.query(conn,"select * from goods where goods_name like ? limit ?,?",new BeanListHandler<>(Goods.class),'%'+goods_name+'%',(pageNo-1)*pageSize,pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return goodslist;
	}
	/**
	 * ��ҳ��
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
