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


    <script src='https://api.mapbox.com/mapbox-gl-js/v1.4.1/mapbox-gl.js'></script>
    <link href='https://api.mapbox.com/mapbox-gl-js/v1.4.1/mapbox-gl.css' rel='stylesheet' />

</head>
<body>
<% String Pass = null;
    long l;
    long var= 0;
    String temp="Dustbin";
    List<Double> lat= new ArrayList<>();
    List<Double> log= new ArrayList<>();
    double lata[]=new double[100];
    double loga[]=new double[100];
    PrintWriter Out = response.getWriter();
    try{

        var  = Long.parseLong(request.getParameter("id"));


        Out.println(var);

        Connection con = null;
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SmartBins", "postgres", "12345");
        PreparedStatement n = con.prepareStatement("SELECT \"Status\", \"F_Name\", \"Last_Name\",\"Latitude_Coordinates\",\"Longitude_Coordinates\",\"Driver\".\"Driver_ID\" FROM \"Driver\", \"Dustbin\" WHERE \"Driver\".\"Driver_ID\"= \"Dustbin\".\"Driver_ID\" AND \"Staff_ID\"=?");
        n.setLong(1, var);
        ResultSet rs = n.executeQuery();
        int i=0;
        while (rs.next())
        {
            //Pass = rs.getString("Password");
            Out.println(rs.getString("F_Name"));
            Out.println(rs.getDouble("Status"));
            lat.add(rs.getDouble("Latitude_Coordinates"));
            log.add(rs.getDouble("Longitude_Coordinates"));
            lata[i]=rs.getDouble("Latitude_Coordinates");
            loga[i]=rs.getDouble("Longitude_Coordinates");
            i++;
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }%>




<div id='map' style='width: 1200px; height: 1200px;'></div>

<script>
<%--    <% String te="Dustbin";%>--%>
var lat='<%= lata[0] %>'
var log='<%= loga[0] %>'

    // alert (c);
<% double cord = 77.362423; %>
var cor = "<%= cord %>"
</script>

<script>
    mapboxgl.accessToken = 'pk.eyJ1IjoicHJhbmt1ciIsImEiOiJjazBlZW9tZDkwMDhjM3B1ZzJtdWgwY2lzIn0.JYo2k03E6aW3f4UYp0Feww';
    var map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/satellite-v9',
        center: [77.362423,28.619753],
        zoom:15
    });
    map.on('load', function() {
        map.loadImage('resources\\trash_can-512.png', function(error, image) {
            if (error) throw error;
            map.addImage('dustbin', image);
            map.addLayer({
                "id": "points",
                "type": "symbol",
                "source": {
                    "type": "geojson",
                    "data": {
                        "type": "FeatureCollection",
                        "features": [{
                            "type": "Feature",
                            "geometry": {
                                "type": "Point",
                                "coordinates":[cor,28.619753]
                            }

                        }]
                    }
                },
                "layout": {
                    "icon-image": "dustbin",
                    "icon-size": 0.10
                }
            });
        });
    });
</script>
</body>
</html>
