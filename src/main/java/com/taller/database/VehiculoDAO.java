package com.taller.database;

import com.taller.model.Cliente;
import com.taller.model.Vehiculo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VehiculoDAO {

    private ClienteDAO clienteDAO;

    public VehiculoDAO(ClienteDAO clienteDAO){
        this.clienteDAO = clienteDAO;
    }
    
    public void insertarVehiculo(Vehiculo vehiculo){
        String sql = "INSERT INTO vehiculo(Dominio,ID_Cliente_Dueño,Marca,Modelo,Año) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = DataBaseManager.conectar().prepareStatement(sql)){
            ps.setString(1, vehiculo.getDominio());
            ps.setInt(2, vehiculo.getDueño().getId());
            ps.setString(3, vehiculo.getMarca());
            ps.setString(4, vehiculo.getModelo());
            ps.setInt(5, vehiculo.getAño());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Vehiculo> obtenerVehiculos(){
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM vehiculo";
        try (ResultSet rs = DataBaseManager.conectar().createStatement().executeQuery(sql)){
            while(rs.next()){
                Vehiculo v = new Vehiculo(rs.getString("Dominio"),rs.getString("Marca"),rs.getString("Modelo"),rs.getInt("Año"), clienteDAO.obtenerClientePorId(rs.getInt("ID_Cliente_Dueño")));
                v.setKilometraje(rs.getInt("Kilometraje"));
                vehiculos.add(v);
            }
            return vehiculos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Vehiculo obtenerVehiculoPorDominio(String dominio){
        String sql = "SELECT * FROM vehiculo WHERE Dominio = ?";
        try (PreparedStatement ps = DataBaseManager.conectar().prepareStatement(sql)){
            ps.setString(1, dominio);
            ResultSet rs = ps.executeQuery();
            Vehiculo v = null;
            if(rs.next()){
                v = new Vehiculo(dominio, rs.getString("Marca"),rs.getString("Modelo"),rs.getInt("Año"), clienteDAO.obtenerClientePorId(rs.getInt("ID_Cliente_Dueño")));
                v.setKilometraje(rs.getInt("Kilometraje"));
            }
            return v;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ArrayList<Vehiculo> obtenerVehiculosPorClienteId(int clienteId){
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM vehiculo WHERE ID_Cliente_Dueño = ?";
        Cliente c = clienteDAO.obtenerClientePorId(clienteId);
        try (PreparedStatement ps = DataBaseManager.conectar().prepareStatement(sql)){
            ps.setInt(1, clienteId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Vehiculo v = new Vehiculo(rs.getString("Dominio"),rs.getString("Marca"),rs.getString("Modelo"),rs.getInt("Año"), c);
                v.setKilometraje(rs.getInt("Kilometraje"));
                vehiculos.add(v);
            }
            return vehiculos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void actualizarDueñoVehiculo(String dominio, int clienteId){
        String sql = "UPDATE vehiculo SET ID_Cliente_Dueño = ? WHERE Dominio = ?";
        try (PreparedStatement ps = DataBaseManager.conectar().prepareStatement(sql)){
            ps.setInt(1, clienteId);
            ps.setString(2, dominio);
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void actualizarKilometrajeVehiculo(String dominio, int kilometraje){
        String sql = "UPDATE vehiculo SET Kilometraje = ? WHERE Dominio = ?";
        try (PreparedStatement ps = DataBaseManager.conectar().prepareStatement(sql)){
            ps.setInt(1, kilometraje);
            ps.setString(2, dominio);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
