package casestudy.business.service;

import java.util.Collection;

import casestudy.business.domain.Basket;
import casestudy.business.domain.Purchaser;
import casestudy.business.domain.TheOrder;
import casestudy.dataaccess.BasketPurchaserDaoImpl;

/**
 * ��ٱ��Ͽ� ���� ó�� ���� ����Ͻ� ������ ������ ���� Ŭ������ 
 * ������ �׼��� ó���� BasketPurchaserDao ��ü���� �����Ͽ� �����Ѵ�.
 */
public class BasketPurchaserServiceImpl implements BasketPurchaserService {
    private BasketPurchaserDao basketPurchaserDao;

    public BasketPurchaserServiceImpl() {
        basketPurchaserDao = new BasketPurchaserDaoImpl();
    }
        
    /* (non-Javadoc)
	 * @see casestudy.business.service.BasketPurchaserService#insertBasketPurchaser(casestudy.business.domain.Basket, casestudy.business.domain.Purchaser, java.lang.String)
	 */
    @Override
	public void insertBasketPurchaser(Basket basket, Purchaser purchaser, String memberID) {
        basketPurchaserDao.insertBasketPurchaser(basket, purchaser, memberID);
    }
    
    /* (non-Javadoc)
	 * @see casestudy.business.service.BasketPurchaserService#deleteBasket(java.lang.String)
	 */
    @Override
	public void deleteBasket(String memberID) {
        basketPurchaserDao.deleteBasket(memberID);
    }

    /* (non-Javadoc)
	 * @see casestudy.business.service.BasketPurchaserService#listBasketPurchaser(java.lang.String)
	 */
    @Override
	public Collection<TheOrder> listBasketPurchaser(String memberID) {
        Collection<TheOrder> coll = basketPurchaserDao.listBasketPurchaser(memberID);
        return coll;
    }
      
    /* (non-Javadoc)
	 * @see casestudy.business.service.BasketPurchaserService#orderMaxNo()
	 */
    @Override
	public int orderMaxNo() {
        int maxNo = basketPurchaserDao.orderMaxNo();
        return maxNo;
    }    
}
