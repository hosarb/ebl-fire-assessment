package my.ebl.fire.exceptions;

import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseBusinessException extends RuntimeException {
    private static final Logger log = LoggerFactory.getLogger(BaseBusinessException.class);
    protected Map<String, Object> messageArgs;

    protected BaseBusinessException() {
        log.error(this.getMessage(), this);
    }

    protected BaseBusinessException(Throwable cause) {
        super(cause);
        log.error(this.getMessage(), this);
    }

    public BaseBusinessException(String message) {
        super(message);
        log.error(this.getMessage(), this);
    }

    public BaseBusinessException(String message, Throwable cause) {
        super(message, cause);
        log.error(this.getMessage(), this);
    }

    public BaseBusinessException(String message, Throwable cause,
                                 boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        log.error(this.getMessage(), this);
    }


    public void addMessageArg(String argKey, Object argValue) {
        if (MapUtils.isEmpty(this.messageArgs)) {
            this.messageArgs = new LinkedHashMap<>();
        }
        this.messageArgs.put(argKey, argValue);
    }

    public Object[] getMessageArgsArray() {
        if (MapUtils.isNotEmpty(messageArgs)) {
            return messageArgs.values().toArray();
        }
        return null;
    }

    public List<Object> getMessageArgsList() {
        if (MapUtils.isNotEmpty(messageArgs)) {
            return new ArrayList(messageArgs.values());
        }
        return null;
    }

    public Map<String, Object> getMessageArgs() {
        return messageArgs;
    }


    public HttpStatus getHttpError() {
        return HttpStatus.BAD_REQUEST;
    }

    public abstract String getErrorCode();
}
