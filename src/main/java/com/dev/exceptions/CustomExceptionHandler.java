package com.dev.exceptions;

import com.dev.dto.responsedto.ErrorResponse;
import com.dev.exceptions.constraintsviolationhandler.DBConstraintsExceptionFactory;
import com.dev.exceptions.constraintsviolationhandler.DataIntegrityViolationInterface;
import com.google.gson.Gson;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;


@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ExceptionUtils exceptionUtils;

    final DataIntegrityViolationInterface dataIntegrityViolation;
    final DBConstraintsExceptionFactory dbConstraintsExceptionFactory;

    public CustomExceptionHandler(DBConstraintsExceptionFactory dbConstraintsExceptionFactory) {
        this.dbConstraintsExceptionFactory = dbConstraintsExceptionFactory;
        this.dataIntegrityViolation = dbConstraintsExceptionFactory.create();
    }


    @ExceptionHandler(value = RuntimeException.class)
    protected ResponseEntity<ErrorResponse> handleError(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(new GenericException(ex.getMessage(),
                ex.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = StringIndexOutOfBoundsException.class)
    protected ResponseEntity<ErrorResponse> handleError(StringIndexOutOfBoundsException ex, WebRequest request) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(new GenericException(ex.getMessage(),
                ex.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ResourceNotFoundExceptionHandler.class)
    protected ResponseEntity<ErrorResponse> handleException(ResourceNotFoundExceptionHandler ex, WebRequest request) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidParamValueException.class)
    protected ResponseEntity<ErrorResponse> handleError(InvalidParamValueException ex, WebRequest request) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse((BaseException) ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = GenericException.class)
    protected ResponseEntity<ErrorResponse> handleError(GenericException ex, WebRequest request) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse((BaseException) ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse> handleError(ConstraintViolationException ex, WebRequest request) {
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse(new DBOperationExceptionHandler(ex.getMessage())),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = TransactionSystemException.class)
    protected ResponseEntity<ErrorResponse> handleError(TransactionSystemException ex, WebRequest request) {
        Throwable throwable = ex.getCause();
        while (true) {
            if (throwable.getCause() == null) {
                break;
            }
            throwable = throwable.getCause();
        }
        String errorMessage = new Gson().toJson(exceptionUtils.dataRangeExceedExceptionMessageProvider(throwable));
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(new DBOperationExceptionHandler(throwable.getMessage(),
                errorMessage)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = DataAccessException.class)
    protected ResponseEntity<ErrorResponse> handleError(DataAccessException ex, WebRequest request) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(new DBOperationExceptionHandler(ex.getMessage(),
                "Database operation failed")), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NullPointerException.class)
    protected ResponseEntity<ErrorResponse> handleError(NullPointerException ex, WebRequest request) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(new GenericException(ex.getMessage(),
                "NullPointerException Occurred!!!")), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse> handleError(SQLIntegrityConstraintViolationException ex, WebRequest request) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(new DBOperationExceptionHandler(ex.getMessage(),
                ex.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = javax.validation.ConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse> handleError(javax.validation.ConstraintViolationException ex, WebRequest request) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(new DBOperationExceptionHandler(ex.getMessage(),
                exceptionUtils.javaxValidationMessageParser(ex))), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    protected ResponseEntity<ErrorResponse> handleError(DataIntegrityViolationException ex, WebRequest request) {
        Throwable t = ex.getCause();
        while (true) {
            if (t.getCause() == null) {
                break;
            }
            t = t.getCause();
        }
        String message = (String) dataIntegrityViolation.parseError(ex).get("message");
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(
                new UniqueConstraintViolateExceptionHandler(
                        StringUtils.hasText(message) ? message : "Database constraint failed!!!",
                        t.getMessage())),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadRequestExceptionHandler.class)
    protected ResponseEntity<ErrorResponse> handleError(BadRequestExceptionHandler ex, WebRequest request) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex), HttpStatus.BAD_REQUEST);
    }

    //handle request-body parameter validation
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        StringBuffer errors = new StringBuffer();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            errors.append(error.getDefaultMessage() + "\n");
        }
        return new ResponseEntity(new ErrorResponse(new InvalidParamValueException(errors.toString())),
                HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatus status, WebRequest request) {
        return new ResponseEntity(new ErrorResponse("Missing Parameter: " + ex.getParameterName()),
                HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        return new ResponseEntity(new ErrorResponse(new BadRequestExceptionHandler(ex.getMessage(), ex.getMessage())),
                HttpStatus.BAD_REQUEST);
    }
}
