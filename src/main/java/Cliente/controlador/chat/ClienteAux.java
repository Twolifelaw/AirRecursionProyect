package Cliente.controlador.chat;

public class ClienteAux {
    private String nombre;

    private String ruta;

    @Override
    public String toString() {
        return "| "+nombre+ " "+ruta+" |";
    }

    public ClienteAux(String nombre, String ruta) {
        this.nombre = nombre;
        this.ruta = ruta;

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
