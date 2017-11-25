package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;

import dao.JDBCUtils;
import dao.UserDAO;

import entity.User;

/**
 * �û�������
 * @author Administrator
 *
 */
public class UserService {
	private UserDAO dao=new UserDAO();
	
	public User queryByName(String user_name) {
		return dao.queryByName(user_name);
	}
	/**
	 * �û�ע��
	 * @param user
	 */
	public boolean register(User user){
		//1.�ȼ���û��Ƿ����,�����û��������
		if(dao.queryByName(user.getUser_name())==null){
			//����û�û�д��ڣ������
			
			return  dao.addUser(user);
		}
		return false;
	}
	/**
	 * ��
	 * @param name �û���
	 * @param password ����
	 * @return �û���Ϣ
	 */
	public User login(String name,String password){
		return dao.queryBynameAndPwd(name,password);
	}
	/**
	 * ȫ����ѯ
	 * @param name
	 * @param password
	 * @return
	 */
	public List<User> queryAll(){
		return dao.queryAll();
	}
	/**
	 * �����
	 * user_id
	 */
	public User queryByUser_id(int user_id){
		return dao.queryByUser_id(user_id);
	}
	/**
	 * ������
	 */
	public boolean updatePassword(String password,int user_id){
		return dao.updatePassword(password, user_id);
	}
}
