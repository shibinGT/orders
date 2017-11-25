package dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	//����Դ
	private static ComboPooledDataSource cpds=null;
	static{
		cpds=new ComboPooledDataSource("c3p0-config.xml");
	}
	/**
	 * ������Դ�л�ȡ���ݿ������
	 * @return Connection���ݿ������
	 */
	public static Connection getConnection(){
		try {
			return cpds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * �ͷ����ݿ�����
	 * @param conn ���ݿ�����
	 */
	public static void closeConnection(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
