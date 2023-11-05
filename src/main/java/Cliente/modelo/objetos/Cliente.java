package Cliente.modelo.objetos;

import java.io.Serializable;

public class Cliente extends Persona implements Serializable {
    private String correo;
    private String telefono;
    private String direccionResidencia;


    public Cliente(String nombre, String apellido, String cedula, String contrasena, String correo, String telefono, String direccionResidencia) {
        super(nombre, apellido, cedula, contrasena);
        this.correo = correo;
        this.telefono = telefono;
        this.direccionResidencia = direccionResidencia;
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

    @Override
    public String toString() {
        return "Cliente{" + super.toString() +
                "correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccionResidencia='" + direccionResidencia + '\'' +
                '}';
    }
}
