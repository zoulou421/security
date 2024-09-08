package com.groupekilo.security.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
	private static final long serialVersionUID = 1L;
	private final IUserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");

		String searchTerm = req.getParameter("search");
		

		// Retrieve sorting and filtering parameters
		String filterColumn = req.getParameter("filterColumn");
		String filterValue = req.getParameter("filterValue");
		String sortColumn = req.getParameter("sortColumn");
		String sortOrder = req.getParameter("sortOrder");

		// Pagination management
		// Pagination parameters
        int page = 1;
        int pageSize = 10;
        String pageParam = req.getParameter("page");
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1; // Default to first page if invalid
            }
        }
		// end pagination management

		if ("edit".equals(action)) {
			String idParam = req.getParameter("id");
			if (idParam != null) {
				try {
					long id = Long.parseLong(idParam);
					UserDto userDto = userService.get(id, null);
					if (userDto != null) {
						req.setAttribute("user", userDto);
						req.getRequestDispatcher("WEB-INF/jsp/users/edit.jsp").forward(req, resp);
					} else {
						resp.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
					}
				} catch (NumberFormatException e) {
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID format");
				}
			} else {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is required");
			}
		} else if ("add".equals(action)) {
			req.getRequestDispatcher("WEB-INF/jsp/users/add.jsp").forward(req, resp);
		} else if ("delete".equals(action)) {
			handleDeleteUser(req, resp);
		} else if ("search".equals(action)) {
			List<UserDto> searchResults = userService.searchByCriteria(searchTerm);
			req.setAttribute("users", searchResults);
			req.getRequestDispatcher("WEB-INF/jsp/users/list.jsp").forward(req, resp);
		}else {
			// Handle filtering and sorting when no action is provided
			handleFilterAndSort(req, resp, filterColumn, filterValue, sortColumn, sortOrder);
		}
		
		
		/*if(page==Integer.parseInt(req.getParameter("page"))) {
			handleSearch(req, resp, searchTerm, page, pageSize);
		}else {
			handleList(req, resp, page, pageSize);
		}*/
		
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");

		if ("add".equalsIgnoreCase(action)) {
			handleAddUser(req, resp);
		} else if ("edit".equalsIgnoreCase(action)) {
			handleEditUser(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
		}
	}

	private void handleAddUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		if (firstName != null && lastName != null && email != null && password != null) {
			try {
				UserDto userDto = new UserDto(0, firstName, lastName, email, password);
				boolean saveSuccess = userService.save(userDto);
				if (saveSuccess) {
					resp.sendRedirect(req.getContextPath() + "/admin?action=list");
				} else {
					req.setAttribute("error", "Failed to add user");
					req.getRequestDispatcher("WEB-INF/jsp/users/add.jsp").forward(req, resp);
				}
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("error", "An error occurred while adding the user");
				req.getRequestDispatcher("WEB-INF/jsp/users/add.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("error", "All fields are required");
			req.getRequestDispatcher("WEB-INF/jsp/users/add.jsp").forward(req, resp);
		}
	}

	private void handleEditUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idParam = req.getParameter("id");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		if (idParam != null && firstName != null && lastName != null && email != null && password != null) {
			try {
				int id = Integer.parseInt(idParam);
				UserDto userDto = new UserDto(id, firstName, lastName, email, password);
				boolean updateSuccess = userService.update(userDto);

				if (updateSuccess) {
					//req.setAttribute("messageEdit", "User updated successfully");
					//req.setAttribute("user", userService.get(id, userDto));
					//req.getRequestDispatcher("WEB-INF/jsp/users/edit.jsp").forward(req, resp);
					// Redirect to the user list page after successful update
	                resp.sendRedirect(req.getContextPath() + "/admin?action=list");
				} else {
					req.setAttribute("error", "Failed to update user");
					req.getRequestDispatcher("WEB-INF/jsp/users/edit.jsp").forward(req, resp);
				}
			} catch (NumberFormatException e) {
				req.setAttribute("error", "Invalid user ID format");
				req.getRequestDispatcher("WEB-INF/jsp/users/edit.jsp").forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("error", "An error occurred while updating the user");
				req.getRequestDispatcher("WEB-INF/jsp/users/edit.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("error", "All fields are required");
			req.getRequestDispatcher("WEB-INF/jsp/users/edit.jsp").forward(req, resp);
		}
	}

	private void handleDeleteUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String idParam = req.getParameter("id");

		if (idParam != null) {
			try {
				Long id = Long.parseLong(idParam); // Ensure this matches your ID type
				boolean deleteSuccess = userService.delete(id, null);

				req.setAttribute("messageDelete", deleteSuccess);
				if (deleteSuccess) {
					showUserList(req, resp);
				} else {
					req.setAttribute("error", "Failed to delete user");
					req.getRequestDispatcher("WEB-INF/jsp/users/list.jsp").forward(req, resp);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				req.setAttribute("error", "Invalid user ID");
				req.getRequestDispatcher("WEB-INF/jsp/users/list.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("error", "No user ID provided");
			req.getRequestDispatcher("WEB-INF/jsp/users/list.jsp").forward(req, resp);
		}
	}

	private void showUserList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("users", userService.getAll());
		req.getRequestDispatcher("WEB-INF/jsp/users/list.jsp").forward(req, resp);
	}

	private void handleFilterAndSort(HttpServletRequest req, HttpServletResponse resp, String filterColumn,
			String filterValue, String sortColumn, String sortOrder) throws ServletException, IOException {
		if (sortOrder == null || (!"asc".equals(sortOrder) && !"desc".equals(sortOrder))) {
			sortOrder = "asc"; // Default sort order
		}
		req.setAttribute("users", userService.filterGetAll(filterColumn, filterValue, sortColumn, sortOrder));
		req.getRequestDispatcher("WEB-INF/jsp/users/list.jsp").forward(req, resp);
	}
	
	
	private void handleList(HttpServletRequest req, HttpServletResponse resp, int page, int pageSize) throws ServletException, IOException {
        List<UserDto> users = userService.getPaginatedUsers(page, pageSize);
        int totalUsers = userService.getTotalUserCount();
        int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

        req.setAttribute("users", users);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("WEB-INF/jsp/users/list.jsp").forward(req, resp);
    }

    private void handleSearch(HttpServletRequest req, HttpServletResponse resp, String searchTerm, int page, int pageSize) throws ServletException, IOException {
        List<UserDto> searchResults = userService.searchByCriteria(searchTerm);
        int totalResults = searchResults.size();
        int totalPages = (int) Math.ceil((double) totalResults / pageSize);

        // Adjust page number if necessary
        if (page > totalPages) {
            page = totalPages;
        }

        // Get the paginated results
        List<UserDto> paginatedResults = searchResults.stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());

        req.setAttribute("users", paginatedResults);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("WEB-INF/jsp/users/list.jsp").forward(req, resp);
    }



}
