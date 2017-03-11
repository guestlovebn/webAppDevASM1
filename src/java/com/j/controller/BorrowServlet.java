/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j.controller;

import com.j.da.BookManager;
import com.j.da.OrderManager;
import com.j.entity.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DSLP
 */
public class BorrowServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BorrowServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BorrowServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String idbook = request.getParameter("book");
        request.setAttribute("book", BookManager.getById(Integer.parseInt(idbook)));
        RequestDispatcher rd = request.getRequestDispatcher("borrow.jsp");
        rd.forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        Order order = new Order();
        order.setName(request.getParameter("name"));
        String a = request.getParameter("idStaff");
        order.setIdStaff(Integer.parseInt(a));
        String b = request.getParameter("idbook");
        order.setIdbook(Integer.parseInt(b));
        order.setDay(Integer.parseInt(request.getParameter("daynumber")));
        if (request.getParameter("note") != null) {
            order.setNote(request.getParameter("note"));
        }
        Date date = new Date();
        
        order.setBorrowDate(new java.sql.Date(date.getTime()));
        
        System.out.println("");
        order.setStatus(true);

        if (OrderManager.AddNew(order)) {
            request.setAttribute("stt", "ok");
        } else {
            request.setAttribute("stt", "bad");
        }

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);

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
