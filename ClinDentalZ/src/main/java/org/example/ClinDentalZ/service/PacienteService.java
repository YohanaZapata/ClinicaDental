package org.example.ClinDentalZ.service;


import org.example.ClinDentalZ.dao.PacienteDAOH2;
import org.example.ClinDentalZ.domain.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
    private PacienteDAOH2 pacienteDAOH2;
    @Autowired
    public PacienteService(PacienteDAOH2 pacienteDAOH2) {
        this.pacienteDAOH2 = pacienteDAOH2;
    }

    public List<Paciente> buscarTodosPacientes(){
        return pacienteDAOH2.buscarTodos();
    }

    public Paciente buscarXEmail(String email){
        return pacienteDAOH2.buscarXCriterioString(email);
    }

    public Paciente guardarPaciente(Paciente paciente){
        return pacienteDAOH2.guardar(paciente);
    }

    public Paciente actualizarPaciente(Paciente paciente) {
        return pacienteDAOH2.actualizar(paciente);
    }

    public Paciente buscarPaciente (int id){
        return pacienteDAOH2.buscar(id);
    }

    public void eliminarPaciente (int id) {
        pacienteDAOH2.eliminar(id);

    }

}
