package tag;
/**
* @author YI
* @since JDK1.8
* @history 2017��8��21�� YI �½�
* ��˵��
*/
public class Pager {
	private int pageSize = 9; // ÿҳҪ��ʾ�ļ�¼��
	private int pageNo = 1; // ��ǰҳ��
	private int recordCount; // �ܼ�¼��
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	
	
	

}

