package Cliente.modelo;

import Cliente.modelo.objetos.Destino;

import java.io.*;
import java.util.ArrayList;

public class DatosPersistencia {

    public static void guardarDestinos(ArrayList<Destino> destinos, String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(destinos);
        } catch ( IOException e) {
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

}
