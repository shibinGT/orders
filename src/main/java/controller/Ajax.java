package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import services.UserService;

/**
 * Servlet implementation class Ajax
 */
@WebServlet("/ajax")
public class Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ajax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String method=request.getParameter("method");
		//if(method.equals("checkuser")){
			check(request,response);
		//}
	}
	protected void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String a=request.getParameter("username");
		UserService service=new UserService();
		User u=service.queryByName(a);
		PrintWriter out=response.getWriter();
		if(u==null){
			out.println("用户名可以使用");
			
		}else{
			out.println("用户名已存在");
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
