package backend.univfit.domain.apply.exception;

import backend.univfit.global.error.code.BaseErrorCode;
import backend.univfit.global.error.exception.GeneralException;

public class ScholarShipFoundationException extends GeneralException {
    private BaseErrorCode baseErrorCode;
    public ScholarShipFoundationException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
