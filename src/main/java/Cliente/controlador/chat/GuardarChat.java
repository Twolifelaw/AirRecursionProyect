package Cliente.controlador.chat;

import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GuardarChat {
    public static ArrayList<String>leerArchivoTexto(String ruta)throws IOException{

        File miArchivo=new File(ruta);
        FileReader miFileReader =new FileReader(miArchivo);
        BufferedReader miBufferReader=new BufferedReader(miFileReader);
        String linea;
        ArrayList<String>misLineas=new ArrayList<>();
        while ((linea=miBufferReader.readLine())!=null){
            misLineas.add(linea);
        }

        miBufferReader.close();
        miFileReader.close();
        return misLineas;
    }
    public static ArrayList<String> transformarObservable (ObservableList<Mensajes>mensajes){
        ArrayList<String >aux=new ArrayList<>();
        for(int i=0;i<mensajes.size();i++){
            aux.add(mensajes.get(i).getCliente()+" "+mensajes.get(i).getServidor());
        }
        return aux;

    }



}
