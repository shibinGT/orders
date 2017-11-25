package utils;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import tag.Pager;

/**
* @author YI
* @since JDK1.8
* @history 2017��8��21�� YI �½�
* ��˵��
*/
public class PageUtils {
	
	public static void parseParam(Pager page,HttpServletRequest request){
		//��ҳ�����
		String pageNo=request.getParameter("pageNo");
		String pageSize=request.getParameter("pageSize");
		
		if(StringUtils.isNotBlank(pageNo)){
			page.setPageNo(Integer.parseInt(pageNo));
		}
		
		if(StringUtils.isNotBlank(pageSize)){
			page.setPageSize(Integer.parseInt(pageSize));
		}
		
	}
}
