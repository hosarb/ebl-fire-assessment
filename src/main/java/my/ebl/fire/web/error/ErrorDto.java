package my.ebl.fire.web.error;

import my.ebl.fire.exceptions.BaseBusinessException;
import my.ebl.fire.exceptions.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorDto implements Serializable {
    private HttpStatus status;
    private String errorCode;
    private Map<String, Object> meta;


    public ErrorDto() {
    }

    public ErrorDto(HttpStatus status, String errorCode, Map<String, Object> meta) {
        this.status = status;
        this.errorCode = errorCode;
        this.meta = meta;
    }

    public ErrorDto(BaseBusinessException ex) {
        this(ex.getHttpError(), ex.getErrorCode(), ex.getMessageArgs());
    }

    public ErrorDto(MethodArgumentNotValidException ex) {
        this.status = HttpStatus.BAD_REQUEST;
        this.errorCode = ErrorCodes.VALIDATION_EXCEPTION.getCode();
        this.meta = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            String key = fieldError.getObjectName() + "." + fieldError.getField() + "." + fieldError.getCode();
            ValidationErrorDto dto = new ValidationErrorDto();
            dto.setDefaultMessage(fieldError.getDefaultMessage());
            dto.setObjectName(fieldError.getObjectName());
            dto.setField(fieldError.getField());
            dto.setRejectedValue(fieldError.getRejectedValue());
            dto.setCode(fieldError.getCode());
            meta.put(key, dto);
        }
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }
}
