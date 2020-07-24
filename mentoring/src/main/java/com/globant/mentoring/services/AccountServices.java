package com.globant.mentoring.services;

import com.globant.mentoring.exceptions.MentoringException;
import com.globant.mentoring.model.entity.AccountEntity;
import com.globant.mentoring.model.entity.CodeAccountEntity;
import com.globant.mentoring.model.repository.AccountRepository;
import com.globant.mentoring.model.repository.CodeAccountRepository;
import com.globant.mentoring.to.OutData;
import com.globant.mentoring.to.OutResponse;
import com.globant.mentoring.utils.MentoringUtils;
import com.globant.mentoring.utils.ResponseUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServices {

    private final AccountRepository accountRepository;
    private final CodeAccountRepository codeAccountRepository;
    private static final Logger logger = LogManager.getLogger(AccountServices.class);

    @Autowired
    public AccountServices(AccountRepository accountRepository, CodeAccountRepository codeAccountRepository) {
        this.accountRepository = accountRepository;
        this.codeAccountRepository = codeAccountRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<OutResponse> listAccounts() {
        logger.info("**** start list accounts ****");
        try {
            List<AccountEntity> listAccounts = new ArrayList<AccountEntity>();
            accountRepository.findAll().forEach(listAccounts::add);

            if (listAccounts.isEmpty()) {
                logger.info("**** finish list accounts - without data ****");
                throw new MentoringException("No Accounts Found");
            }

            OutData<List<AccountEntity>> responseData = new OutData<List<AccountEntity>>();
            responseData.setStatus(1);
            responseData.setInfo(listAccounts);
            logger.info("**** finish list accounts - with data ****");

            return ResponseUtils.responseGenericCorrect("listAccounts", responseData, String.valueOf(listAccounts.size()));
        } catch (MentoringException me) {
            return ResponseUtils.responseGenericException("listAccounts", me.getMessage());
        } catch (Exception e) {
            return ResponseUtils.responseGenericExceptionError("listAccounts", e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<OutResponse> listSpecificAccount(final String accountIdParam) {
        logger.info("**** start list specific account ****");
        try {
            Optional<AccountEntity> accountOptional = accountRepository.findById(MentoringUtils.stringToLong(accountIdParam));

            if (accountOptional.get() == null) {
                logger.info("**** finish list specific account - without data ****");
                throw new MentoringException("No Accounts Found");
            }

            OutData<AccountEntity> responseData = new OutData<>();
            responseData.setStatus(1);
            responseData.setInfo(accountOptional.get());
            logger.info("**** finish list specific account - with data ****");

            return ResponseUtils.responseGenericCorrect("listSpecificAccount", responseData, "0");
        } catch (MentoringException me) {
            return ResponseUtils.responseGenericException("listSpecificAccount", me.getMessage());
        } catch (Exception e) {
            return ResponseUtils.responseGenericExceptionError("listSpecificAccount", e.getMessage());
        }
    }

    public ResponseEntity<OutResponse> newAccount(final AccountEntity accountParam) {
        logger.info("**** start new account ****");
        try {
            accountParam.setCodeAccount(codeAccountRepository.findById(accountParam.getCodeAccount().getId()));
            AccountEntity account = accountRepository.save(accountParam);
            OutData<AccountEntity> responseData = new OutData<AccountEntity>();
            responseData.setStatus(1);
            responseData.setInfo(account);
            logger.info("**** finish new account ****");

            return ResponseUtils.responseGenericCorrect("newAccount", responseData, "0");
        } catch (Exception e) {
            return ResponseUtils.responseGenericExceptionError("newAccount", e.getMessage());
        }
    }

    public ResponseEntity<OutResponse> editAccount(final String accountId, final AccountEntity accountParam) {
        logger.info("**** start edit account ****");
        try {
            Optional<AccountEntity> accountOptional = accountRepository.findById(MentoringUtils.stringToLong(accountId));

            if (!accountOptional.isPresent())
                throw new MentoringException("not find account");

            accountParam.setId(accountOptional.get().getId());

            AccountEntity account = accountRepository.save(accountParam);
            OutData<AccountEntity> responseData = new OutData<AccountEntity>();
            responseData.setStatus(1);
            responseData.setInfo(account);
            logger.info("**** finish edit account ****");

            return ResponseUtils.responseGenericCorrect("editAccount", responseData, "0");
        } catch (Exception e) {
            return ResponseUtils.responseGenericExceptionError("editAccount", e.getMessage());
        }
    }

    public ResponseEntity<OutResponse> deleteAccount(final String accountIdParam) {
        logger.info("**** start delete account ****");
        try {
            accountRepository.delete(accountRepository.findById(MentoringUtils.stringToLong(accountIdParam)).get());

            OutData<String> responseData = new OutData<>();
            responseData.setStatus(1);
            responseData.setInfo("delete code account " + accountIdParam);
            logger.info("**** finish delete account ****");

            return ResponseUtils.responseGenericCorrect("deleteAccount", responseData, "0");
        } catch (Exception e) {
            return ResponseUtils.responseGenericExceptionError("deleteAccount", e.getMessage());
        }
    }
}