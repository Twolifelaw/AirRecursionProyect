package Cliente.modelo.objetos;

public class Cliente extends Persona{
    private String correo;
    private String telefono;
    private String direccionResidencia;

    public Cliente(String nombre, String apellido, String cedula, String contrasena) {
        super(nombre, apellido, cedula, contrasena);
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }
}
