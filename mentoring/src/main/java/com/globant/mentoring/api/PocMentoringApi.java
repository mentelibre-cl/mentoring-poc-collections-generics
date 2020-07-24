package com.globant.mentoring.api;

import com.globant.mentoring.model.entity.AccountEntity;
import com.globant.mentoring.model.entity.CodeAccountEntity;
import com.globant.mentoring.to.OutResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface PocMentoringApi {

    @GetMapping(value = "/account/")
    ResponseEntity<OutResponse> listAccounts();

    @GetMapping(value = "/account/{accountId}")
    ResponseEntity<OutResponse> accountById(@PathVariable String accountId);

    @PostMapping(value = "/account/")
    ResponseEntity<OutResponse> addAccount(@RequestBody AccountEntity accountEntity);

    @PatchMapping(value = "/account/{accountId}")
    ResponseEntity<OutResponse> editAccount(@PathVariable String accountId, @RequestBody AccountEntity accountEntity);

    @DeleteMapping(value = "/account/{accountId}")
    ResponseEntity<OutResponse> deleteAccount(@PathVariable String accountId);

    @GetMapping(value = "/codeaccount/all")
    ResponseEntity<OutResponse> listCodeAccounts();

    @GetMapping(value = "/codeaccount/{codeAccountId}")
    ResponseEntity<OutResponse> codeAccountById(@PathVariable String codeAccountId);

    @PostMapping(value = "/codeaccount/")
    ResponseEntity<OutResponse> addCodeAccount(@RequestBody CodeAccountEntity codeAccount);

    @PatchMapping(value = "/codeaccount/{codeAccountId}")
    ResponseEntity<OutResponse> editCodeAccount(@PathVariable String codeAccountId, @RequestBody CodeAccountEntity codeAccount);

    @DeleteMapping(value = "/codeaccount/{codeAccountId}")
    ResponseEntity<OutResponse> deleteCodeAccount(@PathVariable String codeAccountId);
}
