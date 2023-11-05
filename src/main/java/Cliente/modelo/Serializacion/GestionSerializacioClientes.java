package Cliente.modelo.Serializacion;

import Cliente.modelo.objetos.Cliente;

import java.io.*;
import java.util.ArrayList;

public class GestionSerializacioClientes {

    public static void serializarObjetos(String nombreArchivo, ArrayList<Cliente> listaClientes) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nombreArchivo, true))) {
            out.writeObject(listaClientes);
            System.out.println("Objeto serializado y guardado en " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Cliente> deserializarObjetos(String nombreArchivo) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            ArrayList<Cliente> objeto = (ArrayList<Cliente>) in.readObject();
            System.out.println("Objeto deserializado desde " + nombreArchivo);
            return objeto;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}
