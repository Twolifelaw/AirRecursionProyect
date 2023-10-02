package Cliente.modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GestionClientes {
    public static void main(String[] args) {
        guardarDatosCliente("Camilo","123");
    }
    
    public static void guardarDatosCliente(String nombre, String contrasena) {
        try {
            // Abre un archivo de texto para escritura (si no existe, lo crea)
            BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.txt", true));

            // Escribe los datos del cliente en el archivo
            writer.write("Nombre Usuario: " + nombre);
            writer.newLine(); // Agrega una nueva línea para separar los registros
            writer.write("Contraseña: " + contrasena);
            writer.newLine(); // Agrega otra nueva línea al final

            // Cierra el archivo
            writer.close();

            System.out.println("Datos del cliente guardados con éxito.");
        } catch (IOException e) {
            System.err.println("Error al guardar los datos del cliente: " + e.getMessage());
        }
    }
}
