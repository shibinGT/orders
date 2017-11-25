package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import services.GoodsService;
import tag.Pager;
import utils.PageUtils;
import entity.Goods;

/**
 * Servlet implementation class Goodsservlet
 */
@WebServlet("/goodsservlet")
public class Goodsservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Goodsservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GoodsService service=new GoodsService();
		String name=request.getParameter("goods_name");
		if(StringUtils.isBlank(name)){
			name="";
		}
		Pager page = new Pager();
		page.setPageSize(5);
		PageUtils.parseParam(page, request);
		
		int count = service.queryCount(name);
		
		
		page.setRecordCount(count);
		
		List<Goods> goodslist=service.queryList(name,page.getPageNo(),page.getPageSize());
			request.setAttribute("goodslist", goodslist);
			request.setAttribute("value", name);
			request.setAttribute("page", page);
			request.getRequestDispatcher("/goods.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
