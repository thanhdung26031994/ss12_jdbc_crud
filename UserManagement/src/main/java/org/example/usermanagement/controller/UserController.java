package org.example.usermanagement.controller;

import org.example.usermanagement.model.User;
import org.example.usermanagement.model.service.IUserService;
import org.example.usermanagement.model.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

@WebServlet(name = "UserController", urlPatterns = "/user")
public class UserController extends HttpServlet {
    private static IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreateUser(req,resp);
                break;
            case "edit":
                showEditUser(req,resp);
                break;
            case "delete":
                deleteUser(req,resp);
                break;
            default:
                listUser(req,resp);
                break;
        }
    }

    private void showEditUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        User user = userService.findById(id);
        req.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("edit.jsp");
        dispatcher.forward(req,resp);
        resp.sendRedirect("/user");

    }

    private void showCreateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("create.jsp");
        dispatcher.forward(req,resp);
        resp.sendRedirect("/user");
    }

    private void listUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("list.jsp");
        List<User> users = userService.findAll();
        req.setAttribute("user", users);
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                createUsers(req,resp);
                break;
            case "edit":
                editUser(req,resp);
                break;
            case "country":
                searchByCountry(req,resp);
                break;

        }
    }

    private void searchByCountry(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        List<User> users = userService.findByCountry(name);
        RequestDispatcher dispatcher = req.getRequestDispatcher("list.jsp");
        req.setAttribute("user", users);
        dispatcher.forward(req,resp);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        userService.moveById(id);
        resp.sendRedirect("/user");
    }

    private void editUser(HttpServletRequest req, HttpServletResponse resp) {
        Integer id = Integer.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        User user = new User(id, name, email, country);
        userService.updateUser(user);
        try {
            resp.sendRedirect("/user");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        User user = new User(id, name, email, country);
        userService.addUser(user);
        resp.sendRedirect("/user");
    }
}
