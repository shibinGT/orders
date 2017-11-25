package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Cart;
import entity.Goods;
import entity.User;
import services.CartService;
import services.GoodsService;

/**
 * Servlet implementation class ShoppingCart
 */
@WebServlet("/shoppingCart")
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CartService cservice=new CartService();
		GoodsService gservice=new GoodsService();
		
		//�õ���������Ʒid���ó���Ʒ����
		int goods_id=Integer.parseInt(request.getParameter("goods_id"));
		Goods goods=gservice.queryByGoods_id(goods_id);
		//���
		int stock=goods.getStock();
		//�õ��û�id
		User u=(User)request.getSession().getAttribute("loginuser");
		int user_id=u.getUser_id();
		//�ж����޼�¼
		Cart cart=new Cart();
		//���û��and������0�����
		boolean have=cservice.queryByGoods_idAndUser_id(goods_id, user_id)!=null;
		if(!have&&stock>0){
			cart.setGoods_id(goods_id);
			cart.setGoods_name(goods.getGoods_name());
			cart.setUser_id(user_id);
			cart.setNumber(1);
			//���
			cservice.addCart(cart);
			//�����
		}else if(have&&stock>0){
			cart=cservice.queryByGoods_idAndUser_id(goods_id, user_id);
			int number=cart.getNumber();
			//������Ϊԭ����+1
			cservice.updateGoods(number+1, goods_id, user_id);	
		}
		//��ת��shoppingservlet
		response.sendRedirect("shoppingservlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
