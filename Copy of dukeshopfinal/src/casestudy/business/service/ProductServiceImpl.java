/**
 * ���ϸ� : ProductServiceImpl.java
 * �ۼ��� : 2014. 2. 13.
 * ���ϼ��� : 
 */
package casestudy.business.service;

import casestudy.business.domain.Product;

/**
 * ��ǰ ���� ����Ͻ� ������ ������ ���� Ŭ������ 
 * ������ �׼��� ó���� ProductDao ��ü���� �����Ͽ� �����Ѵ�.
 * 
 * @author �����(kidmania@hotmail.com)
 *
 */
public class ProductServiceImpl implements ProductService {
	
    private ProductDao productDataAccess;

    /* 
     * ProductDaoImpl ��ü�� �����Ͽ� productDataAccess �ν��Ͻ� ���� �ʱ�ȭ  
     */
    public ProductServiceImpl() {
    	productDataAccess = new casestudy.dataaccess.ProductDaoImpl();
    }    
    
	/* (non-Javadoc)
	 * @see casestudy.business.service.ProductService#findProduct(java.lang.String)
	 * 
	 * ProductDao ��ü�� ����� ���ڷ� ���� productID�� �ش��ϴ� ��ǰ ������ ã�Ƽ� �����Ѵ�.
     *  1. productID�� �ش��ϴ� ��ǰ�� �������� ���� ��� DataNotFoundException�� �߻���Ų��.
	 */
	@Override
	public Product findProduct(String productID) throws DataNotFoundException {
		if (!productDataAccess.productIDExists(productID)) {
			throw new DataNotFoundException("�������� �ʴ� ��ǰ�Դϴ�.(" + productID + ")");
		}
		
        return productDataAccess.selectProduct(productID);
	}

	/* (non-Javadoc)
	 * @see casestudy.business.service.ProductService#getAllProducts()
	 * 
	 * ProductDao ��ü�� ����� ��� ��ǰ ������ ���ؼ� �����Ѵ�.
	 */
	@Override
	public Product[] getAllProducts() {
        return productDataAccess.selectAllProducts();
	}

}
