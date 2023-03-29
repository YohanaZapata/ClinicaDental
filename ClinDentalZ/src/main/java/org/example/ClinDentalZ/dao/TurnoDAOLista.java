package org.example.ClinDentalZ.dao;

import org.example.ClinDentalZ.domain.Turno;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class TurnoDAOLista implements IDao<Turno> {
    private List<Turno> turnos=new ArrayList<>();

    private PacienteDAOH2 pacienteDAOH2;
    private OdontologoDAOH2 odontologoDAOH2;

    public TurnoDAOLista(List<Turno> turnos, PacienteDAOH2 pacienteDAOH2, OdontologoDAOH2 odontologoDAOH2) {
        this.turnos = turnos;
        this.pacienteDAOH2 = pacienteDAOH2;
        this.odontologoDAOH2 = odontologoDAOH2;
    }

    @Override
    public Turno guardar(Turno turno) {
        turno.setId(turnos.size()+1);
        turno.setPaciente(pacienteDAOH2.buscar(turno.getPaciente().getId()));
        turno.setOdontologo(odontologoDAOH2.buscar(turno.getOdontologo().getId()));
        turnos.add(turno);
        return turno;
    }

    @Override
    public Turno buscar(int id) {
       // Turno turnoBuscado=null;
        for(Turno turnoEnRevision:turnos){
            if(turnoEnRevision.getId()==id){
                return turnoEnRevision;
            }
        }
        return null;
    }


    public Optional<Turno> buscarOptional(int id) {
        // Turno turnoBuscado=null;
        for(Turno turnoEnRevision:turnos){
            if(turnoEnRevision.getId()==id){
                return Optional.of(turnoEnRevision);
            }
        }
        return Optional.empty();
    }


    @Override
    public void eliminar(int id) {
        Turno buscado=buscar(id);
        if(buscado!=null){
            turnos.remove(buscado);
        }
        else {
            System.out.println("Se a eliminado el turno " + turnos);
        }

    }

    @Override
    public Turno actualizar(Turno turno) {
       // int indice= turnos.indexOf(turno);
       // turnos.set(indice, turno);
     //   return buscar(turno.getId());

        //alternativa B
        eliminar(turno.getId());
        return guardar(turno);
    }

    @Override
    public List<Turno> buscarTodos() {
        return turnos;
    }

    @Override
    public Turno buscarXCriterioString(String criterio) {
        return null;
    }
}
