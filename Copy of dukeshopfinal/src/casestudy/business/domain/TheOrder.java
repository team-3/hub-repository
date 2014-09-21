package casestudy.business.domain;

/**
 * This domain object represents a TheOrder.
 * The data attributes are all package-private to allow access to them
 * in the {@link BasketPurchaserServiceImpl} class.
 */
public class TheOrder {
	private int orderNum;
	private String productName;
	private int	quantity;
	private int	price;
	private String name;
	private String address;
	private String tel;
	private String email;
	private String paytype;
    
    /** Creates a new instance of TheOrder */
    public TheOrder() {};

    /**
     * This is the full constructor.
     */
    public TheOrder(int orderNum, String productName,int quantity,int price, String name, String address, String tel) {
        this.orderNum = orderNum;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.address = address;
        this.tel = tel;
    }

    /**
     * �ֹ���ȣ
     * @param int orderNum
     */
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
     /**
     * ��ǰ��
     * @param String productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**
     * �ֹ�����
     * @param int quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * �ֹ���ǰ����
     * @param int price
     */
    public void setPrice(int price) {
        this.price = price;
    }
    /**
     * �ֹ����̸�
     * @param String name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * �ֹ����ּ�
     * @param String address
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * �ֹ��ڿ���ó
     * @param String tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }
    /**
     * �̸����ּ�
     * @param String email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * ����Ÿ��(�¶����Ա�/ī��)
     * @param String paytype
     */
    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }
    
    public int getOrderNum() {return orderNum;}
    public String getProductName() {return productName;}
    public int getQuantity() {return quantity;}
    public int getPrice() {return price;}
    public String getName() {return name;}
    public String getAddress() {return address;}
    public String getTel() {return tel;}
    public String getEmail() { return email; }
    public String getPaytype() { return paytype; }
    
    @Override
    public String toString() {
        return  "orderNum = "+ orderNum + "\n"+
                "productName = "+productName+ "\n"+
                "quantity = "+quantity+ "\n"+
                "price = "+price+ "\n"+
                "name = "+name+ "\n"+
                "address = "+address+ "\n"+
                "tel = "+tel+ "\n"+
                "paytype = "+paytype+ "\n"+
                "email = "+email;
    }
    
}
