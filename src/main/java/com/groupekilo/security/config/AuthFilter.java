package com.groupekilo.security.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({"/admin/*", "/welcome/*"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false); // Avoid creating a new session

        // Retrieve the username from the session, if it exists
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        // Get the requested path
        String path = req.getServletPath();

        // Allow access to login and logout paths without authentication
        if (path.equals("/login") || path.equals("/logout")) {
            chain.doFilter(request, response); // Allow the request
        } else if (username != null) {
            chain.doFilter(request, response); // User is authenticated, allow the request
        } else {
            // Redirect to login page if not authenticated
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}
