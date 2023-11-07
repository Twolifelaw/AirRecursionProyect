package Cliente.modelo.objetos;

public class GuiaTuristico extends Persona {
    private String[] lenguajes;
    private double experiencia;
    private String telefono;
    private String correo;


    public GuiaTuristico(String nombre, String apellido, String cedula, String contrasena) {
        super(nombre, apellido, cedula, contrasena);
    }

    public String[] getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(String[] lenguajes) {
        this.lenguajes = lenguajes;
    }

    public double getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(double experiencia) {
        this.experiencia = experiencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
