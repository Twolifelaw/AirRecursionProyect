package Cliente.modelo.objetos;

import java.util.Date;

public class Reserva {

    private Date fechaSolicitud;
    private Date fechaPlanificada;
    private String nombreCliente;
    private int cantidadViajantes;
    private PaqueteTuristico paqueteTuristico;
    private Estado estado;
    private EnumGuiaTuristico guiaTuristico;

    public Reserva(Date fechaSolicitud, Date fechaPlanificada, String nombreCliente, int cantidadViajantes, PaqueteTuristico paqueteTuristico, Estado estado, EnumGuiaTuristico guiaTuristico) {
        this.fechaSolicitud = fechaSolicitud;
        this.fechaPlanificada = fechaPlanificada;
        this.nombreCliente = nombreCliente;
        this.cantidadViajantes = cantidadViajantes;
        this.paqueteTuristico = paqueteTuristico;
        this.estado = estado;
        this.guiaTuristico = guiaTuristico;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaPlanificada() {
        return fechaPlanificada;
    }

    public void setFechaPlanificada(Date fechaPlanificada) {
        this.fechaPlanificada = fechaPlanificada;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getCantidadViajantes() {
        return cantidadViajantes;
    }

    public void setCantidadViajantes(int cantidadViajantes) {
        this.cantidadViajantes = cantidadViajantes;
    }

    public PaqueteTuristico getPaqueteTuristico() {
        return paqueteTuristico;
    }

    public void setPaqueteTuristico(PaqueteTuristico paqueteTuristico) {
        this.paqueteTuristico = paqueteTuristico;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public EnumGuiaTuristico getGuiaTuristico() {
        return guiaTuristico;
    }

    public void setGuiaTuristico(EnumGuiaTuristico guiaTuristico) {
        this.guiaTuristico = guiaTuristico;
    }
}
