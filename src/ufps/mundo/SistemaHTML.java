package ufps.mundo;

import ufps.util.*;
import ufps.mundo.TagGeneral;

/**
 * @author FERNEY JARAMILLO
 * @author VALERIA SALAZAR
 */
public class SistemaHTML {

    private ListaCD<String> filasDelArchivo;
    private Secuencia<TagGeneral> tags;
    private Pila<ErrorHTML> errores;
    private Error error1;
    private Error error2;
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
    public SistemaHTML() {

        inicializar();
    }

    //Constructor de archivos locales  para pasar a lista
    public SistemaHTML(String ruta) {

        inicializar();
        cargarEtiquetas();
        ArchivoLeerTexto file = new ArchivoLeerTexto(ruta);
        Object v[] = file.leerTodo();

        for (Object dato : v) {

            filasDelArchivo.addFin(dato.toString());

        }

    }

    //Constructor desde url y pasar  a lista 
    public SistemaHTML(String url, boolean x) {

        inicializar();
        cargarEtiquetas();
        String link = "url";
        ArchivoLeerURL file = new ArchivoLeerURL(url);
        Object v[] = file.leerArchivo();

        for (Object dato : v) {

            filasDelArchivo.addFin(dato.toString());

        }

    }

    public String[] ayudaHTML(String etiqueta) {


        String vector[] = new String[3];

        for (int i = 0; i < this.tags.length(); i++) {

            TagGeneral x;
            x = this.tags.get(i);

            String v2[] = x.buscarEtiqueta(etiqueta);


            if (v2 != null) {

                vector[0] = v2[0];
                vector[1] = this.tags.get(i).getTipo();
                vector[2] = v2[1];

            }

            return vector;
        }
        vector[0] = "No se encuentra la etiqueta";
        return vector;

    }

    private void inicializar() {

        this.error1 = new Error("No tiene fin de etiqueta");
        this.error2 = new Error("No tiene etiqueta de inicio");
        this.error3 = new Error("Etiqueta no reconocida");
        this.error4 = new Error("No tiene etiqueta de estructura");
        this.filasDelArchivo = new ListaCD<String>();
        this.tags = new Secuencia<TagGeneral>(12);
        this.errores = new Pila<ErrorHTML>();

    }

    private void cargarEtiquetas() {

        String link = "http://sandbox1.ufps.edu.co/~madarme/estructuras/html_w3c.txt";
        ArchivoLeerURL file = new ArchivoLeerURL(link);
        Object v[] = file.leerArchivo();
        Cola<String> cola = new Cola();
        int cont = 0;
        for (Object dato : v) {

            String[] dato2 = dato.toString().split(";");
            int pos = buscarDatoEnSecuencia(dato2[0]);

            if (pos != -1) {

                if (dato2[1].equalsIgnoreCase("<h1> to <h6>")) {

                    for (int i = 0; i < 6; i++) {

                        String msg = "Basic;<h" + (i + 1) + ">;Defines an HTML document";
                        this.tags.get(pos).getCola().enColar(new EtiquetaHTML(msg, dato2[2]));
                    }

                    this.tags.get(pos).getCola().enColar(new EtiquetaHTML(dato2[1], dato2[0]));
                } else {
                    Cola<EtiquetaHTML> c = new Cola<EtiquetaHTML>();
                    c.enColar(new EtiquetaHTML(dato2[1], dato2[2]));
                    this.tags.set(cont, new TagGeneral(dato2[0], c));
                    cont++;

                }
            }
        }
    }

    public int buscarDatoEnSecuencia(String dato) {

        for (int i = 0; i < this.tags.length(); i++) {
            TagGeneral aux = this.tags.get(i);
            if (dato.equalsIgnoreCase(aux.getTipo())) {
                return (i);

            }
        }
        return -1;

    }

    public String analizadorHTML() {

        Secuencia<Cola<String>> a = this.separar();

        isCorrect(a.get(0));
        isCorrect(a.get(1));


        return null;

    }

    public Secuencia<Cola<String>> separar() {

        Cola<String> binaria = new Cola<String>();
        Cola<String> unaria = new Cola<String>();
        for (String d : this.filasDelArchivo) {
            int i = 0;
            String datos[] = d.split(">");
       //for que recorra datos
            datos[i] = datos[i] + ">";
            String palabra = datos[i];
            int j = palabra.lastIndexOf("<");
            String original = "";

            for (; j < datos.length; j++) {

                original += d.charAt(j);

                int u = isUnaria(original);

                if (u == 1) {

                    unaria.enColar(original);

                } else if (isUnaria(original) == 0) {
                    binaria.enColar(original);

                } else if (isUnaria(original) == 4) {
                    Error e = new Error("bla,bla");
                    EtiquetaHTML eti = new EtiquetaHTML(original, original);
                    ErrorHTML a = new ErrorHTML(e, eti);

                    this.errores.push(a);
                }
            }
            i++;
            //se cierra el for
        }
        Secuencia<Cola<String>> nueva = new Secuencia<Cola<String>>(2);
        nueva.set(0, unaria);
        nueva.set(1, binaria);
        return nueva;
    }

    private int isUnaria(String original) {

        if (original.equals("<html/>") || original.equals("<title/>") || original.equals("<boddy/>") || original.equals("<head/>") || original.equals("<!Doctype HTML>")) {

            return 4;

        } else {
            char p[] = original.toCharArray();

            if (p[p.length - 1] == '/') {
                original.charAt(original.length() - 1);
                return 1; //es unaria
//            } else if (original.equals("<html>") || original.equals("<title>") || original.equals("<body>") || original.equals("<head>") || original.equals("<!Doctype HTML>") || original.equals("</html>") || original.equals("</title>") || original.equals("</body>") || original.equals("</head>")) {
//                return -1;
//
//            }



        }
        return 0; //es binaria
    }
    }

    public void isCorrect(Cola<String> a) {

//        Pila<String> original = new Pila<String>();
        Pila<String> aux = new Pila<String>();

        while (!a.esVacio()) {

            String aux1="";
            String aux2="";

            if (aux.esVacio()) {
            
                aux1=a.deColar();
                aux2=a.deColar();

            } else {
                aux1=a.deColar();
                if(aux2==""){
                aux2=aux.pop();
                }
//                String m = original.pop();
//                String c = aux.pop();
//                String i = m;
//
//                String h = getInverso(i);
//
//                if (!c.equals(h)) {
//                    aux.push(c);
//                    String aux2 = m;
//                }

            }
            
            String h=getInverso(aux1);
           if ( h=="o"){
           
           aux.push(aux2);
           aux2=aux1;
           
           
           }
            
            
            if (!h.equals(aux2)){
                aux.push(aux2);
                aux2=aux1;
            }
        //Preguntan si son inversos. Si son inversos se van los 2
            //Si no son inversos en aux hacen push aux2 y aux2=aux1;



        }

    }

    private String getInverso(String x) {
        
        char t[] = x.toCharArray();
       
if (t[1]=='/'){

    return "o";


}
else{
        String a = x.substring(1);
        System.out.println(a);
        a = "</" + a;
        return a;

}
    }
}
