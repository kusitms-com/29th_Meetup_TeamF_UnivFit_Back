package backend.univfit.global.error.exception;

import backend.univfit.global.error.code.BaseErrorCode;

public class OnboardException extends GeneralException{

    public OnboardException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
