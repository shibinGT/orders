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
		// ׼����������Ʒservice
		GoodsService service = new GoodsService();
		// newһ��Goods
		Goods g = new Goods();

		/*
		 * ͼƬ����
		 */
		// �ļ��ϴ�����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// �ļ��ϴ�������
		ServletFileUpload upload = new ServletFileUpload(factory);
		// ͼƬ����Ŀ¼
		String savaPath = getServletContext().getRealPath("/img");
		System.out.println(savaPath);
		File savaDir = new File(savaPath);

		// ���Ŀ¼�����ھʹ���
		if (!savaDir.exists()) {
			savaDir.mkdir();
		}
		try {
			List<FileItem> list = upload.parseRequest(request);
			System.out.println(list);
			// ѭ��
			for (FileItem item : list) {
				// ���������ͨ��
				if (!item.isFormField()) {
					// �ļ���
					String fileName = item.getName();
					// �������jpg�ļ�
					if (!fileName.endsWith(".jpg")) {
						request.setAttribute("msg", "����jpg��ʽ�����ϴ�");
					} else {

						// ��jpg�ļ�
						// �ļ�ǰ����ʱ�䣬άһ
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
						request.setAttribute("msg", "�ϴ��ɹ�");
					}

					// ͼƬ
					g.setPicture(fileName);
				} else {
					// ��ͨ����name
					String name = item.getFieldName();
					if (name.equals("goods_name")) {
						String value = item.getString();
						// ���±���,�������
						value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
						g.setGoods_name(value);
					} else if (name.equals("price")) {
						String value = item.getString();
						// ���±���,�������
						value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
						g.setPrice(Float.parseFloat(value));
					} else if (name.equals("stock")) {
						String value = item.getString();
						// ���±���,�������
						value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
						g.setStock(Integer.parseInt(value));
					} else if (name.equals("describes")) {
						String value = item.getString();
						// ���±���,�������
						value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
						g.setDescribes(value);
					}
				}

			}

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// �����Ʒ
		boolean a = service.addGoods(g);
		// �ض���
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
