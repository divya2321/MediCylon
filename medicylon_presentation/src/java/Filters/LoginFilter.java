/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author divsi
 */
public class LoginFilter implements Filter{

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
       
        HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse response = (HttpServletResponse) sr1;
        
        HttpSession session = request.getSession();
        
//        if(session != null){
            String job = (String) session.getAttribute("role");
            
            if(job != null){
                if (job.equalsIgnoreCase("admin")) {
                response.sendRedirect("lab_admin/admin_dashboard.jsp");
            } else if (job.equalsIgnoreCase("receptionist")) {
                response.sendRedirect("lab_receptionist/receptionist_dashboard.jsp");
            } else if (job.equalsIgnoreCase("technician")) {
                response.sendRedirect("lab_technician/technician_dashboard.jsp");
            }
//            }
        }else{
            fc.doFilter(sr, sr1);
        }
    }
    
}
