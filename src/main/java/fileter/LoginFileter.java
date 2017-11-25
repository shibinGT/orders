package fileter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet Filter implementation class LoginFileter
 */

public class LoginFileter implements Filter {
private String[] pa;
    /**
     * Default constructor. 
     */
    public LoginFileter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//ת����HttpServlet
		HttpServletRequest hsr=(HttpServletRequest)request;
		HttpServletResponse hsp=(HttpServletResponse)response;
		//��ȡ��½ʱ����session����û�
		 Object o=hsr.getSession().getAttribute("loginuser");
		 String path=hsr.getServletPath();
		 //���Ϊ�գ��շ��ص�½����
		 if(o==null && !onThePath(path)){
			 hsp.sendRedirect("tologin.jsp");
			 return;
		 }
		
		
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//��ȡweb.xml�з���Ĺ���������
		String p=fConfig.getInitParameter("nologinUrl");
		//��;�ŷָ������
		pa=p.split(";");
	}
	
	private boolean onThePath(String path){
		
		for(String a:pa){
			if(path.startsWith(a)){
				return true;
			}			
		}
		return false;
	}

}
