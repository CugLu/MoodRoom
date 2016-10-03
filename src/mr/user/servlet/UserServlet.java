package mr.user.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mr.user.domain.User;
import mr.user.service.UserException;
import mr.user.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.servlet.BaseServlet;

public class UserServlet extends BaseServlet {
	private UserService userService=new UserService();
	
	public String quit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.getSession().invalidate();
		return "f:/users/login.jsp";
	}
	
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		User form=CommonUtils.toBean(request.getParameterMap(), User.class);
		Map<String,String> errors=new HashMap<String,String>();
		String username=form.getUname();
		if(username==null||username.trim().isEmpty()){
			errors.put("username", "用户名不能为空");
		}else if(username.length()<3||username.length()>10){
			errors.put("username", "用户名长度必须在3-10之间");
		}
		
		String password=form.getUpassword();
		if(password==null||password.trim().isEmpty()){
			errors.put("password", "password不能为空");
		}else if(password.length()<3||password.length()>10){
			errors.put("password", "password长度必须在3-10之间");
		}
		
		if(errors.size()>0){
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			return "f:/users/login.jsp";
		}
		
		try {
			User user=userService.login(form);
			request.getSession().setAttribute("session_user", user);
			return "/PostServlet?method=findAll";
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/users/login.jsp";
		}
	}
	
	public String active(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String code=request.getParameter("code");
		try{
			userService.active(code);
			request.setAttribute("msg","激活成功");
		}catch (UserException e){
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/users/login.jsp";
	}
	
	public String regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		User form=CommonUtils.toBean(request.getParameterMap(), User.class);
		form.setUid(CommonUtils.uuid());
		form.setUcode(CommonUtils.uuid()+CommonUtils.uuid());
		
		Map<String,String> errors=new HashMap<String,String>();
		
		String username=form.getUname();
		if(username==null||username.trim().isEmpty()){
			errors.put("username", "用户名不能为空");
		}else if(username.length()<3||username.length()>10){
			errors.put("username", "用户名长度必须在3-10之间");
		}
		
		String password=form.getUpassword();
		if(password==null||password.trim().isEmpty()){
			errors.put("password", "password不能为空");
		}else if(password.length()<3||password.length()>10){
			errors.put("password", "password长度必须在3-10之间");
		}
		
		String repassword=form.getRepassword();
		if(repassword==null||repassword.trim().isEmpty()){
			errors.put("repassword", "repassword不能为空,且2次的密码不一样！");
		}else if(!password.equals(repassword)){
			errors.put("repassword", "2次的密码不一样！");
		}
		
		String email=form.getUemail();
		if(email==null||email.trim().isEmpty()){
			errors.put("email", "email不能为空");
		}else if(!email.matches("\\w+@\\w+\\.\\w+")){
			errors.put("email", "email格式错误");
		}
		
		if(errors.size()>0){
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			return "f:/users/regist.jsp";
		}
		
		try {
			userService.regist(form);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/users/regist.jsp";
		}
		
		/*
		 * 发邮件
		 */
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader()
				.getResourceAsStream("email_template.properties"));
		String host = props.getProperty("host");//获取服务器主机
		String uname = props.getProperty("uname");//获取用户名
		String pwd = props.getProperty("pwd");//获取密码
		String from = props.getProperty("from");//获取发件人
		String to = form.getUemail();//获取收件人
		String subject = props.getProperty("subject");//获取主题
		String content = props.getProperty("content");//获取邮件内容
		content = MessageFormat.format(content, form.getUcode());//替换{0}
		
		Session session = MailUtils.createSession(host, uname, pwd);//得到session
		Mail mail = new Mail(from, to, subject, content);//创建邮件对象
		try {
			MailUtils.send(session, mail);//发邮件！
		} catch (MessagingException e) {
		}
		
		request.setAttribute("msg", "恭喜，注册成功！请激活后登录");
		return "f:/users/login.jsp";
	}
}
