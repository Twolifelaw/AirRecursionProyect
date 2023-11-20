package Cliente.modelo.objetos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class PaqueteTuristico implements Serializable {

    private ArrayList<Destino> destinos;
    private String nombre;
    private Date duracion;
    private String servicios;
    private double precio;
    private int cupoMaximo;
    private Date fechasDisponobles;

    public PaqueteTuristico(ArrayList<Destino> destinos, String nombre, Date duracion, String servicios, double precio, int cupoMaximo, Date fechasDisponobles) {
        this.destinos = destinos;
        this.nombre = nombre;
        this.duracion = duracion;
        this.servicios = servicios;
        this.precio = precio;
        this.cupoMaximo = cupoMaximo;
        this.fechasDisponobles = fechasDisponobles;
    }

    public PaqueteTuristico() {
    }

    public ArrayList<Destino> getDestinos() {
        return destinos;
    }

    public void setDestinos(ArrayList<Destino> destinos) {
        this.destinos = destinos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getDuracion() {
        return duracion;
    }

    public void setDuracion(Date duracion) {
        this.duracion = duracion;
    }

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public Date getFechasDisponobles() {
        return fechasDisponobles;
    }

    public void setFechasDisponobles(Date fechasDisponobles) {
        this.fechasDisponobles = fechasDisponobles;
    }
}
