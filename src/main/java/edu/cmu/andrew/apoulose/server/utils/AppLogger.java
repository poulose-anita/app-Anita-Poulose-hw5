package edu.cmu.andrew.apoulose.server.utils;


import java.io.IOException;
import java.util.Date;
import java.util.logging.*;

public class AppLogger {

    private static AppLogger appLogger;


    public final static Logger logger = Logger.getLogger(Config.logName);

    public static AppLogger getInstance(){
        if(appLogger == null){
            appLogger = new AppLogger();
        }
        return appLogger;
    }

    public AppLogger(){
        try {
            SimpleFormatter formatter = new SimpleFormatter(){
                private static final String format = "[%1$tF %1$tT] [%2$s] %3$s %n";

                @Override
                public synchronized String format(LogRecord lr) {
                    return String.format(format,
                            new Date(lr.getMillis()),
                            lr.getLevel().getLocalizedName(),
                            lr.getMessage()
                    );
                }
            };
            FileHandler fileText = new FileHandler(Config.logFile, true);
            fileText.setFormatter(formatter);
            logger.addHandler(fileText);
            setLevel(Config.logLevel);

        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public  Logger getLOGGER() {
        return logger;
    }

    public static void error(String message, Exception e){
        AppLogger.getInstance().getLOGGER().log(Level.SEVERE,message,e);
    }

    public static void warning(String message){
        AppLogger.getInstance().getLOGGER().warning(message);
    }

    public static void info(String message){
        AppLogger.getInstance().getLOGGER().info(message);
    }
    public static void debug(String message){
        AppLogger.getInstance().getLOGGER().fine(message);
    }

    public static void setLevel(String level){
        switch(level) {

            case "ERROR":
                logger.setLevel(Level.SEVERE);
                break;

            case "WARNING":
                logger.setLevel(Level.WARNING);
                break;

            case "INFO":
                logger.setLevel(Level.INFO);
                break;

            case "DEBUG":
                logger.setLevel(Level.FINE);
                break;

        }

    }



}
