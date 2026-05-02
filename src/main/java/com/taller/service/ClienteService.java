package com.taller.service;

import com.taller.database.ClienteDAO;
import com.taller.model.Cliente;

public class ClienteService {

    private ClienteDAO clienteDAO;

    public ClienteService(ClienteDAO clienteDAO){
        this.clienteDAO = clienteDAO;
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


    



}
