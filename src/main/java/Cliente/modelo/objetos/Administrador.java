package Cliente.modelo.objetos;public class Administrador extends Persona{    private String correo;    public Administrador(String nombre, String apellido, String cedula, String contrasena, String correo) {        super(nombre, apellido, cedula, contrasena);        this.correo = correo;    }    public String getCorreo() {        return correo;    }    public void setCorreo(String correo) {        this.correo = correo;    }}