package ufps.mundo;

import javax.print.DocFlavor;
import org.xml.sax.ErrorHandler;
import ufps.util.*;
import ufps.mundo.TagGeneral;

/**
 * @author FERNEY JARAMILLO
 * @author VALERIA SALAZAR
 */
/**
 *
 * Inicio clase SistemaHTML
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
     * otrso indice, si ya esta , se saca .
     * 
     * con los demas datos
     *   secuencia(i).setMisetiquetas(new Cola<Html>());
     * 
     * depsues
     * secuancia(i).getMisEtiquetas.enColar(new etiqueta con la info de datos2(1) datos(2)
     */
    /**
     * Constructor vacío de la clase SistemaHtml
     */
    public SistemaHTML() {

        inicializar();
        cargarEtiquetas();
    }

    //Constructor de archivos locales  para pasar a lista
    /**
     * Constructor con parametro de la clase
     *
     * @param ruta recibe como parámetro la ruta del archivo local
     */
    public SistemaHTML(String ruta) {

        inicializar();
        cargarEtiquetas();
        ArchivoLeerTexto file = new ArchivoLeerTexto(ruta);
        Object v[] = file.leerTodo();

        for (Object dato : v) {

            filasDelArchivo.addFin(dato.toString());

        }

    }

    public Secuencia<TagGeneral> getTags() {
        return tags;
    }

    //Constructor desde url y pasar  a lista 
    /**
     * Constructor con parámetros de la clase
     *
     * @param url dirección leer el archivo u
     * @param x parámetro para diferenciar del Constructor con ruta
     */
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

    /**
     * Método que lee las etiquetas html según la W3C.
     *
     * @param etiqueta variable a buscar.
     * @return un vector de String de 3 posiciones, en la [0] error, [1] el
     * tipo, [2] etiqueta.
     */
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

    /**
     * Método que nos permite inicializar las diferentes estructuras a utilizar
     * en los constructores.
     */
    private void inicializar() {

        this.error1 = new Error("No tiene fin de etiqueta");
        this.error2 = new Error("No tiene etiqueta de inicio");
        this.error3 = new Error("Etiqueta no reconocida");
        this.error4 = new Error("No tiene etiqueta de estructura");
        this.filasDelArchivo = new ListaCD<String>();
        this.tags = new Secuencia<TagGeneral>(12);
        this.errores = new Pila<ErrorHTML>();

    }

    /**
     * Método que realiza la carga de las etiquetas.
     */
    private void cargarEtiquetas() {

        String link = "http://sandbox1.ufps.edu.co/~madarme/estructuras/html_w3c.txt";
        ArchivoLeerURL file = new ArchivoLeerURL(link);
        Object v[] = file.leerArchivo();
//        Cola<String> cola = new Cola();
        int cont = -1;
        for (Object dato : v) {

            String[] dato2 = dato.toString().split(";");
            int pos = buscarDatoEnSecuencia(dato2[0], dato2[1], dato2[2]);

            if (pos != -1) {

                if (dato2[1].equalsIgnoreCase("<h1> to <h6>")) {

                    for (int i = 0; i < 6; i++) {

                        String msg = "<h" + (i + 1) + ">";
                        String descripcion = "Defines an HTML document";
                        this.tags.get(pos).getEtiquetas().enColar(new EtiquetaHTML(msg, descripcion));
                    }
                    if (!dato2[1].equalsIgnoreCase("<h1> to <h6>")) {
                        this.tags.get(pos).getEtiquetas().enColar(new EtiquetaHTML(dato2[1], dato2[0]));
                    }
                } else {
                    this.tags.get(pos).getEtiquetas().enColar(new EtiquetaHTML(dato2[1], dato2[2]));
                }

            }

        }
    }

    /**
     *
     * @param dato
     * @return
     */
    public int buscarDatoEnSecuencia(String dato, String etiqueta, String descripcion) {

        Cola<EtiquetaHTML> c = new Cola<EtiquetaHTML>();


        for (int i = 0; i < this.tags.length(); i++) {
            if (this.tags.get(i) != null) {
                if (this.tags.get(i).getTipo().equalsIgnoreCase(dato)) {
                    return i;
                }
            } else {

                c.enColar(new EtiquetaHTML(etiqueta, descripcion));
                this.tags.set(i, new TagGeneral(dato, c));
                return -1;
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

    /**
     * Método que me separa la Lista filasArchivo en dos colas, una cola para
     * etiquetas binarias, y la otra cola para etiquentas unarias.
     *
     * @return una Secuencia de Cola tipo String, en la posición 0 guardará la
     * Cola de unarias y en la posición 1 la Cola de binarias.
     */
    private Secuencia<Cola<String>> separar() {

        Cola<String> binaria = new Cola<String>();
        Cola<String> unaria = new Cola<String>();
        for (String d : this.filasDelArchivo) {
            int i = 0;
            String datos[] = d.split(">");
            for (; i < datos.length; i++) {

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
            }


        }
        Secuencia<Cola<String>> nueva = new Secuencia<Cola<String>>(2);
        nueva.set(0, unaria);
        nueva.set(1, binaria);
        return nueva;
    }

    /**
     * Método que me indica si la etiqueta es unaria o binaria
     *
     * @param original etiqueta leida de la Lista de filasArchivo, y que será
     * evaluada para saber a que caso pertenece.
     * @return un valor entero: 4 si es un error, 1 si es unaria, 0 si es
     * binaria.
     */
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

    /**
     * Método que me evalua si las etiquetas estan equilibradas por parejas de
     * apertura y cierre.
     *
     * @param a Cola de String que contiene las etiquetas binarias o etiquetas
     * unarias.
     */
    public Pila<ErrorHTML> isCorrect(Cola<String> a) {
        Pila<ErrorHTML> errores = new Pila<ErrorHTML>();

        Pila<String> etiquetaApertura = new Pila<String>();
        Pila<String> posibleError = new Pila<String>();
        String dato;
        while (!a.esVacio()) {
            dato = a.deColar();
            if (casoEtiqueta(dato)) {
                etiquetaApertura.push(dato);

            } 
            else {
                while (!etiquetaApertura.esVacio()) {

//                String dato2= etiquetaApertura.pop();
                    String dato3 = etiquetaApertura.pop();
                    String inverso = getInverso(dato3);
                    if (!(inverso.equalsIgnoreCase(dato))) {
                        posibleError.push(dato3);


                    }
                    break;
                }

            }


        }
        if (!etiquetaApertura.esVacio() && !posibleError.esVacio()) {
            while (!etiquetaApertura.esVacio() && !posibleError.esVacio()) {

                dato = etiquetaApertura.pop();
                String dato2 = posibleError.pop();
                String inverso = getInverso(dato);
                if (!(inverso.equalsIgnoreCase(dato2))) {
                    posibleError.push(dato);
                } 
                else {
                    
                    break;
                }
            }
        }
        
        else {
            EtiquetaHTML eti;
            ErrorHTML nuevo;
            while (!posibleError.esVacio()) {
                String etiqueta = posibleError.pop();
                 eti = new EtiquetaHTML(etiqueta, "");
               
               if( casoEtiqueta(etiqueta)){
               nuevo = new ErrorHTML(error1, eti);
                
                errores.push(nuevo);
               }
                else  {
                   nuevo = new ErrorHTML(error2, eti);
                   errores.push(nuevo);
               }
               
                    
                
            }

        }
        return errores;

    }

    /**
     * Método que permite a una etiqueta de apertura crearle su etiqueta de
     * cierre para luego compararla en el metodo de isCorrect.
     *
     * @param x etiqueta a crearle su inversa
     * @return un String "0" si la etiqueta recibida como parametro ya es la
     * inversa, y en caso contrario retornara la etiqueta inversa.
     */
    private String getInverso(String x) {


        String a = x.substring(1);
//            System.out.println(a);
        a = "</" + a;
        return a;

    }

    public void combo() {

        this.cargarEtiquetas();

    }

    public boolean casoEtiqueta(String dato) {

        char etiqueta[] = dato.toCharArray();
        if (etiqueta[1]!= '/') {
            return true;
        }

        return false;
    }
}
