package Cliente.modelo.objetos;

import Cliente.modelo.enums.EstadoMensaje;

import java.util.Date;

public class Mensaje {

    private String texto;
    private Date fecha;
    private Cliente propietario;

    private EstadoMensaje estadoMensaje;

    public Mensaje(String texto, Date fecha, Cliente propietario, EstadoMensaje estadoMensaje) {
        this.texto = texto;
        this.fecha = fecha;
        this.propietario = propietario;
        this.estadoMensaje = estadoMensaje;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getPropietario() {
        return propietario;
    }

    public void setPropietario(Cliente propietario) {
        this.propietario = propietario;
    }

    public EstadoMensaje getEstadoMensaje() {
        return estadoMensaje;
    }

    public void setEstadoMensaje(EstadoMensaje estadoMensaje) {
        this.estadoMensaje = estadoMensaje;
    }
}
