package org.example.usermanagement.model.service;

import org.example.usermanagement.model.DBConnection;
import org.example.usermanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection connection;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement("select * from users;");
            rs = statement.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return users;
    }

    @Override
    public void addUser(User user) {
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement("insert into users values (?,?,?,?);");
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getCountry());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void updateUser(User user) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("update users set name = ?, email = ?, country = ? where id = ?;");
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public User findById(Integer id) {
        User user = null;
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("select * from users where id = ?;");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Integer id1 = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id1, name, email, country);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return user;
    }

    @Override
    public void moveById(Integer id) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("delete from users where id = ?;");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<User> findByCountry(String name) {
        List<User> userList = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("select * from users where id = ?;");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Integer id = rs.getInt("id");
                String name1 = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                User user = new User(id, name1, email, country);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return userList;
    }


}
