package core.modules;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseManager {
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet rs = null;
    private String url = "jdbc:mysql://localhost/chat_pairs?serverTimezone=Europe/Moscow&useSSL=false";
    private String username = "root";
    private String password = "21partyholiday";

    public DataBaseManager() {
        try {
           // connection = ConnectionDBUtil.getInstance().getConnection();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    ConnectionDBUtil.getInstance().close(connection);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
//    public DataBaseManager() {
//        String url = "jdbc:mysql://localhost/chat_pairs?serverTimezone=Europe/Moscow&useSSL=false";
//        String username = "root";
//        String password = "21partyholiday";
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        try (Connection conn = DriverManager.getConnection(url, username, password)) {
//            this.statement = conn.createStatement();
//            statement.executeUpdate("USE chat_pairs;");
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    public void createChatPairsTable() {
        try {
            statement.executeUpdate("CREATE TABLE pairs (first_id INT UNIQUE, second_id INT UNIQUE);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPairToTable(int firstID, int secondID) {
        try {
            statement.executeUpdate("USE chat_pairs;");
            statement.executeUpdate("INSERT INTO pairs (first_id, second_id) VALUES (" + firstID + ", " + secondID + ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getFirstIdFromPairs(int secondID) {
        Integer id = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM pairs;");
            while (resultSet.next())
                id = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static void main3(String[] args) {
        DataBaseManager dataBaseManager = new DataBaseManager();
        dataBaseManager.createChatPairsTable();
        dataBaseManager.addPairToTable(2222, 3333);
        System.out.println(dataBaseManager.getFirstIdFromPairs(1));
    }

    public boolean addToGayBase(int id){
        try {
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                statement.executeUpdate("USE gay_game;");
                statement.executeUpdate("INSERT INTO gays (id) VALUES ("+id+");");
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            ex.printStackTrace();
            System.out.println(ex);
            return false;
        }
        return true;
    }

    public ArrayList<Integer> getGayBase() {
        ArrayList<Integer> result = new ArrayList<>();
        try {
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                Statement statement = conn.createStatement();
                statement.executeUpdate("USE gay_game;");
                ResultSet resultSet = statement.executeQuery("SELECT * FROM pairs;");
                while (resultSet.next()) {
                    result.add(resultSet.getInt(1));
                }
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            ex.printStackTrace();
            System.out.println(ex);
        }
        return result;

    }


    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost/chat_pairs?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "21partyholiday";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            // команда создания таблицы


            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                Statement statement = conn.createStatement();
                statement.executeUpdate("DROP TABLE pairs;");
                // создание таблицы
                statement.executeUpdate("CREATE TABLE pairs (first_id INT UNIQUE, second_id INT UNIQUE);");
                System.out.println("Database has been created!");
                statement.executeUpdate("INSERT INTO pairs (first_id, second_id) VALUES (1111, 4444);");
                statement.executeUpdate("USE chat_pairs;");
                ResultSet resultSet = statement.executeQuery("SELECT * FROM pairs;");
                while (resultSet.next()) {
                    int test = resultSet.getInt(1);
                    System.out.println("test = " + test);
                    main3(args);
                }
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            ex.printStackTrace();
            System.out.println(ex);
        }
    }
}
