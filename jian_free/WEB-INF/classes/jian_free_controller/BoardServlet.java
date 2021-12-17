package jian_free_controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import jian_free_domain.BoardVO;
import jian_free_persistence.BoardDAO;



/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardServlet() {
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
			RequestDispatcher view = request.getRequestDispatcher("Sale.jsp");
			view.forward(request,  response);
		}
		else if(cmdReq.equals("read")) {
			String number = request.getParameter("bnum");
			BoardVO boardVO = BoardDAO.read(number);
	    	request.setAttribute("Board",  boardVO);
	    	
	    	RequestDispatcher view = request.getRequestDispatcher("ReadPost.jsp");
	    	view.forward(request, response);
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
			BoardDAO dao = new BoardDAO();
			String message;
			
	    	BoardVO boardVO = new BoardVO();   	
	    	boardVO.setBnum(BoardDAO.getNext());
	    	boardVO.setBtitle(request.getParameter("btitle"));
	    	boardVO.setBwriter(request.getParameter("bwriter"));
	    	boardVO.setBcontent(request.getParameter("bcontent"));
	    	boardVO.setBdate(BoardDAO.getDate());
	    	
	    	if(BoardDAO.add(boardVO)) message = "게시글 작성 성공";
	    	else if(BoardDAO.getNext() == -1) {
				message="데이터베이스 오류";
			}
	    	else message="게시글 작성 실패";
	    	
	    	request.setAttribute("Board",  boardVO);
	    	request.setAttribute("message",  message);

	    	RequestDispatcher view = request.getRequestDispatcher("Sale.jsp");
	    	view.forward(request, response);
	    }
		else if(cmdReq.equals("update")) {
			BoardDAO dao = new BoardDAO();
	    	String message;
	    	String str = request.getParameter("bnum");
	    	int number = Integer.parseInt(str);
	    	
	    	BoardVO boardVO = new BoardVO();
	    	boardVO.setBnum(number);
	    	boardVO.setBtitle(request.getParameter("btitle"));
	    	boardVO.setBwriter(request.getParameter("bwriter"));
	    	boardVO.setBcontent(request.getParameter("bcontent"));
	    	boardVO.setBdate(BoardDAO.getDate());
	    	
	    	if(dao.update(boardVO)) message = "수정이 완료되었습니다. ";
	    	else message = "수정 실패입니다.";
	    	
	    	request.setAttribute("message",  message);
	    	request.setAttribute("Board",  boardVO);;
	    	
	    	RequestDispatcher view = request.getRequestDispatcher("Delete_result.jsp");
	    	view.forward(request, response);
	    }
		else if(cmdReq.equals("delete")) {
	    	BoardDAO dao = new BoardDAO();
	    	String message;
	    	
	    	if(dao.delete(request.getParameter("bnum"))) message = "글을 삭제하였습니다.";
	    	else message = "글 삭제에 실패하였습니다.";
	    	request.setAttribute("message",  message);
	    	RequestDispatcher view = request.getRequestDispatcher("board_delete_result.jsp");
	    	view.forward(request, response);
		}
		else if(cmdReq.equals("update_move")) {
			String number = request.getParameter("bnum");
			BoardVO boardVO = BoardDAO.read(number);
	    	request.setAttribute("Board",  boardVO);
	    	
	    	RequestDispatcher view = request.getRequestDispatcher("UpdatePost.jsp");
	    	view.forward(request, response);
		}
		
		doGet(request, response);
	}

}
