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
		//1.拿到用户ID，得到用户的购物车信息
		
		User u=(User)request.getSession().getAttribute("loginuser");
		int user_id=u.getUser_id();
		String user_name=u.getUser_name();
		//准备工作
		CartService cservice=new CartService();
		GoodsService goodservice=new GoodsService();
		OrdersService oservice=new OrdersService();
		//用户的购物车信息,里面可以拿商品id和数量，然后可以得到商品-商品价格
		//然后算出总价
		/**
		 * 添加订单
		 */
		
		
		float money=0;
		List<Cart> cartlist=cservice.queryByUser_id(user_id);
		for(Cart cart:cartlist){
			int goods_id=cart.getGoods_id();
			 Goods goods=goodservice.queryByGoods_id(goods_id);
			 float price=goods.getPrice();
			 //总价
			int number=cart.getNumber();
			 money+=price*number;
		}
		
		Orders orders=new Orders();
		//时间格式
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//创建时间
		String createtime=sf.format(new Date());
		orders.setCreatetime(createtime);
		//总价
		orders.setMoney(money);
		//用户id
		orders.setUser_id(user_id);
		orders.setUser_name(user_name);
		//添加订单
		oservice.addOrders(orders);
		//拿到订单id
		
		int orders_id=oservice.queryByUser_idAndCreatetime(user_id, createtime).getOrders_id();
		//传订单id
		
		/**
		 * 添加订单商品
		 */
		Orders_goods orders_goods=new Orders_goods();
		Orders_goodsService ogservice=new Orders_goodsService();
		//再次循环购物车
		for(Cart cart:cartlist){
			//set订单商品
			int goods_id=cart.getGoods_id();
			//id
			orders_goods.setGoods_id(goods_id);
			Goods goods=goodservice.queryByGoods_id(goods_id);
			float price=goods.getPrice();
			//单价
			orders_goods.setPrice(price);
			//名字
			orders_goods.setGoods_name(cart.getGoods_name());
			//数量
			orders_goods.setNumber(cart.getNumber());
			//订单id
			orders_goods.setOrders_id(orders_id);
			//添加订单商品
			ogservice.addOrders_goods(orders_goods);
		}
		//根据用户id清空购物车
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
