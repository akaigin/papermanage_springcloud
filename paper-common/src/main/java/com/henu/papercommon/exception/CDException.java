package com.henu.papercommon.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author tiger
 */
public class CDException extends AuthenticationException {
    public CDException(String message) {
        super(message);
    }
}
