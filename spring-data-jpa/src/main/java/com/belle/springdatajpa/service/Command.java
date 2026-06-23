package com.belle.springdatajpa.service;

import com.belle.springdatajpa.dto.TransferDTO;
import org.springframework.http.ResponseEntity;

public interface Command<T, T1> {
    ResponseEntity<String> execute(TransferDTO transferDTO);
}
