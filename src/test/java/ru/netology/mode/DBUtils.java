package ru.netology.mode;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    public static String getVerificationCode() {
        String codeSQL = "SELECT code FROM auth_codes WHERE created = (SELECT max(created) FROM auth_codes);";
        String verificationCode = "";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass")) {
            QueryRunner runner = new QueryRunner();
            verificationCode = runner.query(conn, codeSQL, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return verificationCode;
    }

    public static void clearDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass")) {
            String clearAuthCodesSQL = "DELETE FROM auth_codes;";
            String clearUsersSQL = "DELETE FROM users;";

            QueryRunner runner = new QueryRunner();
            runner.update(conn, clearAuthCodesSQL);
            runner.update(conn, clearUsersSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
