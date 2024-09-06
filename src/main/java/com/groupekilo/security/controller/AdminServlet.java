package com.groupekilo.security.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupekilo.security.dto.UserDto;
import com.groupekilo.security.service.IUserService;
import com.groupekilo.security.service.UserService;

@WebServlet(name = "admin", value = "/admin")
public class AdminServlet extends HttpServlet {
	IUserService userService=new UserService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 List<UserDto>users=new ArrayList<UserDto>();
		 
		UserDto user1=new UserDto(1,"Bonevy","BEBY","bonevy@formationkilo.com");
		UserDto user2=new UserDto(2,"Natasha","BEBY","natasha@formationkilo.com");
		UserDto user3=new UserDto(3,"Ketsia","BEBY","ketsia@formationkilo.com");
		users.add(user1);
		users.add(user2);
		users.add(user3);*/
		
		//req.setAttribute("users", users);
		req.setAttribute("users", userService.getAll());
		req.getRequestDispatcher("WEB-INF/jsp/users/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
	}
}
