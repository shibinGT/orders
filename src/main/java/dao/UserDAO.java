package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import entity.User;

/**
 * �û���ϢDAO
 * 
 * @author Administrator
 *
 */
public class UserDAO {
	// ʹ��DBUtils�ĸ��������������ݿ�
	private QueryRunner query = new QueryRunner();

	/**
	 * ����û�
	 * 
	 * @param u
	 *            �û�
	 */
	public boolean addUser(User u) {
		Connection conn = null;
		// �������������Ĭ��ʧ��
		boolean flag = false;
		try {
			// ��ȡ����
			conn = JDBCUtils.getConnection();
			// ִ��SQL���
			int row = query.update(conn, "insert into user(user_name,password,age,phone) values(?,?,?,?)",
					u.getUser_name(), u.getPassword(), u.getAge(), u.getPhone());
			// ���SQL���ؽ������1��˵�������ɹ�
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
	 * �����û�������������ѯ
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public User queryBynameAndPwd(String user_name, String password) {
		Connection conn = null;
		User user = null;
		try {
			// ��ȡ����
			conn = JDBCUtils.getConnection();
			// ִ��SQL���
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
	 * �����
	 * 
	 * @param user_id
	 * @return
	 */
	public User queryByUser_id(int user_id) {
		Connection conn = null;
		User user = null;
		try {
			// ��ȡ����
			conn = JDBCUtils.getConnection();
			// ִ��SQL���
			user = query.query(conn, "select * from user where user_id=?", new BeanHandler<>(User.class), user_id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(conn);
		}

		return user;
	}

	/**
	 * ȫ����ѯ
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public List<User> queryAll() {
		Connection conn = null;
		List<User> userlist = null;
		try {
			// ��ȡ����
			conn = JDBCUtils.getConnection();
			// ִ��SQL���
			userlist = query.query(conn, "select * from user ", new BeanListHandler<>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(conn);
		}

		return userlist;

	}

	/**
	 * �����û���ѯ
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public User queryByName(String user_name) {
		Connection conn = null;
		User user = null;
		try {
			// ��ȡ����
			conn = JDBCUtils.getConnection();
			// ִ��SQL���
			user = query.query(conn, "select * from user where user_name=?", new BeanHandler<>(User.class), user_name);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeConnection(conn);
		}

		return user;

	}

	/**
	 * ������
	 */
	public boolean updatePassword(String password, int user_id) {
		Connection conn = null;
		// �������������Ĭ��ʧ��
		boolean flag = false;
		try {
			// ��ȡ����
			conn = JDBCUtils.getConnection();
			// ִ��SQL���
			int row = query.update(conn,
					"update user set password=?  where user_id=? ",password,user_id);
			// ���SQL���ؽ������1��˵�������ɹ�
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
