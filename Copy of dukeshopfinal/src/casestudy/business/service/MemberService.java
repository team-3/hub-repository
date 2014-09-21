/**
 * ���ϸ� : MemberService.java
 * �ۼ��� : 2014. 2. 12.
 * ���ϼ��� : 
 */
package casestudy.business.service;

import casestudy.business.domain.Member;

/**
 * ȸ���� ������ ������ ó���� ������ ���񽺸� ����� ��ü�� �԰��� ������ �������̽�.<br/> 
 * ����Ͻ� ���� ���� �������̽��� ǥ���Ǵ� Ư�� ������ Ư�� �μ� ó���� ������ ���� �� ���������� �����ȴ�.<br/>
 * ����Ͻ� ���� ���� ���ø����̼��� �߽��� �Ǹ�, ���ø����̼��� ��� �߰��� �����̶� �ַ� ����Ͻ� ���� ���� �����̴�.
 * ���� ������ ��Ű��ó�� ���� ���ø����̼��� ����� ���ؼ��� ����Ͻ� ���� ���� �� ����� ���� �߿��ϴ�.
 *  
 * @author �����(kidmania@hotmail.com)
 *
 */
public interface MemberService {
	
    /**
     * �μ��� �־��� Member ��ü�� ������ �ش��ϴ� ȸ���� ���(ȸ�� ����)�Ѵ�.
     *
     * @param member ����ϰ��� �ϴ� ȸ�������� ��� �ִ� Member ��ü
     * @throws DataDuplicatedException �ߺ��� memberID�� ���� ���� ȸ���� ������ ��� �߻� 
     */
	public void registerMember(Member member) throws DataDuplicatedException;
	
    /**
     * �μ��� �־��� memberID�� �ش��ϴ� ȸ���� �˻��Ѵ�.
     *
     * @param memberID �˻��ϰ��� �ϴ� ȸ���� memberID
     * @return �˻��� ȸ�������� ��� �ִ� Member ��ü
     * @throws DataNotFoundException memberID�� �ش��ϴ� ȸ���� �������� ���� ��� �߻� 
     */
	public Member findMember(String memberID) throws DataNotFoundException;
	
    /**
     * �μ��� �־��� Member ��ü�� ������ �ش� ȸ�������� ����(ȸ������ ����)�Ѵ�.
     *
     * @param member �����ϰ��� �ϴ� ȸ�������� ��� �ִ� Member ��ü
     * @throws DataNotFoundException �ش� ȸ���� �������� ���� ��� �߻�
     */
	public void updateMember(Member member) throws DataNotFoundException;
	
    /**
     * �μ��� �־��� Member ��ü�� ������ �ش��ϴ� ȸ���� ����(ȸ�� Ż��)�Ѵ�.
     *
     * @param member �����ϰ��� �ϴ� ȸ�������� ��� �ִ� Member ��ü
     * @throws DataNotFoundException �ش� ȸ���� �������� ���� ��� �߻�
     */
	public void removeMember(Member member) throws DataNotFoundException;
	
    /**
     * �μ��� �־��� memberID�� password�� �ش��ϴ� ȸ�������� Ȯ���Ͽ� 
     * ��ȿ�� ȸ���ΰ� ����(�α��� ���� ����)�� �Ǻ��Ѵ�.<br/>
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
	public Member loginCheck(String memberID, String password);
	
    /**
     * ��� ȸ���� �˻��Ѵ�.
     * 
     * @return �˻��� ��� ȸ�������� ��� �ִ� Member �迭
     */
	public Member[] getAllMembers();
	
}
