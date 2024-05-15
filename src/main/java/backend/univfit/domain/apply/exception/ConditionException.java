package backend.univfit.domain.apply.exception;

import backend.univfit.global.error.code.BaseErrorCode;
import backend.univfit.global.error.exception.GeneralException;

public class ConditionException extends GeneralException {
    private BaseErrorCode baseErrorCode;
    public ConditionException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
