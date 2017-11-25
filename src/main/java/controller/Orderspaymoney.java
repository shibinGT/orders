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
import services.OrdersService;
import services.Orders_goodsService;

/**
 * Servlet implementation class Orderspaymoney
 */
@WebServlet("/orderspaymoney")
public class Orderspaymoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Orderspaymoney() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrdersService oservice = new OrdersService();
		Orders_goodsService ogservice = new Orders_goodsService();
		//得到订单id
		int orders_id=Integer.parseInt(request.getParameter("orders_id"));
		
		/**
		 * 以下和增加订单servlet跳转相同
		 */
		//商品列表
		List<Orders_goods> orders_goodslist=ogservice.queryByOrders_id(orders_id);
		request.setAttribute("orders_goodslist", orders_goodslist);
		//传本订单
		Orders o=oservice.queryByOrders_id(orders_id);
		request.setAttribute("orders", o);
		request.getRequestDispatcher("payment.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
