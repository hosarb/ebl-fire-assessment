package my.ebl.fire.web.error;

import my.ebl.fire.exceptions.BaseBusinessException;
import my.ebl.fire.exceptions.ErrorCodes;
import my.ebl.fire.exceptions.UnknownSystemException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class BaseBusinessExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BaseBusinessException.class})
    protected ResponseEntity<ErrorDto> handleBaseBusinessException(BaseBusinessException ex) {
        ErrorDto dto = new ErrorDto(ex);
        return new ResponseEntity<>(dto, dto.getStatus());
    }

    @ExceptionHandler(value = {Throwable.class})
    protected ResponseEntity<ErrorDto> handleGlobalException(Exception ex) {
        UnknownSystemException usEx = new UnknownSystemException(ex);
        ErrorDto dto = new ErrorDto(usEx);
        return new ResponseEntity<>(dto, dto.getStatus());
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    protected ResponseEntity<Object> handleAccessDeniedException(Exception ex) {
        return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        HashMap<String, Object> meta = makeOtherExceptionsMeta(ex);
        ErrorDto dto = new ErrorDto(HttpStatus.BAD_REQUEST, ErrorCodes.VALIDATION_EXCEPTION.getCode(), meta);
        return new ResponseEntity<>(dto, dto.getStatus());
    }

    private HashMap<String, Object> makeOtherExceptionsMeta(Exception ex) {
        HashMap<String, Object> meta = new HashMap<>();
        meta.put("className", ex.getClass().getName());
        if (!ObjectUtils.isEmpty(ex.getMessage())) {
            meta.put("exception.message", ex.getMessage());
        }
        if (ex instanceof MissingRequestHeaderException) {
            meta.put("missing.header", ((MissingRequestHeaderException) ex).getHeaderName());
        }
        return meta;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> messages = new HashMap<>();
        ErrorDto dto = new ErrorDto(ex);
        return new ResponseEntity<>(dto, dto.getStatus());
    }

}
