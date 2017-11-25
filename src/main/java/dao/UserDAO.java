package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import entity.User;

/**
 * 用户信息DAO
 * 
 * @author Administrator
 *
 */
public class UserDAO {
	// 使用DBUtils的辅助类来操作数据库
	private QueryRunner query = new QueryRunner();

	/**
	 * 添加用户
	 * 
	 * @param u
	 *            用户
	 */
	public boolean addUser(User u) {
		Connection conn = null;
		// 声明操作结果，默认失败
		boolean flag = false;
		try {
			// 获取连接
			conn = JDBCUtils.getConnection();
			// 执行SQL语句
			int row = query.update(conn, "insert into user(user_name,password,age,phone) values(?,?,?,?)",
					u.getUser_name(), u.getPassword(), u.getAge(), u.getPhone());
			// 如果SQL返回结果大于1，说明操作成功
			if (row > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(conn);
		}
		return flag;
	}

	/**
	 * 根据用户名和密码来查询
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public User queryBynameAndPwd(String user_name, String password) {
		Connection conn = null;
		User user = null;
		try {
			// 获取连接
			conn = JDBCUtils.getConnection();
			// 执行SQL语句
			user = query.query(conn, "select * from user where user_name=? and password=?",
					new BeanHandler<>(User.class), user_name, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(conn);
		}

		return user;
	}

	/**
	 * 查个人
	 * 
	 * @param user_id
	 * @return
	 */
	public User queryByUser_id(int user_id) {
		Connection conn = null;
		User user = null;
		try {
			// 获取连接
			conn = JDBCUtils.getConnection();
			// 执行SQL语句
			user = query.query(conn, "select * from user where user_id=?", new BeanHandler<>(User.class), user_id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(conn);
		}

		return user;
	}

	/**
	 * 全部查询
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public List<User> queryAll() {
		Connection conn = null;
		List<User> userlist = null;
		try {
			// 获取连接
			conn = JDBCUtils.getConnection();
			// 执行SQL语句
			userlist = query.query(conn, "select * from user ", new BeanListHandler<>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(conn);
		}

		return userlist;

	}

	/**
	 * 根据用户查询
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public User queryByName(String user_name) {
		Connection conn = null;
		User user = null;
		try {
			// 获取连接
			conn = JDBCUtils.getConnection();
			// 执行SQL语句
			user = query.query(conn, "select * from user where user_name=?", new BeanHandler<>(User.class), user_name);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(conn);
		}

		return user;

	}

	/**
	 * 改密码
	 */
	public boolean updatePassword(String password, int user_id) {
		Connection conn = null;
		// 声明操作结果，默认失败
		boolean flag = false;
		try {
			// 获取连接
			conn = JDBCUtils.getConnection();
			// 执行SQL语句
			int row = query.update(conn,
					"update user set password=?  where user_id=? ",password,user_id);
			// 如果SQL返回结果大于1，说明操作成功
			if (row > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(conn);
		}
		return flag;
	}
	
}
