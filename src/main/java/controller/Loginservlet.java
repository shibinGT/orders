package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import services.UserService;

import entity.User;

/**
 * Servlet implementation class Loginservlet
 */
@WebServlet("/logins")
public class Loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Loginservlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService service = new UserService();
		// 从request请求中获取登陆名和密码
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		/**
		 * 记住用户名
		 */
		String rememberme =request.getParameter("rememberme");
		//如果按钮选中，则新建一个Cookie
		if(StringUtils.isNotBlank(rememberme)){
			Cookie cookie=new Cookie("user_name",user_name);
			cookie.setMaxAge(60*60*24*30);
			response.addCookie(cookie);
		}else{
			//如果没选中，刚删除
			Cookie[] cookie=request.getCookies();
			for(Cookie coo:cookie){
				if(coo.getName().equals("user_name")){
					coo.setMaxAge(0);
					response.addCookie(coo);
				}
			}
		}
		/**
		 * 
		 */
		boolean status = false;// 登陆状态
		// 1.！=nul 2.去空格 ，然后比较是否为空
		if (StringUtils.isNotBlank(user_name) && StringUtils.isNotBlank(password)) {
			// 根据用户名和密码来查询
			User user = service.login(user_name, password);
			// 如果用户存在，说明登陆成功
			if (user!= null) {
				// 把用户放到session中
				status = true;
				request.getSession().setAttribute("loginuser", user);
				response.sendRedirect("main.jsp");
			} else {
				request.setAttribute("msg", "用户名密码错误");
			}
		} else {
			request.setAttribute("msg", "用户名和密码不能为空");
		}
		// ?
		if (!status) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
