/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Server.Test.TestClientProcedure;
import Server.Test.TestModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author divsi
 */
public class testservlet extends HttpServlet {

    TestClientProcedure testClientProcedure;

    @Override
    public void init(ServletConfig config) throws ServletException {
        testClientProcedure = TestClientProcedure.getUserClientProcedure();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet testservlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet testservlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name").trim();

        if (name.equals("cate")) {

            String category = request.getParameter("category").trim();

            String oldCategory = request.getParameter("oldcate").trim();

            if (oldCategory.equals("")) {

                if (testClientProcedure.isTestCategoryHave(category)) {
                    out.println("This category already have");
                } else {
                    out.println("");
                }

            } else {
                if (testClientProcedure.isTestCategoryHave(category) && !category.equalsIgnoreCase(oldCategory)) {
                    out.println("This category already have");
                } else {
                    out.println("");
                }

            }

        } else {
            String test = request.getParameter("test").trim();

            String oldTest = request.getParameter("oldtest").trim();

            if (oldTest.equals("")) {

                if (testClientProcedure.isTestHave(test)) {
                    out.println("This test already have");
                } else {
                    out.println("");
                }

            } else {

                if (testClientProcedure.isTestHave(test) && !test.equalsIgnoreCase(oldTest)) {
                    out.println("This test already have");
                } else {
                    out.println("");
                }

            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String name = request.getParameter("name").trim();
        if (name.equals("cate")) {

            String oldCategory = request.getParameter("oldcategory").trim();

            if (oldCategory.equals("")) {

                TestModel testModel = new TestModel();
                testModel.setTestCategory(request.getParameter("category").trim());
                testModel.setRoomNo(request.getParameter("roomno").trim());
                testModel.setTestPerDay(request.getParameter("maximumcount").trim());
                testModel.setStaffId(request.getParameter("technician").split(" - ")[0]);

                if (testClientProcedure.insertTestCategory(testModel).getStatus() == 202) {
                    response.sendRedirect("lab_admin/admin_test.jsp");
                }

            } else {
                doPut(request, response);
            }

        } else {
            String oldTest = request.getParameter("oldtest").trim();

            if (oldTest.equals("")) {

                TestModel testModel = new TestModel();
                testModel.setTestName(request.getParameter("testname").trim());
                testModel.setPrice(request.getParameter("price").trim());
                testModel.setTestCategory(request.getParameter("testcategory").trim());

                if (testClientProcedure.insertTest(testModel).getStatus() == 202) {
                    response.sendRedirect("lab_admin/admin_test.jsp");
                }

            } else {
                doPut(request, response);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String name = req.getParameter("name").trim();
        if (name.equals("cate")) {

            String oldCategory = req.getParameter("oldcategory").trim();

            TestModel testModel = new TestModel();
            testModel.setTestCategory(req.getParameter("category").trim());
            testModel.setRoomNo(req.getParameter("roomno").trim());
            testModel.setTestPerDay(req.getParameter("maximumcount").trim());
            testModel.setStaffId(req.getParameter("technician").split(" - ")[0]);
            testModel.setOldTestCategory(oldCategory.trim());

            if (testClientProcedure.updateTestCategory(testModel).getStatus() == 202) {
                resp.sendRedirect("lab_admin/admin_test.jsp");
            }

        } else {

            String oldTest = req.getParameter("oldtest").trim();

            TestModel testModel = new TestModel();
            testModel.setTestName(req.getParameter("testname").trim());
            testModel.setPrice(req.getParameter("price").trim());
            testModel.setTestCategory(req.getParameter("testcategory").trim());
            testModel.setOldTestName(oldTest);

            if (testClientProcedure.updateTest(testModel).getStatus() == 202) {
                resp.sendRedirect("lab_admin/admin_test.jsp");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
