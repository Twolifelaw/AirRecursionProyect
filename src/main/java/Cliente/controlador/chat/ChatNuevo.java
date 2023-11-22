package Cliente.controlador.chat;

import Cliente.controlador.VentanaUtilidades;
import Cliente.modelo.exceptions.VerificarException;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ChatNuevo implements Initializable,Runnable {

    @FXML
    private VBox Box;

    @FXML
    private Button btnEnviar;
    @FXML
    private TextFlow FlowCliente;

    @FXML
    private TextFlow FlowServer;


    @FXML
    private ImageView imagenAvion1;

    @FXML
    private ImageView imagenAvion2;

    @FXML
    private ImageView imagenRotar;


    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtEscribe;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextArea AreaCliente;

    @FXML
    private TextArea AreaServer;


    @FXML
    private VBox boxServer;

    @FXML
    private Label lblStatus;
    @FXML
    private Pane paneServer;

    @FXML
    private TableView<Mensajes> TableChat;

    @FXML
    private TableColumn<Mensajes, String> columCliente;

    @FXML
    private TableColumn<Mensajes, String> columServer;




    private ObservableList<Mensajes> observableList;
    private ObservableList<Mensajes> mensajesGuardados;

    public static String restante="";



    public ChatNuevo (){
        Thread miHilo=new Thread(this);
        miHilo.start();

    }

    public void escribirMensaje (String mensaje){

    }

    public String retornaMensaje (){
        return txtEscribe.getText();
    }

    public void escribeServer (String mensaje){

    }

    public void actualizarText (){
        txtEscribe.setText(" ");
    }

    @FXML
    void OnEnviar(ActionEvent event) throws IOException, InterruptedException {

        //Lineas para la hora y fecha
        Calendar Hora=Calendar.getInstance();
        String fechaActual,horaActual;
        int hora,minutos,segundos,dia,mes,anio;


        hora=Hora.get(Calendar.HOUR_OF_DAY);
        minutos=Hora.get(Calendar.MINUTE);
        segundos=Hora.get(Calendar.SECOND);


        horaActual=hora+":"+minutos+":"+segundos;
        String aux=txtEscribe.getText();
        Mensajes msj=new Mensajes(" ",aux);
        try {
            Socket miSocket = new Socket("localhost", 8092);
            txtDireccion.setText(miSocket.getInetAddress().getHostAddress());
            PaqueteEnvio datos=new PaqueteEnvio();
            //DataOutputStream data=new DataOutputStream(miSocket.getOutputStream());
            //data.writeUTF(aux);
            datos.setNombre(combrobarVacioNombre(txtNombre.getText()));
            datos.setIp(combrobarVacioIp(txtDireccion.getText()));
            datos.setMensaje(aux);
            ObjectOutputStream paqueteDatos=new ObjectOutputStream(miSocket.getOutputStream());
            paqueteDatos.writeObject(datos);


            lblStatus.setText("IP: "+miSocket.getInetAddress().getHostAddress());
            miSocket.close();

        }catch (IOException e){
            e.printStackTrace();
        }


        if (aux.length()>18){
            int cont=0;
            String []array=convertirMsj(aux);
            int i=0;

            while(i<array.length){
                String mensa="";
                while (cont<16&&i< array.length){
                    mensa=mensa+" "+array[i];
                    cont=cont+array[i].length();
                    i++;
                }
                cont=0;
                Mensajes msj2=new Mensajes("",mensa);
                observableList.add(msj2);

            }


            Mensajes msj3=new Mensajes("",horaActual);
            observableList.add(msj3);
            TableChat.setItems(observableList);

        }else{

            observableList.add(msj);
            Mensajes msj3=new Mensajes("",horaActual);
            observableList.add(msj3);
            TableChat.setItems(observableList);
        }

        txtEscribe.setText("");
    }

    public static String [] convertirMsj (String msj){
        String []resultado=msj.split(" ");
        return resultado;
    }
    public String combrobarVacioNombre (String txt){
        String r="Usuario";
        if (txt.equals("")||txt==null){
            return r;
        }
        return txt;
    }

    public String combrobarVacioIp (String txt){
        String r="localhost";
        if (txt.equals("")||txt==null){
            return r;
        }
        return txt;

    }

    public static String organizarPrimer (String msj){

        int lastSpace=0;
        for (int i=0;i<msj.length();i++){
            char op=msj.charAt(i);
            if(op==32){
                lastSpace=i;
            }
        }

        String resu="";

        for (int j=0;j<lastSpace;j++){
            resu= String.valueOf(msj.charAt(j));
        }
        for(int k=lastSpace;k<msj.length();k++){
            restante= String.valueOf(msj.charAt(k));
        }
        return resu;

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        observableList= FXCollections.observableArrayList();
        mensajesGuardados=FXCollections.observableArrayList();
        columServer.setCellValueFactory(new PropertyValueFactory<>("Servidor"));
        columCliente.setCellValueFactory(new PropertyValueFactory<>("Cliente"));
        inicializarEnterKey();

        VentanaUtilidades.agregarAnimacionBoton(btnEnviar);
        RotateTransition rotate2 = new RotateTransition();
        rotate2.setNode(imagenAvion1);
        rotate2.setDuration(Duration.millis(1700));
        rotate2.setCycleCount(TranslateTransition.INDEFINITE);
        rotate2.setInterpolator(Interpolator.LINEAR);
        rotate2.setByAngle(360);
        rotate2.setAxis(Rotate.Y_AXIS);
        rotate2.play();

        RotateTransition rotate3 = new RotateTransition();
        rotate3.setNode(imagenAvion2);
        rotate3.setDuration(Duration.millis(1700));
        rotate3.setCycleCount(TranslateTransition.INDEFINITE);
        rotate3.setInterpolator(Interpolator.LINEAR);
        rotate3.setByAngle(-360);
        rotate3.setAxis(Rotate.Y_AXIS);
        rotate3.play();


    }

    private void inicializarEnterKey() {
        TextField[] camposTexto = {txtEscribe};

        for (TextField campo : camposTexto) {
            campo.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    btnEnviar.fire();
                }
            });
        }
    }

    @Override
    public void run() {
        ServerSocket serverCliente = null;

        try {
            serverCliente = new ServerSocket(8090);

            while (true) {
                Socket cliente = serverCliente.accept();
                ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());
                PaqueteEnvio paqueteRecibido = (PaqueteEnvio) flujoEntrada.readObject();
                String nombre = paqueteRecibido.getNombre();
                String ip = paqueteRecibido.getIp();
                String mensaje = paqueteRecibido.getMensaje();

                Mensajes msj4 = new Mensajes("Mensaje de: " + nombre + " " + ip, "");
                observableList.add(msj4);

                Mensajes msj = new Mensajes(mensaje, "");
                if (mensaje.length() > 18) {
                    int cont = 0;
                    String[] array = convertirMsj(mensaje);
                    int i = 0;

                    while (i < array.length) {
                        String mensa = "";
                        while (cont < 16 && i < array.length) {
                            mensa = mensa + " " + array[i];
                            cont = cont + array[i].length();
                            i++;
                        }
                        cont = 0;
                        Mensajes msj2 = new Mensajes(mensa, "");
                        observableList.add(msj2);
                    }
                    TableChat.setItems(observableList);

                } else {

                    observableList.add(msj);
                    TableChat.setItems(observableList);
                }
            }
        } catch (BindException bindException) {
            System.out.println("¡Error! La dirección ya se está utilizando. Otro usuario ya se ha conectado.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Aquí puedes agregar código adicional si es necesario
        }
    }
    public ObservableList<Mensajes> transformarArray (ArrayList<String> array){
        return mensajesGuardados;

    }
}
