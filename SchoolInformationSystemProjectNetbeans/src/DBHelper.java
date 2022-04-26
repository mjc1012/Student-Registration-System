
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JTextField;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp2020
 */
public class DBHelper {
    Connection con = null;
    Statement stmt = null;
    
    public void connectDB() throws SQLException{
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/StudentInformationSystem;create=true", "root", "root");
        System.out.println("Connected to database");
    }
    
    public boolean insertAccount (String username, String password){
        boolean flag = false;
        int lastid = 0;
        try {
            stmt = con.createStatement();
            String sql = "Select max(idno) From APP.TBLACCOUNTINFO";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                lastid = rs.getInt(1);
                lastid++;
            }
            sql = "Insert into APP.TBLACCOUNTINFO values("+lastid+",'"+username+"', '"+password+"')";
            if(stmt.executeUpdate(sql) == 1)
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flag;
    }
    
    public boolean updateAccount (int id, String username, String password){
        boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "Update APP.TBLACCOUNTINFO set username = '"+username+"', password = '"+password+"' where idno = "+id+" ";
            if(stmt.executeUpdate(sql) == 1)
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flag;
    }
    
    public int getIdOfAccount(String username, String password){
        boolean flag = false;
        int id = 0;
        try {
            stmt = con.createStatement();
            String sql = "Select idno From App.TBLACCOUNTINFO where username = '"+username+"' And password = '"+password+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
                id = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    
    public boolean deleteAccount (int id){
         boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "Delete from APP.TBLACCOUNTINFO where idno = "+id+" ";
            if(stmt.executeUpdate(sql) == 1)
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flag;
    }
    
    public boolean verifyAccount (String username, String password){
        boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "Select * From App.TBLACCOUNTINFO where username = '"+username+"' And password = '"+password+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flag;
    }
    
    public boolean searchUsername (String username){
        boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "Select * From App.TBLACCOUNTINFO where username = '"+username+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flag;
    }
    
     public ResultSet displayStudent (){
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            String sql = "Select * From APP.TBLSTUDENTINFO";
            rs = stmt.executeQuery(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
     
     public ResultSet displayStudent (String lname){
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            String sql = "Select * From APP.TBLSTUDENTINFO where LASTNAME = '"+lname+"'";
            rs = stmt.executeQuery(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
     
    public boolean searchStudent (int idno, String fname, String mname, String lname, String bdate, String gender, String email, String contactno, String street, String barangay, String city, String province){
        boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "Select * From App.TBLSTUDENTINFO where idno = "+idno+" And firstname = '"+fname+"' And middlename = '"+mname+"' And lastname = '"+lname+"' And birthdate = '"+bdate+"' And gender = '"+gender+"' And email = '"+email+"' And street = '"+street+"' And barangay = '"+barangay+"' And city = '"+city+"' And province = '"+province+"' And contactno = '"+contactno+"' ";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flag;
    }
     
    public ResultSet displayTeacher (){
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            String sql = "Select * From APP.TBLTEACHERINFO";
            rs = stmt.executeQuery(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
     
     public ResultSet displayTeacher (String lname){
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            String sql = "Select * From APP.TBLTEACHERINFO where LASTNAME = '"+lname+"'";
            rs = stmt.executeQuery(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
     
     public boolean searchTeacher (int idno, String fname, String mname, String lname, String bdate, String gender, String email, String contactno, String street, String barangay, String city, String province){
        boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "Select * From App.TBLTEACHERINFO where idno = "+idno+" And firstname = '"+fname+"' And middlename = '"+mname+"' And lastname = '"+lname+"' And birthdate = '"+bdate+"' And gender = '"+gender+"' And email = '"+email+"' And street = '"+street+"' And barangay = '"+barangay+"' And city = '"+city+"' And province = '"+province+"' And contactno = '"+contactno+"' ";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flag;
    }
    
    public boolean insertStudent (int idno, String fname, String mname, String lname, String bdate, String gender, String email, String contactno, String street, String barangay, String city, String province){
        boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "Insert into APP.TBLSTUDENTINFO values("+idno+", '"+fname+"', '"+mname+"', '"+lname+"', '"+bdate+"', '"+gender+"', '"+email+"', '"+street+"', '"+barangay+"', '"+city+"', '"+province+"', '"+contactno+"')";
            if(stmt.executeUpdate(sql) == 1)
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flag;
    }
    
    public boolean updateStudent (int idno, String fname, String mname, String lname, String bdate, String gender, String email, String contactno, String street, String barangay, String city, String province){
        boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "Update APP.TBLSTUDENTINFO set firstname = '"+fname+"', middlename = '"+mname+"', lastname = '"+lname+"', birthdate = '"+bdate+"', gender = '"+gender+"', email = '"+email+"', street = '"+street+"', barangay = '"+barangay+"', city = '"+city+"', province = '"+province+"', contactno = '"+contactno+"' where idno = "+idno+" ";
            if(stmt.executeUpdate(sql) == 1)
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flag;
    }
    
    public boolean deleteStudent (int idno){
         boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "Delete from APP.TBLSTUDENTINFO where idno = "+idno+" ";
            if(stmt.executeUpdate(sql) == 1)
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flag;
    }
    
    public boolean insertTeacher (int idno, String fname, String mname, String lname, String bdate, String gender, String street, String barangay, String city, String province, String email, String contactno){
        boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "Insert into APP.TBLTEACHERINFO values("+idno+", '"+fname+"', '"+mname+"', '"+lname+"', '"+bdate+"', '"+gender+"', '"+email+"', '"+street+"', '"+barangay+"', '"+city+"', '"+province+"', '"+contactno+"')";
            if(stmt.executeUpdate(sql) == 1)
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flag;
    }
    
    public boolean updateTeacher (int idno, String fname, String mname, String lname, String bdate, String gender, String street, String barangay, String city, String province, String email, String contactno){
        boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "Update APP.TBLTEACHERINFO set firstname = '"+fname+"', middlename = '"+mname+"', lastname = '"+lname+"', birthdate = '"+bdate+"', gender = '"+gender+"', email = '"+email+"', street = '"+street+"', barangay = '"+barangay+"', city = '"+city+"', province = '"+province+"', contactno = '"+contactno+"' where idno = "+idno+" ";
            if(stmt.executeUpdate(sql) == 1)
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flag;
    }
    
    public boolean deleteTeacher (int idno){
         boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "Delete from APP.TBLTEACHERINFO where idno = "+idno+" ";
            if(stmt.executeUpdate(sql) == 1)
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flag;
    }
    
    public void disconnectDb(){
        
            try {
                if(con != null)
                    con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

}
