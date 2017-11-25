package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
 * Servlet implementation class GoodsChangeservlet
 */
@WebServlet("/goodsChangeservlet")
public class GoodsChangeservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsChangeservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//newservice�����
		GoodsService service=new GoodsService();
		
		String goods_name="";
		String describes="";
		int goods_id=0;
		float price=0;
		String picture="";
		int stock=0;
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
					picture=fileName;
				} else {
					// ��ͨ����name
					String name = item.getFieldName();
					if (name.equals("goods_name")) {
						String value = item.getString();
						// ���±���,�������
						goods_name = new String(value.getBytes("ISO-8859-1"), "UTF-8");
					
					} else if (name.equals("price")) {
						String value = item.getString();
						// ���±���,�������
						value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
						price=Float.parseFloat(value);
					} else if (name.equals("stock")) {
						String value = item.getString();
						// ���±���,�������
						value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
						stock=Integer.parseInt(value);
					} else if (name.equals("describes")) {
						String value = item.getString();
						// ���±���,�������
						describes = new String(value.getBytes("ISO-8859-1"), "UTF-8");
						
					}
					else if (name.equals("goods_id")) {
						String value = item.getString();
						// ���±���,�������
						value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
						goods_id=Integer.parseInt(value);
					}
				}

			}

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		service.updateGoods(goods_name, price, stock, picture,describes,goods_id);
		
		response.sendRedirect("goodsservlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
