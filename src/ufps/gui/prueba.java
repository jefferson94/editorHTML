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
     SistemaHTML h=new SistemaHTML();
     Secuencia<TagGeneral> s=h.getTags();
    
     for(int i=0;i<s.length();i++){
        if(s.get(i)!=null){ 
         Cola<EtiquetaHTML> c=s.get(i).getEtiquetas();
            System.out.println("este es el tipo:  "+ s.get(i).getTipo());
         while(!c.esVacio()){
             System.out.println(c.deColar().getEtiqueta());
         }
        }
     }
    }
}
