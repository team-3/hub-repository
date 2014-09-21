package casestudy.business.domain;

/**
 * �� ������ ��ü�� ��ǰ �Ѱ��� ������ ���� �� �ִ� Basket ��ü�̴�.
 */
public class Basket {
    private int orderNum;
    private String productID;
    private String mallID;
    private String productName;
    private String company;
    private int quantity;
    private int price;
    
    public Basket() {}
    
    /**
     * �ֹ���ȣ�� �ƱԸ�Ʈ�� �޴� ������
     */
    public Basket(int orderNum) {
        this(orderNum, "", 0, 0);
    }
    
    /**
     * �ֹ���ȣ, ��ǰID, ����, ��ǰ������ �ƱԸ�Ʈ�� �޴� ������
     */
    public Basket(int orderNum, String productID, int quantity, int price) {
        this.orderNum = orderNum;
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
    }
    
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
    public void setProductID(String productID) {
        this.productID = productID;
    }
    public void setMallID(String mallID) {
        this.mallID = mallID;
    }    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    
    public int getOrderNum() {
        return orderNum;
    }
    public String getProductID() {
        return productID;
    }
    public String getMallID() {
        return mallID;
    }
    public String getProductName() {
        return productName;
    }
    public String getCompany() {
        return company;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getPrice() {
        return price;
    }

    public int getTotalPrice() {
        int totalPrice = price * quantity;
        return totalPrice;
    }
    
    /**
     * quantity�� ���� ���� ���� �ش�.
     */
    public void incrementQuantity() {
        quantity++;
    }
    /**
     * parameter�� ���� ������ quantity�� ���� ���� ���� �ش�.
     * @param int num
     */
    public void incrementQuantity(int num) {
        quantity = quantity + num;
    }
    
    /**
     * quantity�� ���� ���� ���� �ش�.
     */
    public void decrementQuantity() {
        quantity--;
    }

}
