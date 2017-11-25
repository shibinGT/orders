package entity;
/**
 * 用户实体类
 */
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * 
 * @author Administrator 用户实体类
 */
public class User implements HttpSessionBindingListener{
	private static Integer usernumber=0;
	
	public static Integer getUsernumber() {
		return usernumber;
	}

	// ID
	private int user_id;
	// 名字
	private String user_name;
	// 密码
	private String password;
	//权限
	private int type;
	//年龄
	private int age;
	//电话
	private String phone;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", password=" + password + ", type=" + type
				+ ", age=" + age + ", phone=" + phone + "]";
	}

	public User(int user_id, String user_name, String password, int type, int age, String phone) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
		this.type = type;
		this.age = age;
		this.phone = phone;
	}

	public User() {
		super();
	}

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		usernumber++;
		
		
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		usernumber--;
		
		
	}

}
