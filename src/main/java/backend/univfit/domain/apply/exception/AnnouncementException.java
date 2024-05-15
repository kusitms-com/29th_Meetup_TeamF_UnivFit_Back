package backend.univfit.domain.apply.exception;

import backend.univfit.global.error.code.BaseErrorCode;
import backend.univfit.global.error.exception.GeneralException;

public class AnnouncementException extends GeneralException {
    private BaseErrorCode baseErrorCode;
    public AnnouncementException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
