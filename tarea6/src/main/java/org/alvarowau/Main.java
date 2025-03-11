package org.alvarowau;

import org.exist.xmldb.DatabaseInstanceManager;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

/**
 * Clase principal que se conecta a una base de datos eXist-db para recuperar y mostrar información
 * sobre libros almacenados en un documento XML.
 */
public class Main {

    /**
     * Método principal que ejecuta la aplicación.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        String dbURL = "xmldb:exist://localhost:8080/exist/xmlrpc";
        String dbUser = "admin";
        String dbPassword = "admin";
        String collection = "/tarea6";
        String documentName = "libros.xml";

        try {
            String driver = "org.exist.xmldb.DatabaseImpl";
            Class<?> clazz = Class.forName(driver);
            Database database = (Database) clazz.newInstance();
            DatabaseManager.registerDatabase(database);

            Collection col = DatabaseManager.getCollection(dbURL + collection, dbUser, dbPassword);

            if (col == null) {
                System.err.println("Error: No se pudo obtener la colección '" + collection + "'.");
                return;
            }

            XPathQueryService service = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            String xquery = "doc('" + documentName + "')//libro";
            ResourceSet result = service.query(xquery);

            System.out.println("Información de Libros desde eXist-db:\n");
            ResourceIterator iter = result.getIterator();
            int libroCount = 0;

            while (iter.hasMoreResources()) {
                libroCount++;
                XMLResource res = (XMLResource) iter.nextResource();
                String content = res.getContent().toString();

                String titulo = getValue(content, "<titulo>", "</titulo>");
                String año = getValue(content, "<libro a\\u00f1o=\"", "\">");
                String editorial = getValue(content, "<editorial>", "</editorial>");
                String precio = getValue(content, "<precio>", "</precio>");

                System.out.println("----------------------------------------");
                System.out.println("Libro " + libroCount + ":");
                System.out.println("----------------------------------------");
                System.out.println("Título: " + titulo);
                System.out.println("Año: " + año);

                System.out.println("Autor(es):");
                String autoresContent = getContentBetween(content, "<autor>", "</autor>");
                if (!autoresContent.isEmpty()) {
                    String[] autores = autoresContent.split("</autor><autor>");
                    for (String autorXML : autores) {
                        String nombreAutor = getValue(autorXML, "<nombre>", "</nombre>");
                        String apellidoAutor = getValue(autorXML, "<apellido>", "</apellido>");
                        System.out.println("    - Nombre: " + nombreAutor + " Apellido: " + apellidoAutor);
                    }
                } else {
                    System.out.println("    Ninguno");
                }

                System.out.println("Editorial: " + editorial);
                System.out.println("Precio: " + precio);
                System.out.println("----------------------------------------\n");
            }

            System.out.println("Total de libros encontrados: " + libroCount);

            col.close();

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.err.println("Error al inicializar el driver de eXist-db: " + e.getMessage());
            e.printStackTrace();
        } catch (XMLDBException e) {
            System.err.println("Error de XMLDB: " + e.getMessage());
            System.err.println("Código de error XMLDB: " + e.errorCode);
            System.err.println("Mensaje detallado XMLDB: " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("Causa raíz del error XMLDB: " + e.getCause().getMessage());
            }
            e.printStackTrace();
        }
    }

    /**
     * Extrae un valor de una cadena XML basado en las etiquetas de inicio y fin proporcionadas.
     *
     * @param xmlContent El contenido XML del cual extraer el valor.
     * @param startTag La etiqueta de inicio que precede al valor deseado.
     * @param endTag La etiqueta de fin que sigue al valor deseado.
     * @return El valor extraído, o una cadena vacía si no se encuentra.
     */
    private static String getValue(String xmlContent, String startTag, String endTag) {
        int startIndex = xmlContent.indexOf(startTag);
        if (startIndex != -1) {
            startIndex += startTag.length();
            int endIndex = xmlContent.indexOf(endTag, startIndex);
            if (endIndex != -1) {
                return xmlContent.substring(startIndex, endIndex).trim();
            }
        }
        return "";
    }

    /**
     * Extrae el contenido entre dos etiquetas en una cadena XML.
     *
     * @param xmlContent El contenido XML del cual extraer el contenido.
     * @param startTag La etiqueta de inicio que precede al contenido deseado.
     * @param endTag La etiqueta de fin que sigue al contenido deseado.
     * @return El contenido extraído, o una cadena vacía si no se encuentra.
     */
    private static String getContentBetween(String xmlContent, String startTag, String endTag) {
        int startIndex = xmlContent.indexOf(startTag);
        if (startIndex != -1) {
            startIndex += startTag.length();
            int endIndex = xmlContent.lastIndexOf(endTag);
            if (endIndex != -1 && endIndex > startIndex) {
                return xmlContent.substring(startIndex, endIndex).trim();
            }
        }
        return "";
    }
}