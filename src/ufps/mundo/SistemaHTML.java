
package ufps.mundo;
import ufps.util.*;
import ufps.mundo.TagGeneral;
/**
 *
 * @author FERNEY JARAMILLO
 */
public class SistemaHTML {
    
    private ListaCD<String> filasDelArchivo;
    private Secuencia<TagGeneral> tags = new Secuencia<TagGeneral>(12);
    private Pila <ErrorHTML> errores;
    private Error error1;
    private  Error error2;
    private Error error3;
    private Error error4;
             
   public SistemaHTML (){
   
       String link="http://sandbox1.ufps.edu.co/~madarme/estructuras/html_w3c.txt";
       ArchivoLeerURL file = new ArchivoLeerURL(link);
       Object v[]= file.leerArchivo();
       Cola<String> cola = new Cola();
       for(Object dato: v){
       
       String []dato2=dato.toString().split(";");
       String datoaux = dato2[1];
       /*
        * deben con dato2(0) preguntar si esta en la secuencia , sino esta adicionarlo en el indice i, deben crear
        * otro indice, si ya esta , se saca .
        * 
        * con los demas datos
        *   secuencia(i).setMisetiquetas(new Cola<Html>());
        * 
        * depsues
        * secuancia(i).getMisEtiquetas.enColar(new etiqueta con la info de datos2(1) datos(2)
        */
       
       
           if (datoaux.equalsIgnoreCase("<h1> to <h6>")) {
               for (int i = 0; i < 6; i++) {
                   String msg="Basic;<h"+(i+1)+">;Defines an HTML document";
                   cola.enColar(msg);
               }
           }else{
               cola.enColar(dato.toString());
           }
       }
       int i = 0;
       String x = cola.deColar();
       String [] fila = x.split(";");
           String tipo = fila[0];
           TagGeneral aux = this.tags.get(i);
           aux.setTipo(tipo);
           EtiquetaHTML etiqueta = new EtiquetaHTML();
           etiqueta.setEtiqueta(fila[1]);
           etiqueta.setDescripcion(fila[2]);
           aux.getEtiquetas().enColar(etiqueta);
           
       while(!cola.esVacio()){
           
            x = cola.deColar();
       fila = x.split(";");
            tipo = fila[0];
           boolean sw =true;
            while(sw){
            if(tipo.equalsIgnoreCase(aux.getTipo())){
            
             aux = this.tags.get(i);
           aux.setTipo(tipo);
            etiqueta = new EtiquetaHTML();
           etiqueta.setEtiqueta(fila[1]);
           etiqueta.setDescripcion(fila[2]);
           aux.getEtiquetas().enColar(etiqueta);
           break;
            }
            else{
                i++;
                
                 aux = this.tags.get(i);
           aux.setTipo(tipo);
            etiqueta = new EtiquetaHTML();
           etiqueta.setEtiqueta(fila[1]);
           etiqueta.setDescripcion(fila[2]);
           aux.getEtiquetas().enColar(etiqueta);
           sw=false;
            }
            }
       }
       
   }
    
}
