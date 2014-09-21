package casestudy.business.domain;

import java.util.*;

/**
 * �� ������ ��ü�� ��ǰ �Ѱ��� ������ ��� Basket ��ü�� ������ ���� �� �ִ� ��ü�̴�.
 */
public class BasketCart {
    private HashMap<String, Basket> items = null;
    private int numberOfItems = 0;

    /** Creates a new instance of BasketCart */
    public BasketCart() {
        items = new HashMap<String, Basket>();
    }
    
    /**
     * BasketCart�� HashMap�� basket��ü�� ���� �ش�.
     * ProductId�� Ű�� �Ͽ� ���� ������ Ȯ���ϰ�
     * �����ϸ� quantity�� ���� ���� ��Ų��.
     * �������� ������ basket��ü�� �����Ѵ�.
     *
     * @param String productID,Basket basket
     */
    public void add(String productID, Basket basket) {
        if (items.containsKey(productID)) {
            //�����ϸ� ������ ������Ų��
            basket = items.get(productID);
            basket.incrementQuantity();
        } else {
            //�������� ������ basket��ü�� �����Ѵ�.
            items.put(productID, basket);
            numberOfItems++;
        }
    }
    
    /**
     * BasketCart�� HashMap�� basket��ü�� ���� �ش�.
     * ProductID�� Ű�� �Ͽ� ���縦 ������ Ȯ���ϰ�
     * ������ �� quantity�� ���� parameter�� �޾� ���� ��Ų��.
     *
     * @param String productID,Basket basket,int quantity
     */
    public void add(String productID, Basket basket,int quantity) {
        if (items.containsKey(productID)) {
            basket = items.get(productID);
            basket.incrementQuantity(quantity);
        } else {
            items.put(productID, basket);
            numberOfItems++;
        }        
    }
        
    /**
     * BasketCart�� HashMap�� basket��ü�� �������ش�.
     * ProductId�� Ű�� �Ͽ� ���� ������ Ȯ���ϰ�
     * �����ϸ� HashMap���� ��� �����Ѵ�.
     *
     * @param String productID
     */
    public void removeAll(String productID) {
        if (items.containsKey(productID)) {
            items.remove(productID);
            numberOfItems--;
        }
    }
    
    /**
     * BasketCart�� HashMap�� basket��ü�� �������ش�.
     * ProductID�� Ű�� �Ͽ� ���� ������ Ȯ���ϰ�
     * �����Ҷ� quantity�� ���� ���� ��Ų��.
     *
     * @param String productID
     */
    public void remove(String productID) {
        if (items.containsKey(productID)) {
            Basket basket = items.get(productID);
            //System.out.println("������ :" + basket.getQuantity() + " " + basket.getTotalPrice());
            basket.decrementQuantity();
            //System.out.println("������ :" + basket.getQuantity() + " " + basket.getTotalPrice());
            
            if (basket.getQuantity() <= 0) {
                items.remove(productID);
                numberOfItems--;
            }
        }
    }

     /**
     * HashMap�� ��� Values�� ��ȯ�Ѵ�
     */
    public Collection<Basket> getItems() {
        return items.values();
    }
    
    /**
     * HashMap�� ProductID key������ ��ȯ�Ѵ�.
     * @return String[] keySet
     */
    public String[] keySet() {
        Set<String> set = items.keySet();
        String[] keys = set.toArray(new String[0]);
        
        return keys;
    }
    
    /**
     * HashMap�� ���� ������ �����Ѵ�.
     */
    public int getNumberOfItems() {
        return numberOfItems;
    }
    
    public int getTotalPrice() {
        int totalPrice = 0;
        Collection<Basket> collection = items.values();
        Iterator<Basket> iterator = collection.iterator();
        Basket basket;
        while (iterator.hasNext()) {
            basket = iterator.next();
            totalPrice = totalPrice + basket.getTotalPrice();
        }
        return totalPrice;
    }
    
    /**
     * HashMap�� ��� ������ �����Ѵ�.
     */
    public void clear() {
        items.clear();
        numberOfItems = 0;
    }  
}
