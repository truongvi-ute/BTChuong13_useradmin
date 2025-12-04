package murach.email;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import murach.business.User;
import murach.data.UserDB;

public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = "/useradmin.jsp";
        
        // Lấy hành động hiện tại (action)
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        if (action.equals("list")) {
            // Hiển thị danh sách users
            List<User> users = UserDB.selectAllUsers();
            request.setAttribute("users", users);
            url = "/useradmin.jsp";
        } 
        else if (action.equals("add")) {
            // Lấy tham số từ form
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");

            // Tạo User object và insert vào DB
            User user = new User(firstName, lastName, email);
            UserDB.insert(user);

            // Quay lại trang danh sách
            List<User> users = UserDB.selectAllUsers();
            request.setAttribute("users", users);
            url = "/useradmin.jsp";
        }
        else if (action.equals("update")) {
            // Lấy tham số từ form
            String userIdStr = request.getParameter("userId");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");

            // Tạo User object và update
            User user = new User(firstName, lastName, email);
            user.setUserId(Long.parseLong(userIdStr));
            UserDB.update(user);

            // Quay lại trang danh sách
            List<User> users = UserDB.selectAllUsers();
            request.setAttribute("users", users);
            url = "/useradmin.jsp";
        }
        else if (action.equals("delete")) {
            // Lấy email để xóa
            String email = request.getParameter("email");
            User user = UserDB.selectUser(email);
            if (user != null) {
                UserDB.delete(user);
            }

            // Quay lại trang danh sách
            List<User> users = UserDB.selectAllUsers();
            request.setAttribute("users", users);
            url = "/useradmin.jsp";
        }
        else if (action.equals("displayUpdate")) {
            // Hiển thị form update
            String email = request.getParameter("email");
            User user = UserDB.selectUser(email);
            request.setAttribute("user", user);
            url = "/index.jsp";
        }
        
        // Chuyển hướng request
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}