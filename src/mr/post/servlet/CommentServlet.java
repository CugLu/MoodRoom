package mr.post.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mr.post.domain.Comment;
import mr.post.service.CommentService;
import mr.post.service.PostService;
import mr.user.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class CommentServlet extends BaseServlet {
	private CommentService commentService=new CommentService();
	private PostService postService = new PostService();
	
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		Comment comment=new Comment();
		
		String ccomment=request.getParameter("ccomment");
		Map<String,String> errors=new HashMap<String,String>();
		
		if(ccomment==null||ccomment.trim().isEmpty()){
			errors.put("Comment", "评论不能为空");
		}else if(ccomment.length()>200){
			errors.put("Comment", "评论必须在200字以内!");
		}
		
		if(errors.size()>0){
			request.setAttribute("errors", errors);
		}
		else{
			comment.setCid(CommonUtils.uuid());
			comment.setPid(request.getParameter("pid"));
			comment.setUname(((User)request.getSession().getAttribute("session_user")).getUname());
			comment.setCcomment(ccomment);
			commentService.add(comment);
			
			//添加完评论后，要将帖子的数据库评论数+1
			postService.addPcomment(request.getParameter("pid"));
		}
		//查出这个帖子
		String pid=request.getParameter("pid");
		request.setAttribute("post", postService.findPost(pid));
		//查出所有关于这个帖子的评论
		request.setAttribute("commentList", commentService.findPostComment(pid));
		return "f:/room/singlePost.jsp";
		
	}
	
}
