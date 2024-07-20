package com.morioucho.samarkand.util;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Logger {
    public static void log(String type, String message){
        try{
            FileWriter fileWriter = new FileWriter("Logs.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("[" + type + "] " + message);
            printWriter.close();
        } catch (Exception exception){
            System.out.println(exception.getLocalizedMessage());
        }
    }
}
