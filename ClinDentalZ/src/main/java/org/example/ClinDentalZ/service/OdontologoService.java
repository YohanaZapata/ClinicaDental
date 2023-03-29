package org.example.ClinDentalZ.service;



import org.example.ClinDentalZ.dao.OdontologoDAOH2;
import org.example.ClinDentalZ.domain.Odontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {


    //public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(OdontologoService.class);
    @Autowired
    private OdontologoDAOH2 odontologoDAOH2;

    public OdontologoService(OdontologoDAOH2 odontologoDAOH2) {
        this.odontologoDAOH2 = odontologoDAOH2;
    }

    public Odontologo buscarOdontologo(int id){
        return odontologoDAOH2.buscar(id);
    }

    public Odontologo actualizarOdontologo(Odontologo odontologo) {
       // LOGGER.info("Se comenzo a actualizar al odontologo con ID: " + odontologo.getId());
        return odontologoDAOH2.actualizar(odontologo);
    }
    public List<Odontologo> buscarOdontologos(){
        return odontologoDAOH2.buscarTodos();
    }
    public void eliminarOdontologo (int id) {
        odontologoDAOH2.eliminar(id);

    }
    public Odontologo registrarOdontologo(Odontologo odontologo){
        return odontologoDAOH2.guardar(odontologo);
    }


}
