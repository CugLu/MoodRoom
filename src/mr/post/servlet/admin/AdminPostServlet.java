package mr.post.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mr.post.service.PostService;

import cn.itcast.servlet.BaseServlet;

public class AdminPostServlet extends BaseServlet {
	private PostService postService = new PostService();
	
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String pid=request.getParameter("pid");
		
		postService.delete(pid);
		
		return findAllPost(request, response);
	}
	
	public String findAllPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setAttribute("hotPostList", postService.findHotPost());
		request.setAttribute("coldPostList", postService.findColdPost());
		return "f:/admin/posts.jsp";
	}
}
