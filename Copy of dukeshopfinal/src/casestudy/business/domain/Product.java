/**
 * ���ϸ� : Product.java
 * �ۼ��� : 2014. 2. 12.
 * ���ϼ��� : 
 */
package casestudy.business.domain;

import java.sql.Date;

/**
 * ��ǰ�� ������ ������ �����ϰ� �ִ� ��ü�� ������ ������ Ŭ����.<br/> 
 * ����Ͻ� ���� ���� �������̽��� ǥ���Ǵ� Ư�� ������ Ư�� �μ� ó���� ������ ���� �� ���������� �����ȴ�.<br/>
 * �������� ���񽺷κ��� ����Ͻ��� �����ϴµ� �־� �翬�� �νĵǴ� Ŭ����(���̳� �ֹ��� ����)�� ��������
 * �ڽ��� �������� ��Ÿ���� ���� �� ���� �̿��� ó���� �����Ѵ�.<br/> 
 * �������� ������ �������� �ʰ� �ܼ��� ���� �����ϱ⸸ �ϴ� ��ü�� ��� VO(Value Object: ���� �����ϴ� ��ü)��
 * DTO(Data Transfer Object: ���� �����ϱ⸸ �ϴ� ��ü)��� �θ��⵵ �Ѵ�. 
 *  
 * @author �����(kidmania@hotmail.com)
 *
 */
public class Product {
	private String productID;
	private String mallID;
	private String productName;
	private String company;
	private int price1;
	private int price2;
	private String installment;
	private String keyword;
	private String detail;
	private Date productDate;
	private String photoDir;
	
	public Product() {
	}

	public Product(String productID, String mallID, String productName,
			String company, int price1, int price2, String installment,
			String keyword, String detail, Date productDate, String photoDir) {
		this.productID = productID;
		this.mallID = mallID;
		this.productName = productName;
		this.company = company;
		this.price1 = price1;
		this.price2 = price2;
		this.installment = installment;
		this.keyword = keyword;
		this.detail = detail;
		this.productDate = productDate;
		this.photoDir = photoDir;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getMallID() {
		return mallID;
	}

	public void setMallID(String mallID) {
		this.mallID = mallID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getPrice1() {
		return price1;
	}

	public void setPrice1(int price1) {
		this.price1 = price1;
	}

	public int getPrice2() {
		return price2;
	}

	public void setPrice2(int price2) {
		this.price2 = price2;
	}

	public String getInstallment() {
		return installment;
	}

	public void setInstallment(String installment) {
		this.installment = installment;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public String getPhotoDir() {
		return photoDir;
	}

	public void setPhotoDir(String photoDir) {
		this.photoDir = photoDir;
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", mallID=" + mallID
				+ ", productName=" + productName + ", company=" + company
				+ ", price1=" + price1 + ", price2=" + price2 + ", installment="
				+ installment + ", keyword=" + keyword + ", detail=" + detail
				+ ", productDate=" + productDate + ", photoDir=" + photoDir
				+ "]";
	}	
}
