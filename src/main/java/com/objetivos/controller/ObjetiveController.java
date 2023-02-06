package com.objetivos.controller;

import com.objetivos.entity.Objetive;
import com.objetivos.repository.ObjetiveRepository;
import com.objetivos.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ObjetiveController {

    @Autowired
    private ObjetiveRepository repository;

    @GetMapping("/objetives")
    public List<Objetive> findObjetives(){
        return repository.findByFinished(1);
    }

    @GetMapping("/finished")
    public List<Objetive> findFinished(){
        return repository.findByFinished(0);
    }

    @PostMapping("/new")
    public void newObjetive(@RequestBody Objetive objetive){
        repository.save(objetive);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Objetive> listObjetiveByID(@PathVariable Integer id){
        Optional<Objetive> objetive = repository.findById(id);

        if (!objetive.isPresent()){
            return null;
        }

        return new ResponseEntity(objetive, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Objetive> editar(@PathVariable Integer id, @RequestBody Objetive objetive){
        Optional<Objetive> objetive1 = repository.findById(id);

        System.out.println("111111 " + objetive);

        if(!objetive1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Objetive newObjetive = objetive1.get();

        newObjetive.setName(objetive.getName());
        newObjetive.setDescription(objetive.getDescription());
        newObjetive.setImage(objetive.getImage());
        newObjetive.setCurrentMoney(objetive.getCurrentMoney());
        newObjetive.setTotalMoney(objetive.getTotalMoney());
        newObjetive.setStartDate(objetive.getStartDate());
        newObjetive.setEndDate(objetive.getEndDate());
        newObjetive.setFinished(objetive.getFinished());

        repository.save(newObjetive);
        System.out.println("22222 " + newObjetive);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteObjetive(@PathVariable Integer id){
        repository.deleteById(id);
    }

}
