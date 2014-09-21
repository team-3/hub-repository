/**
 * ���ϸ� : Member.java
 * �ۼ��� : 2014. 2. 12.
 * ���ϼ��� : 
 */
package casestudy.business.domain;

import java.sql.Date;

/**
 * ȸ���� ������ ������ �����ϰ� �ִ� ��ü�� ������ ������ Ŭ����.<br/> 
 * ����Ͻ� ���� ���� �������̽��� ǥ���Ǵ� Ư�� ������ Ư�� �μ� ó���� ������ ���� �� ���������� �����ȴ�.<br/>
 * �������� ���񽺷κ��� ����Ͻ��� �����ϴµ� �־� �翬�� �νĵǴ� Ŭ����(���̳� �ֹ��� ����)�� ��������
 * �ڽ��� �������� ��Ÿ���� ���� �� ���� �̿��� ó���� �����Ѵ�.<br/> 
 * �������� ������ �������� �ʰ� �ܼ��� ���� �����ϱ⸸ �ϴ� ��ü�� ��� VO(Value Object: ���� �����ϴ� ��ü)��
 * DTO(Data Transfer Object: ���� �����ϱ⸸ �ϴ� ��ü)��� �θ��⵵ �Ѵ�. 
 *  
 * @author �����(kidmania@hotmail.com)
 *
 */
public class Member {
	private String memberID;
	private String password;
	private String name;
	private String email;
	private String tel;
	private String zipcode;
	private String address;
	private int point;
	private Date memberDate;
	private int check;	// VALID_MEMBER or INVALID_ID or INVALID_PASSWORD
	
	/** ��ȿ�� ȸ������ ��Ÿ���� ��� */
	public static final int VALID_MEMBER = 1;
	/** memberID�� �������� �ʴ� ȸ������ ��Ÿ���� ��� */
	public static final int INVALID_ID = 0; 
	/** password�� ��ġ���� �ʴ� ȸ������ ��Ÿ���� ��� */
	public static final int INVALID_PASSWORD = -1;
	
	public Member() {
	}

	public Member(String memberID, String password) {
		this.memberID = memberID;
		this.password = password;
	}
	
	public Member(String memberID, String password, String name, String email,
			String tel, String zipcode, String address) {
		this.memberID = memberID;
		this.password = password;
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.zipcode = zipcode;
		this.address = address;
	}
	
	public Member(String memberID, String password, String name, String email,
			String tel, String zipcode, String address, int point) {
		this(memberID, password, name, email, tel, zipcode, address);
		this.point = point;
	}
	
	public Member(String memberID, String password, String name, String email,
			String tel, String zipcode, String address, int point, Date memberDate) {
		this(memberID, password, name, email, tel, zipcode, address);
		this.point = point;
		this.memberDate = memberDate;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Date getMemberDate() {
		return memberDate;
	}

	public void setMemberDate(Date memberDate) {
		this.memberDate = memberDate;
	}

	public int getCheck() {
		return check;
	}

	/** ȸ���� ��ȿ�� ���θ� ��Ÿ���� check ���� �����Ѵ�.
	 * check �μ��� ���� Member.VALID_MEMBER, Member.INVALID_ID, Member.INVALID_PASSWORD �� �ϳ����� �Ѵ�.
	 * 	
	 * @param check Member.VALID_MEMBER, Member.INVALID_ID or Member.INVALID_PASSWORD
	 */
	public void setCheck(int check) {
		this.check = check;
	}

	@Override
	public String toString() {
		return "Member [memberID=" + memberID + ", password=" + password
				+ ", name=" + name + ", email=" + email + ", tel=" + tel
				+ ", zipcode=" + zipcode + ", address=" + address + ", point="
				+ point + ", memberDate=" + memberDate + ", check=" + check
				+ "]";
	}
}
