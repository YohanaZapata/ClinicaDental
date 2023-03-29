package org.example.ClinDentalZ.controller;


import org.example.ClinDentalZ.domain.Turno;
import org.example.ClinDentalZ.service.OdontologoService;
import org.example.ClinDentalZ.service.PacienteService;
import org.example.ClinDentalZ.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno){
        ResponseEntity<Turno> respuesta;
        if (pacienteService.buscarPaciente(turno.getPaciente().getId())!=null &&
                odontologoService.buscarOdontologo(turno.getOdontologo().getId())!=null){
            respuesta=ResponseEntity.ok(turnoService.guardarTurno(turno));
        }
        else{
            respuesta=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable int id){
        Optional<Turno> turnoBuscado= turnoService.buscarTurnoOptional(id);
        if (turnoBuscado.isPresent()){
            //Que el turno existe
            return ResponseEntity.ok(turnoBuscado.get());
        }
        else{
           //no existe el turno con dicho id
           return ResponseEntity.notFound().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTurnos(){
        return ResponseEntity.ok(turnoService.buscarTodosTurnos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable int id) {
        Optional<Turno> turnoBuscado = turnoService.buscarTurnoOptional(id);
        if (turnoBuscado.isPresent()) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Se eliminó el turno" +
                    " con id= " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se puede eliminar el turno" +
                    " con id= " + id +" pues no existe en la base de datos.");
        }
    }

    @PutMapping()
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno){

        //Podemos encontrar un problema con el id del turno
        //Podemos encontrar un problema con el id del odontologo y/o paciente

        Optional<Turno> turnoBuscado= turnoService.buscarTurnoOptional(turno.getId());
        if (turnoBuscado.isPresent()){
            //el turno existe, podemos verificar el resto
            //PacienteService pacienteService= new PacienteService();
                //ambos existen, puedo guardar el turno
                turnoService.actualizarTurno(turno);
                return ResponseEntity.ok("Se actualizó el turno con id= "+turno.getId());
            }
            else{
                return ResponseEntity.badRequest().body("No se puede actualizar el turno con" +
                        " id= "+turno.getId()+" ya que existe un error con el odontologo y/o " +
                        "paciente");
            }

    }


}
