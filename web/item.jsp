<%-- 
    Document   : item
    Created on : Jun 19, 2023, 8:39:29 PM
    Author     : Quang Phat
--%>

<%@page import="basicobject.Item"%>
<%@page import="java.util.ArrayList"%>
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
            String result = (String) request.getAttribute("isDupplicated");
            if (result != null) {
        %>
        <h1><%= result %> </h1>
        <%
            }
        %>


        <%
            ArrayList<Item> itemsList = (ArrayList<Item>) session.getAttribute("itemList");
            if (itemsList != null && !itemsList.isEmpty()) {
        %>
        <h1>Item List</h1>
        <table style="margin: 0 auto">
            <tr>
                <th>Item ID</th>
                <th>Item Name</th>
                <th>Action</th>
            </tr>
            <% for (Item item : itemsList) {%>
            <form action="MainController" method="post">
                <tr>
                <input type="hidden" value="3" name="action"/>
                <td><input type="text" name="txtshowid" readonly="" value="<%= item.getItemId()%>"/></td>
                <td><input type="text" readonly="" value="<%= item.getItemName()%>"/></td>
                <td><input type="submit" class="button" value="Edit"></td>
                </tr>
            </form>
            <% }%>
        </table>
        
        <hr/>
        
        <h1 style="align-items: center">Add Item</h1>
        <form action="MainController" method="post" style="border: 1px">
            <input type="hidden" value="7" name="action"/>
            <input type="text" required="" placeholder="Enter Item ID" min="0" name="txtItemId"/> 
            <br/>
            <input type="text" required="" placeholder="Enter Item Name" name="txtItemName"/> 
            <br/>
            <input type="text" pattern="[1-9][0-9]*" title="Vui lòng nhập số nguyên lớn hơn 0" min="0" required="" placeholder="Enter Item price" name="txtItemPrice"/> 
            <br/>
            <select name="txtitemtype" required="">
                <option value="1" >Dining tables</option>
                <option value="2" >Chairs</option>
                <option value="3" >Clocks & Barometers</option> 
            </select>
            <br>
            <input type="submit" value="Submit">
        </form>



        <%
        } else {
        %>
        <%
                out.print("Nothing to show");
            }
        %>
    </tr>
</body>
</html>
