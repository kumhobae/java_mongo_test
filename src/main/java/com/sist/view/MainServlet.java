package com.sist.view;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 *    <%
 *        for(MovieVO vo:list)
 *        {
 *           if(vo..)
 *           {
 *     %>
 *                 HTML
 *     <%
 *           }
 *           else
 *           {
 *     %>
 *                HTML
 *     <%
 *           }
 *         }
 *    %>
 *    
 *    JSTL
 *    <c:forEach>
 *     <c:if>
 *       HTML
 *     </c:if>
 *     <c:else>
 *       HTML
 *     </c:else>
 *    </c:forEach>
 *     
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
        // 변경할 서블릿명 
		String mode=request.getParameter("mode");
		if(mode==null)
			mode="1";
		int n=Integer.parseInt(mode);
		String sname="";
		switch(n)
		{
		  case 1:
			  sname="ReleasedServlet";
			  break;
		  case 2:
			  sname="ScheduledServlet";
			  break;
		  case 3:
			  sname="NewsServlet";
			  break;
		  case 4:
			  sname="BoxOfficeWeekly";
			  break;
		  case 5:
			  sname="BoxOfficeMonthly";
			  break;
		  case 6:
			  sname="BoxOfficeYearly";
			  break;
		  case 7:
			  sname="MovieDetailServlet";
			  break;
		}
		String menu="<nav class=\"navbar navbar-inverse\">"
                   +"<div class=\"container-fluid\">"
				   +"<div class=\"navbar-header\">"
				   +"<a class=\"navbar-brand\" href=\"MainServlet\">SIST MC</a>"
				   +"</div>"
				   +"<ul class=\"nav navbar-nav\">"
				   +"<li class=\"active\"><a href=\"MainServlet\">현재상영</a></li>"
				   +"<li class=\"dropdown\"><a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">박스오피스<span class=\"caret\"></span></a>"
				   +"<ul class=\"dropdown-menu\">"
				   +"<li><a href=\"MainServlet?mode=4\">주간</a></li>"
				   +"<li><a href=\"MainServlet?mode=5\">월간</a></li>"
				   +"<li><a href=\"MainServlet?mode=6\">연간</a></li>"
				   +"</ul>"
				   +"</li>"
				   +"<li><a href=\"MainServlet?mode=2\">개봉예정</a></li>"
				   +"<li><a href=\"MainServlet?mode=3\">뉴스</a></li>"
				   +"</ul>"
				   +"</div>"
				   +"</nav>";
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>");
		out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>");
		out.println("</head>");
		out.println("<body>");
		out.println(menu);
		out.println("<div class=\"container\">");
		RequestDispatcher rd=request.getRequestDispatcher(sname);
		rd.include(request, response);
		/*
		 *    void include(HttpServletRequest,HttpServletResponse response)
		 *    {
		 *         ReleasedServlet.doGet(req,res)
		 *    }
		 */
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}












