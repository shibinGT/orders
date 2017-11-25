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
		// ��request�����л�ȡ��½��������
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		/**
		 * ��ס�û���
		 */
		String rememberme =request.getParameter("rememberme");
		//�����ťѡ�У����½�һ��Cookie
		if(StringUtils.isNotBlank(rememberme)){
			Cookie cookie=new Cookie("user_name",user_name);
			cookie.setMaxAge(60*60*24*30);
			response.addCookie(cookie);
		}else{
			//���ûѡ�У���ɾ��
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
		boolean status = false;// ��½״̬
		// 1.��=nul 2.ȥ�ո� ��Ȼ��Ƚ��Ƿ�Ϊ��
		if (StringUtils.isNotBlank(user_name) && StringUtils.isNotBlank(password)) {
			// �����û�������������ѯ
			User user = service.login(user_name, password);
			// ����û����ڣ�˵����½�ɹ�
			if (user!= null) {
				// ���û��ŵ�session��
				status = true;
				request.getSession().setAttribute("loginuser", user);
				response.sendRedirect("main.jsp");
			} else {
				request.setAttribute("msg", "�û����������");
			}
		} else {
			request.setAttribute("msg", "�û��������벻��Ϊ��");
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
