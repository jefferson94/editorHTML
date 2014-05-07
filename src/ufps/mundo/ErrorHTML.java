/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.mundo;

/**
 *
 * @author FERNEY JARAMILLO
 */
public class ErrorHTML {
    
    private EtiquetaHTML myEtiqueta;
    private Error myError;

    public ErrorHTML() {
    }
    
    
    
    
    public ErrorHTML( Error a , EtiquetaHTML b){
    this.myError=a;
    this.myEtiqueta=b;
   
    }

    public EtiquetaHTML getMyEtiqueta() {
        return myEtiqueta;
    }

    public Error getMyError() {
        return myError;
    }

    public void setMyEtiqueta(EtiquetaHTML myEtiqueta) {
        this.myEtiqueta = myEtiqueta;
    }

    public void setMyError(Error myError) {
        this.myError = myError;
    }
    
}
