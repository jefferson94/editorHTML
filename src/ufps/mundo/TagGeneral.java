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
    
}