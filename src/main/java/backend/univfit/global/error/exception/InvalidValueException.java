package backend.univfit.global.error.exception;


import backend.univfit.global.error.code.BaseErrorCode;

public class InvalidValueException extends GeneralException {
    private BaseErrorCode baseErrorCode;
    public InvalidValueException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}