package my.ebl.fire.exceptions;

public class EntityIdExpectedException extends BaseBusinessException {
    public EntityIdExpectedException(String entityName) {
        addMessageArg("entityName", entityName);
    }

    @Override
    public String getErrorCode() {
        return ErrorCodes.ENTITY_ID_EXPECTED_EXCEPTION.getCode();
    }

}
