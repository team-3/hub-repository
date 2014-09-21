/**
 * ���ϸ� : ProductDaoImpl.java
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

import casestudy.business.domain.Product;
import casestudy.business.service.ProductDao;

/**
 * ��ǰ ���� ������ �׼��� ó���� JDBC API�� Ȱ���Ͽ� ������ Ŭ������ 
 * �����ͺ��̽� ���Ӱ� Product ���̺��� ����ϴ� SQL ���� ������ ���� ���� ó���� �����Ѵ�.
 * 
 * @author �����(kidmania@hotmail.com)
 *
 */
public class ProductDaoImpl implements ProductDao {
	private DataSource dataSource;
	
    /*
     * 1. JNDI API�� �̿��Ͽ� ���̹� ���񽺿� ���ε�(jdbc/dukeshopDB)�� DataSource�� �˻��Ѵ�.
     */
    public ProductDaoImpl() {
        try {
            Context context = new InitialContext();
            context = (Context) context.lookup("java:comp/env");
            dataSource = (DataSource) context.lookup("jdbc/dukeshopDB");
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
	 * @see casestudy.business.service.ProductDao#selectProduct(java.lang.String)
	 * 
	 * ���ڷ� ���� productID�� ���ڵ带 ã��(select) �ش� ������ ���� Product ��ü�� �����Ѵ�.
     * 3. ���� Statement ��ü ��� PreparedStatement ��ü�� Ȱ���Ѵ�.
	 */
	@Override
	public Product selectProduct(String productID) {
        Product product = null;

        String query = "SELECT ProductID, MallID, ProductName, Company, Price1, Price2, Installment, Keyword, Detail, ProductDate, PhotoDir " +
                "FROM Product WHERE ProductID=?";
        System.out.println("ProductDAOImpl selectProduct() query: " + query);
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            connection = obtainConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, productID);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                product = new Product(rs.getString("ProductID"),
  					  				  rs.getString("MallID"),
  					  				  rs.getString("ProductName"),
  					  				  rs.getString("Company"),
  					  				  rs.getInt("Price1"),
  					  				  rs.getInt("Price2"),
  					  				  rs.getString("Installment"),
  					  				  rs.getString("Keyword"),
  					  				  rs.getString("Detail"),
  					  				  rs.getDate("ProductDate"),
  					  				  rs.getString("PhotoDir"));
            }
            
        } catch(SQLException se) {
            System.err.println("ProductDAOImpl selectProduct() Error :" + se.getMessage());
            se.printStackTrace(System.err);  
            throw new RuntimeException("A database error occured. " + se.getMessage());

        } finally {
            try { if (rs != null) rs.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
            try { if (stmt != null) stmt.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
            try { if (connection != null) connection.close(); } catch(SQLException e) { e.printStackTrace(System.err); }
        }

        return product;
	}

	/* (non-Javadoc)
	 * @see casestudy.business.service.ProductDao#selectAllProducts()
	 * 
     * Product ���̺��� ��� ��ǰ ������ �˻��� �迭�� ��� �����Ѵ�.
     * 4. ���� Statement ��ü ��� PreparedStatement ��ü�� Ȱ���Ѵ�.
	 */
	@Override
	public Product[] selectAllProducts() {
        String query = "SELECT ProductID, MallID, ProductName, Company, Price1, Price2, Installment, Keyword, Detail, ProductDate, PhotoDir FROM Product";
        System.out.println("ProductDAOImpl selectAllProducts() query: " + query);
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Product> temp = new ArrayList<Product>();
        Product product = null;
        
        try {
            connection = obtainConnection();
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                product = new Product(rs.getString("ProductID"),
                					  rs.getString("MallID"),
                					  rs.getString("ProductName"),
                					  rs.getString("Company"),
                					  rs.getInt("Price1"),
                					  rs.getInt("Price2"),
                					  rs.getString("Installment"),
                					  rs.getString("Keyword"),
                					  rs.getString("Detail"),
                					  rs.getDate("ProductDate"),
                					  rs.getString("PhotoDir"));
                temp.add(product);
            }
            
        } catch(SQLException se) {
            System.err.println("ProductDAOImpl selectAllProducts() Error :" + se.getMessage());
            se.printStackTrace(System.err);  
            throw new RuntimeException("A database error occurred. " + se.getMessage());

        } finally {
            try { if (rs != null) rs.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
            try { if (stmt != null) stmt.close(); } catch(SQLException ex) { ex.printStackTrace(System.err); }
            try { if (connection != null) connection.close(); } catch(SQLException ex){ ex.printStackTrace(System.err); }
        }

        return temp.toArray(new Product[0]);
	}

	/* (non-Javadoc)
	 * @see casestudy.business.service.ProductDao#productIDExists(java.lang.String)
	 * 
     * ���ڷ� ���� productID�� �ش��ϴ� ���� ���ڵ尡 Product ���̺� �����ϴ��� ���θ� Ȯ���Ѵ�.
     * 5. ���� Statement ��ü ��� PreparedStatement ��ü�� Ȱ���Ѵ�.
	 */
	@Override
	public boolean productIDExists(String productID) {
		boolean result = false;
		
        String query = "SELECT ProductID FROM Product WHERE ProductID=?";                
        System.out.println("ProductDAOImpl productIDExists() query: " + query);
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            connection = obtainConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, productID);
            rs = stmt.executeQuery();
            result = rs.next();

        } catch(SQLException se) {
            System.err.println("ProductDAOImpl productIDExists() Error :" + se.getMessage());
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
