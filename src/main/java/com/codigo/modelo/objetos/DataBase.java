    package com.codigo.modelo.objetos;

    import java.util.ArrayList;

    public class DataBase {

        public  static  ArrayList<Empleado> empleados = new ArrayList<>();


        public ArrayList<Empleado> setValoresQuemados(){
            Empleado empleado = new Empleado("Brandon","Montealegre","1001277430","1234");
            empleados.add(empleado);
            return empleados;
        }


    }
