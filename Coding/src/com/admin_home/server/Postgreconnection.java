package com.admin_home.server;

import com.admin_home.client.Admin_homeService;
import com.admin_home.client.DBConnection;
import com.admin_home.client.Details;
import com.admin_home.client.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.sql.*;

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
        public Details authenticateDetails()
        {
            Details det = null;
            try {
                PreparedStatement driver = con.prepareStatement("select * from public.\"Driver\"");
                ResultSet rs = driver.executeQuery();
                System.out.println("Drivers Table is");
                while (rs.next()) {
                    det = new Details( rs.getLong(1) , rs.getString(3),  rs.getString(4) );
                }
            } catch (SQLException f) {
                f.printStackTrace();
            }
            return
                    det;
        }
        

    }



