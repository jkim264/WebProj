package jian_free_controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jian_free_domain.UserVO;
import jian_free_persistence.UserDAO;
/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json;charset=UTF-8");
    	response.setContentType("text/html;charset=UTF-8");
		
		String cmdReq;
	    cmdReq = request.getParameter("cmd");
	    
		response.getWriter().append("Served at: ").append(request.getContextPath());
		if(cmdReq.equals("join")) {
			response.sendRedirect("register.html");
		}
		else if (cmdReq.equals("list")) {
			UserDAO dao = new UserDAO();
			ArrayList<UserVO> userlist = dao.getUserList();
			request.setAttribute("userlist", userlist);
			request.setAttribute("message", "관리자 회원관리 페이지");
			RequestDispatcher view = request.getRequestDispatcher("admin_login.jsp");
			view.forward(request,  response);
		}
		else if (cmdReq.equals("update")) {
			UserDAO dao = new UserDAO();
			UserVO user = dao.read(request.getParameter("userId"));
			request.setAttribute("user",  user);
			RequestDispatcher view = request.getRequestDispatcher("update.jsp");
			view.forward(request,  response);
		}
		else if(cmdReq.equals("logout")) {
			HttpSession session = request.getSession();
			String str = (String)session.getAttribute("ID");
			request.setAttribute("userId", str);
			request.setAttribute("message", str+"님, 정상적으로 로그아웃 되었습니다.");
			session.invalidate();
			RequestDispatcher view = request.getRequestDispatcher("Delete_result.jsp");
			view.forward(request,  response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "static-access", "null" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cmdReq;
		response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("utf-8");
	    cmdReq = request.getParameter("cmd");
	    HttpSession session = null;
	    

	    if(cmdReq.contentEquals("join")) {
	    	UserVO userVO = new UserVO();
	    	
	    	userVO.setUserId(request.getParameter("userId"));
	    	userVO.setUserPasswd(request.getParameter("userPasswd"));
	    	userVO.setUserName(request.getParameter("userName"));
	    	userVO.setUserMobile(request.getParameter("userMobile"));
	    	userVO.setUserEmail(request.getParameter("userEmail"));
	    	
	    	UserDAO dao = new UserDAO();
	    	String message;
	    	int result = dao.login(userVO.getUserId(), userVO.getUserPasswd());
	    	
	    	if(result == 0 ) message = "아이디가 이미 존재합니다.";
			else if(dao.add(userVO)) message = request.getParameter("userName") + "님! 가입 축하합니다.";
	    	else message = "가입 실패입니다.";
	    	
	    	request.setAttribute("message",  message);
	    	request.setAttribute("user",  userVO);
	    	
	    	RequestDispatcher view = request.getRequestDispatcher("Login_result.jsp");
	    	view.forward(request, response);
	    }
	    else if(cmdReq.equals("update")) {
	    	UserVO userVO = new UserVO();
	    	userVO.setUserId(request.getParameter("userId"));
	    	userVO.setUserPasswd(request.getParameter("userPasswd"));
	    	userVO.setUserName(request.getParameter("userName"));
	    	userVO.setUserMobile(request.getParameter("userMobile"));
	    	userVO.setUserEmail(request.getParameter("userEmail"));
	    	
	    	UserDAO dao = new UserDAO();
	    	String message;
	    	
	    	if(dao.update(userVO)) message = "수정이 완료되었습니다. ";
	    	else message = "수정 실패입니다.";
	    	
	    	request.setAttribute("message",  message);
	    	request.setAttribute("user",  userVO);;
	    	
	    	RequestDispatcher view = request.getRequestDispatcher("Login_result.jsp");
	    	view.forward(request, response);
	    }
	    else if(cmdReq.equals("login")) {
	    	UserVO userVO = new UserVO();
	    	userVO.setUserId(request.getParameter("userId"));
	    	userVO.setUserPasswd(request.getParameter("userPasswd"));
	    	
	    	UserDAO dao = new UserDAO();
	    	String message;
	    	
	    	int result = dao.login(userVO.getUserId(), userVO.getUserPasswd());
	    	if(result==1) {
	    		message = userVO.getUserId() + "님! 어서오세요,";
	    		session = request.getSession();

	    		session.setAttribute("ID", userVO.getUserId());

	    	}
	    	else if(result == 0) {
	    		message = "비밀번호가 틀렸습니다.";
	    	}
	    	else if(result==-1) {
	    		message = "아이디가 존재하지 않습니다.";
	    		
	    	}
	    	else message = "DB오류가 발생했습니다.";
	    	
	    	request.setAttribute("message",  message);
	    	request.setAttribute("user",  userVO);;
	    	RequestDispatcher view;
	    	view = request.getRequestDispatcher("Login_result.jsp");
	    	view.forward(request, response);
	    }
	 else if(cmdReq.equals("delete")) {
	    	UserDAO dao = new UserDAO();
	    	String message;
	    	
	    	int result = dao.login(request.getParameter("userId"), "");
	    	
	    	if(result == -1 ) message = "아이디가 존재하지 않습니다.";
	    	else if(dao.delete(request.getParameter("userId"))) {
	    		message = "삭제가 완료되었습니다. ";
	    	}
	    	else message = "삭제 실패";
	    	
	    	request.setAttribute("message",  message);
	    	request.setAttribute("userId",  request.getParameter("userId"));;
	    	
	    	RequestDispatcher view = request.getRequestDispatcher("Delete_result.jsp");
	    	view.forward(request, response);
	    	
	    }
	    
	 else if(cmdReq.contentEquals("logout")) {
		 String pid = request.getParameter("userId");
		 String message = null;
		 if(session.getAttribute("ID").equals(pid)) message = pid + "님, 로그아웃되었습니다.";
		 else message = "로그아웃 실패";
		 request.setAttribute("message",  message);
		 RequestDispatcher view = request.getRequestDispatcher("Delete_result.jsp");
		 view.forward(request, response);
		 session.invalidate();
	 }
		doGet(request, response);
	
	}
}
