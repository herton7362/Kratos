package com.kratos.exceptions;

import org.springframework.http.HttpStatus;

/**
 * 未授权 Exception
 *
 * @author tang he
 * @since 1.0.0
 */
public class UnauthorizedException extends BusinessException {
    public UnauthorizedException(String message) {
        super(message);
    }

    @Override
    public int getStatus() {
        return HttpStatus.UNAUTHORIZED.value();
    }
}
