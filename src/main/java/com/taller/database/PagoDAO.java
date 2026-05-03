package com.taller.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taller.enums.TipoPago;
import com.taller.model.OrdenDeTrabajo;
import com.taller.model.Pago;
import com.taller.model.PagoEfectivo;
import com.taller.model.PagoTarjeta;
import com.taller.model.PagoTransferencia;

@Repository
public class PagoDAO {

    private OrdenDeTrabajoDAO ordenDeTrabajoDAO;

    @Autowired
    public PagoDAO(OrdenDeTrabajoDAO ordenDeTrabajoDAO){
        this.ordenDeTrabajoDAO = ordenDeTrabajoDAO;
    }

    public void insertarPago(Pago pago){
        String sql = "INSERT INTO pago(ID_OrdenDeTrabajo,Monto,Fecha,Tipo,NumeroComprobante,TipoTarjeta,Ultimos4Digitos) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = DataBaseManager.conectar();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, pago.getOrdenDeTrabajo().getId());
            ps.setDouble(2, pago.getMonto());
            ps.setString(3, pago.getFecha().toString());
            ps.setString(4, pago.getTipo().toString());
            if (pago.getTipo() == TipoPago.TRANSFERENCIA){
                ps.setString(5, ((PagoTransferencia) pago).getNumeroComprobante());
                ps.setNull(6, java.sql.Types.VARCHAR);
                ps.setNull(7, java.sql.Types.VARCHAR);
            } else if (pago.getTipo() == TipoPago.TARJETA){
                ps.setNull(5, java.sql.Types.VARCHAR);
                ps.setString(6, ((PagoTarjeta) pago).getTipoTarjeta());
                ps.setString(7, ((PagoTarjeta) pago).getUltimos4Digitos());
            } else {
                ps.setNull(5, java.sql.Types.VARCHAR);
                ps.setNull(6, java.sql.Types.VARCHAR);
                ps.setNull(7, java.sql.Types.VARCHAR);
            }
            ps.executeUpdate();
            var keys = ps.getGeneratedKeys();
            if (keys.next()){
                pago.setId(keys.getInt(1));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar pago", e);
        }
    }

    public ArrayList<Pago> obtenerPagos(){
        ArrayList<Pago> pagos = new ArrayList<>();
        String sql = "SELECT * FROM pago";
        try (Connection conn = DataBaseManager.conectar();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                TipoPago t = TipoPago.valueOf(rs.getString("Tipo"));
                Pago p;
                if (t == TipoPago.TRANSFERENCIA){
                    p = new PagoTransferencia(rs.getDouble("Monto"), ordenDeTrabajoDAO.obtenerOrdenDeTrabajoPorId(rs.getInt("ID_OrdenDeTrabajo")), rs.getString("NumeroComprobante"));
                }   else if (t == TipoPago.TARJETA){
                        p = new PagoTarjeta(rs.getDouble("Monto"), ordenDeTrabajoDAO.obtenerOrdenDeTrabajoPorId(rs.getInt("ID_OrdenDeTrabajo")), rs.getString("TipoTarjeta"),rs.getString("Ultimos4Digitos"));
                }   else {
                        p = new PagoEfectivo(rs.getDouble("Monto"), ordenDeTrabajoDAO.obtenerOrdenDeTrabajoPorId(rs.getInt("ID_OrdenDeTrabajo")));
                }
                p.setId(rs.getInt("ID"));
                p.setFecha(LocalDate.parse(rs.getString("Fecha")));
                pagos.add(p);
            }
            return pagos;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener pagos", e);        
        }
    }

    public ArrayList<Pago> obtenerPagosPorOrdenId(int idOrden){
        ArrayList<Pago> pagos = new ArrayList<>();
        String sql = "SELECT * FROM pago WHERE ID_OrdenDeTrabajo = ?";
        try (Connection conn = DataBaseManager.conectar();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, idOrden);
            try (ResultSet rs = ps.executeQuery()){
                OrdenDeTrabajo orden = ordenDeTrabajoDAO.obtenerOrdenDeTrabajoPorId(idOrden);
                while(rs.next()){
                    Pago p;
                    TipoPago t = TipoPago.valueOf(rs.getString("Tipo"));
                    if (t == TipoPago.TRANSFERENCIA){
                        p = new PagoTransferencia(rs.getDouble("Monto"), orden, rs.getString("NumeroComprobante"));
                    }   else if (t == TipoPago.TARJETA){
                            p = new PagoTarjeta(rs.getDouble("Monto"), orden, rs.getString("TipoTarjeta"),rs.getString("Ultimos4Digitos"));
                    }   else {
                            p = new PagoEfectivo(rs.getDouble("Monto"), orden);
                    }
                    p.setId(rs.getInt("ID"));
                    p.setFecha(LocalDate.parse(rs.getString("Fecha")));
                    pagos.add(p);
                }
                return pagos;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener pagos", e);
        }
    }


    
}
