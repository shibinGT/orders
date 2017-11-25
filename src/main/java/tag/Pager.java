package tag;
/**
* @author YI
* @since JDK1.8
* @history 2017年8月21日 YI 新建
* 类说明
*/
public class Pager {
	private int pageSize = 9; // 每页要显示的记录数
	private int pageNo = 1; // 当前页号
	private int recordCount; // 总记录数
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

