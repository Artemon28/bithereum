package com.example.demo.repositories;


import com.example.demo.Entities.User;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserInfoRepositoryImpl implements UserInfoRepository{
    private final String fileName;

    public UserInfoRepositoryImpl(String fileName){
        this.fileName = fileName;
    }

    public void createNewDatabase() {
        String url = getConnectionString(fileName);
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                createUsersInfo();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createUsersInfo() {
        String url = getConnectionString(fileName);

        String sql = "CREATE TABLE IF NOT EXISTS users_info (\n" +
                "id bigserial constraint users_info_pk primary key,\n" +
                "login    varchar(255) not null,\n" +
                "password varchar(255) not null,\n" +
                "nickname varchar(255) not null);";
        try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public User findByUserId(long id) {
        String url = getConnectionString(fileName);

        String sql = "SELECT login, password, nickname FROM users_info WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt  = conn.prepareStatement(sql)) {

            pstmt.setInt(1, (int)id);
            ResultSet rs  = pstmt.executeQuery();
            return new User(rs.getLong("id"), rs.getString("login"), rs.getBytes("password"), rs.getString("nickname"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users_info(login, password, nickname) VALUES(?,?,?)";

        String url = getConnectionString(fileName);
        try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getNickname());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteById(long id) {
        String sql = "DELETE FROM users_info WHERE id = ?";

        String url = getConnectionString(fileName);
        try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, (int)id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> findAll() {
        String url = getConnectionString(fileName);

        List<User> allUsers = new ArrayList<>();
        String sql = "SELECT login, password, nickname FROM users_info";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {
            while (rs.next()) {
                allUsers.add(new User(rs.getLong("id"), rs.getString("login"), rs.getBytes("password"), rs.getString("nickname")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allUsers;
    }

    private String getConnectionString(String fileName){
        String classPath = System.getProperty("user.dir");
        return "jdbc:sqlite:" + classPath + File.separator + fileName;
    }
}
