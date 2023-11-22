package Cliente.controlador.chat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class escribirChat {
    public void escribirArchivo (String nombre, ArrayList<String>miTexto, boolean adicionar) throws IOException {
        File miArchivo=new File(nombre);
        FileWriter miFileWriter=new FileWriter(miArchivo,adicionar);
        BufferedWriter miBufferWriter=new BufferedWriter(miFileWriter);

        for(String linea:miTexto){
            miBufferWriter.write(linea+"\n");
        }
        miBufferWriter.close();
        miFileWriter.close();
    }
}
