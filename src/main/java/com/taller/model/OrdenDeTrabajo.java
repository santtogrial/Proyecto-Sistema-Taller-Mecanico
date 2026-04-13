package com.taller.model;
import com.taller.enums.EstadoOrden;
import java.time.LocalDate;
import java.util.List;

public class OrdenDeTrabajo {
    private int id;
    private Vehiculo vehiculo;
    private int kilometraje;
    private LocalDate fechaRecepcion;
    private LocalDate fechaFinalizacion;
    private EstadoOrden estado;
    private List<ItemRepuesto> repuestos;
    private List<ItemManoDeObra> manoDeObra;
    private List<Pago> pagos;
    //Path presupuesto


    public double calcularTotal(){
        return 1;
    }

    public double calcularTotalPagado(){
        return 1;
    }

    public double calcularSaldo(){
        return 1;
    }

    public boolean estaSaldada(){
        return true;
    }


    
}
