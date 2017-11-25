package dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import org.apache.commons.dbutils.handlers.BeanListHandler;



import entity.Orders_goods;



/**
 * ������ƷDAO
 * @author Administrator
 *
 */
public class OrderGoodsDAO {
	//ʹ��DBUtils�ĸ��������������ݿ�
		private QueryRunner query=new QueryRunner();

	/**
	 * ��Ӷ�����Ʒ
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
	 * ����orders_id����ѯ
	 * ����JSP���������Ʒ
	 * @param orders_id
	 * @param 
	 * @return
	 */
	public List<Orders_goods> queryByOrders_id(int orders_id){
		Connection conn=null;
		List<Orders_goods> orders_goodslist=null;
		try {
			//��ȡ����
			 conn=JDBCUtils.getConnection();
			 //ִ��SQL���
			 orders_goodslist=query.query(conn,"select * from orders_goods where orders_id=?",new BeanListHandler<>(Orders_goods.class),orders_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(conn);
		}
		
		return orders_goodslist;
	}
}
