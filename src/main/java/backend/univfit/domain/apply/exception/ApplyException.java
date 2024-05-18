package backend.univfit.domain.apply.exception;

import backend.univfit.global.error.code.BaseErrorCode;
import backend.univfit.global.error.exception.GeneralException;

public class ApplyException extends GeneralException {
    public ApplyException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
