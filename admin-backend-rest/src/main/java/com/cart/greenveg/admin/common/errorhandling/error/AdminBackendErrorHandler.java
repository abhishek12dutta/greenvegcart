package com.cart.greenveg.admin.common.errorhandling.error;

import com.cart.greenveg.admin.common.AdminBackEndErrorCodes;
import com.cart.greenveg.admin.common.AdminBackEndException;
import com.cart.greenveg.admin.common.errorhandling.model.AdminBackendError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class AdminBackendErrorHandler extends ResponseEntityExceptionHandler {

    private static final List<String> badRequests = Arrays.asList(AdminBackEndErrorCodes.BAD_REQUEST.getCode());

    private static final List<String> notFoundRequests = Arrays.asList(AdminBackEndErrorCodes.INVALID_CATEGORY_ID.getCode(),
            AdminBackEndErrorCodes.INVALID_SUB_CATEGORY_ID.getCode());

    @ExceptionHandler(AdminBackEndException.class)
    public ResponseEntity<AdminBackendError> customHandleNotFound(Exception ex, WebRequest request) {
        AdminBackEndException adminBackEndException = (AdminBackEndException) ex;
        String errorCode = adminBackEndException.getErrorCode();
        AdminBackendError errors = new AdminBackendError();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setErrorCode(errorCode);
        errors.setAdditionalInfo(adminBackEndException.getAdditionalInfo());
        errors.setPath(((ServletWebRequest) request).getRequest().getRequestURI());

        if (badRequests.contains(errorCode)) {
            errors.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else if (notFoundRequests.contains(errorCode)) {
            errors.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
