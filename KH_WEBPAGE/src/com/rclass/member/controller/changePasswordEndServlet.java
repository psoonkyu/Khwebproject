package com.rclass.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rclass.member.vo.Member;

/**
 * Servlet implementation class changePasswordEndServlet
 */
@WebServlet(name = "changePasswordEndServelet", urlPatterns = "/changePasswordEnd")
public class changePasswordEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public changePasswordEndServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String currentPw = request.getParameter("password");
		String newPw = request.getParameter("password_new");
		String userId = request.getParameter("userId");
		System.out.println(currentPw + " " + newPw + "" + userId);
		Member m=new Member();
		m.setUserId(userId);
		Member result = new MemberService().seletOne(m);
		int insertResult = 0;
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		if (result !=null) {
			if(result.getPassword().equals(currentPw)) {
				m.setPassword(newPw);
				insertResult = new MemberService().updatePassword(m);
				if (insertResult>0) {
					msg="패스워드 수정이 완료되었습니다";
					loc="/updateMEmber?userId="+userId;
					String script="self.close()";
					request.setAttribute("script", script);
				}else {
					msg = "패스워드 수정이 실패하였습니다.";
					loc="/views/member/changePassword.jsp?userId="+userId;
				}
			}
		}else {
			msg = "현재 비밀번호가 일치하지 않습니다.;";
			loc="/views/member/changePassword.jsp?userId="+userId;
		}

		request.setAttribute("msg",msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request,  response);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
