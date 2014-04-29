
package ufps.mundo;
import ufps.util.*;
import ufps.mundo.TagGeneral;
/**
 *
 * @author FERNEY JARAMILLO
 */
public class SistemaHTML {
    
    private ListaCD<String> filasDelArchivo;
    private Secuencia<TagGeneral> tags;
    private Pila <ErrorHTML> errores;
    private Error error1;
    private  Error error2;
    private Error error3;
    private Error error4;
             
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
    
    public SistemaHTML(){
    
    inicializar();
    }
    
    
    
    //Constructor de archivos locales pasar a lista
   public SistemaHTML (String ruta){
       
        inicializar();
        cargarEtiquetas();
        ArchivoLeerTexto file=new ArchivoLeerTexto(ruta);
          Object v[]=file.leerTodo();
     
       for(Object dato: v){
       
             
             filasDelArchivo.addFin(dato.toString());
        
       
         
       }
   
       
       
   }
   
   //Constructor desde url y pasar  a lista 
   public SistemaHTML(String url, boolean x){
       
       
       inicializar();
       cargarEtiquetas();
       String link="url";
       ArchivoLeerURL file = new ArchivoLeerURL(url);
       Object v[]= file.leerArchivo();
      
       for(Object dato: v){
       
      
      filasDelArchivo.addFin(dato.toString());
       
       
       
       }
   
   
   }
       
   
   
  private void cargarEtiquetas(){
      
      String link="http://sandbox1.ufps.edu.co/~madarme/estructuras/html_w3c.txt";
       ArchivoLeerURL file = new ArchivoLeerURL(link);
       Object v[]= file.leerArchivo();
      Cola<String> cola = new Cola();
      int cont =0;
       for(Object dato: v){
       
       String []dato2=dato.toString().split(";");
       int pos=buscarDatoEnSecuencia(dato2[0]);
       
       if(pos!=-1){
           
          if (dato2[1].equalsIgnoreCase("<h1> to <h6>")) {
              for (int i = 0; i < 6; i++) {
                  String msg="Basic;<h"+(i+1)+">;Defines an HTML document";
                   this.tags.get(pos).getCola().enColar(new EtiquetaHTML(msg, dato2[2]));               
              }
           
              this.tags.get(pos).getCola().enColar(new EtiquetaHTML(dato2[1], dato2[0]));
          }
      
      else{
           Cola<EtiquetaHTML> c=new Cola<EtiquetaHTML>();
           c.enColar(new EtiquetaHTML(dato2[1], dato2[2]));
           this.tags.set(cont, new TagGeneral(dato2[0], c ));
          cont++;
         
      }
       }
       }}
   
    
   
   private void inicializar(){
       
       this.error1=new Error("No tiene fin de etiqueta");
       this.error2= new Error("No tiene etiqueta de inicio");
       this.error3= new Error("Etiqueta no reconocida");
       this.error4= new Error("No tiene etiqueta de estructura");
       this.filasDelArchivo=new ListaCD<String>();
       this.tags = new Secuencia<TagGeneral>(12);
       this.errores= new Pila<ErrorHTML>();
    
             
   }
       
        
          public int buscarDatoEnSecuencia (String dato){
          
           for(int i=0; i<this.tags.length(); i++){
                   TagGeneral aux= this.tags.get(i);
               if (dato.equalsIgnoreCase(aux.getTipo())){
                   return (i);
//                  EtiquetaHTML nueva = new EtiquetaHTML
               }
           }
           return -1;
       
}
          
     public String[] ayudaHTML(String etiqueta){
     cargarEtiquetas();
     String vector[] = new String [3];
      
     for (int i = 0 ; i<this.tags.length();i++){
     
    Cola<EtiquetaHTML> x=  this.tags.get(i).getCola();
     
    String v[] = buscarEtiqueta(x,etiqueta);
    
    if ( v!=null){
        
        vector[0]=v[0];
        vector[1]=this.tags.get(i).getTipo();
        vector[2]=v[1];
   
    }
     
     return vector;
     }
   vector[0]= "No se encuentra la etiqueta";
     return vector;
   
     }     

    private  String[] buscarEtiqueta(Cola<EtiquetaHTML> x, String etiqueta) {
        
       
        String v1[]=new String[2];
        String msg ="";
        while(!x.esVacio()){
        
        EtiquetaHTML d= x.deColar();
        etiqueta="<"+etiqueta+">";
        if( etiqueta.equals(d.getEtiqueta()));
        v1[0]= etiqueta;
        v1[1]=d.getDescripcion();
        
        return v1;
        }
        
        return null;
        
        
    }
          
}

