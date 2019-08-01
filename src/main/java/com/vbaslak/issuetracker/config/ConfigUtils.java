package com.vbaslak.issuetracker.config;

public class ConfigUtils {
    private static String OS = System.getProperty("os.name");
    public static String getOsDependedPathPrefix() {
        return OS.substring(0, OS.indexOf(' ')).equals("Windows") ? "/" : "//";
    }
}
