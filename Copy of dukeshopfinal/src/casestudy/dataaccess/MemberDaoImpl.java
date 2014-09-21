/**
 * ���ϸ� : MemberDaoImpl.java
 * �ۼ��� : 2014. 2. 12.
 * ���ϼ��� : 
 */
package casestudy.dataaccess;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import casestudy.business.domain.Member;
import casestudy.business.service.MemberDao;

/**
 * ȸ�� ���� ������ �׼��� ó���� JDBC API�� Ȱ���Ͽ� ������ Ŭ������ 
 * �����ͺ��̽� ���Ӱ� Member ���̺��� ����ϴ� SQL ���� ������ ���� ���� ó���� �����Ѵ�.
 * 
 * @author �����(kidmania@hotmail.com)
 *
 */
public class MemberDaoImpl implements MemberDao {
	private DataSource dataSource;
	
    /*
     * 1. JNDI API�� �̿��Ͽ� ���̹� ���񽺿� ���ε�(jdbc/dukeshopDB)�� DataSource�� �˻��Ѵ�.
     */
    public MemberDaoImpl() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/dukeshopDB");
        } catch(NamingException ne) {
            System.err.println("A JNDI error occured.");
            ne.printStackTrace(System.err);
            throw new RuntimeException("A JNDI error occurred. " + ne.getMessage());            
        }    	
    }
    
    /*
     * 2. DataSource�� getConnection() �޼ҵ带 ���� Connection�� �����.
     */
    private Connection obtainConnection() throws SQLException {
    	return dataSource.getConnection();
    }
    
	/* (non-Javadoc)
	 * @see casestudy.business.service.MemberDao#insertMember(casestudy.business.domain.Member)
	 * 
	 * ���ڷ� ���� Member ��ü ������ ���� Member ���̺� ���ο� ���ڵ带 �߰�(insert) �Ѵ�.
     * 3. ���� Statement ��ü ��� PreparedStatement ��ü�� Ȱ���Ѵ�.
	 */
	@Override
	public void insertMember(Member member) {
        String query = "INSERT INTO Member (MemberID, Password, Name, Email, Tel, Zipcode, Address, Point, MemberDate) " 
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        System.out.println("MemberDAOImpl insertMember() query: " + query);
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            connection = obtainConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, member.getMemberID());
            stmt.setString(2, member.getPassword());
            stmt.setString(3, member.getName());
            stmt.setString(4, member.getEmail());
            stmt.setString(5, member.getTel());
            stmt.setString(6, member.getZipcode());
            stmt.setString(7, member.getAddress());
            stmt.setInt(8, member.getPoint());
            stmt.setDate(9, new Date(System.currentTimeMillis()));            
            stmt.executeUpdate();

        } catch (SQLException se) {
            System.err.println("MemberDAOImpl insertMember() Error :" + se.getMessage());
            se.printStackTrace(System.err);
            throw new RuntimeException("A database error occurred. " + se.getMessage());
            
        } finally {
            try { 
                if (stmt != null) stmt.close(); 
            } catch (SQLException se) { se.printStackTrace(System.err); }
            try { 
                if (connection != null) connection.close(); 
            } catch (SQLException se) { se.printStackTrace(System.err); }
        }
	}

	/* (non-Javadoc)
	 * @see casestudy.business.service.MemberDao#selectMember(java.lang.String)
	 * 
	 * ���ڷ� ���� memberID�� ���ڵ带 ã��(select) �ش� ������ ���� Member ��ü�� �����Ѵ�.
     * 4. ���� Statement ��ü ��� PreparedStatement ��ü�� Ȱ���Ѵ�.
	 */
	@Override
	public Member selectMember(String memberID) {
        Member member = null;
        
        String query = "SELECT MemberID, Password, Name, Email, Tel, Zipcode, Address, Point, MemberDate"
        		+" FROM Member WHERE MemberID = ?";                
        System.out.println("MemberDAOImpl selectMember() query: " + query);
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            connection = obtainConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, memberID);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                member = new Member(rs.getString("MemberID"), 
                					rs.getString("Password"),
                					rs.getString("Name"),
                					rs.getString("Email"),
                					rs.getString("Tel"),
                					rs.getString("Zipcode"),
                					rs.getString("Address"),
                					rs.getInt("Point"),
                					rs.getDate("MemberDate"));
            }
            
        } catch(SQLException se) {
            System.err.println("MemberDAOImpl selectMember() Error :" + se.getMessage());
            se.printStackTrace(System.err);        	
            throw new RuntimeException("A database error occurred. " + se.getMessage());
            
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException se) { se.printStackTrace(System.err); }
            try { 
                if (stmt != null) stmt.close(); 
            } catch (SQLException se) { se.printStackTrace(System.err); }
            try {
                if (connection != null) connection.close(); 
            } catch (SQLException se) { se.printStackTrace(System.err); }
        }
        
        return member;
	}

	/* (non-Javadoc)
	 * @see casestudy.business.service.MemberDao#updateMember(casestudy.business.domain.Member)
	 * 
     * ���ڷ� ���� Member ��ü�� ������ Member ���̺��� ���ڵ带 ����(update) �Ѵ�.
     * 5. ���� Statement ��ü ��� PreparedStatement ��ü�� Ȱ���Ѵ�.
	 */
	@Override
	public void updateMember(Member member) {
        String query = "UPDATE Member SET Password=?, Name=?, Email=?, Tel=?, Zipcode=?, Address=?" 
                + " WHERE MemberID=?";
        System.out.println("MemberDAOImpl updateMember() query: " + query);
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {            
            connection = obtainConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, member.getPassword());
            stmt.setString(2, member.getName());
            stmt.setString(3, member.getEmail());
            stmt.setString(4, member.getTel());
            stmt.setString(5, member.getZipcode());
            stmt.setString(6, member.getAddress());
            stmt.setString(7, member.getMemberID());
            stmt.executeUpdate();
            
        } catch(SQLException se) {
            System.err.println("MemberDAOImpl updateMember() Error :" + se.getMessage());
            se.printStackTrace(System.err);    
            throw new RuntimeException("A database error occurred. " + se.getMessage());
            
        } finally {
            try { 
                if (stmt != null) stmt.close(); 
            } catch (SQLException se) { se.printStackTrace(System.err); }
            try { 
                if (connection != null) connection.close(); 
            } catch (SQLException se) { se.printStackTrace(System.err); }
        }
	}

	/* (non-Javadoc)
	 * @see casestudy.business.service.MemberDao#deleteMember(casestudy.business.domain.Member)
	 * 
     * ���ڷ� ���� Member ��ü�� ������ ���� Member ���̺��� ���ڵ带 ����(delete) �Ѵ�.
     * 6. ���� Statement ��ü ��� PreparedStatement ��ü�� Ȱ���Ѵ�.
     */
	@Override
	public void deleteMember(Member member) {
        String query = "DELETE FROM Member WHERE MemberID=?";
        System.out.println("MemberDAOImpl deleteMember() query: " + query);
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {            
            connection = obtainConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, member.getMemberID());
            stmt.executeUpdate();
            
        } catch(SQLException se) {
            System.err.println("MemberDAOImpl deleteMember() Error :" + se.getMessage());
            se.printStackTrace(System.err);   
            throw new RuntimeException("A database error occurred. " + se.getMessage());
            
        } finally {
            try { 
                if (stmt != null) stmt.close(); 
            } catch (SQLException se) { se.printStackTrace(System.err); }
            try { 
                if (connection != null) connection.close(); 
            } catch (SQLException se) { se.printStackTrace(System.err); }
        }
	}

	/* (non-Javadoc)
	 * @see casestudy.business.service.MemberDao#checkMember(java.lang.String, java.lang.String)
	 * 
	 * ���ڷ� ���� ���̵�� �н����� ������ �α��� ���� ����(id, password Ȯ��)�� Ȯ���ϰ� 
	 * �ش� ������ ���� Member ��ü�� �����Ѵ�.
     *  (1) ���̵� �������� ���� ��� Member�� check�� ���� Member.INVALID_ID ��,
     *  (2) �н����尡 ���� ���� ��� Member�� check ���� Member.INVALID_PASSWORD ��,
     *  (3) ���̵�� �н����尡 ��� ��ġ�� ��� Member�� check�� ���� Member.VALID_MEMBER �� �����Ͽ�
     *  Member ��ü�� �����Ѵ�.
     *  
     * 7. ���� Statement ��ü ��� PreparedStatement ��ü�� Ȱ���Ѵ�.
	 */
	@Override
	public Member checkMember(String memberID, String password) {
		Member member = new Member(memberID, password);
		
        String query = "SELECT Password, Name, Email, Tel, Zipcode, Address, Point, MemberDate FROM Member WHERE MemberID=?";
        System.out.println("MemberDAOImpl checkMember() query: " + query);
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            connection = obtainConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, memberID);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                String pw = rs.getString("Password");
                if (pw.equals(password)) {
                    member.setName(rs.getString("Name"));
                    member.setEmail(rs.getString("Email"));
                    member.setTel(rs.getString("Tel"));
                    member.setZipcode(rs.getString("Zipcode"));
                    member.setAddress(rs.getString("Address"));
                    member.setPoint(rs.getInt("Point"));
                    member.setMemberDate(rs.getDate("MemberDate"));
                    member.setCheck(Member.VALID_MEMBER);
                } else {
                    member.setCheck(Member.INVALID_PASSWORD);
                }
            } else {
                member.setCheck(Member.INVALID_ID);
            }
            
        } catch(SQLException se) {
            System.err.println("MemberDAOImpl checkMember() Error :" + se.getMessage());
            se.printStackTrace(System.err);
            throw new RuntimeException("A database error occurred. " + se.getMessage());

        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            try { if (stmt != null) stmt.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            try { if (connection != null) connection.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
        }

        return member;
	}

	/* (non-Javadoc)
	 * @see casestudy.business.service.MemberDao#selectAllMembers()
	 * 
     * Member ���̺��� ��� ȸ�� ������ �˻��� �迭�� ��� �����Ѵ�.
     * 8. ���� Statement ��ü ��� PreparedStatement ��ü�� Ȱ���Ѵ�.
	 */
	@Override
	public Member[] selectAllMembers() {
        String query = "SELECT MemberID, Password, Name, Email, Tel, Zipcode, Address, Point, MemberDate FROM Member";                
        System.out.println("MemberDAOImpl selectAllMembers() query: " + query);
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Member> temp = new ArrayList<Member>();
        Member member = null;
        
        try {
            connection = obtainConnection();
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                member = new Member(rs.getString("MemberID"), 
    								rs.getString("Password"),
    								rs.getString("Name"),
    								rs.getString("Email"),
    								rs.getString("Tel"),
    								rs.getString("Zipcode"),
    								rs.getString("Address"),
    								rs.getInt("Point"),
    								rs.getDate("MemberDate"));
                temp.add(member);
            }
            
        } catch(SQLException se) {
            System.err.println("MemberDAOImpl selectAllMembers() Error :" + se.getMessage());
            se.printStackTrace(System.err);  
            throw new RuntimeException("A database error occurred. " + se.getMessage());

        } finally {
            try { if (rs != null) rs.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
            try { if (stmt != null) stmt.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
            try { if (connection != null) connection.close(); } catch(SQLException ex){ ex.printStackTrace(System.err); }
        }

        return temp.toArray(new Member[0]);
	}

	/* (non-Javadoc)
	 * @see casestudy.business.service.MemberDao#memberIDExists(java.lang.String)
	 * 
     * ���ڷ� ���� memberID�� �ش��ϴ� ���� ���ڵ尡 Member ���̺� �����ϴ��� ���θ� Ȯ���Ѵ�.
     * 9. ���� Statement ��ü ��� PreparedStatement ��ü�� Ȱ���Ѵ�.
	 */
	@Override
	public boolean memberIDExists(String memberID) {
		boolean result = false;
		
        String query = "SELECT MemberID FROM Member WHERE MemberID=?";
        System.out.println("MemberDAOImpl memberIDExists() query: " + query);
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            connection = obtainConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, memberID);
            rs = stmt.executeQuery();
            result = rs.next();

        } catch(SQLException se) {
            System.err.println("MemberDAOImpl memberIDExists() Error :" + se.getMessage());
            se.printStackTrace(System.err);  
            throw new RuntimeException("A database error occurred. " + se.getMessage());

        } finally {
            try { if (rs != null) rs.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
            try { if (stmt != null) stmt.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
            try { if (connection != null) connection.close(); } catch(SQLException ex){ ex.printStackTrace(System.err); }
        }
        
        return result;
	}
}
