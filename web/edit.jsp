<%-- 
    Document   : edit
    Created on : Jun 20, 2023, 11:22:16 AM
    Author     : Quang Phat
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="basicobject.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function warning() {
                var message = "Are you sure you want to remove this item?";
                return confirm(message);
            }
        </script>
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
            ArrayList<Item> itemsList = (ArrayList<Item>) session.getAttribute("itemSelectedList");
            if (itemsList != null && !itemsList.isEmpty()) {
        %>

        <h1>Edit form</h1>
        <table>
            <tr>
                <th>Item ID</th>
                <th>Item Name</th>
                <th>Item Price</th>
                <th>Item Category</th>
            </tr>
            <% for (Item item : itemsList) {%>
            <form action="MainController" method="post">
                <tr>
                <input type="hidden" value="4" name="action"/>
                <td><input type="text" name="txteditid" readonly="" value="<%= item.getItemId()%>"/></td>
                <td><input type="text" name="txtitemname" required="" value="<%= item.getItemName()%>"/></td>
                <td><input type="text" pattern="[1-9][0-9]*" title="Vui lòng nhập số nguyên lớn hơn 0" min="0" name="txtitemprice" required="" value="<%= item.getPrice()%>"  /></td>
                <td>
                    <select name="txtitemtype" required="">
                        <option value="1" <%= item.getCateId() == 1 ? "selected" : ""%>>Dining tables</option>
                        <option value="2" <%= item.getCateId() == 2 ? "selected" : ""%>>Chairs</option>
                        <option value="3" <%= item.getCateId() == 3 ? "selected" : ""%>>Clocks & Barometers</option> 
                    </select>
                </td>
                <td><input type="submit" class="button" value="Save"></td>
                <td><input type="submit" class="button" value="Delete" name="Delete" onclick="return warning()" ></td>
                </tr>
            </form>
            <% } %>
        </table>
        <a href="MainController?action=1">Previous</a>
        <a href="MainController?action=">Home Pages</a>

        <%
        } else {
        %>
        <%
                out.print("Nothing to show or status in Database is false(status = 0)");
            }
        %>
        
        
        <%
            ArrayList<Item> itemList  =(ArrayList<Item>) request.getAttribute("findItem");
            if(itemList != null){
                out.print(itemList);
            }
        %>
    </body>
</html>
