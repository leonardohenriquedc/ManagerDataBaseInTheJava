package database;

import lombok.Getter;
import lombok.Setter;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

@Getter
@Setter
public class DatabasePreparingAndEstablishingConnection {

    private Connection connection = null;

    private Statement statement = null;

    public Statement createConnectionAndGetStatement(){

        if(getConnection() == null && getStatement() == null){

            try {

                Properties propos = loadProperties();

                String urlConnection = propos.getProperty("dburl");

                this.setConnection(DriverManager.getConnection(urlConnection, propos));

                this.setStatement(this.getConnection().createStatement());

            } catch (Exception e) {

                throw new RuntimeException(e);
            }
        }

        return this.getStatement();
    }

    public static Properties loadProperties(){

        try {

            FileInputStream propsFile = new FileInputStream("env.properties");

            Properties props = new Properties();

            props.load(propsFile);

            return props;

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    public void closeConnection(){

        if(this.getConnection() != null){

            try {

                this.getConnection().close();

            } catch (SQLException e) {

                throw new RuntimeException(e);
            }
        }
    }
}
