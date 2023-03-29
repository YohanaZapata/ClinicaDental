package org.example.ClinDentalZ.dao;



import org.apache.log4j.Logger;
import org.example.ClinDentalZ.bd.BD;
import org.example.ClinDentalZ.domain.Domicilio;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DomicilioDAOH2 implements IDao<Domicilio> {

    public static final Logger logger=Logger.getLogger(DomicilioDAOH2.class);
    @Override
    public Domicilio guardar(Domicilio domicilio) {
        Connection connection=null;
        try{
            connection= BD.getConnection();
            PreparedStatement ps= connection.prepareStatement("INSERT INTO DOMICILIOS (" +
                    "CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, domicilio.getCalle());
            ps.setString(2, domicilio.getNumero());
            ps.setString(3, domicilio.getLocalidad());
            ps.setString(4, domicilio.getProvincia());
            ps.execute();
            ResultSet clave= ps.getGeneratedKeys();
            while (clave.next()){
                domicilio.setId(clave.getInt(1));
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return domicilio;
    }

    @Override
    public Domicilio buscar(int id) {
        Connection connection=null;
        Domicilio domicilio=null;
        try{
            connection=BD.getConnection();
            PreparedStatement ps= connection.prepareStatement("SELECT * FROM DOMICILIOS WHERE ID=?");
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                domicilio=new Domicilio(rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }
        return domicilio;
    }

    @Override
    public void eliminar(int id) {
        Connection connection=null;
        try{
            connection=BD.getConnection();
            PreparedStatement ps= connection.prepareStatement("DELETE FROM DOMICILIOS WHERE ID=?");
            ps.setInt(1,id);
            ps.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }

    @Override
    public Domicilio actualizar(Domicilio domicilio) {
        Connection connection=null;
        try{
            connection=BD.getConnection();
            PreparedStatement ps= connection.prepareStatement("UPDATE DOMICILIOS SET CALLE=?, NUMERO=?, LOCALIDAD=?, PROVINCIA=? WHERE ID=?");
            ps.setString(1, domicilio.getCalle());
            ps.setString(2, domicilio.getNumero());
            ps.setString(3, domicilio.getLocalidad());
            ps.setString(4, domicilio.getProvincia());
            ps.setInt(5,domicilio.getId());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }
        return domicilio;
    }

    @Override
    public List<Domicilio> buscarTodos() {
        Connection connection=null;
        List<Domicilio> domicilios=new ArrayList<>();
        try{
            connection=BD.getConnection();
            PreparedStatement ps= connection.prepareStatement("SELECT * FROM DOMICILIOS");
            ResultSet rs= ps.executeQuery();
            while (rs.next()){

                domicilios.add(new Domicilio(rs.getInt(1),rs.getString(2),rs.getString(3),
                                rs.getString(4),rs.getString(5)));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }
        return domicilios;
    }

    @Override
    public Domicilio buscarXCriterioString(String criterio) {
        Connection connection=null;
        Domicilio domicilio=null;
        try{
            connection= BD.getConnection();
            PreparedStatement ps= connection.prepareStatement("SELECT * FROM DOMICILIOS WHERE PROVINCIA=?");
            ps.setString(1,criterio);
            ResultSet rs= ps.executeQuery();
            DomicilioDAOH2 domicilioDAOH2= new DomicilioDAOH2();
            while (rs.next()){
                domicilio=new Domicilio(rs.getInt(1),rs.getString(2),
                        rs.getString(3),rs.getString(4),
                        rs.getNString(5));
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }
        return domicilio;
    }

}
