package org.example.ClinDentalZ.dao;

;
import org.example.ClinDentalZ.bd.BD;
import org.example.ClinDentalZ.domain.Odontologo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OdontologoDAOH2 implements IDao<Odontologo>{
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection=null;
        try{
            connection= BD.getConnection();
            PreparedStatement ps= connection.prepareStatement("INSERT INTO ODONTOLOGOS (" +
                    "APELLIDO, NOMBRE, MATRICULA) " +
                    "VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, odontologo.getApellido());
            ps.setString(2, odontologo.getNombre());
            ps.setString(3, odontologo.getMatricula());
            ps.execute();
            ResultSet rs= ps.getGeneratedKeys();
            while (rs.next()){
                odontologo.setId(rs.getInt(1));
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
            return odontologo;
        }
    }

    @Override
    public Odontologo buscar(int id) {
        Connection connection=null;
        Odontologo odontologo=null;
        try{
            connection= BD.getConnection();
            PreparedStatement ps=connection.prepareStatement("SELECT * FROM ODONTOLOGOS WHERE ID=?");
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                odontologo=new Odontologo(rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getString(4));
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
        return odontologo;
    }

    @Override
    public void eliminar(int id) {
        Connection connection=null;
        try{
            connection= BD.getConnection();
            PreparedStatement ps= connection.prepareStatement("DELETE FROM ODONTOLOGOS WHERE ID=?");
            ps.setInt(1,id);
            ps.execute();
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

    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        Connection connection=null;
        try{
            connection= BD.getConnection();
            PreparedStatement ps= connection.prepareStatement("UPDATE ODONTOLOGOS " +
                    "SET APELLIDO=?, NOMBRE=?, MATRICULA=? WHERE ID=?");
            ps.setString(1, odontologo.getApellido());
            ps.setString(2, odontologo.getNombre());
            ps.setString(3, odontologo.getMatricula());
            ps.setInt(4,odontologo.getId());
            ps.execute();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return odontologo;
        }
    }

    @Override
    public List<Odontologo> buscarTodos() {
        Connection connection=null;
        List<Odontologo> odontologos=new ArrayList<>();
        try{
            connection= BD.getConnection();
            PreparedStatement ps= connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet rs= ps.executeQuery();
            DomicilioDAOH2 domicilioDAOH2= new DomicilioDAOH2();
            while (rs.next()){
                odontologos.add(new Odontologo(rs.getInt(1),rs.getString(2),
                        rs.getString(3),rs.getString(4)));
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
        return odontologos;

    }

    @Override
    public Odontologo buscarXCriterioString(String criterio) {
        Connection connection=null;
        Odontologo odontologo=null;
        try{
            connection= BD.getConnection();
            PreparedStatement ps= connection.prepareStatement("SELECT * FROM ODONTOLOGOS WHERE MATRICULA=?");
            ps.setString(1,criterio);
            ResultSet rs= ps.executeQuery();
            DomicilioDAOH2 domicilioDAOH2= new DomicilioDAOH2();
            while (rs.next()){
                odontologo=new Odontologo(rs.getInt(1),rs.getString(2),
                        rs.getString(3),rs.getString(4));
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
        return odontologo;
    }


}
