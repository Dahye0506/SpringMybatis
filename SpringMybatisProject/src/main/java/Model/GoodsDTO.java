package Model;

public class GoodsDTO {//값을 db테이블로 전달하는 애 = db테이블에있는 컬럼리스트의 이름과 같아야한다.

	Long prodNum;
	String prodName;
	Long prodPrice;
	String prodImage;//db에 넣을때는 파일명을 넣기때문에 String으로 생성
	String prodDetail;
	String prodCapacity;
	String prodSupplyer;
	Long prodDelFee;
	String recommend;
	String employeeId;
	String ctgr;
	
	
	
	public Long getProdNum() {
		return prodNum;
	}
	public void setProdNum(Long prodNum) {
		this.prodNum = prodNum;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public Long getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(Long prodPrice) {
		this.prodPrice = prodPrice;
	}
	public String getProdImage() {
		return prodImage;
	}
	public void setProdImage(String prodImage) {
		this.prodImage = prodImage;
	}
	public String getProdDetail() {
		return prodDetail;
	}
	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}
	public String getProdCapacity() {
		return prodCapacity;
	}
	public void setProdCapacity(String prodCapacity) {
		this.prodCapacity = prodCapacity;
	}
	public String getProdSupplyer() {
		return prodSupplyer;
	}
	public void setProdSupplyer(String prodSupplyer) {
		this.prodSupplyer = prodSupplyer;
	}
	public Long getProdDelFee() {
		return prodDelFee;
	}
	public void setProdDelFee(Long prodDelFee) {
		this.prodDelFee = prodDelFee;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getCtgr() {
		return ctgr;
	}
	public void setCtgr(String ctgr) {
		this.ctgr = ctgr;
	}
	
	
}
