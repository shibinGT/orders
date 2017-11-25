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

import entity.Cart;
import entity.Goods;
import entity.Orders;
import entity.Orders_goods;
import entity.User;
import services.CartService;
import services.GoodsService;
import services.OrdersService;
import services.Orders_goodsService;

/**
 * Servlet implementation class Addorders
 */
@WebServlet("/addorders")
public class Addorders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addorders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.�õ��û�ID���õ��û��Ĺ��ﳵ��Ϣ
		
		User u=(User)request.getSession().getAttribute("loginuser");
		int user_id=u.getUser_id();
		String user_name=u.getUser_name();
		//׼������
		CartService cservice=new CartService();
		GoodsService goodservice=new GoodsService();
		OrdersService oservice=new OrdersService();
		//�û��Ĺ��ﳵ��Ϣ,�����������Ʒid��������Ȼ����Եõ���Ʒ-��Ʒ�۸�
		//Ȼ������ܼ�
		/**
		 * ��Ӷ���
		 */
		
		
		float money=0;
		List<Cart> cartlist=cservice.queryByUser_id(user_id);
		for(Cart cart:cartlist){
			int goods_id=cart.getGoods_id();
			 Goods goods=goodservice.queryByGoods_id(goods_id);
			 float price=goods.getPrice();
			 //�ܼ�
			int number=cart.getNumber();
			 money+=price*number;
		}
		
		Orders orders=new Orders();
		//ʱ���ʽ
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//����ʱ��
		String createtime=sf.format(new Date());
		orders.setCreatetime(createtime);
		//�ܼ�
		orders.setMoney(money);
		//�û�id
		orders.setUser_id(user_id);
		orders.setUser_name(user_name);
		//��Ӷ���
		oservice.addOrders(orders);
		//�õ�����id
		
		int orders_id=oservice.queryByUser_idAndCreatetime(user_id, createtime).getOrders_id();
		//������id
		
		/**
		 * ��Ӷ�����Ʒ
		 */
		Orders_goods orders_goods=new Orders_goods();
		Orders_goodsService ogservice=new Orders_goodsService();
		//�ٴ�ѭ�����ﳵ
		for(Cart cart:cartlist){
			//set������Ʒ
			int goods_id=cart.getGoods_id();
			//id
			orders_goods.setGoods_id(goods_id);
			Goods goods=goodservice.queryByGoods_id(goods_id);
			float price=goods.getPrice();
			//����
			orders_goods.setPrice(price);
			//����
			orders_goods.setGoods_name(cart.getGoods_name());
			//����
			orders_goods.setNumber(cart.getNumber());
			//����id
			orders_goods.setOrders_id(orders_id);
			//��Ӷ�����Ʒ
			ogservice.addOrders_goods(orders_goods);
		}
		//�����û�id��չ��ﳵ
		cservice.deleteGoodsAll(user_id);
		
		
		response.sendRedirect("orderspaymoney?orders_id="+orders_id);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
