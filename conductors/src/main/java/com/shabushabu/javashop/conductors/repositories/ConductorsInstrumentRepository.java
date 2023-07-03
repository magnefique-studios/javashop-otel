package com.shabushabu.javashop.conductors.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shabushabu.javashop.conductors.model.Instrument;

public interface ConductorsInstrumentRepository extends JpaRepository<Instrument, String>, FindConductorsRepository {
}
