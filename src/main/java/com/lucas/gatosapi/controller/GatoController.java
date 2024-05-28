package com.lucas.gatosapi.controller;

import com.lucas.gatosapi.models.GatoModel;
import com.lucas.gatosapi.repository.GatoRepository;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = "1.Operações Crud dos Gatos")
public class GatoController {
    @Autowired
    GatoRepository gatoRepository;

    @PostMapping("/gatos")
    public ResponseEntity<GatoModel> adicionarGato(@RequestBody @Valid GatoModel gato){
        return ResponseEntity.status(HttpStatus.OK).body(gatoRepository.save(gato));
    }

    @GetMapping("/gatos")
    public ResponseEntity<List<GatoModel>> listarGatos(){
        return ResponseEntity.status(HttpStatus.OK).body(gatoRepository.findAll());
    }

    @GetMapping("gatos/{id}")
    public ResponseEntity<Object> buscarUmGatoPorMatricula(@PathVariable(value = "id") Long id){
        Optional<GatoModel> gato = gatoRepository.findById(id);
        if(gato.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gatinho não encontrado papai");
        }
        return ResponseEntity.status(HttpStatus.OK).body(gato.get());
    }

    @PutMapping("/gatos/{id}")
    public ResponseEntity<Object> atualizarGato(@PathVariable(value = "id") Long id,
                                                @RequestBody @Valid GatoModel gato) {
        Optional<GatoModel> gatoAlterar = gatoRepository.findById(id);
        if (gatoAlterar.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gatinho não encontrado máquina");
        }
        var gatoModel = gatoAlterar.get();
        BeanUtils.copyProperties(gato, gatoModel, "id"); // Copiando propriedades do objeto recebido, exceto o ID
        return ResponseEntity.status(HttpStatus.OK).body(gatoRepository.save(gatoModel));
    }

    @DeleteMapping("/gatos/{id}")
    public ResponseEntity<Object> deletarGato(@PathVariable(value = "id") Long id){
        Optional<GatoModel> gato = gatoRepository.findById(id);
        if(gato.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gatinho não encontrado maninho");
        }
        gatoRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Gato com id "+ id+ " excluido com sucesso");
    }
}

