package my.ebl.fire.exceptions;

import org.springframework.http.HttpStatus;

public class EntityNotFoundByIdException extends BaseBusinessException {

    public EntityNotFoundByIdException(String entityName, Object requestedId) {
        addMessageArg("entityName", entityName);
        addMessageArg("requestedId", requestedId);
    }

    public HttpStatus getHttpError() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getErrorCode() {
        return ErrorCodes.ENTITY_NOT_FOUND_BY_ID_EXCEPTION.getCode();
    }
}
