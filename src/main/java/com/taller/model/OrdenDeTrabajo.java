package com.taller.model;
import com.taller.enums.EstadoOrden;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdenDeTrabajo {
    private int id;
    private Vehiculo vehiculo;
    private int kilometraje;
    private String descripcion;
    private LocalDate fechaRecepcion;
    private LocalDate fechaFinalizacion;
    private EstadoOrden estado;
    private List<ItemRepuesto> repuestos;
    private List<ItemManoDeObra> manoDeObra;
    private List<Pago> pagos;
    //Path presupuesto

    // Getters y Setters
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Vehiculo getVehiculo(){
        return vehiculo;
    }
    public void setVehiculo(Vehiculo vehiculo){
        this.vehiculo = vehiculo;
    }

    public int getKilometraje(){
        return kilometraje;
    }
    public void setKilometraje(int kilometraje){
        this.kilometraje = kilometraje;
    }

    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public LocalDate getFechaRecepcion(){
        return fechaRecepcion;
    }
    public void setFechaRecepcion(LocalDate fechaRecepcion){
        this.fechaRecepcion = fechaRecepcion;
    }

    public LocalDate getFechaFinalizacion(){
        return fechaFinalizacion;
    }
    public void setFechaFinalizacion(LocalDate fechafinalizacion){
        this.fechaFinalizacion = fechafinalizacion;
    }

    public EstadoOrden getEstadoOrden(){
        return estado;
    }
    public void setEstadoOrden(EstadoOrden estado){
        this.estado = estado;
    }

    public List<ItemRepuesto> getRepuestos(){
        return repuestos;
    }
    
    public List<ItemManoDeObra> getManoDeObra(){
        return manoDeObra;
    }

    public List<Pago> getPagos(){
        return pagos;
    }

    // Constructor

    public OrdenDeTrabajo(Vehiculo vehiculo, int kilometraje, String descripcion){
        this.vehiculo = vehiculo;
        this.kilometraje = kilometraje;
        this.descripcion = descripcion;
        estado = EstadoOrden.EN_PROCESO;
        fechaRecepcion = LocalDate.now();
        vehiculo.setKilometraje(kilometraje);
        repuestos = new ArrayList<ItemRepuesto>();
        manoDeObra = new ArrayList<ItemManoDeObra>();
        pagos = new ArrayList<Pago>();
    }

    // Funciones

    // Pagos
    public double calcularTotal(){
        double total = 0;
        for (ItemRepuesto r : repuestos){
            total = total + r.getMonto();
        }
        for (ItemManoDeObra m : manoDeObra){
            total = total + m.getMonto();
        }
        return total;
    }

    public double calcularTotalPagado(){
        double total = 0;
        for (Pago p : pagos){
            total = total + p.getMonto();
        }
        return total;
    }

    public double calcularSaldo(){
        return calcularTotal() - calcularTotalPagado();
    }

    public boolean estaSaldada(){
        return calcularSaldo() == 0;
    }

    public void registrarPago(Pago pago){
        pagos.add(pago);
    }


    // Repuestos y Mano de Obra
    public void agregarRepuesto(ItemRepuesto repuesto){
        repuestos.add(repuesto);
    }

    public void agregarManoDeObra(ItemManoDeObra manoDeObra){
        this.manoDeObra.add(manoDeObra);
    }


    
}
