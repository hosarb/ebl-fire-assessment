package my.ebl.fire.exceptions;

public class EntityIdNotExpectedException extends BaseBusinessException {
    public EntityIdNotExpectedException(String entityName) {
        addMessageArg("entityName", entityName);
    }

    @Override
    public String getErrorCode() {
        return ErrorCodes.ENTITY_ID_NOT_EXPECTED_EXCEPTION.getCode();
    }
}
