package Cliente.modelo.objetos;

import java.util.ArrayList;

public class Chat {
    private ArrayList<Mensaje> listaMensajes;
    private String idChat;

    public Chat(ArrayList<Mensaje> listaMensajes, String idChat) {
        this.listaMensajes = listaMensajes;
        this.idChat = idChat;
    }

    public ArrayList<Mensaje> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(ArrayList<Mensaje> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }

    public String getIdChat() {
        return idChat;
    }

    public void setIdChat(String idChat) {
        this.idChat = idChat;
    }
}
