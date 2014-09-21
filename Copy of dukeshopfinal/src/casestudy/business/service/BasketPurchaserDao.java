package casestudy.business.service;

import java.util.Collection;

import casestudy.business.domain.Basket;
import casestudy.business.domain.Purchaser;
import casestudy.business.domain.TheOrder;

/**
 * Basket(����īƮ)�� Purchaser(����) ���� ������ �׼��� ó���� 
 * ����� ��ü�� �԰��� ������ �������̽�.
 */
public interface BasketPurchaserDao {

	/**
	 * Basket, Purchaser ��ü�� memberID�� ���ڷ� �޾� Basket ���̺� ������ ��Ͻ�Ų��.
	 * @param Basket basket,Purchaser purchaser,String memberID
	 */
	public void insertBasketPurchaser(Basket basket, Purchaser purchaser, String memberID);

	/**
	 * �ֹ���ȣ�� ���� �߰��ϱ� ���� �ֹ���ȣ �� ���� ū ���� ��ȸ�Ͽ� 1 ������Ų ���� ��ȯ�Ѵ�.
	 * @param
	 * @return int
	 */
	public int orderMaxNo();

	/**
	 * ���� �Ϸ� �Ŀ� memberID�� argument�� �޾� Basket ���̺��� ������ �����Ѵ�.
	 * @param String memberID
	 */
	public void deleteBasket(String memberID);

	/**
	 * memberID�� argument�� �޾� �ϳ��� �ֹ������� ��Ÿ���� TheOrder ��ü�� �����ϰ�
	 * �ϼ��� TheOrder ��ü���� Collection�� ��� �����Ѵ�.
	 * @param String memID
	 * @return Collection
	 */
	public Collection<TheOrder> listBasketPurchaser(String memberID);

}