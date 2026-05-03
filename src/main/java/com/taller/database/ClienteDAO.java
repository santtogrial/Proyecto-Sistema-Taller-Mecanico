package com.taller.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.taller.model.Cliente;

@Repository
public class ClienteDAO {

    public void insertarCliente(Cliente cliente){
        String sql = "INSERT INTO clientes(Nombre,CuitCuil,Telefono) VALUES(?,?,?)";
        try (Connection conn = DataBaseManager.conectar();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getCuitCuil());
            ps.setString(3, cliente.getTelefono());
            ps.executeUpdate();
            var keys = ps.getGeneratedKeys();
            if (keys.next()){
                cliente.setId(keys.getInt(1));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar cliente", e);
        }
    }

    public ArrayList<Cliente> obtenerClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = DataBaseManager.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                Cliente c = new Cliente(rs.getString("Nombre"),rs.getString("CuitCuil"),rs.getString("Telefono"));
                c.setId(rs.getInt("ID"));
                clientes.add(c);
            }
            return clientes;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener clientes", e);
        }
    }

    public Cliente obtenerClientePorId(int id){
        String sql = "SELECT * FROM clientes WHERE ID = ?";
        try (Connection conn = DataBaseManager.conectar();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();){
            Cliente c = null;
            if(rs.next()){
                c = new Cliente(rs.getString("Nombre"),rs.getString("CuitCuil"),rs.getString("Telefono"));
                c.setId(id);
            }
            return c;
        }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener cliente", e);
        }
    }

    public Cliente obtenerClientePorCuitCuil(String cuitCuil){
        String sql = "SELECT * FROM clientes WHERE CuitCuil = ?";
        try (Connection conn = DataBaseManager.conectar();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, cuitCuil);
            try (ResultSet rs = ps.executeQuery()){
                Cliente c = null;
                if(rs.next()){
                    c = new Cliente(rs.getString("Nombre"),rs.getString("CuitCuil"),rs.getString("Telefono"));
                    c.setId(rs.getInt("ID"));
                }
                return c;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener cliente", e);
        }
    }
    
}
