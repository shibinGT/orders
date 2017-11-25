package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import services.GoodsService;

import entity.Goods;

/**
 * Servlet implementation class Fileservlet
 */
@WebServlet("/fileservlet")
public class Fileservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Fileservlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 上传文件
		// 创建解释对象
		//文件上传工厂类（把每一个请求表单项封装为一个个FileItem对象）
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 临时文件
		ServletContext servletContext = this.getServletConfig().getServletContext();
		
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		
		//设置上传临时目录
		factory.setRepository(repository);
		
		//文件上传核心类，可以获取所有的FileItem对象
		ServletFileUpload upload = new ServletFileUpload(factory);

		List<FileItem> list = null;
		File file = null;
		FileInputStream fileInputStream = null;
		Goods goods = new Goods();
		GoodsService service = new GoodsService();

		try {
			// 将文件解释后放在List中
			list = upload.parseRequest(request);
			// 拿第一个表单的值
			FileItem item2 = list.get(0);
			String token = item2.getString();
			
			// 拿session里的token
			String token2 = (String) request.getSession().getAttribute("token");
			
			// 比较如果form表单传来的值和session传来的一至才执行操作，并romove传来的Attribute
			if (StringUtils.isNotBlank(token) && token.equals(token2)) {
					request.getSession().removeAttribute("token");
					//线程睡眠，以便观察
					Thread.sleep(5000);
					//文件表单
				for (FileItem items : list) {
					// 不是普通的表单
					if (!items.isFormField()) {
						//上传到指定目录
						String path = servletContext.getRealPath("/");
						file = new File(path + items.getName());
						try {
							items.write(file);
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}

				// 读取上传文件得到的临时文件
				// 创建输出流
				fileInputStream = new FileInputStream(file);
				// 工作薄
				Workbook wb = new HSSFWorkbook(fileInputStream);
				Sheet sheet = wb.getSheetAt(0);
				for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
					Row row = sheet.getRow(rowIndex);
					// row有可能为空
					if (row == null) {
						continue;
					}
					Cell cell1 = row.getCell(0);

					goods.setGoods_name(cell1.getStringCellValue());
					Cell cell2 = row.getCell(1);
					goods.setPrice((float) cell2.getNumericCellValue());
					Cell cell3 = row.getCell(2);
					goods.setStock((int) cell3.getNumericCellValue());
					Cell cell4 = row.getCell(3);
					goods.setPicture( cell4.getStringCellValue());
					Cell cell5 = row.getCell(4);
					goods.setDescribes( cell5.getStringCellValue());
					
					// 根据获得的值得到Goods，把Goods存入数据库
					service.addGoods(goods);
				}

				response.sendRedirect("goodsservlet");
			}
			//如果两个token值不一至，则啥也不做
			else{
				return;
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (file != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
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
