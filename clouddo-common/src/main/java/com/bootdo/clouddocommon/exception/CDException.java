package com.bootdo.clouddocommon.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author bootdo
 */
public class CDException extends AuthenticationException {
    public CDException(String message) {
        super(message);
    }
}
