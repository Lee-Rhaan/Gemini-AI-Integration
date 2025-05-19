package com.ai.geminiIntegration.util;

import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.ai.geminiIntegration.util.ApplicationConstants.*;

/**
 * This class contains the JDBC Database Operation Logic
 */
public class AIRepository {

    private static AIRepository aiRepositoryInstance = null;
    private static final Object threadLock = new Object();
    Connection connection;

    /**
     * Create object and opens a connection to the database
     */
    public AIRepository() {
        openConnection();
    }

    public void openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);

            if (connection == null) {
                System.err.println("DBConnect: getConnection: connection null ");
            }

        } catch (Exception e) {
            System.err.println("DBConnect: getConnection: exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Return an instance of AIRepository class
     * @return aiRepository instance
     */
    public static AIRepository getInstance() {
        synchronized (threadLock) {
            return aiRepositoryInstance == null ? aiRepositoryInstance = new AIRepository() : aiRepositoryInstance;
        }
    }

    /**
     * Closes a database connection
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.err.println("DBConnect::closeConnection: Exception: " + e.getMessage());
        }
    }

    /**
     * Cleans up system resources after database connection closes
     * @throws Throwable
     */
    protected void finalize() throws Throwable {
        closeConnection();
        super.finalize();
    }

    /**
     * Generic database method for retrieving sensitive AI information
     *
     * @param key information reference
     * @return requested sensitive value
     */
    public String retrieveAIInformation(String key) {
        if(StringUtils.isEmpty(key) || "null".equals(key)) {
            return "Cannot retrieve DB Value with an empty or null key";
        }
        String value = null;
        String query = "select config_value from gemini_config where config_key=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, key);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                value = rs.getString(1);
            }

        } catch (Exception exception){
            System.out.println(exception);
        }

        return value;
    }
}
