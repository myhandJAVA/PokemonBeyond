package PokemonBeyond.run;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {
    public static Connection getConnection(){
        Properties prop = new Properties();
        Connection con = null;

        try {
            prop.load(
                    new FileReader("src/main/java/PokemonBeyond/run/connection-info.properties"));
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        /* connection을 닫는 개념은 별도로 분리
        *  - 실제로 닫는건 service 계층 */
        return con;
    }

    public static void close(Connection con) {
        try{
            if(con!=null && !con.isClosed()) con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void close(Statement stmt) {
        try{
            if(stmt!=null && !stmt.isClosed()) stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void close(ResultSet rset) {
        try{
            if(rset!=null && !rset.isClosed()) rset.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

