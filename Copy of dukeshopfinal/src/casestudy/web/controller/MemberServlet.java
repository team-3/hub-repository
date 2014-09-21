package casestudy.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import casestudy.business.domain.Member;
import casestudy.business.service.DataDuplicatedException;
import casestudy.business.service.DataNotFoundException;
import casestudy.business.service.MemberService;
import casestudy.business.service.MemberServiceImpl;

/**
 * Servlet implementation class MemberServlet
 */
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /* 
     * HTTP GET�� POST ����� ��û�� ��� ó���Ѵ�.
     * ��û�Ķ���� ���� Ȯ���Ͽ� ������ ������� ��û�� ó���Ѵ�.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //request.setCharacterEncoding("UTF-8");
        
        try {
	        // action ��û�Ķ���� ���� Ȯ���Ѵ�.
	        String action = request.getParameter("action");
	
	        // action ��(select/register/update/login/logout)�� ���� ������ �޼ҵ带 �����Ͽ� ȣ���Ѵ�.
	        if (action.equals("select")) {
	        	selectMember(request, response);
	        } else if (action.equals("register")) {
	        	registerMember(request, response);
	        } else if (action.equals("update")) {
	        	updateMember(request, response);           
	        } else if (action.equals("login")) {
	            login(request, response);
	        } else if (action.equals("logout")) {
	            logout(request, response);
	        }
        } catch (Exception ex) {
        	throw new ServletException(ex);
        }
    }
    
    /* 
     * �� ���� ȸ�� �������� �����ִ� ��û�� ó���Ѵ�.
     */
    private void selectMember(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DataNotFoundException {
    	
        // 3. session scope �Ӽ����� ȸ�� ������ ã�� memberID�� Ȯ���Ѵ�.
        HttpSession session = request.getSession(false);        
        if (session == null) {
        	response.sendError(HttpServletResponse.SC_FORBIDDEN, "�α����� �ʿ��մϴ�.");
        	return;
        }
        Member member = (Member) session.getAttribute("loginMember");
        if (member == null) {
        	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "�α����� �ʿ��մϴ�.");
        	return;
        }
        String memberID = member.getMemberID();
        
        // ����Ͻ� ������ ������ MemberService ��ü�� �����Ͽ�
        MemberService memberService = new MemberServiceImpl();
        
        // memberID�� ȸ���� �˻��Ѵ�.(MemberService�� findMember() ���)
        Member selectedMember = memberService.findMember(memberID);

        // request scope �Ӽ��� ��ȸ�� member�� �����ϰ�
        request.setAttribute("selectedMember", selectedMember);
        
        // RequestDispatcher ��ü�� ���� �� ������(updateMember.jsp)�� ��û�� �����Ѵ�.
        RequestDispatcher dispatcher = request.getRequestDispatcher("updateMember.jsp");
        dispatcher.forward(request, response);
    }
    
    /* 
     * ���ο� ȸ���� ����ϴ� ��û�� ó���Ѵ�.
     */
    private void registerMember(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DataDuplicatedException {
    	
        // ��û �Ķ���͸� ���� HTML �� �����͸� ����.
        String memberID = request.getParameter("memberID");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String zipcode = request.getParameter("zipcode");
        String address = request.getParameter("address");
        int point = 0;
        
        // �� �������� ��ȿ���� �����ϴ� ó���� �Ѵ�.
        // ���� �޽������� ������ ����Ʈ
        List<String> errorMsgs = new ArrayList<String>();

        // �� �����Ͱ� ��ȿ�� �� ����
        if ((memberID == null) || (memberID.length() == 0)) {
            errorMsgs.add("ȸ�����̵� �Է����ּ���.");
        }
        if ((password == null) || (password.length() == 0)) {
            errorMsgs.add("�н����带 �Է����ּ���.");
        }        
        if ((name == null) || (name.length() == 0)) {
            errorMsgs.add("�̸��� �Է����ּ���.");
        }        
        if ((email == null) || (email.length() == 0)) {
            errorMsgs.add("�̸����ּҸ� �Է����ּ���.");
        }
                
        // ��ȿ���� ���� �����Ͱ� ������ 
        if (!errorMsgs.isEmpty()) {
        	// ���� ������ request scope �Ӽ��� �����ϰ�
            request.setAttribute("errorMsgs", errorMsgs);
            // ���� ������ ��(userError.jsp)�� ��û�� �����Ѵ�.
            RequestDispatcher dispatcher = request.getRequestDispatcher("userError.jsp");
            dispatcher.forward(request,response);
            return;
        }
        
        // ���ο� ȸ���� ����ϴ� ó���� �Ѵ�.
        // ������ �����͸� ���� Member ��ü�� �����Ͽ�
        Member member = new Member(memberID, password, name, email, tel, zipcode, address, point);
        
        // MemberService ��ü�� �����Ͽ� ȸ���� ����Ѵ�.
        MemberService memberService = new MemberServiceImpl();
        memberService.registerMember(member);
        
        // request scope �Ӽ��� ��ϵ� member�� �����ϰ� 
        request.setAttribute("member", member);
        // RequestDispatcher ��ü�� ���� �� ������(thankYou.jsp)�� ��û�� �����Ѵ�.
        RequestDispatcher dispatcher = request.getRequestDispatcher("thankYou.jsp");
        dispatcher.forward(request,response);
    }
    
    /* 
     * ���� ȸ�� ������ �����ϴ� ��û�� ó���Ѵ�.
     */
    private void updateMember(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DataNotFoundException {
    	
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginMember") == null) {
        	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "�α����� �ʿ��մϴ�.");
        	return;
        }
        
        String memberID = ((Member) session.getAttribute("loginMember")).getMemberID();    	
        // ��û �Ķ���͸� ���� HTML �� �����͸� ����.
        //String memberID = request.getParameter("memberID");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String zipcode = request.getParameter("zipcode");
        String address = request.getParameter("address");
        
        // �� �������� ��ȿ���� �����ϴ� ó���� �Ѵ�.
        // ���� �޽������� ������ ����Ʈ
        List<String> errorMsgs = new ArrayList<String>();

        // �� �����Ͱ� ��ȿ�� �� ����
        if ((password == null) || (password.length() == 0)) {
            errorMsgs.add("�н����带 �Է����ּ���.");
        }        
        if ((name == null) || (name.length() == 0)) {
            errorMsgs.add("�̸��� �Է����ּ���.");
        }        
        if ((email == null) || (email.length() == 0)) {
            errorMsgs.add("�̸����ּҸ� �Է����ּ���.");
        }
                
        // ��ȿ���� ���� �����Ͱ� ������ ���� ������ request scope �Ӽ��� �����ϰ�
        // ���� ������ ��(userError.jsp)�� ��û�� �����Ѵ�.
        if (!errorMsgs.isEmpty()) {
            request.setAttribute("errorMsgs", errorMsgs);
            RequestDispatcher dispatcher = request.getRequestDispatcher("userError.jsp");
            dispatcher.forward(request,response);
            return;
        }
        
        // ������ �����͸� ���� Member ��ü�� �����Ͽ�
        Member member = new Member(memberID, password, name, email, tel, zipcode, address);
        
        // MemberService ��ü�� �����Ͽ� ȸ�������� �����Ѵ�.
        MemberService memberService = new MemberServiceImpl();
        memberService.updateMember(member);
        
        // request scope �Ӽ��� ��ϵ� member�� �����ϰ� 
        // RequestDispatcher ��ü�� ���� �� ������(thankYou.jsp)�� ��û�� �����Ѵ�.
        request.setAttribute("member", member);
        RequestDispatcher dispatcher = request.getRequestDispatcher("thankYou.jsp");
        dispatcher.forward(request,response);
    }
    
    /* 
     * �α��� ��û�� ó���Ѵ�.
     */
    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        // ��û �Ķ���ͷκ��� memberID�� password�� �޾�
        String memberID = request.getParameter("memberID");
        String password = request.getParameter("password");
        
        // MemberService ��ü�� �����Ͽ� �α��� ���� ���θ� Ȯ���Ѵ�.
        MemberService service = new MemberServiceImpl();
        Member member = service.loginCheck(memberID, password);
        
        // check ���� Ȯ���Ͽ� 
        int check = member.getCheck();
        
        if (check == Member.VALID_MEMBER) {
        	// 1. ��ȿ�� ȸ���� ��� ���� ��ü�� ��� session scope �Ӽ��� ȸ�� ������ �����Ѵ�. 
            HttpSession session = request.getSession();
            session.setAttribute("loginMember", member);                
            // RequestDispatcher ��ü�� ���� ���� ������(index.jsp)�� ��û�� �����Ѵ�.
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request,response);
            
        } else {
        	// ��ȿ���� ���� ��� request scope �Ӽ��� ���� �޽����� �����ϰ� 
            // RequestDispatcher ��ü�� ���� ���� ������(index.jsp)�� ��û�� �����Ѵ�.
            String loginErrorMsg = null;
            
            if (check == Member.INVALID_ID) {
            	loginErrorMsg = "���̵� �������� �ʽ��ϴ�.";
            } else if (check == Member.INVALID_PASSWORD) {
            	loginErrorMsg = "�н����尡 ��ġ���� �ʽ��ϴ�.";
            }
            
            request.setAttribute("loginErrorMsg", loginErrorMsg);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }
 
    /* 
     * �α׾ƿ� ��û�� ó���Ѵ�.
     */
    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 2. session scope �Ӽ��� ����� ȸ�� ������ �����ϰ� ������ ��ȿȭ �Ѵ�.
    	HttpSession session = request.getSession(false);
    	if (session != null) {
    		session.removeAttribute("loginMember");
    		session.invalidate();
    	}    		
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request,response);
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
