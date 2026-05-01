package com.taller.database;

import com.taller.model.Pago;
import com.taller.model.PagoEfectivo;
import com.taller.model.PagoTarjeta;
import com.taller.model.PagoTransferencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class PagoDAO {

    private OrdenDeTrabajoDAO ordenDeTrabajoDAO;

    public PagoDAO(OrdenDeTrabajoDAO ordenDeTrabajoDAO){
        this.ordenDeTrabajoDAO = ordenDeTrabajoDAO;
    }

    public void insertarPago(Pago pago){
        String sql = "INSERT INTO pago(ID_OrdenDeTrabajo,Monto,Fecha,Tipo,NumeroComprobante,TipoTarjeta,Ultimos4Digitos) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement ps = DataBaseManager.conectar().prepareStatement(sql)){
            ps.setInt(1, pago.getOrdenDeTrabajo().getId());
            ps.setDouble(2, pago.getMonto());
            ps.setString(3, pago.getFecha().toString());
            ps.setString(4, pago.getTipo());
            if (pago instanceof PagoTransferencia){
                ps.setString(5, ((PagoTransferencia) pago).getNumeroComprobante());
                ps.setNull(6, java.sql.Types.VARCHAR);
                ps.setNull(7, java.sql.Types.VARCHAR);
            } else if (pago instanceof PagoTarjeta){
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
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Pago> obtenerPagos(){
        ArrayList<Pago> pagos = new ArrayList<>();
        String sql = "SELECT * FROM pago";
        try (ResultSet rs = DataBaseManager.conectar().createStatement().executeQuery(sql)){
            while(rs.next()){
                String t = rs.getString("Tipo");
                Pago p;
                if (t.equals("Transferencia")){
                    p = new PagoTransferencia(rs.getDouble("Monto"), ordenDeTrabajoDAO.obtenerOrdenDeTrabajoPorId(rs.getInt("ID_OrdenDeTrabajo")), rs.getString("NumeroComprobante"));
                }   else if (t.equals("Tarjeta")){
                        p = new PagoTarjeta(rs.getDouble("Monto"), ordenDeTrabajoDAO.obtenerOrdenDeTrabajoPorId(rs.getInt("ID_OrdenDeTrabajo")), rs.getString("TipoTarjeta"),rs.getString("Ultimos4Digitos"));
                }   else {
                        p = new PagoEfectivo(rs.getDouble("Monto"), ordenDeTrabajoDAO.obtenerOrdenDeTrabajoPorId(rs.getInt("ID_OrdenDeTrabajo")));
                        p.setTipo(rs.getString("Tipo"));
                }
                p.setId(rs.getInt("ID"));
                p.setFecha(LocalDate.parse(rs.getString("Fecha")));
                pagos.add(p);
            }
            return pagos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;            
        }
    }


    
}
