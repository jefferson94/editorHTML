/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.gui;

import javax.swing.JOptionPane;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import ufps.mundo.EtiquetaHTML;
import ufps.mundo.SistemaHTML;
import ufps.mundo.TagGeneral;
import ufps.util.*;

/**
 *
 * @author FERNEY JARAMILLO
 */
public class prueba {

    public static void main(String[] args) {
//        String x="<h>";
//        String a = x.substring(1);
//            System.out.println(a);
//            a = "</" + a;
//            System.out.println(a);
    
     SistemaHTML h=new SistemaHTML();
     Secuencia<TagGeneral> s=h.getTags();
    Cola<String> d=new Cola<String>();
//     for(int i=0;i<s.length();i++){
//        if(s.get(i)!=null){ 
//         Cola<EtiquetaHTML> c=s.get(i).getEtiquetas();
//         
//            System.out.println("este es el tipo:  "+ s.get(i).getTipo());
//         while(!c.esVacio()){
//             EtiquetaHTML q=c.deColar();
//             System.out.println(q.getEtiqueta());
//             d.enColar(q.getEtiqueta());
//         }
//         
//           Pila<String> errores=h.isCorrect(d);
//           while(!errores.esVacio()){
//               System.out.println(errores.pop());
//           }
//         
//         
//        }
//     }
    
    d.enColar("<html>");
    d.enColar("<dp>");
    d.enColar("<dp>");
    d.enColar("</dp>");
    d.enColar("</html>");
    d.enColar(null);
    
    Pila<String> errores=h.isCorrect(d);
             while(!errores.esVacio()){
               System.out.println(errores.pop());
           }
  
    
    
    }
}
