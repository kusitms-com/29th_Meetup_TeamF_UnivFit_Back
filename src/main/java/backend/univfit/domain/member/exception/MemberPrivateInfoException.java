package backend.univfit.domain.member.exception;

import backend.univfit.global.error.code.BaseErrorCode;
import backend.univfit.global.error.exception.GeneralException;

public class MemberPrivateInfoException extends GeneralException {
    private BaseErrorCode baseErrorCode;
    public MemberPrivateInfoException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }

}
