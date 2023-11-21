package Cliente.modelo.Serializacion;

import Cliente.modelo.objetos.Destino;
import Cliente.modelo.objetos.PaqueteTuristico;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class GestionPaquetes {

    /**
     * Este método serializa una lista de objetos en un archivo de texto, pero antes de rescribir el arvhio
     * toma todos lo que esté en el archivo (si tubiera ya objetos serializados) y los alamcena en una lista
     * temporal y posteriro a ello añade un nuevo objeto a esa lista y lo serializa en el archivo
     *
     * @param nombreArchivo
     * @param nuevoCliente
     */
    public static void serializarPaquetes(String nombreArchivo, ArrayList<PaqueteTuristico> nuevoCliente) {
        ArrayList<PaqueteTuristico> listaClientes = new ArrayList<>(); // Cargamos la lista existente

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
    public static ArrayList<PaqueteTuristico> deserializarObjetos(String nombreArchivo) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (ArrayList<PaqueteTuristico>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Maneja las excepciones adecuadamente si el archivo no existe o no se puede leer
            e.printStackTrace(); // Asegúrate de manejar la excepción de forma adecuada
            return null;
        }
    }

    /**
     * Este metodo deserialisa los clientes del el archivo.
     *
     *
     * @param nombreArchivo
     * @return
     */
    public static ArrayList<PaqueteTuristico> deserializarPaquetes(String nombreArchivo) {
        ArrayList<PaqueteTuristico> listaPaquetes = new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            ArrayList<PaqueteTuristico> paquetesTuristicos = (ArrayList<PaqueteTuristico>) in.readObject();
            listaPaquetes.addAll(paquetesTuristicos);
            System.out.println("Clientes deserializados desde " + nombreArchivo);
        } catch (EOFException e) {
            // Se lanza EOFException al llegar al final del archivo
            System.out.println("Fin del archivo " + nombreArchivo);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return listaPaquetes;
    }

    /**
     * Este método elimina un destino del archivo basado en el nombre del país.
     *
     * @param nombreArchivo
     * @param nombre          El nombre del país del destino a eliminar
     */
    public static void eliminarPaquete(String nombreArchivo, String nombre) {
        ArrayList<PaqueteTuristico> paqueteTuristicos = deserializarObjetos(nombreArchivo);

        paqueteTuristicos.removeIf(destino -> destino.getNombre().equals(nombre));

        serializarPaquetes(nombreArchivo, paqueteTuristicos);
    }

    /*public static void crearPaquetePrueba(){
        ArrayList<PaqueteTuristico> pack = new ArrayList<>();
        ArrayList<Destino> destinosCargados = GestionSerializacionDestinos.deserializarDestino("destinos.dat");//Se trae los destinos deserializados.
        Date fecha = new Date(2023,11,19);
        pack.add(new PaqueteTuristico(destinosCargados,"Full paquete",fecha,"Hotel , trasporte",50000,200,fecha));
        GestionPaquetes.serializarPaquetes("paquetes.dat",pack);
    }

     */

}
