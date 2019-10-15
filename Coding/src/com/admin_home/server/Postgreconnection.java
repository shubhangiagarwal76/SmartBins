package com.admin_home.server;

import com.admin_home.client.*;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.client.*;
import java.sql.*;
import java.util.ArrayList;

import static org.postgresql.core.SqlCommandType.SELECT;


public class Postgreconnection extends RemoteServiceServlet implements DBConnection {
    private Connection con = null;

    public Postgreconnection() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SmartBins", "postgres", "behenchod101");


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    /*public User authenticateUser(String name, String pass) {
        User user = null;
        try {
            PreparedStatement driver = con.prepareStatement("select * from public.\"Driver\"");
            // PreparedStatement staff = con.prepareStatement("select * from public.\"Staff\"");
            //PreparedStatement dustbin = con.prepareStatement("select * from public.\"Dustbin\"");
            //PreparedStatement location = con.prepareStatement("select * from public.\"Location\"");
            ResultSet rs = driver.executeQuery();
            // ResultSet rs1 = staff.executeQuery();
            // ResultSet d1 = dustbin.executeQuery();
            // ResultSet l1 = location.executeQuery();
            System.out.println("Drivers Table is");
            while (rs.next()) {
                user = new User(rs.getString(3), rs.getString(7));
            }
          *//*  System.out.println("Staff table is");
            while ((rs1.next())) {
                System.out.println(rs1.getLong(1) + " " + rs1.getString(2) + " " + rs1.getString(3) + " " + rs1.getLong(4) + " " + rs1.getLong(5) + " " + rs1.getString(6));
            }
            System.out.println("Dustbin Table is");
            while ((d1.next())) {
                System.out.println(d1.getLong(1) + " " + d1.getLong(2) + " " + d1.getInt(3));
            }
            System.out.println("Location table is");
            while (l1.next()) {
                System.out.println(l1.getLong(1) + " " + l1.getLong(2) + " " + l1.getLong(3) + " " + l1.getString(4));*//*
            //}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }*/
        public ArrayList<Details> authenticateDetails()
        {
            /*Admin_home a = new Admin_home();*/
            ArrayList<Details> det = new ArrayList<Details>();
            try {
                PreparedStatement driver = con.prepareStatement("\n" +
                        "SELECT \"Dustbin_ID\", \"L_Name\", \"Status\", \"F_Name\", \"Last_Name\", \"Driver\".\"Driver_ID\" FROM \"Dustbin\", \"Location\", \"Driver\" WHERE \"Driver\".\"Driver_ID\" = \"Location\".\"Driver_ID\" AND \"Location\".\"Driver_ID\" = \"Dustbin\".\"Driver_ID\" AND \"Driver\".\"Staff_ID\"='111111' AND \"Location\".\"L_Name\"='GHGF';");
                ResultSet rs = driver.executeQuery();

                System.out.println("Drivers Table is");
                while (rs.next()) {
                    det.add(new Details( rs.getLong(1) , rs.getString(2),  rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6) ));
                }
            } catch (SQLException f) {
                f.printStackTrace();
            }
            return det;
        }

        //MY COPY PASTED WORK WITH CONTACT PRANKUR

    public ArrayList<Contact> authenticateContact()
    {
        /*Admin_home a = new Admin_home();*/
        ArrayList<Contact> cont = new ArrayList<Contact>();
        try {
            PreparedStatement driver = con.prepareStatement("SELECT \"Driver\".\"Driver_ID\", \"Driver\".\"F_Name\", \"Last_Name\",\"Location\".\"L_Name\",\"Driver\".\"Mobile_No\"\n" +
                    "FROM \"Driver\", \"Staff\",\"Location\"\n" +
                    "WHERE \"Driver\".\"Staff_ID\" = \"Staff\".\"Staff_ID\" AND \"Location\".\"Staff_ID\"=\"Staff\".\"Staff_ID\" AND\n" +
                    "      \"Driver\".\"Driver_ID\"=\"Location\".\"Driver_ID\" AND\n" +
                    "        \"Location\".\"Staff_ID\" = 111111;");
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



}



