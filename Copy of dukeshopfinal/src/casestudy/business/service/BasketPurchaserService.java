package casestudy.business.service;

import java.util.Collection;

import casestudy.business.domain.Basket;
import casestudy.business.domain.Purchaser;
import casestudy.business.domain.TheOrder;

/**
 * ��ٱ��Ͽ� ���� ó���� ������ ���񽺸� ����� ��ü�� �԰��� ������ �������̽�.
 */
public interface BasketPurchaserService {

	/**
	 * ���ڷ� ���� Basket, Purchaser ��ü�� memberID ������ ������ ���� ó���� �����Ѵ�.
	 * @param Basket basket,Purchaser purchaser,String memberID
	 */
	public void insertBasketPurchaser(Basket basket, Purchaser purchaser, String memberID);

	/**
	 * ���ڷ� ���� memberID�� ���� ��ٱ��� ������ �����Ѵ�.
	 * @param String memberID
	 */
	public void deleteBasket(String memberID);

	/**
	 * ���ڷ� ���� memberID�� ���� ���� ���� �ֹ�����(TheOrder) ����Ʈ�� ���Ѵ�.
	 * @param String memID
	 * @return Collection
	 */
	public Collection<TheOrder> listBasketPurchaser(String memberID);

	/**
	 * ���ο� �ֹ���ȣ�� ���Ѵ�.
	 * @param
	 * @return int
	 */
	public int orderMaxNo();

}