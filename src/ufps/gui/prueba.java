/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.gui;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import ufps.mundo.SistemaHTML;
import ufps.util.*;
/**
 *
 * @author FERNEY JARAMILLO
 */
public class prueba {
    
    public static void main(String [] args){
        String x="<html>";
//        String a =x.substring(1);
//        System.out.println(a);
//        a="</"+a;
//          System.out.println(a);
        int i=x.lastIndexOf("/");
        System.out.println(i);
//        String otro="";
//        for (;  i< x.length(); i++) {
//            otro+=x.charAt(i);
//            
//           
//        }
//        otro="</"+otro;
//        System.out.println(otro);
//        
//    }
        
    SistemaHTML d;
        d = new SistemaHTML("C:/Users/FERNEY JARAMILLO/Desktop");
    Cola<String> g= new Cola<String>();
    
    g.enColar("<html>");
    g.enColar("</html>");
    
    d.analizadorHTML();
    d.separar();
    
    System.out.println(d);
}
}
