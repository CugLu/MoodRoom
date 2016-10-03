package mr.post.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mr.post.domain.PageBean;
import mr.post.domain.Post;
import mr.post.service.CommentService;
import mr.post.service.PostService;
import mr.user.domain.User;
import mr.user.service.UserService;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class PostServlet extends BaseServlet {
	private PostService postService = new PostService();
	private UserService userService=new UserService();
	private CommentService commentService=new CommentService();

	public void changeZan(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String pid=request.getParameter("pid");
		String text=request.getParameter("text");
		postService.changeZan(pid,text);
		
		response.getWriter().print(postService.findZan(pid));
	}
	
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//查出这个帖子
		String pid=request.getParameter("pid");
		request.setAttribute("post", postService.findPost(pid));
		
		//查出所有关于这个帖子的评论
		request.setAttribute("commentList", commentService.findPostComment(pid));
		
		return "f:/room/singlePost.jsp";
	}
	
	public String findUserPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String uname=request.getParameter("uname");
		request.setAttribute("userPostList", postService.findUserPost(uname));
		
		//向session中更新一下session_user
		User user=userService.findUser(uname);
		request.getSession().setAttribute("session_user", user);
		
		return "f:/room/userPost.jsp";
	}
	
	/*public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("postList", postService.findAll());
		return "f:/room/main.jsp";
	}*/
	
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 获取页面传递的pc
		 * 2. 给定ps的值
		 * 3. 使用pc和ps调用service方法，得到PageBean，保存到request域
		 * 4. 转发到list.jsp
		 */
		int pc = getPc(request);//得到pc
		int ps = 2;//给定ps的值，第页10行记录
		PageBean<Post> pb = postService.findAll(pc, ps);//传递pc, ps给Service，得到PageBean
		
		// 设置url
		pb.setUrl(getUrl(request));
		
		request.setAttribute("pb", pb);//保存到request域中
		return "f:/room/main.jsp";
	}
	
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 1.把表单数据封装到Post中
		 */
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setFileSizeMax(1024 * 1024);
		try {
			List<FileItem> fileItemList = sfu.parseRequest(request);
			Map<String, String> map = new HashMap<String, String>();
			for (FileItem fileItem : fileItemList) {
				if (fileItem.isFormField()) {
					map.put(fileItem.getFieldName(),
							fileItem.getString("UTF-8"));
				}
			}
			Post post = CommonUtils.toBean(map, Post.class);
			post.setPid(CommonUtils.uuid());
			User user = (User) request.getSession()
					.getAttribute("session_user");
			post.setUname(user.getUname());
			post.setPtime(new Date());

			/*
			 * 2.保存上传的文件
			 */

			// 得到保存的目录
			String savepath = this.getServletContext().getRealPath("/post_img");
			// 得到文件名称：给原来文件名称添加uuid前缀！避免文件名冲突
			String filename = CommonUtils.uuid() + "_"
					+ fileItemList.get(1).getName();
			/*
			 * 校验文件的扩展名
			 */
			if (!filename.toLowerCase().endsWith("jpg")) {
				request.setAttribute("msg", "您上传的图片不是JPG扩展名！");
				request.getRequestDispatcher("/room/post.jsp").forward(request,
						response);
				return;
			}
			// 使用目录和文件名称创建目标文件
			File destFile = new File(savepath, filename);
			// 保存上传文件到目标文件位置
			fileItemList.get(1).write(destFile);

			/*
			 * 3.设置Post的pimage
			 */
			post.setPimage("post_img/" + filename);

			/*
			 * 4.保存到数据库
			 */
			postService.add(post);
			//更新用户的帖子数
			userService.addPostNumber(post.getUname());
			/*
			 * 5.转发到main.jsp
			 */
			request.getRequestDispatcher("/PostServlet?method=findAll")
				.forward(request,response);
		} catch (Exception e) {
			if(e instanceof FileUploadBase.FileSizeLimitExceededException) {
				request.setAttribute("msg", "您上传的文件超出了1024KB");
				request.getRequestDispatcher("/room/post.jsp")
						.forward(request, response);
			}
		}
	}
	
	/**
	 * 获取pc
	 * @param request
	 * @return
	 */
	private int getPc(HttpServletRequest request) {
		/*
		 * 1. 得到pc
		 *   如果pc参数不存在，说明pc=1
		 *   如果pc参数存在，需要转换成int类型即可
		 */
		String value = request.getParameter("pc");
		if(value == null || value.trim().isEmpty()) {
			return 1;
		}
		return Integer.parseInt(value);
	}
	
	/**
	 * 截取url
	 *   /项目名/Servlet路径?参数字符串
	 * @param request
	 * @return
	 */
	private String getUrl(HttpServletRequest request) {
		String contextPath = request.getContextPath();//获取项目名
		String servletPath = request.getServletPath();//获取servletPath，即/CustomerServlet
		String queryString = request.getQueryString();//获取问号之后的参数部份
		
		//  判断参数部份中是否包含pc这个参数，如果包含，需要截取下去，不要这一部份。
		if(queryString.contains("&pc=")) {
			int index = queryString.lastIndexOf("&pc=");
			queryString = queryString.substring(0, index);
		}
		
		return contextPath + servletPath + "?" + queryString;
	}
}
