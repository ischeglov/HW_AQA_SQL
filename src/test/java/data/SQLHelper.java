package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
    }

    @SneakyThrows
    public static String getVerificationCode() {
        var code = "SELECT code FROM auth_codes ORDER BY code DESC LIMIT 1";
        var conn = getConnection();
        return QUERY_RUNNER.query(conn, code, new ScalarHandler<>());
    }

    @SneakyThrows
    public static void cleanDB() {
        var conn = getConnection();
        QUERY_RUNNER.execute(conn, "DELETE FROM auth_codes");
        QUERY_RUNNER.execute(conn, "DELETE FROM card_transactions");
        QUERY_RUNNER.execute(conn, "DELETE FROM cards");
        QUERY_RUNNER.execute(conn, "DELETE FROM users");

    }

    @SneakyThrows
    public static void cleanAuthCodes() {
        var conn = getConnection();
        QUERY_RUNNER.execute(conn, "DELETE FROM auth_codes");
    }
}
