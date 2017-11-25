package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Orders;
import entity.Orders_goods;
import entity.User;
import services.OrdersService;
import services.Orders_goodsService;

/**
 * Servlet implementation class Orderscheck
 */
@WebServlet("/orderscheck")
public class Orderscheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Orderscheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrdersService oservice=new OrdersService();
		Orders_goodsService ogservice=new Orders_goodsService();
		//用户id
		User u=(User)request.getSession().getAttribute("loginuser");
		int user_id=u.getUser_id();
		//传来的订单id
		//如果为空
		if(request.getParameter("orders_id")==null||request.getParameter("orders_id").isEmpty()){
			
			request.setAttribute("nolist", "- -！");
		}else{
			//如果不为空
			int orders_id=Integer.parseInt(request.getParameter("orders_id"));
			List<Orders_goods> orders_goodslist=ogservice.queryByOrders_id(orders_id);
			request.setAttribute("orders_goodslist", orders_goodslist);
		}
		//查询订单列表
		List<Orders> myorderslist=oservice.queryByUser_id(user_id);
		//传列表
		request.setAttribute("myorderslist", myorderslist);
		request.getRequestDispatcher("myorders.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
