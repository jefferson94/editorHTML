/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.mundo;
import ufps.util.*;
/**
 *
 * @author FERNEY JARAMILLO
 */
public class TagGeneral {
    
    private String tipo;
    private Cola <EtiquetaHTML> etiquetas = new Cola<EtiquetaHTML>();
    
    public TagGeneral(){
    }

    public TagGeneral(String tipo, Cola<EtiquetaHTML> etiquetas) {
        this.tipo = tipo;
        this.etiquetas = etiquetas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cola<EtiquetaHTML> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Cola<EtiquetaHTML> etiquetas) {
        this.etiquetas = etiquetas;
    }
    public Cola<EtiquetaHTML> getCola(){
    
    return etiquetas;
    
    
    }
    
    public String[] buscarEtiqueta( String m) 
    {
  
        String v1[]=new String[2];
        String msg ="";
        while(!this.etiquetas.esVacio()){
        
        EtiquetaHTML d= this.etiquetas.deColar();
        m="<"+m+">";
        if( m.equals(d.getEtiqueta()));
        v1[0]= m;
        v1[1]=d.getDescripcion();
        
        return v1;
        }
        
        return null;
    
    }
    
}
