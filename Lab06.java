/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab06;
import java.sql.*; 
import java.util.Scanner;
/**
 *
 * @author Talha Zubair
 */
public class Lab06 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{  
Class.forName("com.mysql.jdbc.Driver");  
//here sonoo is database name, root is username and password
            try (Connection con = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/e-cafe","root","")) {
                //here sonoo is database name, root is username and password
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("select * from items");
                while(rs.next())
                    System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
                
                //user order
                
                System.out.println("Add order:");
                Scanner order=new Scanner(System.in);
                int ord=order.nextInt(); 
                ResultSet rs2=stmt.executeQuery("select iitem_id,item_name,item_price,time from items where iitem_id= "+ord);
                while(rs2.next()){
                System.out.println("Order Name: "+rs2.getString(2));
                 
                System.out.println("time of delievery: ");
                 int time=order.nextInt();
                 System.out.println("Address if delievered: ");
                 String address=order.next();
                 //insert statement
                 PreparedStatement stmt2=con.prepareStatement("insert into order_place "+" (item_id,name,price,time,address,dish_time)"+" values(?,?,?,?,?,?)");  
                 stmt2.setInt(1,rs2.getInt(1));//1 specifies the first parameter in the query  
                 stmt2.setString(2,rs2.getString(2));  
                 stmt2.setString(3,rs2.getString(3));
                 stmt2.setInt(4,time);  
                 stmt2.setString(5,address);
                 stmt2.setString(6,rs2.getString(4));
                 int i=stmt2.executeUpdate();  
                System.out.println("Bill Generated: "+ rs2.getString(3)+" rps");
                System.out.println("Time Needed: "+ rs2.getString(4)+" mins");
                
                 }
            }
}catch( ClassNotFoundException | SQLException e){ System.out.println(e);}  

    }
    
}

