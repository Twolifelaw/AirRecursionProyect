package Cliente.modelo.objetos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class PaqueteTuristico implements Serializable {

    private ArrayList<Destino> destinos;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private ArrayList<LocalDate> fechaDisponible;
    private String servicios;
    private double precio;
    private int cuposMaximos;

    public PaqueteTuristico(ArrayList<Destino> destinos, String nombre, LocalDate fechaInicio, LocalDate fechaFin, ArrayList<LocalDate> fechaDisponible, String servicios, double precio, int cuposMaximos) {
        this.destinos = destinos;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaDisponible = fechaDisponible;
        this.servicios = servicios;
        this.precio = precio;
        this.cuposMaximos = cuposMaximos;
    }

    public PaqueteTuristico(ArrayList<LocalDate> fechaDisponible) {
        this.fechaDisponible = fechaDisponible;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public ArrayList<LocalDate> getFechaDisponible() {
        return fechaDisponible;
    }

    public void setFechaDisponible(ArrayList<LocalDate> fechaDisponible) {
        this.fechaDisponible = fechaDisponible;
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

    public int getCuposMaximos() {
        return cuposMaximos;
    }

    public void setCuposMaximos(int cuposMaximos) {
        this.cuposMaximos = cuposMaximos;
    }
}
