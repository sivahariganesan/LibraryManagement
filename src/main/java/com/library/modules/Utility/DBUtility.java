package com.library.modules.Utility;

import org.springframework.beans.factory.annotation.Value;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtility
{
    private static Connection connection = null;
    public static Connection getConnection()
    {
        if (connection != null)
            return connection;
        else
        {
            try
            {
                String driver="jdbc:h2:file:~/librarymemory2";
                String userName="user";
                String password="test";
                connection = DriverManager.getConnection(driver, userName, password);
            }
            catch (Exception e)
            {
                System.out.println("Exception occured"+e.getLocalizedMessage());
                e.printStackTrace();
            }
            return connection;
        }
    }
}




