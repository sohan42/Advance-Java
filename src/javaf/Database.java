package javaf;

import java.sql.*;
import javax.sql.rowset.*;

class Database{
    private String url = "jdbc:mysql://localhost:3306/a";
    private String username = "root";
    private String password = "";
    
    void Display(){
        try {
            Connection conn = DriverManager.getConnection(url,username,password);
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT *FROM students");
            
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("Id: "+id+", Name: "+name);
            }
            rs.close();
            stat.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    void update(){        
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stat = conn.createStatement();
            
            String sql = "INSERT INTO students(name,age,grade) VALUES ('Amrit',22,'A')";
            
            stat.executeUpdate(sql);
            
            System.out.println("Data inserted Successfully!");
            
            stat.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    void myPrepareUpdate(){
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO employees (name,salary) VALUES (?,?)"; //SQL query to insert new employee
            PreparedStatement ps = conn.prepareStatement(sql);//Create PreparedStatement object
            
            //set parameter
            ps.setString(1, "Raj");
            ps.setDouble(2, 50000);
            
            ps.executeUpdate(); //Execute the insert query
            System.out.println("Data inserted successfuly!");
            
            ps.close();
            conn.close();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    void callProcedure(){
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "{CALL insertEmployee(?,?)}";
            
            CallableStatement cs = conn.prepareCall(sql);
            
            cs.setString(1, "Abhi");
            cs.setDouble(2, 45000.00);
            
            cs.executeUpdate();
            System.out.println("Data inserted successfully!");
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    void scroll(){
        try{
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String sql = "SELECT id,name,salary FROM employees";
        ResultSet rs = stat.executeQuery(sql);
        
        //Move the cursor to the last row
        rs.last();
        System.out.println("Last row: ID="+rs.getInt("id")+ ", Name="+rs.getString("name")+", Salary="+rs.getDouble("salary"));
        
        //Move the cursor to the last row
        rs.first();
        System.out.println("First row: ID="+rs.getInt("id")+ ", Name="+rs.getString("name")+", Salary="+rs.getDouble("salary"));
        
        //Move the cursor to the second row
        rs.absolute(2);
        System.out.println("Row 2: ID="+rs.getInt("id")+ ", Name="+rs.getString("name")+", Salary="+rs.getDouble("salary"));
        
        //Update the name of the current row
        rs.updateString("name", "Rajan");
        rs.updateRow();
        
        //insert new row
        rs.moveToInsertRow();
        rs.updateString("name", "Shyam");
        rs.updateString("salary", "48000");
        rs.insertRow();
        
        //Delete the third row
        rs.absolute(3);
        rs.deleteRow();
        
        rs.close();
        stat.close();
        conn.close();
    } catch (SQLException ex) {
            System.out.println(ex);
        }
    }  
    
    void rowSet(){
        try {
            JdbcRowSet rose = RowSetProvider.newFactory().createJdbcRowSet();
            
            //set database connection propersties
            rose.setUrl(url);
            rose.setUsername(username);
            rose.setPassword(password);
            
            //set the sql query
            rose.setCommand("SELECT id,name,salary FROM employees");
            
            rose.execute();
            
            //iterate 
           /*while(rose.next()){
               int id = rose.getInt("id");
               String name = rose.getString("name");
               double salary = rose.getInt("salary");
               System.out.println("Id: "+id+", Name: "+name+", Salary: "+salary);
               //System.out.println("Id: "+rose.getInt("id")+", Name: "+rose.getString("name")+", Salary: "+rose.getDouble("salary"));
           } */
         
           //Update third row
           /*rose.absolute(3);
           rose.updateString("name","Ankit");
           rose.updateRow();*/
           
           //Insert new row
           /*rose.moveToInsertRow();
           rose.updateString("name", "Alina");
           rose.updateDouble("salary", 53000);
           rose.insertRow();*/
           
           //delete fourth row
           rose.absolute(4);
           rose.deleteRow();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        /*In main:
            Database db = new Database();
            db.rowSet();
        */
    }
}
