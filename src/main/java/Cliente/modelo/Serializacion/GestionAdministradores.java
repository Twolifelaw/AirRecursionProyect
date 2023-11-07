package Cliente.modelo.Serializacion;

import Cliente.modelo.objetos.Administrador;
import Cliente.modelo.objetos.Cliente;

import java.io.*;
import java.util.ArrayList;



public class GestionAdministradores {

    public static void serializarAdinistrador(String nombreArchivo, ArrayList<Administrador> nuevoCliente) {
        ArrayList<Administrador> listaClientes = deserializarObjetos(nombreArchivo); // Cargamos la lista existente
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

    public static ArrayList<Administrador> deserializarObjetos(String nombreArchivo) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (ArrayList<Administrador>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Maneja las excepciones adecuadamente si el archivo no existe o no se puede leer
            e.printStackTrace(); // Asegúrate de manejar la excepción de forma adecuada
            return null;
        }
    }

    public static ArrayList<Administrador> deserializarAdministradorDesdeArchivo(String nombreArchivo) {
        ArrayList<Administrador> listaClientes = new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            ArrayList<Administrador> clientes = (ArrayList<Administrador>) in.readObject();
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



}
