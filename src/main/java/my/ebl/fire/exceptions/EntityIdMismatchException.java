package my.ebl.fire.exceptions;

public class EntityIdMismatchException extends BaseBusinessException {
    public EntityIdMismatchException(String entityName, Long expectedId, Long sentId) {
        addMessageArg("entityName", entityName);
        addMessageArg("expectedId", expectedId);
        addMessageArg("sentId", sentId);
    }

    @Override
    public String getErrorCode() {
        return ErrorCodes.ENTITY_ID_MISMATCH_EXCEPTION.getCode();
    }
}
