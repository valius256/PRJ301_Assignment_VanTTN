/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import basicobject.Item;
import dbaccess.ItemDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Quang Phat
 */
public class AddItemServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            int itemID = Integer.parseInt(request.getParameter("txtItemId"));
            String itemName = request.getParameter("txtItemName");
            int itemPrice = Integer.parseInt(request.getParameter("txtItemPrice"));
            int itemCategory = Integer.parseInt(request.getParameter("txtitemtype"));

            ArrayList<Item> itemList = ItemDao.getItemByID(itemID);
            
            request.setAttribute("findItem", itemList);
            request.getRequestDispatcher("edit.jsp").forward(request, response);
            

            if (!itemList.isEmpty()) {
                for (Item item : itemList) {
                    if (item.getItemId() == itemID) {
                        String deniedString = "This item has been existed in Database";
                        request.setAttribute("isDupplicated", deniedString);
                        request.getRequestDispatcher("MainController?action=1").forward(request, response);
                    }
                }
            } else {
                int rs = ItemDao.insertITem(itemID, itemName, itemPrice, itemCategory);
                if (rs > 0) {
                    request.getRequestDispatcher("MainController?action=0").forward(request, response);
                } else {
                    out.print("error");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
