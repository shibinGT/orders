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
		// �ϴ��ļ�
		// �������Ͷ���
		//�ļ��ϴ������ࣨ��ÿһ����������װΪһ����FileItem����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// ��ʱ�ļ�
		ServletContext servletContext = this.getServletConfig().getServletContext();
		
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		
		//�����ϴ���ʱĿ¼
		factory.setRepository(repository);
		
		//�ļ��ϴ������࣬���Ի�ȡ���е�FileItem����
		ServletFileUpload upload = new ServletFileUpload(factory);

		List<FileItem> list = null;
		File file = null;
		FileInputStream fileInputStream = null;
		Goods goods = new Goods();
		GoodsService service = new GoodsService();

		try {
			// ���ļ����ͺ����List��
			list = upload.parseRequest(request);
			// �õ�һ������ֵ
			FileItem item2 = list.get(0);
			String token = item2.getString();
			
			// ��session���token
			String token2 = (String) request.getSession().getAttribute("token");
			
			// �Ƚ����form��������ֵ��session������һ����ִ�в�������romove������Attribute
			if (StringUtils.isNotBlank(token) && token.equals(token2)) {
					request.getSession().removeAttribute("token");
					//�߳�˯�ߣ��Ա�۲�
					Thread.sleep(5000);
					//�ļ���
				for (FileItem items : list) {
					// ������ͨ�ı�
					if (!items.isFormField()) {
						//�ϴ���ָ��Ŀ¼
						String path = servletContext.getRealPath("/");
						file = new File(path + items.getName());
						try {
							items.write(file);
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}

				// ��ȡ�ϴ��ļ��õ�����ʱ�ļ�
				// ���������
				fileInputStream = new FileInputStream(file);
				// ������
				Workbook wb = new HSSFWorkbook(fileInputStream);
				Sheet sheet = wb.getSheetAt(0);
				for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
					Row row = sheet.getRow(rowIndex);
					// row�п���Ϊ��
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
					
					// ���ݻ�õ�ֵ�õ�Goods����Goods�������ݿ�
					service.addGoods(goods);
				}

				response.sendRedirect("goodsservlet");
			}
			//�������tokenֵ��һ������ɶҲ����
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
