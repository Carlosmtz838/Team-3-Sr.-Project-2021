/*************************************************
* Carlos Martinez
* Team 3
* Tattoo Parlor project
* Customer class to Login and Register Customers
**************************************************/
import java.sql.*;
import java.util.Random;

public class Customer {
    
    //created variables for use in Customer class
    private int Custid;
    private String Fname;
    private String Lname;
    private String User;
    private String Pass;
    private Boolean Login = false;
    
    //default values
    public Customer(){
        Custid = 0;
        Fname = "";
        Lname = "";
        User = "";
        Pass = "";
    }
    
    //*************GET & SET METHODS*******************************//
    public int getCustid() { return Custid; }
    public void setCustid(int custid) { Custid=custid; }

    public String getFname() { return Fname; }
    public void setFname(String fname) { Fname=fname; }
    
    public String getLname() { return Lname; }
    public void setLname(String lname) { Lname=lname; }
    
    public String getUser() { return User; }
    public void setUser(String user) { User=user; } 
        
    public String getPass() { return Pass; }
    public void setPass(String pass) { Pass=pass; }
    
    public Boolean getUserLogin() { return Login; }
    

    //*****************Login Method********************************//
    public void selectCust(String user, String pass)
    {
        User = user;
        Pass = pass;

        try{
            // Establish a connection 
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/CarlosPC3/Desktop/Team3Tattoo/TattooParlorDatabase.accdb");  
            System.out.println("Database connected"); 

            // Create a statement 
            Statement statement = con.createStatement();
            
            //Runs the statement
            String sql = "SELECT * FROM Customer WHERE Username = '"+getUser()+"' AND Password = '"+getPass()+"' ";
            System.out.println(sql);
            statement.executeQuery(sql);
            
            
            ResultSet rs;
            rs = statement.executeQuery(sql);
            
            //if user and pass both match will allow user to be logged in
            while (rs.next()){
                User = rs.getString(4);
                Pass = rs.getString(5);
                
                if(user.equals(rs.getString(4)) && pass.equals(rs.getString(5)))
                {
                    Login = true;
                }
            }
            con.close(); 
            }
        catch(Exception e){
            System.out.print(e);
        }             
    }


    
    //***************************Registration Method************************//
    public void insertCust(String fname, String lname, String user, String pass) {
            //placeholder for creating new customer ID, might not be the best since duplicates may occur
            Random num = new Random();
            int upperbound = 1000;
            setCustid(num.nextInt(upperbound)); 

            setFname(fname);       
            setLname(lname);
            setUser (user);
            
            Pass= pass;
            
            //Establish a connection
            try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/CarlosPC3/Desktop/Team3Tattoo/TattooParlorDatabase.accdb");  
            System.out.println("Database connected"); 

            //Create statement and runs it
            Statement statement = con.createStatement();
            String sql = "INSERT into Customer (CustomerID, FName, LName, Username, Password)"
                    + "values('"+Custid+"', "+" '"+Fname+"', "+" '"+Lname+"', "+" '"+User+"', "+" '"+Pass+"')";
            System.out.println(sql);
            statement.executeUpdate(sql);				
	    System.out.println("Record Inserted Successfully");
            
            //close connection
            con.close();
                         
        }catch(Exception e){
            System.out.print(e);
        }
    }
    
    }
