package com.example.exam.DbFunctions;

public class SingletonUser {
    private static SingletonUser instance;

    private static String id;
    private static String surname;
    private static String name;
    private static String lastname;

    private static String login;
    private static String password;


    public static synchronized SingletonUser getInstance() {
        if (instance == null) {
            instance = new SingletonUser();
        }
        return instance;
    }



    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        SingletonUser.id = id;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        SingletonUser.login = login;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        SingletonUser.password = password;
    }


}
