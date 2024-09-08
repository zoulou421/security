package com.groupekilo.security.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.groupekilo.security.dto.UserDto;
import com.groupekilo.security.service.IUserService;
import com.groupekilo.security.service.UserService;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserService userService=new UserService();
	
	private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Forward to login page
        req.getRequestDispatcher("index.jsp").forward(req, resp);///WEB-INF/views/login.jsp
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        // Log the email for debugging
        log.info("Email sent is {}", email);

       
        Optional<UserDto>user=userService.login(email, password);
        if(user.isPresent()) {
        	req.getSession().setAttribute("username", email);
        	resp.sendRedirect("welcome");
        }else {
        	
        	resp.sendRedirect("login");
        }
        
    }
}
