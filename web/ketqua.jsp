<%-- 
    Document   : ketqua
    Created on : Jun 28, 2023, 12:52:15 AM
    Author     : Quang Phat
--%>

<%@page import="basicobject.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body{
                text-decoration: aquamarine;
                text-align: center;
                background-color: azure;
            }
        </style>
    </head>
    <body>
        <%
            Item item = (Item) session.getAttribute("updateItem");
            if (item != null) {
        %>  
        <h1>Update item: <%= item.toString()%> success  </h1>
        <a href="MainController?action=">Home Pages</a>
        <%

        } else {
        %>
        <h1>Update failed</h1>
        <%
            }
        %>

        <%
            String result = (String) session.getAttribute("result_D");
            if (result != null) {
        %>
        <h1>Delete <%= result%> </h1>
        <%
            } else {
                out.print("result is null");
            }
        %>
        <a href="MainController">Return Home</a>
    </body>
</html>
