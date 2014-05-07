/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.mundo;
import ufps.util.*;
/**
 *
 * @author FERNEY JARAMILLO
 * @author VALERIA SALAZAR
 */
/**
 * 
 * Inicio clase TagGeneral.
 */
public class TagGeneral {
    
    private String tipo;
    private Cola <EtiquetaHTML> etiquetas = new Cola<EtiquetaHTML>();
    
    
    /**
     * Constructor vacío de la clase
     */
    public TagGeneral(){
    }
    
    /**
     * Constructor con parámetros de la clase 
     * @param tipo corresponde al tipo de etiqueta
     * @param etiquetas Cola de etiquetas correspondientes
     */
    public TagGeneral(String tipo, Cola<EtiquetaHTML> etiquetas) {
        this.tipo = tipo;
        this.etiquetas = etiquetas;
    }

    /**
     * Método que me optiene el tipo de etiqueta
     * @return el tipo de etiqueta 
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Método que hace el cambio correspondiente de variable
     * @param tipo tipo de etiqueta
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    /**
     * Método que obtiene la Cola de EtiquetasHTML 
     * @return la Cola de etiquetas
     */
    public Cola<EtiquetaHTML> getEtiquetas() {
        return etiquetas;
    }
    
    /**
     * Método que realiza el cambio correspondiente 
     * @param etiquetas Cola de etiquetasHTML
     */
    public void setEtiquetas(Cola<EtiquetaHTML> etiquetas) {
        this.etiquetas = etiquetas;
    }
    
//    /**
//     * 
//     * @return 
//     */
//    public Cola<EtiquetaHTML> getCola(){
//    
//    return etiquetas;
//    
//    
//    }
    
    /**
     * Método que realiza la busqueda de una etiqueta especifica
     * @param m etiqueta a buscar
     * @return un vector de String de dos posiciones en la posición [0] la etiqueta, y en la posición [1] la descripción de la etiqueta.
     */
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
/**
 * Fin de la clase
 */
