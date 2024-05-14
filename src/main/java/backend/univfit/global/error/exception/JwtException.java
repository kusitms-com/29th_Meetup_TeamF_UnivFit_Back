package backend.univfit.global.error.exception;

import backend.univfit.global.error.code.BaseErrorCode;

public class JwtException extends GeneralException{
    public JwtException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
