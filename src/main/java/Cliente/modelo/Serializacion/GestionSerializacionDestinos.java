package Cliente.modelo.Serializacion;

import Cliente.modelo.objetos.Destino;

import java.io.*;
import java.util.ArrayList;

public class GestionSerializacionDestinos {

    /**
     * Este método serializa una lista de objetos en un archivo de texto, pero antes de rescribir el arvhio
     * toma todos lo que esté en el archivo (si tubiera ya objetos serializados) y los alamcena en una lista
     * temporal y posteriro a ello añade un nuevo objeto a esa lista y lo serializa en el archivo
     *
     * @param nombreArchivo
     * @param nuevoCliente
     */
    public static void serializarDestino(String nombreArchivo, ArrayList<Destino> nuevoCliente) {
        ArrayList<Destino> listaClientes = new ArrayList<>(); // Cargamos la lista existente

        listaClientes.addAll(nuevoCliente); // Agregamos los nuevos elementos

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            out.writeObject(listaClientes); // Escribimos la lista completa en el archivo
            System.out.println("Objeto serializado y guardado en " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este método se encarga de deserializar los objetos de el archivo para que el método serializarObjetos lo use
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
     *
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

    public static void guardarDestinoTuristico(String nombreArchivo) {
        ArrayList<Destino> destinos = new ArrayList<>();
        //destinos.add(new Destino("Colombia", "Armenia", "aaaa", "/Armenia.jpg", "Calor"));
        //destinos.add(new Destino("Canada", "Toronto", "Descripción", "/Toronto.jpg", "Frio"));
        destinos.add(new Destino("Estados Uniidos", "Texas", "Vaqueros", "/Texas.jpg", "Calor","1212","002",100));
        GestionSerializacionDestinos.serializarDestino(nombreArchivo, destinos);
    }

    /**
     * Este método elimina un destino del archivo basado en el nombre del país.
     *
     * @param nombreArchivo
     * @param id            El nombre del país del destino a eliminar
     */
    public static void eliminarDestino(String nombreArchivo, String id) {
        ArrayList<Destino> destinos = deserializarObjetos(nombreArchivo);

        destinos.removeIf(destino -> destino.getId().equals(id));

        serializarDestino(nombreArchivo, destinos);
    }


}