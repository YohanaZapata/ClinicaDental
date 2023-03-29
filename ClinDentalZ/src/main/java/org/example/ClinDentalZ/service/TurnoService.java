package org.example.ClinDentalZ.service;


import org.example.ClinDentalZ.dao.IDao;
import org.example.ClinDentalZ.dao.TurnoDAOLista;
import org.example.ClinDentalZ.domain.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private TurnoDAOLista turnoIDao;
    @Autowired
    public TurnoService(TurnoDAOLista turnoIDao) {
        this.turnoIDao = turnoIDao;
    }

    public List<Turno> buscarTodosTurnos(){
        return turnoIDao.buscarTodos();
    }

    public Turno guardarTurno(Turno turno){
        return turnoIDao.guardar(turno);
    }

    public Turno actualizarTurno(Turno turno) {
        return turnoIDao.actualizar(turno);
    }

    public Turno buscarTurno (int id){
        return turnoIDao.buscar(id);
    }
    public Optional<Turno> buscarTurnoOptional (int id){
        return turnoIDao.buscarOptional(id);
    }

    public void eliminarTurno (int id) {
        turnoIDao.eliminar(id);

    }
}
