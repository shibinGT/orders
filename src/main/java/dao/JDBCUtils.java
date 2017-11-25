package dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	//数据源
	private static ComboPooledDataSource cpds=null;
	static{
		cpds=new ComboPooledDataSource("c3p0-config.xml");
	}
	/**
	 * 从数据源中获取数据库存连接
	 * @return Connection数据库存连接
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
	 * 释放数据库连接
	 * @param conn 数据库连接
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
