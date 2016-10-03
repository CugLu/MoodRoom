package mr.user.servlet.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import mr.user.domain.User;

/**
 * Servlet Filter implementation class adminLoginFilter
 */
public class adminLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public adminLoginFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * 1. 从session中获取用户信息
		 * 2. 判断如题session中存在用户信息，放行！
		 * 3. 否则，保存错误信息，转发到login.jsp显示
		 */
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String username = (String)httpRequest.getSession().getAttribute("session_adminUser");
		if(username != null) {
			chain.doFilter(request, response);
		} else {
			httpRequest.setAttribute("msg", "您还没有登录！");
			httpRequest.getRequestDispatcher("/admin/login.jsp")
					.forward(httpRequest, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
