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

import jian_free_domain.ServiceVO;
import jian_free_persistence.ServiceDAO;
import jian_free_persistence.UserDAO;

/**
 * Servlet implementation class ServiceServlet
 */
@WebServlet("/ServiceServlet")
public class ServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceServlet() {
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
		if (cmdReq.equals("list")) {
			request.setAttribute("pageNumber",  1);
			RequestDispatcher view = request.getRequestDispatcher("ServiceList.jsp");
			view.forward(request,  response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cmdReq;
		response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("utf-8");
	    cmdReq = request.getParameter("cmd");
	    HttpSession session;
	    
		if(cmdReq.contentEquals("write")) {
	    	ServiceVO serviceVO = new ServiceVO();   	
	    	serviceVO.setSnum(ServiceDAO.getNext());
	    	serviceVO.setSname(request.getParameter("sname"));
	    	serviceVO.setSemail(request.getParameter("semail"));
	    	serviceVO.setScontent(request.getParameter("scontent"));
	    	serviceVO.setSdate(ServiceDAO.getDate());
	    	
	    	ServiceDAO.add(serviceVO);
	    	
	    	request.setAttribute("Service",  serviceVO);
	    	
	    	RequestDispatcher view = request.getRequestDispatcher("ServiceList.jsp");
	    	view.forward(request, response);
	    }
		else if(cmdReq.equals("delete")) {
	    	ServiceDAO dao = new ServiceDAO();
	    	String message;
	    	
	    	if(dao.delete(request.getParameter("snum"))) message = "문의 글을 삭제하였습니다.";
	    	else message = "문의 글 삭제에 실패하였습니다.";
	    	request.setAttribute("message",  message);
	    	RequestDispatcher view = request.getRequestDispatcher("service_delete_result.jsp");
	    	view.forward(request, response);
	    }
		doGet(request, response);
	}

}
