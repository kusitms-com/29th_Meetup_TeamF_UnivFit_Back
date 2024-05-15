package backend.univfit.domain.apply.exception;

import backend.univfit.global.error.code.BaseErrorCode;
import backend.univfit.global.error.exception.GeneralException;

public class LikeException extends GeneralException {
    private BaseErrorCode baseErrorCode;
    public LikeException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
