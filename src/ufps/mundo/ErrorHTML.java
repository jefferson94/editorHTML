/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.mundo;

/**
 *
 * @author FERNEY JARAMILLO
 * @author VALERIA SALAZAR
 */
/**
 * Inicio clase ErrorHTML
 */
public class ErrorHTML {
    
    private EtiquetaHTML myEtiqueta;
    private Error myError;
    
    
    /**
     * Constructor vacío de la clase.
     */
    public ErrorHTML() {
    }
    
    
    
    /**
     * Constructor con parámetros de la clase.
     * @param a descripción del error
     * @param b variablr que corresponde a la etoqueta y descripcion de la etiqueta.
     */
    public ErrorHTML( Error a , EtiquetaHTML b){
    this.myError=a;
    this.myEtiqueta=b;
   
    }

    /**
     * Método que obtiene el dato actual de la etiqueta
     * @return la etiqueta
     */
    public EtiquetaHTML getMyEtiqueta() {
        return myEtiqueta;
    }

    /**
     * Método que obtiene la descripción del error
     * @return el dato correspondiente
     */
    public Error getMyError() {
        return myError;
    }
    
    /**
     *Método que realiza el cambio de variable correspondiente.
     * @param myEtiqueta variable a cambiar
     */
    public void setMyEtiqueta(EtiquetaHTML myEtiqueta) {
        this.myEtiqueta = myEtiqueta;
    }
    
    /**
     * Método que realiza el cambio correspondiente de variable
     * @param myError  variable a cambiar
     */
    public void setMyError(Error myError) {
        this.myError = myError;
    }
    
}
