package casestudy.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import casestudy.business.domain.Basket;
import casestudy.business.domain.BasketCart;
import casestudy.business.domain.Member;
import casestudy.business.domain.Product;
import casestudy.business.domain.Purchaser;
import casestudy.business.domain.TheOrder;
import casestudy.business.service.BasketPurchaserService;
import casestudy.business.service.BasketPurchaserServiceImpl;
import casestudy.business.service.DataNotFoundException;
import casestudy.business.service.MemberService;
import casestudy.business.service.MemberServiceImpl;
import casestudy.business.service.ProductService;
import casestudy.business.service.ProductServiceImpl;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /* 
     * HTTP GET�� POST ����� ��û�� ��� ó���Ѵ�.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //request.setCharacterEncoding("UTF-8");
        
        try {
			// action ��û�Ķ���� ���� Ȯ���Ѵ�.
			String action = request.getParameter("action");
			
			/* action ��(select/select-all)�� ���� ������ �޼ҵ带 �����Ͽ� ȣ���Ѵ�.
			 * (select �̸� selectProduct(), select-all �̸� selectAllProduct() �޼ҵ� ȣ��) */
			if (action.equals("select")) {
				selectProduct(request, response);
			} else if (action.equals("select-all")) {
				selectAllProducts(request, response);
			} else if (action.equals("putOne-basket")) {
                putOneBasket(request,response);
            } else if (action.equals("emptyOne-basket")) {
                emptyOneBasket(request,response);
            } else if (action.equals("emptyAll-basket")) {
                emptyAllBasket(request,response);
            } else if (action.equals("select-memberPurchaser")) {
                selectMember(request,response);
            } else if (action.equals("insert-basketPurchaser")) {
                insertBasketPurchaser(request,response);
            }
			
        } catch (DataNotFoundException ex) {
        	throw new ServletException(ex);
        }
    }
    
    /* 
     * �ϳ��� ��ǰ �������� �����ִ� ��û�� ó���Ѵ�.
     */
    private void selectProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DataNotFoundException {
        
        // ��û�Ķ���� ���� Ȯ���ϰ� ����
        String productID = request.getParameter("productID");
        if ((productID == null) || (productID.length() == 0)) {
            productID = "sams110";
        }
        
        // ���� ��ü�� ���� ������ ����Ͻ� ������ ����
        ProductService productService = new ProductServiceImpl();
        Product product = productService.findProduct(productID);

        // request scope �Ӽ��� ��ȸ�� product�� �����ϰ�
        request.setAttribute("selectedProduct", product);
        
        // RequestDispatcher ��ü�� ���� �� ������(selectProduct.jsp)�� ��û�� �����Ѵ�.
        RequestDispatcher dispatcher = request.getRequestDispatcher("selectProduct.jsp");
        dispatcher.forward(request, response);
    } 
    
    /* 
     * ��� ��ǰ ����Ʈ�� �����ִ� ��û�� ó���Ѵ�.
     */
    private void selectAllProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductService productService = new ProductServiceImpl();
        Product[] productList = productService.getAllProducts();

        // request scope �Ӽ��� ��ȸ�� productList�� �����ϰ�
        request.setAttribute("productList", productList);
        
        // RequestDispatcher ��ü�� ���� �� ������(selectAllProducts.jsp)�� ��û�� �����Ѵ�.
        RequestDispatcher dispatcher = request.getRequestDispatcher("selectAllProducts.jsp");
        dispatcher.forward(request, response);
    }
    
    /** �������� ����ϱ�    */
    private void insertBasketPurchaser(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginMember") == null) {
            List<String> errorMsgs = new ArrayList<String>();
            errorMsgs.add("�α����� �����ϼ���!!");
            request.setAttribute("errorMsgs", errorMsgs);

            RequestDispatcher dispatcher = request.getRequestDispatcher("userError.jsp");
            dispatcher.forward(request,response);
            return;
        }

        BasketPurchaserService basketPurchaserService = new BasketPurchaserServiceImpl();

        String memberID =  ((Member) session.getAttribute("loginMember")).getMemberID();
        BasketCart basketCart = (BasketCart) session.getAttribute("basketCart");
        Collection<Basket> collection = basketCart.getItems();
        Iterator<Basket> iterator = collection.iterator();
        Basket basket = null;
        
        while (iterator.hasNext()) {
            basket = iterator.next();
            Purchaser purchaser = new Purchaser();

            int orderNo = basketPurchaserService.orderMaxNo();
            String cardNumber = request.getParameter("cardnumber");
            String cardType = request.getParameter("cardtype");
            
            if (cardNumber.equals("") | cardType.equals("")) {
                cardNumber = "N";
                cardType = "N";
            }
            
            basket.setOrderNum(orderNo);
            
            purchaser.setOrderNum(orderNo);
            purchaser.setMemberID(memberID);
            purchaser.setAddress(request.getParameter("address"));
            purchaser.setName(request.getParameter("name"));
            purchaser.setEmail(request.getParameter("email"));
            purchaser.setTel(request.getParameter("tel"));
            purchaser.setPlace(request.getParameter("place"));
            purchaser.setCardNumber(cardNumber);
            purchaser.setCardType(cardType);
            purchaser.setPayStatus("N");
            purchaser.setPayType(request.getParameter("paytype"));
            purchaser.setAmount(basket.getTotalPrice());
            
            basketPurchaserService.insertBasketPurchaser(basket, purchaser, memberID);
        }
        
        Collection<TheOrder> orderCollection = basketPurchaserService.listBasketPurchaser(memberID);
        request.setAttribute("theOrderCollection", orderCollection);

        //���� īƮ �ʱ�ȭ
        basketCart.clear();
        basketPurchaserService.deleteBasket(memberID);
        
        RequestDispatcher dispatcher=request.getRequestDispatcher("orderReport.jsp");
        dispatcher.forward(request,response);
    }
    
    /** �������� ����ϱ����� ȸ������ ��ȸ�ϱ�    */
    protected void selectMember(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException, DataNotFoundException {

        HttpSession session = request.getSession(false);
        /* �α����� ���°� �ƴ� ���(loginMember �Ӽ��� ���� ���) �ٸ� �������� �̵�  */
        if (session == null || session.getAttribute("loginMember") == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }        
        // ���� �Ӽ��� Member ��ü���� memberID�� ���س�
        String memberID = ((Member) session.getAttribute("loginMember")).getMemberID();
        
        MemberService service = new MemberServiceImpl();
        Member selectedMember = service.findMember(memberID);

        request.setAttribute("member", selectedMember);
        RequestDispatcher dispatcher = request.getRequestDispatcher("purchaseProduct.jsp");
        dispatcher.forward(request,response);
    }
    
    /** Basket(��ٱ���)���� ��ǰ ��θ� ����    */
    protected void emptyAllBasket(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        
        if (session != null) {
            //����īƮ clear
            BasketCart basketCart = (BasketCart) session.getAttribute("basketCart");
            basketCart.clear();
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("basketProduct.jsp");
        dispatcher.forward(request,response);
    }
    
    /** Basket(��ٱ���)���� ��ǰ �ϳ��� ����     */
    protected void emptyOneBasket(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        
        if (session != null) {
            BasketCart basketCart = (BasketCart) session.getAttribute("basketCart");
            String productID = request.getParameter("productID");
            basketCart.remove(productID);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("basketProduct.jsp");
        dispatcher.forward(request,response);
    }
    
    /** Basket(��ٱ���)�� ������ ��ǰ�� ���    */
    protected void putOneBasket(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException, DataNotFoundException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginMember") == null) {
            List<String> errorMsgs = new ArrayList<String>();
            errorMsgs.add("�α����� �����ϼ���!!");
            request.setAttribute("errorMsgs", errorMsgs);

            RequestDispatcher dispatcher = request.getRequestDispatcher("userError.jsp");
            dispatcher.forward(request,response);
            return;
        }

        String productID = request.getParameter("productID");
        String quantity = request.getParameter("quantity");
        int iQuantity = Integer.parseInt(quantity);
        
        BasketCart basketCart = (BasketCart) session.getAttribute("basketCart");
        Basket basket = listProduct(request, response, productID);
        
        if (basketCart == null) {
            basketCart = new BasketCart();
        }

        basketCart.add(productID, basket,iQuantity);
        
        session.setAttribute("basketCart", basketCart);
        
        RequestDispatcher dispatcher=request.getRequestDispatcher("basketProduct.jsp");
        dispatcher.forward(request,response);
    }
    
    /** ��ǰ���� parameter�� �Է� �޾� �� ��ǰ�� ��ٱ��� ������ ��������    */
    private Basket listProduct(HttpServletRequest request, HttpServletResponse response, String productID) 
    		throws DataNotFoundException {

        ProductService productService = new ProductServiceImpl();
        
        Product product = (Product) productService.findProduct(productID);
        
        String quantity = request.getParameter("quantity");
        System.out.println("quantity: " + quantity);
        Basket basket = new Basket();
        basket.setCompany(product.getCompany());
        basket.setMallID(product.getMallID());
        
        basket.setOrderNum(1);
        basket.setPrice(product.getPrice2());
        basket.setProductID(product.getProductID());
        basket.setProductName(product.getProductName());
        basket.setQuantity(Integer.parseInt(quantity));
        
        return basket;
    }    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
