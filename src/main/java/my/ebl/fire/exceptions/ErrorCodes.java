package my.ebl.fire.exceptions;

public enum ErrorCodes {
    UNKNOWN_SYSTEM_EXCEPTION("000"),
    ENTITY_NOT_FOUND_BY_ID_EXCEPTION("001"),
    ENTITY_ID_NOT_EXPECTED_EXCEPTION("002"),
    ENTITY_ID_EXPECTED_EXCEPTION("003"),
    ENTITY_ID_MISMATCH_EXCEPTION("004"),
    DUPLICATED_PERSON_EXCEPTION("005"),

    VALIDATION_EXCEPTION("999"),
    ;

    private String code;

    ErrorCodes(String code) {
        this.code = code;
    }

    public java.lang.String getCode() {
        return code;
    }
}
