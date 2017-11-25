package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Orders_goods;
import services.GoodsService;
import services.OrdersService;
import services.Orders_goodsService;

/**
 * 支付成功处理 库存减少 订单状态改变 Servlet implementation class Paymoney
 */
@WebServlet("/paymoney")
public class Paymoney extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Paymoney() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrdersService oservice = new OrdersService();
		Orders_goodsService ogservice = new Orders_goodsService();
		// 拿到订单id
		int orders_id = Integer.parseInt(request.getParameter("orders_id"));
		//支付时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String paymenttime=sdf.format(new Date());
		/**
		 *  修改订单状态为2---已支付
		 *  和支付时间
		 */
		oservice.updateGoods(paymenttime,orders_id);
		/**
		 * 减库存操作
		 */
		// 根据订单id拿出全部订单商品
		List<Orders_goods> goodslist = ogservice.queryByOrders_id(orders_id);
		// 订单商品集合
		for (Orders_goods orders_goods : goodslist) {
			// 拿出商品id和数量
			int goods_id = orders_goods.getGoods_id();
			int number = orders_goods.getNumber();

			// 改库存
			GoodsService gservice = new GoodsService();
			// 先查出原本库存

			int stock = gservice.queryByGoods_id(goods_id).getStock();
			// 库存减去number
			gservice.updateGoods(stock - number, goods_id);
		}
		//跳到订单查询
		response.sendRedirect("orderscheck");
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
