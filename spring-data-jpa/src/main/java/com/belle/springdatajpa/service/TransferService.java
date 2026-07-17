package com.belle.springdatajpa.service;

import com.belle.springdatajpa.dto.TransferDTO;
import com.belle.springdatajpa.model.BankAccount;
import com.belle.springdatajpa.repository.BankAccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TransferService implements Command<TransferDTO, String> {

    private final BankAccountRepository bankAccountRepository;

    public TransferService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public ResponseEntity<String> execute(TransferDTO transferDTO) {
        Optional<BankAccount> fromAccount = bankAccountRepository.findById(transferDTO.getFromUser());
        Optional<BankAccount> toAccount = bankAccountRepository.findById(transferDTO.getToUser());

        if (fromAccount.isEmpty() || toAccount.isEmpty()) throw new RuntimeException("User not found.");

        BankAccount sender = fromAccount.get();
        BankAccount receiver = toAccount.get();

        deduct(sender, transferDTO.getAmount());
        add(receiver, transferDTO.getAmount());

        //never called Repository.save()

        return ResponseEntity.ok("Success.");
    }

    private void add(BankAccount bankAccount, double amount) {
        bankAccount.setBalance(bankAccount.getBalance() + amount);
    }

    private void deduct(BankAccount bankAccount, double amount) {
        if (bankAccount.getBalance() < amount) {
            throw new RuntimeException("User '" + bankAccount.getName() + "' does not have enough money for this transaction.");
        }
        bankAccount.setBalance(bankAccount.getBalance() - amount);
    }

}
