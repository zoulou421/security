package com.groupekilo.security.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.groupekilo.security.dto.UserDto;
import com.groupekilo.security.service.IUserService;
import com.groupekilo.security.service.UserService;

@WebServlet(name = "admin", value = "/admin")
public class AdminServletOld extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
		String action=req.getParameter("action");
		if(action!=null) {
			if(action.equals("edit")) {
				req.setAttribute("users", userService.getAll());
		    	req.getRequestDispatcher("WEB-INF/jsp/users/update.jsp").forward(req, resp);
			}else {
				return;
			}
		}else {
			req.setAttribute("users", userService.getAll());
	    	req.getRequestDispatcher("WEB-INF/jsp/users/list.jsp").forward(req, resp);
		}
		
		/*if(action!=null) {
			if(action.equals("edit")){	
					//req.setAttribute("users", userService.getAll());
					//req.setAttribute("users", userService.get(Integer.parseInt(idParam), null));
					req.getRequestDispatcher("WEB-INF/jsp/users/update.jsp").forward(req, resp);
			}
		}else {
			req.setAttribute("users", userService.getAll());
	    	req.getRequestDispatcher("WEB-INF/jsp/users/list.jsp").forward(req, resp);
		}*/
		
    	
		/*String action=req.getParameter("action");
		if ("edit".equals(action)) {
	        String idParam = req.getParameter("id");
	        if (idParam != null) {
	            try {
	                int id = Integer.parseInt(idParam);
	                UserDto userDto = userService.get(id, new UserDto());
	                if (userDto != null) {
	                	req.setAttribute("users", userService.getAll());
	                	req.getRequestDispatcher("WEB-INF/jsp/users/update.jsp").forward(req, resp);	                } else {
	                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Server not found");
	                }
	            } catch (NumberFormatException e) {
	                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format");
	            }
	        }
		}else if("list".equals(action)){
			req.setAttribute("users", userService.getAll());
        	req.getRequestDispatcher("WEB-INF/jsp/users/list.jsp").forward(req, resp);	                } else {
            
	    }*/ 
		 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action=req.getParameter("action");
		
		if(action.equalsIgnoreCase("add")) {
			
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			if(firstName!=null && lastName!=null && email!=null && password!=null) {
				try {
					UserDto userDto=new UserDto(0,firstName,lastName,email,password);
					userService.save(userDto);
					doGet(req, resp);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}else {
				return;
			}
			
		}else if(action.equals("edit")) {
			// Retrieve parameters from the request
			doGet(req, resp);
			
			String idParam = req.getParameter("id");
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			if(idParam!=null && firstName!=null && lastName!=null && email!=null && password!=null) {
				
				try {
					
					int id = Integer.parseInt(idParam);
					// Create a UserDto object and set its properties
					UserDto userDto=new UserDto(id,firstName,lastName,email,password);
					boolean bool=userService.update(userDto);
					req.setAttribute("messageEdit", bool);
	                if (bool==true) {
	                	req.setAttribute("userService", userService.get(id, userDto));
	                    // Redirect to the list page on success
	                    req.getRequestDispatcher("WEB-INF/jsp/users/update.jsp").forward(req, resp);
	                
	                } else {
	                    // Forward to the edit form with an error message on failure
	                    req.setAttribute("error", "Echec mise à jour utilisateur");
	                    req.getRequestDispatcher("WEB-INF/jsp/users/update.jsp").forward(req, resp);
	                }
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
		
	}
}
