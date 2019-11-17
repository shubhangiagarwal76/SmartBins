<%@ page import="java.sql.DriverManager" %>
<%@ page import="static com.google.gwt.view.client.DefaultSelectionEventManager.SelectAction.SELECT" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SMART BINS LOGIN</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="LOGIN.css">
    <script  src="LOGIN/LOGIN.nocache.js"></script>
</head>
<body>
<input type="text" name="value">
<input type="button" value="submit" >
<h1></h1>
<%

    try {
        printwriter Out =response.getwriter();
        Out.println("hi");
Class.forName("org.postgresql.Driver");
con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SmartBins", "postgres", "12345");
statement stmt= con.createStatement();
resultSet rs=stmt.executequery(SELECT "Password" FROM "Driver");
while(rs.next())
{
Out.println("rs.getInt(Password)");

}


} catch (Exception ex) {
System.out.println(ex.getMessage());
}
%>
</body>
</html>