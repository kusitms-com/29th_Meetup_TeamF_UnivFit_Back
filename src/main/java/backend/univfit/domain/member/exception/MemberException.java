package backend.univfit.domain.member.exception;

import backend.univfit.global.error.code.BaseErrorCode;
import backend.univfit.global.error.exception.GeneralException;

public class MemberException extends GeneralException {
    private BaseErrorCode baseErrorCode;
    public MemberException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
