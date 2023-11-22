package Cliente.controlador.chat;

import Cliente.controlador.VentanaUtilidades;
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
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.UUID;

public class ChatServerNuevo implements Runnable, Initializable {

    //
    @FXML
    private TableColumn<ClienteAux, String> colIdenti;

    @FXML
    private TableColumn<ClienteAux, String> colNombre;

    @FXML
    private TableView<ClienteAux> tableConexiones;

    @FXML
    private TableColumn<Mensajes, String> columCliente;
    @FXML
    private ImageView imagenAvion1;

    @FXML
    private ImageView imagenAvion2;


    @FXML
    private TableView<Mensajes> TableChat;

    @FXML
    private TableColumn<ClienteAux, String> columIp;
    @FXML
    private TableColumn<ClienteAux, String> columClientes;

    @FXML
    private TableView<ClienteAux> tableChats;

    @FXML
    private TableColumn<ClienteAux, String> columClientes1;
    @FXML
    private TableColumn<ClienteAux, String> columIp1;

    @FXML
    private TableColumn<Mensajes, String> columServer;

    @FXML
    private TableView<ClienteAux> tableEmplea;

    @FXML
    private Label labelSelec;

    @FXML
    private Button btnEnviar;

    @FXML
    private Label IDCliente;



    private ObservableList<Mensajes> observableList;
    private ObservableList<ClienteAux> chatList;
    private ObservableList<ClienteAux> emList;

    private String arreglo[] = new String[100];

    private boolean centi = false;
    private ArrayList<String> array = new ArrayList<>();
    private ArrayList<String> array2 = new ArrayList<>();

    public ChatServerNuevo() {
        Thread miHilo = new Thread(this);
        miHilo.start();
    }

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtEscribe;

    @FXML
    private TextField txtNombre;
    @FXML
    private Label labelSeleccion1;

    String ip="127.0.0.1";




    public static String[] convertirMsj(String msj) {
        String[] resultado = msj.split(" ");
        return resultado;
    }

    @FXML
    void OnCerrarChat(ActionEvent event) {


    }


    @FXML
    void OnEntrarChat(ActionEvent event) {

    }
    @FXML
    void OnActualizarChat(ActionEvent event) {

    }


    @FXML
    void OnEnviar(ActionEvent event) {
        Calendar Hora = Calendar.getInstance();
        String fechaActual, horaActual;
        int hora, minutos, segundos;


        hora = Hora.get(Calendar.HOUR_OF_DAY);
        minutos = Hora.get(Calendar.MINUTE);
        segundos = Hora.get(Calendar.SECOND);


        horaActual = hora + ":" + minutos + ":" + segundos;
        String aux = txtEscribe.getText();
        Mensajes msj = new Mensajes(" ", aux);
        try {
            Socket miSocket = new Socket(ip, 8090);
            IDCliente.setText(miSocket.getInetAddress().getHostAddress());
            PaqueteEnvio datos = new PaqueteEnvio();
            //DataOutputStream data=new DataOutputStream(miSocket.getOutputStream());
            //data.writeUTF(aux);
            datos.setNombre(combrobarVacioNombre(txtNombre.getText()));
            datos.setIp(combrobarVacioIp(ip));
            datos.setMensaje(aux);
            ObjectOutputStream paqueteDatos = new ObjectOutputStream(miSocket.getOutputStream());
            paqueteDatos.writeObject(datos);

            labelSeleccion1.setText(miSocket.getInetAddress().getHostAddress());
            miSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        if (aux.length() > 18) {
            int cont = 0;
            String[] array = convertirMsj(aux);
            int i = 0;

            while (i < array.length) {
                String mensa = "";
                while (cont < 16 && i < array.length) {
                    mensa = mensa + " " + array[i];
                    cont = cont + array[i].length();
                    i++;
                }
                cont = 0;
                Mensajes msj2 = new Mensajes("", mensa);
                observableList.add(msj2);

            }


            Mensajes msj3 = new Mensajes("", horaActual);
            observableList.add(msj3);
            TableChat.setItems(observableList);

        } else {

            observableList.add(msj);
            Mensajes msj3 = new Mensajes("", horaActual);
            observableList.add(msj3);
            TableChat.setItems(observableList);
        }
        if(!array2.contains(txtNombre.getText())){
            ClienteAux clienteAux=new ClienteAux(combrobarVacioNombre(txtNombre.getText()),ip);
            emList.add(clienteAux);
            tableEmplea.setItems(emList);
            array2.add(txtNombre.getText());

        }


        txtEscribe.setText("");


    }

    @Override
    public void run() {
        String mensaje = "";

        try {
            String nombre, message;
            PaqueteEnvio paqueteRecibido, paqueteRecibido2;
            Socket miSocket;

            ServerSocket server = new ServerSocket(8092);

            while (true) {
                miSocket = server.accept();
                ObjectInputStream paqueteDatos = new ObjectInputStream(miSocket.getInputStream());
                paqueteRecibido = (PaqueteEnvio) paqueteDatos.readObject();

                nombre = paqueteRecibido.getNombre();
                ip = paqueteRecibido.getIp();

                mensaje = paqueteRecibido.getMensaje();
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

                if(!array.contains(ip)){
                    ClienteAux clienteAux=new ClienteAux(nombre,ip);
                    chatList.add(clienteAux);
                    tableConexiones.setItems(chatList);

                    array.add(ip);

                }

                miSocket.close();

            }


            //Mensajes msj4=new Mensajes(" ","Mensaje de: "+miSocket.getInetAddress().getHostAddress());
            //observableList.add(msj4);
            //DataInputStream flujo=new DataInputStream(miSocket.getInputStream());

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public String combrobarVacioNombre (String txt){
        String r="Empleado1";
        if (txt.equals("")||txt==null){
            return r;
        }
        return txt;
    }

    public String combrobarVacioIp (String txt){
        String r="192.1.168.3";
        if (txt.equals("")||txt==null){
            return r;
        }
        return txt;

    }

    @FXML
    void OnAbrirChatEmpleado(ActionEvent event) throws IOException {

    }

    public static String generateShortID() {
        // Genera un UUID (Universally Unique Identifier)
        UUID uuid = UUID.randomUUID();

        // Obtiene solo los primeros 8 caracteres del UUID
        String shortID = uuid.toString().substring(0, 5);

        return shortID;
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableList = FXCollections.observableArrayList();
        chatList = FXCollections.observableArrayList();
        emList = FXCollections.observableArrayList();
        VentanaUtilidades.agregarAnimacionBoton(btnEnviar);
        IDCliente.setText("id: "+generateShortID());



        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colIdenti.setCellValueFactory(new PropertyValueFactory<>("ruta"));

        columServer.setCellValueFactory(new PropertyValueFactory<>("Servidor"));
        columCliente.setCellValueFactory(new PropertyValueFactory<>("Cliente"));

        columClientes1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columIp1.setCellValueFactory(new PropertyValueFactory<>("ruta"));

        tableConexiones.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // Añadir controladores de eventos según sea necesario
        tableConexiones.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Aquí puedes manejar la selección del elemento
            ip=newValue.getRuta();
            labelSelec.setText(""+newValue.toString());

        });

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

    public void abrirChatEmpleado() throws IOException {

    }
}