package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import entity.Orders;
import entity.Orders_goods;
import entity.User;
import services.OrdersService;
import services.Orders_goodsService;
import tag.Pager;
import utils.PageUtils;

/**
 * Servlet implementation class Allorderscheck
 */
@WebServlet("/allorderscheck")
public class Allorderscheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Allorderscheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		OrdersService oservice = new OrdersService();
		Orders_goodsService ogservice=new Orders_goodsService();
		String user_name = request.getParameter("user_name");
		String status = request.getParameter("status");
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
		// 初始查询，如果参数为空
		if (StringUtils.isBlank(user_name)) {
			user_name = "";
		}
		
		/**
		 * 以下判断下拉选择
		 */
		// 如果select选择为空，则查询全部
		if (StringUtils.isBlank(status)) {
			
			status = "all";		
			
			Pager page = new Pager();
			page.setPageSize(10);
			PageUtils.parseParam(page, request);
			int count=oservice.queryCount(user_name);
			page.setRecordCount(count);
			
			List<Orders> list = oservice.queryByUser_name(user_name,page.getPageNo(),page.getPageSize());
			request.setAttribute("page", page);
			
			request.setAttribute("value", user_name);
			request.setAttribute("value2", status);
			request.setAttribute("orderslist", list);
		
			request.getRequestDispatcher("allorders.jsp").forward(request, response);
			return;
		}
		if ("no".equals(status)) {
		
			Pager page = new Pager();
			page.setPageSize(10);
			PageUtils.parseParam(page, request);
			int count=oservice.queryCountNo(user_name);
			page.setRecordCount(count);
			List<Orders> list = oservice.queryNoPayByUser_name(user_name,page.getPageNo(),page.getPageSize());
			request.setAttribute("page", page);
			request.setAttribute("value", user_name);
			request.setAttribute("value2", status);
			request.setAttribute("orderslist", list);
		
			request.getRequestDispatcher("allorders.jsp").forward(request, response);
			return;
		} else if ("yes".equals(status)) {
			
			Pager page = new Pager();
			page.setPageSize(10);
			PageUtils.parseParam(page, request);
			int count=oservice.queryCountYes(user_name);
			page.setRecordCount(count);
			List<Orders> list = oservice.queryPayByUser_name(user_name,page.getPageNo(),page.getPageSize());
			request.setAttribute("page", page);
			request.setAttribute("value", user_name);
			request.setAttribute("value2", status);
			request.setAttribute("orderslist", list);
			
			request.getRequestDispatcher("allorders.jsp").forward(request, response);
			return;
		} else if ("all".equals(status)) {
			
			Pager page = new Pager();
			page.setPageSize(10);
			PageUtils.parseParam(page, request);
			int count=oservice.queryCount(user_name);
			page.setRecordCount(count);
			List<Orders> list = oservice.queryByUser_name(user_name,page.getPageNo(),page.getPageSize());
			request.setAttribute("page", page);
			request.setAttribute("value", user_name);
			request.setAttribute("value2", status);
			request.setAttribute("orderslist", list);
		
			request.getRequestDispatcher("allorders.jsp").forward(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
