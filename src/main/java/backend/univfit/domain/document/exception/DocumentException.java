package backend.univfit.domain.document.exception;

import backend.univfit.global.error.code.BaseErrorCode;
import backend.univfit.global.error.exception.GeneralException;

public class DocumentException extends GeneralException {
    private BaseErrorCode baseErrorCode;
    public DocumentException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
