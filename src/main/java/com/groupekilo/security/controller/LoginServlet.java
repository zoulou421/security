package com.groupekilo.security.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
	/*private Logger log=LoggerFactory.getLogger(LoginServlet.class);
	@Override
	public void init(ServletConfig config) throws ServletException {
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.getRequestDispatcher("index.jsp").forward(req, resp);
		//this.getServletContext().getRequestDispatcher("index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     String email=req.getParameter("email");
     String pwd=req.getParameter("password");
     log.info("Email envoyé est {}",email);
     resp.sendRedirect("welcome");
	}*/
	private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        // Initialization code, if needed
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

        // Simple authentication check (replace with actual authentication logic)
        if ("user@example.com".equals(email) && "password".equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("username", email); // Set username in session
            resp.sendRedirect(req.getContextPath() + "/welcome"); // Redirect to welcome page
        } else {
            // Redirect to login page with error message
            resp.sendRedirect(req.getContextPath() + "/login?error=true");
        }
    }
}
