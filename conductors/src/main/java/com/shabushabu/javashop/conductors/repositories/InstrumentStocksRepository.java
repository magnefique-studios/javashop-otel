package com.shabushabu.javashop.conductors.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shabushabu.javashop.conductors.model.Stock;

public interface InstrumentStocksRepository extends CrudRepository<Stock, String> {

}
