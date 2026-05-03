package com.taller.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller.database.ClienteDAO;
import com.taller.model.Cliente;
import com.taller.model.Vehiculo;

@Service
public class ClienteService {

    private ClienteDAO clienteDAO;
    private VehiculoService vehiculoService;

    @Autowired
    public ClienteService(ClienteDAO clienteDAO, VehiculoService vehiculoService){
        this.clienteDAO = clienteDAO;
        this.vehiculoService = vehiculoService;
    }

    public ArrayList<Cliente> obtenerClientes(){
        return clienteDAO.obtenerClientes();
    }

    public Cliente buscarClientePorId(int id){
        return clienteDAO.obtenerClientePorId(id);
    }

    public Cliente buscarClientePorCuitCuil(String cuitcuil){
        return clienteDAO.obtenerClientePorCuitCuil(cuitcuil);
    }

    public boolean cuitcuilExiste(String cuitcuil){
        return clienteDAO.obtenerClientePorCuitCuil(cuitcuil) != null;
    }

    public void agregarCliente(Cliente cliente){
        if (cuitcuilExiste(cliente.getCuitCuil())){
            throw new IllegalArgumentException("El Cuit/Cuil ya se encuentra registrado: " + cliente.getCuitCuil());
        }
        else{
            clienteDAO.insertarCliente(cliente);
        }
    }

    public double obtenerDeudaCliente(Cliente cliente){
        ArrayList<Vehiculo> vehiculos = vehiculoService.buscarVehiculosPorCliente(cliente);
        double deuda = 0;
        for (Vehiculo v : vehiculos){
            deuda = deuda + vehiculoService.obtenerDeudaVehiculo(v);
        }
        return deuda;
    }

    public ArrayList<Cliente> buscarClientesDeudores(){
        ArrayList<Cliente> clientes = clienteDAO.obtenerClientes();
        ArrayList<Cliente> clientesDeudores = new ArrayList<>();
        for (Cliente c : clientes){
            if (obtenerDeudaCliente(c) > 0){
                clientesDeudores.add(c);
            }
        }
        return clientesDeudores;
    }


    



}
