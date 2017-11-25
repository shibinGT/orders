package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import services.UserService;

import entity.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		UserService service = new UserService();
		// 从request请求中获取登陆名和密码
		String username = request.getParameter("user_name");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		int age;
		if(StringUtils.isBlank(request.getParameter("age"))){
			 age=0;
		}else{
		 age=Integer.parseInt(request.getParameter("age"));
		}
		String phone=request.getParameter("phone");
		
		boolean status = false;// 注册状态
		boolean conFlag=true;//条件状态
		//检查用户名和密码
		if(age==0||StringUtils.isBlank(phone)||StringUtils.isBlank(username)||StringUtils.isBlank(password)||StringUtils.isBlank(password2)){
			conFlag=false;			
			request.setAttribute("msg", "不能留空");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		//密码不相等
		if(conFlag){
			if(!password.equals(password2)){
				conFlag=false;	
				
				request.setAttribute("msg", "两次密码不一样");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
		}
		
		
		//如果条件满足，才添加用户
		if(conFlag){
			
			//封装User对象
			User user=new User();
			user.setUser_name(username);
			user.setPassword(password);
			user.setAge(age);
			user.setPhone(phone);
			//是否添加成功
			boolean falg=service.register(user);
			
			//如果添加成功
			if (falg) {
				// 
				status=true;
				request.setAttribute("msg", "注册成功");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", "用户名已存在");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
		}
		
			
		}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
