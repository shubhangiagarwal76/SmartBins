package com.admin_home.server;

import com.admin_home.client.*;
import com.admin_home.client.Driver;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.postgresql.util.PSQLException;

import java.io.*;
import java.net.*;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Postgreconnection extends RemoteServiceServlet implements DBConnection {
    private Connection con = null;

    //CONSTRUCTOR
    public Postgreconnection() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SmartBins", "postgres", "behenchod101");


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    //Home list
    public ArrayList<Details> authenticateDetails(long sid, String lname) {
        /*Admin_home a = new Admin_home();*/
        ArrayList<Details> det = new ArrayList<Details>();
        try {
            PreparedStatement driver = con.prepareStatement("\n" +
                    "SELECT \"Dustbin_ID\", \"Location_name\", \"Status\", \"F_Name\", \"Last_Name\",\"Mobile_No\", \"Driver\".\"Driver_ID\" FROM \"Dustbin\", \"Location\", \"Driver\" WHERE \"Driver\".\"Driver_ID\" = \"Location\".\"Driver_ID\" AND \"Location\".\"Driver_ID\" = \"Dustbin\".\"Driver_ID\" AND \"Driver\".\"Staff_ID\"=? AND \"Location\".\"Location_name\"=?;");
            driver.setLong(1, sid);
            driver.setString(2, lname);
            ResultSet rs = driver.executeQuery();

            System.out.println("Drivers Table is");
            while (rs.next()) {
                det.add(new Details(rs.getLong(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getLong(6), rs.getString(7)));
            }
        } catch (SQLException f) {
            f.printStackTrace();
        }
        return det;
    }

    //MY COPY PASTED WORK WITH CONTACT PRANKUR
    //CONTACT LIST
    public ArrayList<Contact> authenticateContact(long sid) {
        /*Admin_home a = new Admin_home();*/
        ArrayList<Contact> cont = new ArrayList<Contact>();
        try {
            PreparedStatement driver = con.prepareStatement("SELECT \"Driver\".\"Driver_ID\", \"Driver\".\"F_Name\", \"Last_Name\",\"Location\".\"Location_name\",\"Driver\".\"Mobile_No\"\n" +
                    "FROM \"Driver\", \"Staff\",\"Location\"\n" +
                    "WHERE \"Driver\".\"Staff_ID\" = \"Staff\".\"Staff_ID\" AND \"Location\".\"Staff_ID\"=\"Staff\".\"Staff_ID\" AND\n" +
                    "      \"Driver\".\"Driver_ID\"=\"Location\".\"Driver_ID\" AND\n" +
                    "        \"Location\".\"Staff_ID\" = ?;");
            driver.setLong(1, sid);
            ResultSet rs = driver.executeQuery();

            System.out.println("Drivers Table is with");
            while (rs.next()) {
                cont.add(new Contact(rs.getString(2), rs.getString(3), rs.getLong(1), rs.getLong(5), rs.getString(4)));
            }
        } catch (SQLException f) {
            f.printStackTrace();
        }
        return cont;
    }

    //LOCATION DROPDOWN
    public ArrayList<Location> locationList(long sid) {
        ArrayList<Location> locations = new ArrayList<Location>();
        try {
            PreparedStatement loc = con.prepareStatement("SELECT DISTINCT \"Location_name\" FROM \"Location\" WHERE \"Staff_ID\"=?");
            loc.setLong(1, sid);
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
    public User authenticateUser(long name, String pass, int n) {
        User user = null;
        try {
            PreparedStatement passrs = con.prepareStatement("Select \"Staff\".\"Password\" from \"Staff\" where \"Staff_ID\"=?");
            passrs.setLong(1, name);
            ResultSet rs = passrs.executeQuery();
            System.out.println("User id passed " + name + "password passed " + pass);
            //System.out.println("number of rows "+rs.getRow());
            if (rs.next()) {
                //rs.next();
                if (rs.getString(1).equals(pass)) {
                    user = new User(name, pass, 1);
                    System.out.println("password " + rs.getString(1));
                } else {
                    user = new User(name, pass, 0);
                    System.out.println("password wrong " + rs.getString(1));
                }
            } else {
                user = new User(name, pass, -1);
                System.out.println("password null ");
            }
        } catch (PSQLException pe) {
            System.out.println(pe);
        } catch (SQLException e) {
            System.out.println(e);
        }

        return user;
    }

    // SENDING SMS
    public String sendSms(String phoneno) {
        try {
            // Construct data
            String authkey = "18sUVSjxhSo-ok70P2kfLE9FIkGSax0ISKq1KBR2zr";
            String senderId = "TXTLCL";
            String route = "4";
            URLConnection myURLConnection = null;
            URL myURL = null;
            BufferedReader reader = null;
            //String encoded_message=URLEncoder.encode(Pass);
            String mainUrl = "https://api.textlocal.in/send/?";
            StringBuilder sbPostData = new StringBuilder(mainUrl);
            sbPostData.append("apikey=" + authkey);
            sbPostData.append("&numbers=" + phoneno);
            sbPostData.append("&message=" + "EMPTY YOUR DUSTBIN");
            sbPostData.append("&route=" + route);
            sbPostData.append("&sender=" + senderId);

            mainUrl = sbPostData.toString();
            try {
//prepare connection
                myURL = new URL(mainUrl);
                myURLConnection = myURL.openConnection();
                myURLConnection.connect();
                reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
                System.out.println("MESSAGE SENT SUCCESSFULLY " + phoneno);
//finally close connection
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }

    //ADD DRIVER
    public Driver insertInfo(String f_name, String l_name, long mobile_no, long aadhar, Date DOB, String email, String gender, String address, long sid, String location)
    {
        Driver d1 = null;

        try {
            //java.util.Date to String
            Date d = DOB;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(d);

            //INSERTING IN DRIVERS TABLE QUERY
            PreparedStatement p = con.prepareStatement("INSERT INTO \"Driver\"(\"F_Name\", \"Last_Name\", \"Mobile_No\", \"Aadhar\", \"Staff_ID\", \"gender\", \"Email_ID\", \"Password\", \"dob\")\n" +
                    "VALUES (?,?,?, ?,?, ?, ?, ?,?);");
            p.setString(1, f_name);
            p.setString(2, l_name);
            p.setLong(3, mobile_no);
            p.setLong(4, aadhar);
            p.setLong(5, sid);
            p.setString(6, gender);
            p.setString(7, email);

            //AUTOMATIC SETTING OF PASSWORD
            String pass = f_name+"123";
            p.setString(8, pass);

            //STRING TO JAVA.SQL.DATE
            java.sql.Date dob = java.sql.Date.valueOf(strDate);
            p.setDate(9, dob );
            int i = p.executeUpdate();


            //RETURNING DRIVER ID AND PASSWORD
            PreparedStatement p1 = con.prepareStatement("Select \"Driver_ID\", \"Password\" From \"Driver\" where \"Mobile_No\"=?;");
            p1.setLong(1, mobile_no);
            ResultSet rs = p1.executeQuery();
            rs.next();
            d1 = new Driver(rs.getLong(1), rs.getString(2));


            //INSERTING INTO LOCATION TABLE OF ADD DRIVER
            PreparedStatement p2 = con.prepareStatement("insert into \"Location\"(\"Staff_ID\", \"Driver_ID\", \"Location_name\") VALUES(?, ?, ?);");
            p2.setLong(1,sid);
            p2.setLong(2, rs.getLong(1) );
            p2.setString(3,location);
            p2.executeUpdate();
        }
        catch(PSQLException exp)
        {
            System.out.println(exp);
            d1=new Driver(0000, "");

        }
        catch (SQLException e) {
            System.out.println(e);
            d1=new Driver(0000, "");

        }
        catch(Exception exp)
        {
            System.out.println(exp);
            d1=new Driver(0000, "");

        }

        return d1;
    }

    //ADD DUSTBIN
    public String insertDustbin(long drid, double cap) {
        String r = null;
        try {
            PreparedStatement d = con.prepareStatement("INSERT INTO \"Dustbin\"(\"Driver_ID\", \"Capacity\")\n" +
                    "VALUES (?,?);");
            d.setLong(1, drid);
            d.setDouble(2, cap);
            System.out.println(drid + " " + cap);
            int i = d.executeUpdate();
            r = "S";

        }

    catch(PSQLException exception)
    {
        System.out.println(exception);
        r = "E";
    }
    catch (SQLException e)
    {
            e.printStackTrace();
            r = "E";
    }
    catch (Exception e)
    {
        System.out.println("PLSQL EXCEPTIION HANDLE");
        e.printStackTrace();
        r = "E";
    }

        return r;
    }
}





