package my.ebl.fire.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class UnknownSystemException extends BaseBusinessException {
    private static final Logger log = LoggerFactory.getLogger(UnknownSystemException.class);

    public UnknownSystemException(Exception e) {
        super(e);
        addMessageArg("exceptionClass", e.getClass().getName());
        addMessageArg("exceptionMessage", e.getMessage());
        StackTraceElement[] stackTrace = e.getStackTrace();
        int couner = 0;
        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < stackTrace.length; i++) {
            if (couner > 3) {
                break;
            }
            StackTraceElement element = stackTrace[i];
            if (element.getClassName().startsWith("my.ebl.fire.")) {
                s.append(couner).append(">")
                        .append(element.getClassName())
                        .append(".").append(element.getMethodName())
                        .append(":").append(element.getLineNumber())
                        .append(";\n");
                couner++;
            }
        }
        addMessageArg("stacktrace", s.toString());
        log.error(e.getMessage(), e);

    }

    @Override
    public HttpStatus getHttpError() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String getErrorCode() {
        return ErrorCodes.UNKNOWN_SYSTEM_EXCEPTION.getCode();
    }
}
