package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Goods;
import entity.User;
import services.CartService;
import services.GoodsService;

/**
 * �Ƴ����ﳵ��Ʒ
 * Servlet implementation class DeleteCart
 */
@WebServlet("/deleteCart")
public class DeleteCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCart() {
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
		int goods_id=Integer.parseInt(request.getParameter("deletegoods_id"));
		Goods goods=gservice.queryByGoods_id(goods_id);
				
		//�õ��û�id
		User u=(User)request.getSession().getAttribute("loginuser");
		int user_id=u.getUser_id();
		//ɾ��
		cservice.deleteGoods(goods_id, user_id);
		//�� ת
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
