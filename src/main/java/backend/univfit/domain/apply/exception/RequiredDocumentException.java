package backend.univfit.domain.apply.exception;

import backend.univfit.global.error.code.BaseErrorCode;
import backend.univfit.global.error.exception.GeneralException;

public class RequiredDocumentException extends GeneralException {
    private BaseErrorCode baseErrorCode;
    public RequiredDocumentException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
