package com.bootdo.clouddoadmin.handler.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @author yuit
 * @date 2018/10/19 16:18
 *
 */
public class VerificationCodeFailureException extends AuthenticationException {

    public VerificationCodeFailureException(String msg, Throwable t) {
        super(msg, t);
    }

    public VerificationCodeFailureException(String msg) {
        super(msg);
    }
}
