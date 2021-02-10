package my.ebl.fire.exceptions;

public class DuplicatedPersonException extends BaseBusinessException {
    @Override
    public String getErrorCode() {
        return ErrorCodes.DUPLICATED_PERSON_EXCEPTION.getCode();
    }
}
