/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.yournamehere.client.Akcie;
import org.yournamehere.client.GWTService;
import org.yournamehere.client.ZalobyZpravy;
import net.sf.jasperreports.engine.*;

/**
 *
 * @author Martin
 */
public class GWTServiceImpl extends RemoteServiceServlet implements GWTService {

    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        //List<Parametry> parametryList = null;
        try {
            System.out.println("Step3");
            session.beginTransaction();
            
            //parametryList = new ArrayList<Parametry>(session.createQuery("from Parametry").list());
            /*Parametry parametr = new Parametry();            
            parametr.setKod("TEST");
            session.save(parametr);*/
            
            String b2 = new String("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            ZalobyZpravy tmp = new ZalobyZpravy();
            tmp.setTelo(s);
            //byte[] test = b.getBytes();
            session.save(tmp);
            //session.flush();
            
            session.getTransaction().commit();
            
            printList();
            
        } catch (Exception e) {
            s = e.toString(); // .printStackTrace();
        }
        return "Server says: " + s;
    }
    
    public String printList(){
        try {
            System.out.println("krok 1");
            JasperReport jr = JasperCompileManager.compileReport("c:/Martin/reports/report2.jrxml");

            System.out.println("krok 2");
            //Connection con =  
              //DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","mbsystem","mbsystem");
            JasperPrint jp = JasperFillManager.fillReport(jr, null, new JREmptyDataSource());  //getServletContext().getRealPath("/")+ "/WEB-INF/report1.jrxml"
            
            System.out.println("krok 3"); 
            JasperPrintManager.printReport(jp, true); 
            
            //JasperExportManager.exportReportToPdfFile(jp, "C:/sample_report.pdf");
            
            System.out.println("krok 4");
            
        //} catch (SQLException ex) {
          //  Logger.getLogger(GWTServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            System.out.println(ex.getMessage());
        }
        return "xxx";
    }
}
