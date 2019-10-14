package edu.cmu.andrew.apoulose.server.http;

import org.glassfish.jersey.server.ResourceConfig;


public class AppApplication extends ResourceConfig {
    public static String upSince = "";

    static {

        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tF %1$tT] [%4$-7s] %5$s %n");
    }

    public AppApplication() {

    }
}