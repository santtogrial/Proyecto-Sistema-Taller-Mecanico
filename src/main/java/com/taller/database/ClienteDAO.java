package com.taller.database;

import com.taller.model.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClienteDAO {

    public void insertarCliente(Cliente cliente){
        String sql = "INSERT INTO clientes(Nombre,CuitCuil,Telefono) VALUES(?,?,?)";
        try (PreparedStatement ps = DataBaseManager.conectar().prepareStatement(sql)){
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getCuitCuil());
            ps.setString(3, cliente.getTelefono());
            ps.executeUpdate();
            var keys = ps.getGeneratedKeys();
            if (keys.next()){
                cliente.setId(keys.getInt(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Cliente> obtenerClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (ResultSet rs = DataBaseManager.conectar().createStatement().executeQuery(sql)){
            while(rs.next()){
                Cliente c = new Cliente(rs.getString("Nombre"),rs.getString("CuitCuil"),rs.getString("Telefono"));
                c.setId(rs.getInt("ID"));
                clientes.add(c);
            }
            return clientes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Cliente obtenerClientePorId(int id){
        String sql = "SELECT * FROM clientes WHERE ID = ?";
        try (PreparedStatement ps = DataBaseManager.conectar().prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Cliente c = null;
            if(rs.next()){
                c = new Cliente(rs.getString("Nombre"),rs.getString("CuitCuil"),rs.getString("Telefono"));
                c.setId(id);
            }
            return c;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Cliente obtenerClientePorCuitCuil(String cuitCuil){
        String sql = "SELECT * FROM clientes WHERE CuitCuil = ?";
        try (PreparedStatement ps = DataBaseManager.conectar().prepareStatement(sql)){
            ps.setString(1, cuitCuil);
            ResultSet rs = ps.executeQuery();
            Cliente c = null;
            if(rs.next()){
                c = new Cliente(rs.getString("Nombre"),rs.getString("CuitCuil"),rs.getString("Telefono"));
                c.setId(rs.getInt("ID"));
            }
            return c;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}
