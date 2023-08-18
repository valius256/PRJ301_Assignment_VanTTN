<%-- 
    Document   : faqpages
    Created on : Jun 28, 2023, 8:45:53 AM
    Author     : Quang Phat
--%>

<%@page import="basicobject.Faq"%>
<%@page import="java.util.ArrayList"%>
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
    <%
        ArrayList<Item> typesList = (ArrayList<Item>) session.getAttribute("itemList");
        if (typesList != null) {
    %>
    <form action="MainController" method="post">
        <input type="hidden" name="action" value="6">
        <select name="selectedItem">
            <%
                for (Item type : typesList) {
            %>
            <option value="<%= type.getItemId()%>"> <%= type.getItemName()%> </option>
            <%
                }
            %>   
            <br/>
        </select>
        <input type="submit" class="button" value="submit"/>

    </form>
    <%
    } else {
        out.print("Nothing load");
    %>
    <%
        }
    %>

    <%
        ArrayList<Faq> faqList = (ArrayList<Faq>) session.getAttribute("FaqList");
        if (faqList != null) {
            for (Faq elem : faqList) {
    %>
    <hr/>
    <%
            out.println("Result is: ");
            out.println(elem);
        }
    %>
    <br/>
    <a href="MainController?action=">Home Pages</a>
    <%
        } else {
            out.print("Can't load faqList");
        }
    %>

</body>
</html>

