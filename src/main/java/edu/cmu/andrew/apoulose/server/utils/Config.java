package edu.cmu.andrew.apoulose.server.utils;

import java.util.HashMap;

public class Config {

    private static Config config;
    private static HashMap<String, Object> currentConfig = new HashMap<>();


    public static final String database = "app-apoulose";
    public static final int dbPort = 27017;
    public static final String dbHost = "localhost";
    public static String verion = "0.1.1";
    public static String logFile = "/var/log/app.log";
    public static String logLevel = "ERROR";
    public static String logName = "AppLog";




}
