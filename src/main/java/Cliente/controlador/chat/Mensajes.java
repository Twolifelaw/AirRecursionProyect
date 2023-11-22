package Cliente.controlador.chat;

public class Mensajes {
    private String Servidor;
    private String Cliente;

    public Mensajes(String servidor, String cliente) {
        Servidor = servidor;
        Cliente = cliente;
    }

    public String getServidor() {
        return Servidor;
    }

    public void setServidor(String servidor) {
        Servidor = servidor;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }
}
