/**
 * ���ϸ� : MemberDao.java
 * �ۼ��� : 2014. 2. 12.
 * ���ϼ��� : 
 */
package casestudy.business.service;

import casestudy.business.domain.Member;

/**
 * ȸ�� ���� ������ �׼��� ó���� ����� ��ü�� �԰��� ������ �������̽�.<br/> 
 * ������ �׼��� ���� �и������ν� ������ �׼��� �� �̿� ����̳� ������ ����Ǿ 
 * ����Ͻ� ���� ���� ������ ���� �ʴ´�.
 *  
 * @author �����(kidmania@hotmail.com)
 *
 */
public interface MemberDao {
	
    /**
     * ������ ����ҿ� �μ��� �־��� Member ��ü�� ������ �߰��Ѵ�.
     *
     * @param member �߰��ϰ��� �ϴ� ȸ�������� ��� �ִ� Member ��ü
     */
	public void insertMember(Member member);
	
    /**
     * ������ ����ҿ��� �μ��� �־��� memberID�� �ش��ϴ� ȸ�������� �˻��Ѵ�.
     *
     * @param memberID �˻��ϰ��� �ϴ� ȸ���� memberID
     * @return �˻��� ȸ�������� ��� �ִ� Member ��ü
     */
	public Member selectMember(String memberID);
	
    /**
     * ������ ����ҿ��� �μ��� �־��� Member ��ü�� ������ �����Ѵ�.
     *
     * @param member �����ϰ��� �ϴ� ȸ�������� ��� �ִ� Member ��ü
     */
	public void updateMember(Member member);
	
    /**
     * ������ ����ҿ��� �μ��� �־��� Member ��ü�� ������ �����Ѵ�.
     *
     * @param member �����ϰ��� �ϴ� ȸ�������� ��� �ִ� Member ��ü
     */
	public void deleteMember(Member member);
	
    /**
     * ������ ����ҿ��� �μ��� �־��� memberID�� password�� �ش��ϴ� ȸ�������� Ȯ���Ͽ� 
     * ��ȿ�� ȸ���ΰ� ���θ� �Ǻ��Ѵ�.<br/>
     * 
     *  ���̵� �������� ���� ��쿡�� Member�� check ���� Member.INVALID_ID ��,
     *  ���̵�� �����ϳ� �н����尡 ���� ���� ��쿡�� Member�� check ���� Member.INVALID_PASSWORD ��,
     *  ���̵�� �н����尡 ��� ��ġ�� ��쿡�� Member�� check�� ���� Member.VALID_MEMBER �� �����ϰ� 
     *  �ش� ȸ�������� ���� Member ��ü�� �����Ѵ�.
     *
     * @param memberID Ȯ���ϰ��� �ϴ� ȸ���� memberID
     * @param password Ȯ���ϰ��� �ϴ� ȸ���� password
     * @return ��ȿ ȸ�� ���� ���� ȸ�������� ��� �ִ� Member ��ü
     */
	public Member checkMember(String memberID, String password);
	
    /**
     * ������ ����ҿ��� ��� ȸ�������� �˻��Ѵ�.
     * 
     * @return �˻��� ��� ȸ�������� ��� �ִ� Member �迭
     */
	public Member[] selectAllMembers();
	
    /**
     * ������ ����ҿ� �μ��� �־��� memberID�� �ش��ϴ� ���� ȸ�������� �ִ��� Ȯ���Ѵ�.
     * 
     * @return �ش��ϴ� ȸ�������� �����ϸ� true, �������� ������ false
     */
	public boolean memberIDExists(String memberID);
	
}
