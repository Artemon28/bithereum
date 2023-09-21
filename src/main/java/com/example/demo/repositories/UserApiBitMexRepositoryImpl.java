package com.example.demo.repositories;


import com.example.demo.Entities.User;
import com.example.demo.Entities.UserApi;
import org.springframework.stereotype.Component;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class UserApiBitMexRepositoryImpl implements UserApiRepository{
    private final String fileName = "test.db";
    private final DataEncrypter dataEncrypter = new DataEncrypter();

    public UserApiBitMexRepositoryImpl() {

    }

    public void createNewDatabase() {
        String url = getConnectionString(fileName);
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                createUsersApi();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createUsersApi() {
        String url = getConnectionString(fileName);
        String sql = "CREATE TABLE IF NOT EXISTS users_api_bitmex (\n" +
                "user_id bigint not null constraint users_api_bitmex_users_info__fk references users_info,\n" +
                "bitmex_key varchar(255),\n" +
                "id bigserial constraint users_api_pk primary key,\n" +
                "bitmex_secret_key varchar(255) not null);";
        try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public UserApi findByUserId(long id) {
        String url = getConnectionString(fileName);

        String sql = "SELECT id, bitmex_key, bitmex_secret_key, user_id FROM users_api_bitmex WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt  = conn.prepareStatement(sql)) {
            pstmt.setInt(1, (int)id);
            ResultSet rs  = pstmt.executeQuery();
            UserInfoRepositoryImpl u = new UserInfoRepositoryImpl(fileName);
            User user = u.findByUserId(rs.getLong("user_id"));
            if (rs.getLong("id") == 0){
                return null;
            }
            String decryptedApiKey = dataEncrypter.decryptData(rs.getString("bitmex_key"));
            String decryptedSecretApiKey = dataEncrypter.decryptData(rs.getString("bitmex_secret_key"));
            return new UserApi(rs.getLong("id"), decryptedApiKey,
                    decryptedSecretApiKey, user);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(UserApi user) {
        String sql = "INSERT INTO users_api_bitmex(id, bitmex_key, bitmex_secret_key, user_id) VALUES(?,?,?,?)";

        String url = getConnectionString(fileName);
        try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String encryptedBitmexKey = dataEncrypter.encryptData(user.getKey());
            String encryptedBitmexSecretKey = dataEncrypter.encryptData(user.getSecretKey());

            pstmt.setLong(1, user.getId());
            pstmt.setString(2, encryptedBitmexKey);
            pstmt.setString(3, encryptedBitmexSecretKey);
            pstmt.setLong(4, user.getUserId());
            int idd = pstmt.executeUpdate();
            user.setId(idd);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void deleteById(long id) {
        String sql = "DELETE FROM users_api_bitmex WHERE id = ?";

        String url = getConnectionString(fileName);
        try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, (int)id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<UserApi> findAll() {
        String url = getConnectionString(fileName);

        List<UserApi> allUsers = new ArrayList<>();
        String sql = "SELECT id, name, capacity FROM users_info";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {
            while (rs.next()) {
                UserInfoRepositoryImpl u = new UserInfoRepositoryImpl(fileName);
                User user = u.findByUserId(rs.getLong("user_id"));
                allUsers.add(new UserApi(rs.getLong("id"), rs.getString("binance_key"),
                        rs.getString("binance_secret_key"), user));
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
