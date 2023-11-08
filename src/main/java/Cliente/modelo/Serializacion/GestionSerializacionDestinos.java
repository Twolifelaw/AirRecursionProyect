package Cliente.modelo.Serializacion;

import Cliente.modelo.objetos.Destino;

import java.io.*;
import java.util.ArrayList;

public class GestionSerializacionDestinos {
    public static ArrayList<Destino> destinos = new ArrayList<>();

    public static void guardarDestinos(ArrayList<Destino> destinos, String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(destinos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Destino> cargarDestinos(String archivo) {
        ArrayList<Destino> destinos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            destinos = (ArrayList<Destino>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return destinos;
    }



    /**Este método serializa una lista de objetos en un archivo de texto, pero antes de rescribir el arvhio
     * toma todos lo que esté en el archivo (si tubiera ya objetos serializados) y los alamcena en una lista
     * temporal y posteriro a ello añade un nuevo objeto a esa lista y lo serializa en el archivo
     *
     * @param nombreArchivo
     * @param nuevoCliente
     */
    public static void serializarDestino(String nombreArchivo, ArrayList<Destino> nuevoCliente) {
        ArrayList<Destino> listaClientes = deserializarObjetos(nombreArchivo); // Cargamos la lista existente
        if (listaClientes == null) {
            listaClientes = new ArrayList<>(); // Si no hay datos en el archivo, creamos una nueva lista
        }
        listaClientes.addAll(nuevoCliente); // Agregamos los nuevos elementos

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            out.writeObject(listaClientes); // Escribimos la lista completa en el archivo
            System.out.println("Objeto serializado y guardado en " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**Este método se encarga de deserializar los objetos de el archivo para que el método serializarObjetos lo use
     *
     * @param nombreArchivo
     * @return
     */
    public static ArrayList<Destino> deserializarObjetos(String nombreArchivo) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (ArrayList<Destino>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Maneja las excepciones adecuadamente si el archivo no existe o no se puede leer
            e.printStackTrace(); // Asegúrate de manejar la excepción de forma adecuada
            return null;
        }
    }


    /**
     * Este metodo deserialisa los clientes del el archivo.
     * @param nombreArchivo
     * @return
     */
    public static ArrayList<Destino> deserializarDestino(String nombreArchivo) {
        ArrayList<Destino> listaClientes = new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            ArrayList<Destino> clientes = (ArrayList<Destino>) in.readObject();
            listaClientes.addAll(clientes);
            System.out.println("Clientes deserializados desde " + nombreArchivo);
        } catch (EOFException e) {
            // Se lanza EOFException al llegar al final del archivo
            System.out.println("Fin del archivo " + nombreArchivo);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return listaClientes;
    }

    public static void guardarDestinoTuristico( String nombreArchivo){
        destinos.add(new Destino("Colombia", "Armenia", "aaaa","/com/vista/Imagenes/Armenia.jpg" , "Calor"));
        destinos.add(new Destino("Canada", "Toronto", "Descripción","/com/vista/Imagenes/Toronto.jpg", "Frio"));
        GestionSerializacionDestinos.serializarDestino(nombreArchivo,destinos);
    }

}
