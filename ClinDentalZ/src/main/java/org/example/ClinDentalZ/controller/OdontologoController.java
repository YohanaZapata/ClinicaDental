package org.example.ClinDentalZ.controller;


import org.example.ClinDentalZ.domain.Odontologo;
import org.example.ClinDentalZ.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/{id}")
    public Odontologo buscarOdontologo(@PathVariable int id){
        return odontologoService.buscarOdontologo(id);
    }

    @PutMapping
    public Odontologo actualizarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.actualizarOdontologo(odontologo);
    }

    @GetMapping ()
    public List<Odontologo> buscarTodos(){
        return  odontologoService.buscarOdontologos();
    }

    @DeleteMapping ("/{id}")
    public void  eliminarOdontologo(@PathVariable int id){
        odontologoService.eliminarOdontologo(id);
    }
    @PostMapping
    public ResponseEntity<Odontologo> agregarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.registrarOdontologo(odontologo));
    }
}

