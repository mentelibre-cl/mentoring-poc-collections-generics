package com.globant.mentoring.api;

import com.globant.mentoring.model.entity.AccountEntity;
import com.globant.mentoring.model.entity.CodeAccountEntity;
import com.globant.mentoring.services.AccountServices;
import com.globant.mentoring.services.CodeAccountServices;
import com.globant.mentoring.to.OutResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class PocMentoringController implements PocMentoringApi {

    private AccountServices accountServices;
    private CodeAccountServices codeAccountServices;

    @Autowired
    public PocMentoringController(AccountServices accountServices, CodeAccountServices codeAccountServices) {
        this.accountServices = accountServices;
        this.codeAccountServices = codeAccountServices;
    }

    // list all accounts
    @Override
    public ResponseEntity<OutResponse> listAccounts() {
        return accountServices.listAccounts();
    }

    // list specific account
    @Override
    public ResponseEntity<OutResponse> accountById(String accountId) {
        return accountServices.listSpecificAccount(accountId);
    }

    // add new account
    @Override
    public ResponseEntity<OutResponse> addAccount(AccountEntity accountEntity) {
        return accountServices.newAccount(accountEntity);
    }

    // edit account
    @Override
    public ResponseEntity<OutResponse> editAccount(String accountId, AccountEntity accountEntity) {
        return accountServices.editAccount(accountId, accountEntity);
    }

    // delete account
    @Override
    public ResponseEntity<OutResponse> deleteAccount(String accountId) {
        return accountServices.deleteAccount(accountId);
    }

    // list all codeAccounts
    @Override
    public ResponseEntity<OutResponse> listCodeAccounts() {
        return codeAccountServices.listCodeAccounts();
    }

    // list specific codeAccount
    @Override
    public ResponseEntity<OutResponse> codeAccountById(String codeAccountId) {
        return codeAccountServices.listSpecificCodeAccount(codeAccountId);
    }

    // add new codeAccounts
    @Override
    public ResponseEntity<OutResponse> addCodeAccount(CodeAccountEntity codeAccountEntity) {
        return codeAccountServices.newCodeAccount(codeAccountEntity);
    }

    // edit codeAccounts
    @Override
    public ResponseEntity<OutResponse> editCodeAccount(String codeAccountId, CodeAccountEntity codeAccountEntity) {
        return codeAccountServices.editCodeAccount(codeAccountId, codeAccountEntity);
    }

    // delete codeAccounts
    @Override
    public ResponseEntity<OutResponse> deleteCodeAccount(String codeAccountId) {
        return codeAccountServices.deleteCodeAccount(codeAccountId);
    }
}
