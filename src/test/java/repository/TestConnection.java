package repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.fail;


public class TestConnection {

    Connection conn = null;

    @Test
    public void connection() {
        try {
            // db parameters
           String url = "jdbc:sqlite::memory";
//            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            Assert.assertTrue(conn != null);

        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    public void createNewDatabase(){
        String testNameOne = "Project";
        String testNameTwo = "Vulcan";
        String url = "jdbc:sqlite::vulcanDb";
        try{
            Connection conn = DriverManager.getConnection(url);
            if(conn != null){
                Statement statement = conn.createStatement();
                statement.setQueryTimeout(30);  // set timeout to 30 sec.

                statement.executeUpdate("drop table if exists person");
                statement.executeUpdate("create table person (id integer, name string)");
                statement.executeUpdate("insert into person values(1, '"+testNameOne+"')");
                statement.executeUpdate("insert into person values(2, '"+testNameTwo+"')");
                ResultSet rs = statement.executeQuery("select * from person");
                while(rs.next())
                {
                    Assert.assertTrue(rs.getString("name").contains(testNameOne));
                    rs.next();
                    Assert.assertTrue(rs.getString("name").contains(testNameTwo));
                }

            }
        } catch (SQLException e){
            System.out.println(e);
            fail();
        }
    }

    @After
    public void destroyInMemoryDB(){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            fail();
        }
    }
}
