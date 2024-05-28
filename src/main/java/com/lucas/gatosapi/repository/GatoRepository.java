package com.lucas.gatosapi.repository;

import com.lucas.gatosapi.models.GatoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GatoRepository extends JpaRepository<GatoModel, Long> {
}
