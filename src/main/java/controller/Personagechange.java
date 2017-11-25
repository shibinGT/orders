package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import entity.User;
import services.UserService;

/**
 * Servlet implementation class Personage
 */
@WebServlet("/personagechange")
public class Personagechange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Personagechange() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService uservice=new UserService();
		//拿到用户id
		User u=(User)request.getSession().getAttribute("loginuser");
		int user_id=u.getUser_id();
		String phone=u.getPhone();
		//拿到输入密码
		String password=request.getParameter("password");
		String password2=request.getParameter("password");
		//输入的验证电话
		String newphone=request.getParameter("phone");
		boolean conFlag=true;//两个密码判断条件状态
			//检查密码是否为空
				if(StringUtils.isBlank(password)||StringUtils.isBlank(password2)){
					conFlag=false;			
					request.setAttribute("msg", "密码不能为空");
					request.getRequestDispatcher("personaldata.jsp").forward(request, response);
					return;
				}
				//密码不相等
				if(conFlag){
					if(!password.equals(password2)){
						conFlag=false;	
						request.setAttribute("msg", "两次密码不一样");
						request.getRequestDispatcher("personaldata.jsp").forward(request, response);
						return;
					}
				}
				
				if(!phone.equals(newphone)){
					conFlag=false;
					request.setAttribute("msg", "验证不成功");
					request.getRequestDispatcher("personaldata.jsp").forward(request, response);
					return;
				}
				//都满足才修改
				if(conFlag){
					boolean flag=uservice.updatePassword(password, user_id);
					if(flag){
						request.setAttribute("msg", "修改成功");
						request.getRequestDispatcher("personaldata.jsp").forward(request, response);
					}else{
						request.setAttribute("msg", "修改不成功");
						request.getRequestDispatcher("personaldata.jsp").forward(request, response);
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
