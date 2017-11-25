package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import services.GoodsService;

import entity.Goods;

/**
 * Servlet implementation class Goodsupdate
 */
@WebServlet(name = "GoodAdd", urlPatterns = { "/goodsAdd" })
public class GoodAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GoodAdd() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 准备工作，商品service
		GoodsService service = new GoodsService();
		// new一个Goods
		Goods g = new Goods();

		/*
		 * 图片处理
		 */
		// 文件上传工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 文件上传核心类
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 图片保存目录
		String savaPath = getServletContext().getRealPath("/img");
		System.out.println(savaPath);
		File savaDir = new File(savaPath);

		// 如果目录不存在就创建
		if (!savaDir.exists()) {
			savaDir.mkdir();
		}
		try {
			List<FileItem> list = upload.parseRequest(request);
			System.out.println(list);
			// 循环
			for (FileItem item : list) {
				// 如果不是普通表单
				if (!item.isFormField()) {
					// 文件名
					String fileName = item.getName();
					// 如果不是jpg文件
					if (!fileName.endsWith(".jpg")) {
						request.setAttribute("msg", "不是jpg格式不能上传");
					} else {

						// 是jpg文件
						// 文件前加上时间，维一
						SimpleDateFormat sim = new SimpleDateFormat(" yy/MM/dd HH:mm:ss");
						Date date = new Date();
						String a = sim.format(date);
						
						File file = new File(savaPath, fileName);
						try {
							item.write(file);
							System.out.println("yess");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						request.setAttribute("msg", "上传成功");
					}

					// 图片
					g.setPicture(fileName);
				} else {
					// 普通表单的name
					String name = item.getFieldName();
					if (name.equals("goods_name")) {
						String value = item.getString();
						// 重新编码,解决乱码
						value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
						g.setGoods_name(value);
					} else if (name.equals("price")) {
						String value = item.getString();
						// 重新编码,解决乱码
						value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
						g.setPrice(Float.parseFloat(value));
					} else if (name.equals("stock")) {
						String value = item.getString();
						// 重新编码,解决乱码
						value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
						g.setStock(Integer.parseInt(value));
					} else if (name.equals("describes")) {
						String value = item.getString();
						// 重新编码,解决乱码
						value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
						g.setDescribes(value);
					}
				}

			}

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 添加商品
		boolean a = service.addGoods(g);
		// 重定向
		response.sendRedirect("goodsservlet");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
