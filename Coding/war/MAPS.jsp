<%--
  Created by IntelliJ IDEA.
  User: QUBIT
  Date: 12/28/2019
  Time: 5:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.PrintWriter" %>
<%@page import="java.io.BufferedReader"%>
<%@page import ="java.io.IOException" %>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.URLConnection"%>
<%@page import=" java.net.URLEncoder"%>
<%@ page import="sun.security.util.Password" %>
<%@ page import="org.omg.CORBA.Request" %>
<%@ page import="javax.xml.ws.Response" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Maps On Click</title>

    <meta name="viewport" content="initial-scale=1.0">
    <meta charset="utf-8">
    <style>
        /* Always set the map height explicitly to define the size of the div
         * element that contains the map. */
        #map {
            height: 100%;
        }
        /* Optional: Makes the sample page fill the window. */
        #heading{
            position: center;
            font-family: "Arial Black";
            color: darkcyan;
        }
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }

    </style>

</head>
<body>
<h1 id="heading">GOOGLE MAP</h1>
<%--<% String Pass = null;--%>
<%--    long l;--%>
<%--    long var= 0;--%>
<%--    String temp="Dustbin";--%>
<%--    List<Double> lat= new ArrayList<>();--%>
<%--    List<Double> log= new ArrayList<>();--%>
<%--    double lata[]=new double[100];--%>
<%--    double loga[]=new double[100];--%>
<%--    PrintWriter Out = response.getWriter();--%>
<%--    try{--%>

<%--        var  = Long.parseLong(request.getParameter("id"));--%>
<%--        //Out.println(var);--%>
<%--        Connection con = null;--%>
<%--        Class.forName("org.postgresql.Driver");--%>
<%--        con = DriverManager.getConnection("jdbc:postgresql://ec2-3-229-210-93.compute-1.amazonaws.com:5432/de7glaogm4jfro\\\", \\\"grofwmeazmakhx\\\", \\\"57a5efa8c7da81bd5191609ca480cfab6c6a511f866a2bf8f28a29636fad16cc");--%>
<%--        PreparedStatement n = con.prepareStatement("SELECT \"Status\", \"F_Name\", \"Last_Name\",\"Latitude_Coordinates\",\"Longitude_Coordinates\",\"Driver\".\"Driver_ID\" FROM \"Driver\", \"Dustbin\" WHERE \"Driver\".\"Driver_ID\"= \"Dustbin\".\"Driver_ID\" AND \"Staff_ID\"=?");--%>
<%--        n.setLong(1, var);--%>
<%--        ResultSet rs = n.executeQuery();--%>
<%--        int i=0;--%>
<%--        while (rs.next())--%>
<%--        {--%>
<%--            /*Pass = rs.getString("Password");--%>
<%--            Out.println(rs.getString("F_Name"));--%>
<%--            Out.println(rs.getDouble("Status"));--%>
<%--            lat.add(rs.getDouble("Latitude_Coordinates"));--%>
<%--            log.add(rs.getDouble("Longitude_Coordinates"));--%>
<%--            lata[i]=rs.getDouble("Latitude_Coordinates");--%>
<%--            loga[i]=rs.getDouble("Longitude_Coordinates");--%>
<%--            i++;*/--%>
<%--        }--%>
<%--    } catch (SQLException ex) {--%>
<%--        System.out.println(ex);--%>
<%--    }%>--%>

<div id="map"></div>
<script>var map;
function initMap() {
    var uluru1 = {lat: 28.619863, lng: 77.361824};
    var uluru2 = {lat: 28.619869, lng: 77.362557};
    var uluru3 = {lat: 28.619860, lng: 77.362048};
    var uluru4 = {lat: 28.619619, lng: 77.362522};
    var uluru5 = {lat: 28.619619, lng: 77.362068};
    var uluru6 = {lat: 28.618595, lng: 77.362472};
    var uluru7 = {lat: 28.619648, lng: 77.361820};
    var pic = '/resources/trash_can-512.png';
    map = new google.maps.Map(document.getElementById('map'),
        {
            center: {lat: 28.619863, lng: 77.361824},
            zoom: 18,
            mapTypeId: 'satellite'
        });
    var marker = new google.maps.Marker({position: uluru1, icon: pic, map: map,title:'Girls Hostel First Floor'});
    var marker = new google.maps.Marker({position: uluru2, icon: pic, map: map,title:'Boys Hostel First Floor'});
    var marker = new google.maps.Marker({position: uluru3,icon: pic, map: map,title:'Girls Hostel First Floor'});
    var marker = new google.maps.Marker({position: uluru4, icon: pic, map: map,title:'Boys Hostel Ground Floor'});
    var marker = new google.maps.Marker({position: uluru5, icon: pic, map: map,title:'Girls Hostel Ground Floor'});
    var marker = new google.maps.Marker({position: uluru6, icon: pic, map: map,title:'Academic Block'});
    var marker = new google.maps.Marker({position: uluru7, icon: pic, map: map,title:'Girls Hostel Ground Floor'});

    map.addListener('center_changed',function () {
        window.setTimeout(function () {
            map.panTo(marker.getPosition());
        },3000);
    });
}
</script>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA6NTzmJDphM56mmRW3Vr8EVJFVMFOkuZg&callback=initMap"
        async defer></script>
</body>
</html>
