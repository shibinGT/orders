package tag;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author yixuefei
 * @since JDK1.6
 * @history 2017��8��20�� yixuefei �½� ��ҳ��ǩ������
 */
public class PagerTag extends TagSupport {
	private static final long serialVersionUID = 5729832874890369508L;
	private String url; // ����URI
	private int pageSize = 10; // ÿҳҪ��ʾ�ļ�¼��
	private int pageNo = 1; // ��ǰҳ��
	private int recordCount; // �ܼ�¼��

	public int doStartTag() throws JspException {
		int pageCount = (recordCount + pageSize - 1) / pageSize; // ������ҳ��

		// ƴдҪ�����ҳ���HTML�ı�
		StringBuilder sb = new StringBuilder();

		sb.append("<style type=\"text/css\">");
		sb.append(".pagination {padding: 5px;float:right;font-size:12px;}");
		sb.append(
				".pagination a, .pagination a:link, .pagination a:visited {padding:2px 5px;margin:2px;border:1px solid #aaaadd;text-decoration:none;color:#006699;}");
		sb.append(
				".pagination a:hover, .pagination a:active {border: 1px solid #ff0000;color: #000;text-decoration: none;}");
		sb.append(
				".pagination span.current {padding: 2px 5px;margin: 2px;border: 1px solid #ff0000;font-weight: bold;background-color: #ff0000;color: #FFF;}");
		sb.append(".pagination span.disabled {padding: 2px 5px;margin: 2px;border: 1px solid #eee; color: #ddd;}");
		sb.append("</style>\r\n");
		sb.append("<div class=\"pagination\">\r\n");
		if (recordCount == 0) {
			sb.append("<strong>û�п���ʾ����Ŀ</strong>\r\n");
		} else {
			// ҳ��Խ�紦��
			if (pageNo > pageCount) {
				pageNo = pageCount;
			}
			if (pageNo < 1) {
				pageNo = 1;
			}

			sb.append("<form method=\"post\" action=\"").append(this.url).append("\" name=\"qPagerForm\">\r\n");

			// ��ȡ�����е����в���
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			Enumeration<String> enumeration = request.getParameterNames();
			String name = null; // ������
			String value = null; // ����ֵ
			// �������е����в����������ر���
			while (enumeration.hasMoreElements()) {
				name = enumeration.nextElement();
				value = request.getParameter(name);
				// ȥ��ҳ��
				if (name.equals("pageNo")) {
					if (null != value && !"".equals(value)) {
						pageNo = Integer.parseInt(value);
					}
					continue;
				}
				sb.append("<input type=\"hidden\" name=\"").append(name).append("\" value=\"").append(value)
						.append("\"/>\r\n");
			}

			// �ѵ�ǰҳ�����ó��������
			sb.append("<input type=\"hidden\" name=\"").append("pageNo").append("\" value=\"").append(pageNo)
					.append("\"/>\r\n");

			// ���ͳ������
			sb.append("&nbsp;��<strong>").append(recordCount).append("</strong>��").append(",<strong>").append(pageCount)
					.append("</strong>ҳ:&nbsp;\r\n");

			// ��һҳ����
			if (pageNo == 1) {
				sb.append("<span class=\"disabled\">&laquo;&nbsp;��һҳ").append("</span>\r\n");
			} else {
				sb.append("<a href=\"javascript:turnOverPage(").append((pageNo - 1))
						.append(")\">&laquo;&nbsp;��һҳ</a>\r\n");
			}

			// ���ǰ��ҳ������,��ʾ"..."
			int start = 1;
			if (this.pageNo > 4) {
				start = this.pageNo - 1;
				sb.append("<a href=\"javascript:turnOverPage(1)\">1</a>\r\n");
				sb.append("<a href=\"javascript:turnOverPage(2)\">2</a>\r\n");
				sb.append("&hellip;\r\n");
			}
			// ��ʾ��ǰҳ������ҳ
			int end = this.pageNo + 1;
			if (end > pageCount) {
				end = pageCount;
			}
			for (int i = start; i <= end; i++) {
				if (pageNo == i) { // ��ǰҳ�Ų���Ҫ������
					sb.append("<span class=\"current\">").append(i).append("</span>\r\n");
				} else {
					sb.append("<a href=\"javascript:turnOverPage(").append(i).append(")\">").append(i)
							.append("</a>\r\n");
				}
			}
			// �������ҳ������,��ʾ"..."
			if (end < pageCount - 2) {
				sb.append("&hellip;\r\n");
			}
			if (end < pageCount - 1) {
				sb.append("<a href=\"javascript:turnOverPage(").append(pageCount - 1).append(")\">")
						.append(pageCount - 1).append("</a>\r\n");
			}
			if (end < pageCount) {
				sb.append("<a href=\"javascript:turnOverPage(").append(pageCount).append(")\">").append(pageCount)
						.append("</a>\r\n");
			}

			// ��һҳ����
			if (pageNo == pageCount) {
				sb.append("<span class=\"disabled\">��һҳ&nbsp;&raquo;").append("</span>\r\n");
			} else {
				sb.append("<a href=\"javascript:turnOverPage(").append((pageNo + 1))
						.append(")\">��һҳ&nbsp;&raquo;</a>\r\n");
			}
			sb.append("</form>\r\n");

			// �����ύ����JS
			sb.append("<script language=\"javascript\">\r\n");
			sb.append("  function turnOverPage(no){\r\n");
			sb.append("    if(no>").append(pageCount).append("){");
			sb.append("      no=").append(pageCount).append(";}\r\n");
			sb.append("    if(no<1){no=1;}\r\n");
			sb.append("    document.qPagerForm.pageNo.value=no;\r\n");
			sb.append("    document.qPagerForm.submit();\r\n");
			sb.append("  }\r\n");
			sb.append("</script>\r\n");
		}
		sb.append("</div>\r\n");

		// �����ɵ�HTML�������Ӧ��
		try {
			pageContext.getOut().println(sb.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY; // ����ǩ����Ϊ��,����ֱ����������
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
}
