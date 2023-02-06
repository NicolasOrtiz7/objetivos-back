package com.objetivos.repository;

import com.objetivos.entity.Objetive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObjetiveRepository extends JpaRepository<Objetive, Integer> {

    // Listar los que estan finalizados
    List<Objetive> findByFinished(int finished);

}
