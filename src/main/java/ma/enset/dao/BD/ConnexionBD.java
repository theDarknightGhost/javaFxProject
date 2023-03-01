package ma.enset.dao.BD;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionBD {
    private static Connection con ;
    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
          con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_stock","root", "");

        }catch(Exception ex){
           System.out.print(ex);
        }
    }

    public static Connection getConnetionBD(){
        try{
            return con ;
        }catch(Exception ex){
            return null ;
        }
    }

    public static void main(String[] args) {
        System.out.print(ConnexionBD.getConnetionBD());
    }
}
