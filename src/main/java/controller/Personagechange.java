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
		//�õ��û�id
		User u=(User)request.getSession().getAttribute("loginuser");
		int user_id=u.getUser_id();
		String phone=u.getPhone();
		//�õ���������
		String password=request.getParameter("password");
		String password2=request.getParameter("password");
		//�������֤�绰
		String newphone=request.getParameter("phone");
		boolean conFlag=true;//���������ж�����״̬
			//��������Ƿ�Ϊ��
				if(StringUtils.isBlank(password)||StringUtils.isBlank(password2)){
					conFlag=false;			
					request.setAttribute("msg", "���벻��Ϊ��");
					request.getRequestDispatcher("personaldata.jsp").forward(request, response);
					return;
				}
				//���벻���
				if(conFlag){
					if(!password.equals(password2)){
						conFlag=false;	
						request.setAttribute("msg", "�������벻һ��");
						request.getRequestDispatcher("personaldata.jsp").forward(request, response);
						return;
					}
				}
				
				if(!phone.equals(newphone)){
					conFlag=false;
					request.setAttribute("msg", "��֤���ɹ�");
					request.getRequestDispatcher("personaldata.jsp").forward(request, response);
					return;
				}
				//��������޸�
				if(conFlag){
					boolean flag=uservice.updatePassword(password, user_id);
					if(flag){
						request.setAttribute("msg", "�޸ĳɹ�");
						request.getRequestDispatcher("personaldata.jsp").forward(request, response);
					}else{
						request.setAttribute("msg", "�޸Ĳ��ɹ�");
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
