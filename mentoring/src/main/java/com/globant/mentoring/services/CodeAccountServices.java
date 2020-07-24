package com.globant.mentoring.services;

import com.globant.mentoring.exceptions.MentoringException;
import com.globant.mentoring.model.entity.CodeAccountEntity;
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
public class CodeAccountServices {

    private final CodeAccountRepository codeAccountRepository;
    private static final Logger logger = LogManager.getLogger(CodeAccountServices.class);

    @Autowired
    public CodeAccountServices(CodeAccountRepository codeAccountRepository) {
        this.codeAccountRepository = codeAccountRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<OutResponse> listCodeAccounts() {
        logger.info("**** start list code accounts ****");
        try {
            List<CodeAccountEntity> listCodeAccounts = new ArrayList<CodeAccountEntity>();
            codeAccountRepository.findAll().forEach(listCodeAccounts::add);

            if (listCodeAccounts.isEmpty()) {
                logger.info("**** finish list code accounts - without data ****");
                throw new MentoringException("No Code Accounts Found");
            }

            OutData<List<CodeAccountEntity>> responseData = new OutData<List<CodeAccountEntity>>();
            responseData.setStatus(1);
            responseData.setInfo(listCodeAccounts);
            logger.info("**** finish list code accounts - with data ****");

            return ResponseUtils.responseGenericCorrect("listCodeAccounts", responseData, String.valueOf(listCodeAccounts.size()));
        } catch (MentoringException me) {
            return ResponseUtils.responseGenericException("listCodeAccounts", me.getMessage());
        } catch (Exception e) {
            return ResponseUtils.responseGenericExceptionError("listCodeAccounts", e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<OutResponse> listSpecificCodeAccount(final String codeAccountIdParam) {
        logger.info("**** start list specific code account ****");
        try {
            Optional<CodeAccountEntity> codeAccountOptional = codeAccountRepository.findById(MentoringUtils.stringToLong(codeAccountIdParam));

            if (codeAccountOptional.get() == null) {
                logger.info("**** finish list specific code account - without data ****");
                throw new MentoringException("No Code Accounts Found");
            }

            OutData<CodeAccountEntity> responseData = new OutData<>();
            responseData.setStatus(1);
            responseData.setInfo(codeAccountOptional.get());
            logger.info("**** finish list specific code account - with data ****");

            return ResponseUtils.responseGenericCorrect("listSpecificCodeAccount", responseData, "0");
        } catch (MentoringException me) {
            return ResponseUtils.responseGenericException("listSpecificCodeAccount", me.getMessage());
        } catch (Exception e) {
            return ResponseUtils.responseGenericExceptionError("listSpecificCodeAccount", e.getMessage());
        }
    }

    public ResponseEntity<OutResponse> newCodeAccount(final CodeAccountEntity accountParam) {
        logger.info("**** start new code account ****");
        try {
            CodeAccountEntity codeAccount = codeAccountRepository.save(accountParam);
            OutData<CodeAccountEntity> responseData = new OutData<CodeAccountEntity>();
            responseData.setStatus(1);
            responseData.setInfo(codeAccount);
            logger.info("**** finish new code account ****");

            return ResponseUtils.responseGenericCorrect("newCodeAccount", responseData, "0");
        } catch (Exception e) {
            return ResponseUtils.responseGenericExceptionError("newCodeAccount", e.getMessage());
        }
    }

    public ResponseEntity<OutResponse> editCodeAccount(final String codeAccountId, final CodeAccountEntity codeAccountParam) {
        logger.info("**** start edit code account ****");
        try {
            Optional<CodeAccountEntity> codeAccountOptional = codeAccountRepository.findById(MentoringUtils.stringToLong(codeAccountId));

            if (!codeAccountOptional.isPresent())
                return ResponseEntity.notFound().build();

            codeAccountParam.setId(codeAccountOptional.get().getId());

            CodeAccountEntity codeAccount = codeAccountRepository.save(codeAccountParam);
            OutData<CodeAccountEntity> responseData = new OutData<CodeAccountEntity>();
            responseData.setStatus(1);
            responseData.setInfo(codeAccount);
            logger.info("**** finish edit code account ****");

            return ResponseUtils.responseGenericCorrect("editCodeAccount", responseData, "0");
        } catch (Exception e) {
            return ResponseUtils.responseGenericExceptionError("editCodeAccount", e.getMessage());
        }
    }

    public ResponseEntity<OutResponse> deleteCodeAccount(final String accountIdParam) {
        logger.info("**** start delete code account ****");
        try {
            codeAccountRepository.delete(codeAccountRepository.findById(MentoringUtils.stringToLong(accountIdParam)).get());

            OutData<String> responseData = new OutData<>();
            responseData.setStatus(1);
            responseData.setInfo("delete code account " + accountIdParam);
            logger.info("**** finish delete code account ****");

            return ResponseUtils.responseGenericCorrect("deleteCodeAccount", responseData, "0");
        } catch (Exception e) {
            return ResponseUtils.responseGenericExceptionError("deleteCodeAccount", e.getMessage());
        }
    }
}