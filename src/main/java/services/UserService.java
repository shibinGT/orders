package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;

import dao.JDBCUtils;
import dao.UserDAO;

import entity.User;

/**
 * 用户服务类
 * @author Administrator
 *
 */
public class UserService {
	private UserDAO dao=new UserDAO();
	
	public User queryByName(String user_name) {
		return dao.queryByName(user_name);
	}
	/**
	 * 用户注册
	 * @param user
	 */
	public boolean register(User user){
		//1.先检查用户是否存在,根据用户名来检查
		if(dao.queryByName(user.getUser_name())==null){
			//如果用户没有存在，则添加
			
			return  dao.addUser(user);
		}
		return false;
	}
	/**
	 * 登
	 * @param name 用户名
	 * @param password 密码
	 * @return 用户信息
	 */
	public User login(String name,String password){
		return dao.queryBynameAndPwd(name,password);
	}
	/**
	 * 全部查询
	 * @param name
	 * @param password
	 * @return
	 */
	public List<User> queryAll(){
		return dao.queryAll();
	}
	/**
	 * 查个人
	 * user_id
	 */
	public User queryByUser_id(int user_id){
		return dao.queryByUser_id(user_id);
	}
	/**
	 * 改密码
	 */
	public boolean updatePassword(String password,int user_id){
		return dao.updatePassword(password, user_id);
	}
}
