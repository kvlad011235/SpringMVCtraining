//package testgroup;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
///**
// * чтобы проверить соединение создадим метод main.
// * Тут нерешенная проблема с javax.net.ssl.SSLException: closing inbound before receiving peer's close_notify - не стали решать, так как задача стоит не в этом, а просто показать как подключить MySQL
// */
//public class Main {
//    public static void main(String[] args) {
//        String url = "jdbc:mysql://localhost:3306/test";
//        String username = "root";
//        String password = "мой пароль, потом не забыть вписать";
//        System.out.println("Connecting...");
//
//        try (Connection connection = DriverManager.getConnection(url, username, password)) {
//            System.out.println("Connection successful!");
//        } catch (SQLException e) {
//            System.out.println("Connection failed!");
//            e.printStackTrace();
//        }
//    }
//}
