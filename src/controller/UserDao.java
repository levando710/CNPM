/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controller;

/**
 *
 * @author do lee
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.User; // Đây là lớp model đại diện cho User

public class UserDao {

    // Thêm người dùng mới vào cơ sở dữ liệu
    public static boolean addUser(User user) {
        String sql = "INSERT INTO user (username, password, name, email, type, position) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getType());
            stmt.setString(6, user.getPosition());
            
            
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy danh sách người dùng
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                User user = new User(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("type"),
                    rs.getString("position")
                    
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public static User getUserByUsername(String username) {
    User user = null;
    String sql = "SELECT * FROM user WHERE username = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            user = new User(null,null,null,null,null,null);
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setType(rs.getString("type"));
            user.setPosition(rs.getString("position"));
        }

    } catch (SQLException e) {
        System.err.println("Lỗi khi tìm user theo username: " + e.getMessage());
    }

    return user;
}

    // Tìm người dùng theo username
    public static List<User> searchUserByUsername(String username) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE username LIKE ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + username + "%");
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("type"),
                        rs.getString("position")
                        
                    );
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Cập nhật thông tin người dùng
    // Cập nhật thông tin người dùng, nhưng không thay đổi username
    public static boolean updateUser(User user) {
        // Chỉ cập nhật các trường dữ liệu khác ngoài username
        String sql = "UPDATE user SET password = ?, name = ?, type = ?, position = ?, email = ? WHERE username = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(6, user.getUsername());  // username vẫn là điều kiện để xác định bản ghi cần cập nhật
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getName());
            stmt.setString(5, user.getEmail());
            stmt.setString(3, user.getType());
            stmt.setString(4, user.getPosition());
            
          
        
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    // Xóa người dùng
    public static boolean deleteUser(String username) {
        String sql = "DELETE FROM user WHERE username = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
