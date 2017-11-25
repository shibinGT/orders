package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import entity.Cart;
import entity.Goods;
import entity.User;
import services.CartService;
import services.GoodsService;
import tag.Pager;
import utils.PageUtils;

/**
 * ��������servlet
 * 
 * Servlet implementation class Shoppingservlet
 */
@WebServlet("/shoppingservlet")
public class Shoppingservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Shoppingservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.����Ʒ�б����
		GoodsService service=new GoodsService();
		String name=request.getParameter("goods_name");
		if(StringUtils.isBlank(name)){
			name="";
		}
		//��ҳ
		Pager page = new Pager();
		
		PageUtils.parseParam(page, request);
		
		int count = service.queryCount(name);
		
		
		page.setRecordCount(count);
		//������
		List<Goods> goodslist=service.queryList(name,page.getPageNo(),page.getPageSize());
			request.setAttribute("goodslist", goodslist);
			request.setAttribute("value", name);
			request.setAttribute("page", page);
			
			//�õ��û�id
			User u=(User)request.getSession().getAttribute("loginuser");
			int user_id=u.getUser_id();
			//�ѹ��ﳵ���Ϸŵ�request��
			CartService cservice=new CartService();
			List<Cart> cartlist=cservice.queryByUser_id(user_id);
			request.setAttribute("cartlist", cartlist);
			request.getRequestDispatcher("/shopping.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
