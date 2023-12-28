package com.example.exam.DbFunctions;

import com.example.exam.Recept;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DbFunctions {

    public static Connection connect_to_db() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" +
                    "exam", "postgres", "123");
            if (connection != null) {
                System.out.println("Connection successful");
            } else {
                System.out.println("Connected failed");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }


    public void createUser(String login, String password) {
        try {
            String query = String.format("insert into users (login, password) " +
                            "values('%s', '%s');",
                    login, password);
            Statement statement = connect_to_db().createStatement();
            statement.executeUpdate(query);
            System.out.println("user created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int signIn(String login, String password) {
        try {
            String query = String.format("select * from users where login = '%s' and password='%s'", login, password);
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (!resultSet.next()) {
                return 0;
            }
            Variables.LOGIN_USER = resultSet.getString("id");
//            Variables.ROLE_USER = resultSet.getString("роль");
            Variables.ACTIVE_USER = resultSet.getString("login");
            SingletonUser.getInstance().setId(resultSet.getString("id"));
            SingletonUser.getInstance().setLogin(resultSet.getString("login"));
            SingletonUser.getInstance().setPassword(resultSet.getString("password"));



        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 404;
        }
        return 201;
    }

    public int check_login(String login) {
        try {
            String query = String.format("select * from users where login = '%s'", login);
            Statement statement = connect_to_db().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.last();
            if (resultSet.getRow() >= 1) {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 404;
        }
        return 201;
    }

    public ObservableList<Recept> getAllRecepts() {
        ObservableList<Recept> users = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connect_to_db().createStatement().executeQuery("select id, name, sostav, author from recepts");
            while (resultSet.next()) {
                users.add(new Recept(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("sostav"),
                        resultSet.getString("author")
                ));
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return users;
        }
    }



//    public XYChart.Series<String, Number> getStatistic() { для статистики
//        XYChart.Series<String, Number> series = new XYChart.Series<>();
//        try {
//            ResultSet resultSet = connect_to_db().createStatement().executeQuery("select * from users");
//
//            while (resultSet.next()) {
//                String string = resultSet.getString("extract");
//                Number value = resultSet.getInt("sum");
//                series.getData().add(new XYChart.Data<>(string, value));
//            }
//            return series;
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            return series;
//        }
//    }


    // этот метод прописывать в классе в котором нужно вызвать
//    private void installBarChart() throws SQLException {
//        CategoryAxis categoryAxis = new CategoryAxis();
//        categoryAxis.setLabel("Год");
//        NumberAxis numberAxis = new NumberAxis();
//        numberAxis.setLabel("Сумма");
//        barChart = new BarChart<>(categoryAxis, numberAxis);
//        barChart.getData().add(dbFunctions.getStatistic());
//        tabSums.setContent(barChart);
//
//
//    }


}
