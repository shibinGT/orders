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
 * ֧���ɹ����� ������ ����״̬�ı� Servlet implementation class Paymoney
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
		// �õ�����id
		int orders_id = Integer.parseInt(request.getParameter("orders_id"));
		//֧��ʱ��
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String paymenttime=sdf.format(new Date());
		/**
		 *  �޸Ķ���״̬Ϊ2---��֧��
		 *  ��֧��ʱ��
		 */
		oservice.updateGoods(paymenttime,orders_id);
		/**
		 * ��������
		 */
		// ���ݶ���id�ó�ȫ��������Ʒ
		List<Orders_goods> goodslist = ogservice.queryByOrders_id(orders_id);
		// ������Ʒ����
		for (Orders_goods orders_goods : goodslist) {
			// �ó���Ʒid������
			int goods_id = orders_goods.getGoods_id();
			int number = orders_goods.getNumber();

			// �Ŀ��
			GoodsService gservice = new GoodsService();
			// �Ȳ��ԭ�����

			int stock = gservice.queryByGoods_id(goods_id).getStock();
			// ����ȥnumber
			gservice.updateGoods(stock - number, goods_id);
		}
		//����������ѯ
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
