/**
 * ���ϸ� : MemberServiceImpl.java
 * �ۼ��� : 2014. 2. 13.
 * ���ϼ��� : 
 */
package casestudy.business.service;

import casestudy.business.domain.Member;

/**
 * ȸ�� ���� ����Ͻ� ������ ������ ���� Ŭ������ 
 * ������ �׼��� ó���� MemberDao ��ü���� �����Ͽ� �����Ѵ�.
 * 
 * @author �����(kidmania@hotmail.com)
 *
 */
public class MemberServiceImpl implements MemberService {

    private MemberDao memberDataAccess;
    
    /* 
     * MemberDaoImpl ��ü�� �����Ͽ� memberDataAccess �ν��Ͻ� ���� �ʱ�ȭ  
     */
    public MemberServiceImpl() {
    	memberDataAccess = new casestudy.dataaccess.MemberDaoImpl();
    }
    
	/* (non-Javadoc)
	 * @see casestudy.business.service.MemberService#registerMember(casestudy.business.domain.Member)
	 * 
	 * MemberDao ��ü�� ����� ���ڷ� ���� ȸ�� ������ ����Ѵ�.
     *  1.1. �ߺ��� memberID�� ���� ���� ȸ���� ������ ��� ������� �ʰ� DataDuplicatedException�� �߻���Ų��.
     *      (MemberDao�� memberIDExists() �޼ҵ� Ȱ��)
     *  1.2. �޼ҵ� ����ο� DataDuplicatedException �߻� �������� ǥ���Ѵ�.(throws DataDuplicatedException)
	 */
	@Override
	public void registerMember(Member member) throws DataDuplicatedException {
		if (memberDataAccess.memberIDExists(member.getMemberID())) {
			throw new DataDuplicatedException("������ memberID�� ���� ȸ���� �ֽ��ϴ�.(" + member.getMemberID() + ")");
		}
		
        memberDataAccess.insertMember(member);
	}

	/* (non-Javadoc)
	 * @see casestudy.business.service.MemberService#findMember(java.lang.String)
	 * 
	 * MemberDao ��ü�� ����� ���ڷ� ���� memberID�� �ش��ϴ� ȸ�� ������ ã�Ƽ� �����Ѵ�.
     *  2.1. memberID�� �ش��ϴ� ȸ���� �������� ���� ��� DataNotFoundException�� �߻���Ų��.
     *      (MemberDao�� memberIDExists() �޼ҵ� Ȱ��)
     *  2.2. �޼ҵ� ����ο� DataNotFoundException �߻� �������� ǥ���Ѵ�.(throws DataNotFoundException) 
	 */
	@Override
	public Member findMember(String memberID) throws DataNotFoundException {
		if (!memberDataAccess.memberIDExists(memberID)) {
			throw new DataNotFoundException("�������� �ʴ� ȸ���Դϴ�.(" + memberID + ")");
		}
		
        return memberDataAccess.selectMember(memberID);
	}

	/* (non-Javadoc)
	 * @see casestudy.business.service.MemberService#updateMember(casestudy.business.domain.Member)
	 * 
	 * MemberDao ��ü�� ����� ���ڷ� ���� ȸ�� ������ �����Ѵ�.
	 *  3. �ش��ϴ� ȸ���� �������� ���� ��� DataNotFoundException�� �߻���Ų��.
	 */
	@Override
	public void updateMember(Member member) throws DataNotFoundException {
		if (!memberDataAccess.memberIDExists(member.getMemberID())) {
			throw new DataNotFoundException("�������� �ʴ� ȸ���Դϴ�.(" + member.getMemberID() + ")");
		}
		
        memberDataAccess.updateMember(member);
	}

	/* (non-Javadoc)
	 * @see casestudy.business.service.MemberService#removeMember(casestudy.business.domain.Member)
	 * 
	 * MemberDao ��ü�� ����� ���ڷ� ���� ȸ�� ������ �����Ѵ�.
	 *  4. �ش��ϴ� ȸ���� �������� ���� ��� DataNotFoundException�� �߻���Ų��.
	 */
	@Override
	public void removeMember(Member member) throws DataNotFoundException {
		if (!memberDataAccess.memberIDExists(member.getMemberID())) {
			throw new DataNotFoundException("�������� �ʴ� ȸ���Դϴ�.(" + member.getMemberID() + ")");
		}
		
        memberDataAccess.deleteMember(member);
	}

	/* (non-Javadoc)
	 * @see casestudy.business.service.MemberService#loginCheck(java.lang.String, java.lang.String)
	 * 
	 * MemberDao ��ü�� ����� ���ڷ� ���� memberID, password�� 
	 * �α��� ���� ���θ� Ȯ���ϰ�, �ش� ȸ�� ������ �����Ѵ�.
	 */
	@Override
	public Member loginCheck(String memberID, String password) {
        return memberDataAccess.checkMember(memberID, password);
	}

	/* (non-Javadoc)
	 * @see casestudy.business.service.MemberService#getAllMembers()
	 * 
	 * MemberDao ��ü�� ����� ��� ȸ�� ������ ���ؼ� �����Ѵ�.
	 */
	@Override
	public Member[] getAllMembers() {
		return memberDataAccess.selectAllMembers();
	}

}
