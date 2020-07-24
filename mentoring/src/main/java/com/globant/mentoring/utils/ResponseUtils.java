package com.globant.mentoring.utils;

import com.globant.mentoring.to.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;

public class ResponseUtils {

    public static OutData<OutError> reponseGenericError(final String errorText) {
        OutData<OutError> outResponse = new OutData<OutError>();
        outResponse.setStatus(0);
        outResponse.setInfo(OutError.builder().error(errorText).build());
        return outResponse;
    }

    public static OutMeta responseGenericMeta(final String name) {
        return OutMeta.builder()
            .name(name)
            .date(new Date()).build();
    }

    public static OutLink responseGenericLink(final String numRows) {
        return OutLink.builder()
            .numrows(numRows)
            .uri(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString()).build();
    }

    public static ResponseEntity<OutResponse> responseGenericException(final String method, final String message) {
        OutData<OutError> responseData = new OutData<>();
        responseData.setStatus(0);
        responseData.setInfo(OutError.builder().error(message).build());
        return new ResponseEntity<>(
            OutResponse.builder()
                .data(responseData)
                .meta(ResponseUtils.responseGenericMeta(method))
                .link(ResponseUtils.responseGenericLink("0")).build(), HttpStatus.OK);
    }

    public static ResponseEntity<OutResponse> responseGenericCorrect(final String method, final OutData outData, final String numrows) {
        return new ResponseEntity<OutResponse>(
                OutResponse.builder()
                        .data(outData)
                        .meta(ResponseUtils.responseGenericMeta(method))
                        .link(ResponseUtils.responseGenericLink(numrows)).build(), HttpStatus.OK);
    }

    public static ResponseEntity<OutResponse> responseGenericExceptionError(final String method, final String message) {
        OutData<OutError> responseData = new OutData<>();
        responseData.setStatus(0);
        responseData.setInfo(OutError.builder().error(message).build());
        return new ResponseEntity<>(
                OutResponse.builder()
                    .data(responseData)
                    .meta(ResponseUtils.responseGenericMeta(method))
                    .link(ResponseUtils.responseGenericLink("0")).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
