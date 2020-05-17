package com.microservices.commons.utils;

public class Messages {

    public static String findObjectMessage(String arg1, String arg2){
        return "Looking for " + arg1 + " with id: " + arg2 + ".";
    }

    public static String findUserMessage(String arg1, String arg2){
        return "Looking for " + arg1 + " with username: " + arg2 + ".";
    }

    public static String nullUserMessage(String arg1, String arg2){
        return arg1 + " with username: " + arg2 + ", not found in database.";
    }

    public static String errorDatabaseAccessMessage(String arg){
        return "Error accessing to database: " + arg + ".";
    }

    public static String nullObjectMessage(String arg1, String arg2){
        return arg1 + " with id: " + arg2 + ", not found in database.";
    }

    public static String creatingObjectMessage(String arg){
        return "Persisting new " + arg + " in database.";
    }

    public static String errorsCreatingObjectMessage(String arg){
        return "Errors present creating new " + arg + ".";
    }

    public static String errorDatabaseCreateMessage(String arg1, String arg2){
        return "Error in database when trying to create new " + arg1 + ". Error: " + arg2 + ".";
    }

    public static String updatingObjectMessage(String arg1, String arg2){
        return "Updating " + arg1 + " with id " + arg2 + " in database.";
    }

    public static String errorsUpdatingObjectMessage(String arg1, String arg2){
        return "Errors present updating " + arg1 + " with id: " + arg2 + ".";
    }

    public static String errorDatabaseUpdateMessage(String arg1, String arg2, String arg3){
        return "Error in database when trying to update " + arg1 + " with id " + arg2 + ". Error: " + arg3 + ".";
    }

    public static String deletingObjectMessage(String arg1, String arg2){
        return "Deleting " + arg1 + " with id: " + arg2 + ".";
    }

    public static String errorDatabaseDeleteMessage(String arg1, String arg2, String arg3){
        return "Error in database when trying to delete " + arg1 + " with id " + arg2 + ". Error: " + arg3 + ".";
    }
}
