package com.admin_home.server;

import com.admin_home.client.*;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.client.*;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;
//import static org.postgresql.core.SqlCommandType.SELECT;
public class Postgreconnection extends RemoteServiceServlet implements DBConnection {
    private Connection con = null;

    public Postgreconnection() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SmartBins", "postgres", "12345");


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
        //Home list
        public ArrayList<Details> authenticateDetails(long sid,String lname)
        {
            /*Admin_home a = new Admin_home();*/
            ArrayList<Details> det = new ArrayList<Details>();
            try {
                PreparedStatement driver = con.prepareStatement("\n" +
                        "SELECT \"Dustbin_ID\", \"L_Name\", \"Status\", \"F_Name\", \"Last_Name\",\"Mobile_No\", \"Driver\".\"Driver_ID\" FROM \"Dustbin\", \"Location\", \"Driver\" WHERE \"Driver\".\"Driver_ID\" = \"Location\".\"Driver_ID\" AND \"Location\".\"Driver_ID\" = \"Dustbin\".\"Driver_ID\" AND \"Driver\".\"Staff_ID\"=? AND \"Location\".\"L_Name\"=?;");
                driver.setLong(1,sid);
                driver.setString(2,lname);
                ResultSet rs = driver.executeQuery();

                System.out.println("Drivers Table is");
                while (rs.next()) {
                    det.add(new Details( rs.getLong(1) , rs.getString(2),  rs.getInt(3), rs.getString(4), rs.getString(5),rs.getLong(6) , rs.getString(7) ));
                }
            } catch (SQLException f) {
                f.printStackTrace();
            }
            return det;
        }

        //MY COPY PASTED WORK WITH CONTACT PRANKUR

    public ArrayList<Contact> authenticateContact(long sid)
    {
        /*Admin_home a = new Admin_home();*/
        ArrayList<Contact> cont = new ArrayList<Contact>();
        try {
            PreparedStatement driver = con.prepareStatement("SELECT \"Driver\".\"Driver_ID\", \"Driver\".\"F_Name\", \"Last_Name\",\"Location\".\"L_Name\",\"Driver\".\"Mobile_No\"\n" +
                    "FROM \"Driver\", \"Staff\",\"Location\"\n" +
                    "WHERE \"Driver\".\"Staff_ID\" = \"Staff\".\"Staff_ID\" AND \"Location\".\"Staff_ID\"=\"Staff\".\"Staff_ID\" AND\n" +
                    "      \"Driver\".\"Driver_ID\"=\"Location\".\"Driver_ID\" AND\n" +
                    "        \"Location\".\"Staff_ID\" = ?;");
            driver.setLong(1,sid);
            ResultSet rs = driver.executeQuery();

            System.out.println("Drivers Table is with");
            while (rs.next()) {
                cont.add(new Contact(rs.getString(2),  rs.getString(3), rs.getLong(1), rs.getLong(5), rs.getString(4) ));
            }
        } catch (SQLException f) {
            f.printStackTrace();
        }
        return cont;
    }
    public ArrayList<Location> locationList(long sid)
    {
        ArrayList<Location> locations = new ArrayList<Location>();
        try {
            PreparedStatement loc = con.prepareStatement("SELECT \"L_Name\" FROM \"Location\" WHERE \"Staff_ID\"=?");
            loc.setLong(1,sid);
            ResultSet rs = loc.executeQuery();
            System.out.println("Location");
            while (rs.next()) {
                locations.add(new Location(rs.getString(1)));
            }
        } catch (SQLException f) {
            f.printStackTrace();
        }
        return locations;
    }
    //SERVER SIDE AUTHENTICATION LOGIN
    public User authenticateUser(long name, String pass, int n)
    {
        User user= null;
        try {
            PreparedStatement passrs = con.prepareStatement("Select \"Staff\".\"Password\" from \"Staff\" where \"Staff_ID\"=?");
            passrs.setLong(1, name);
            ResultSet rs = passrs.executeQuery();
            System.out.println("User id passed "+name+"password passed "+pass);
            //System.out.println("number of rows "+rs.getRow());
            if(rs.next()) {
                //rs.next();
                if (rs.getString(1).equals(pass)) {
                    user = new User(name, pass, 1);
                    System.out.println("password "+rs.getString(1));
                } else {
                    user = new User(name, pass, 0);
                    System.out.println("password wrong " + rs.getString(1));
                }
            } else  {
                user = new User(name, pass, -1);
                System.out.println("password null ");
            }
        }
        catch(PSQLException pe){System.out.println(pe);}
        catch (SQLException e){ System.out.println(e);}

        return user;
        }
    }





