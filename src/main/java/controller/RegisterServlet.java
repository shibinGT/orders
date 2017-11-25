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
		// ��request�����л�ȡ��½��������
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
		
		boolean status = false;// ע��״̬
		boolean conFlag=true;//����״̬
		//����û���������
		if(age==0||StringUtils.isBlank(phone)||StringUtils.isBlank(username)||StringUtils.isBlank(password)||StringUtils.isBlank(password2)){
			conFlag=false;			
			request.setAttribute("msg", "��������");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		//���벻���
		if(conFlag){
			if(!password.equals(password2)){
				conFlag=false;	
				
				request.setAttribute("msg", "�������벻һ��");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
		}
		
		
		//����������㣬������û�
		if(conFlag){
			
			//��װUser����
			User user=new User();
			user.setUser_name(username);
			user.setPassword(password);
			user.setAge(age);
			user.setPhone(phone);
			//�Ƿ���ӳɹ�
			boolean falg=service.register(user);
			
			//�����ӳɹ�
			if (falg) {
				// 
				status=true;
				request.setAttribute("msg", "ע��ɹ�");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", "�û����Ѵ���");
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
