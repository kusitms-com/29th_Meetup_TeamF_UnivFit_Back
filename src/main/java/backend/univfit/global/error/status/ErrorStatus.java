package backend.univfit.global.error.status;

import backend.univfit.global.error.code.BaseErrorCode;
import backend.univfit.global.error.dto.ErrorReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    //기본(전역) 에러
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"COMMON_500", "서버에서 요청을 처리 하는 동안 오류가 발생했습니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON_400", "입력 값이 잘못된 요청 입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON_401", "인증이 필요 합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON_403", "금지된 요청 입니다."),

    //User 관련 에러
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_001", "사용자를 찾을 수 없습니다."),

    //Body 에러
    INVALID_BODY(HttpStatus.BAD_REQUEST, "BODY_ERROR", "Body가 올바르지 않습니다."),

    //JWT 관련 에러
    JWT_EXPIRED(HttpStatus.UNAUTHORIZED, "JWT_001", "JWT인증시간이 만료되었습니다."),
    JWT_UNSUPPORTED_TOKEN_TYPE(HttpStatus.UNAUTHORIZED, "JWT_002", "지원하는 JWT타입이 아닙니다."),
    JWT_INVALIED(HttpStatus.UNAUTHORIZED, "JWT_003", "유효한 JWT가 아닙니다."),

    //온보딩 에러
    ONBOARD_DUPLICATED_NICKNAME(HttpStatus.BAD_REQUEST, "ONBOARD_001", "중복된 닉네임 입니다.");




    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDto getReason() {
        return ErrorReasonDto.builder()
                .isSuccess(false)
                .code(code)
                .message(message)
                .build();
    }

    @Override
    public ErrorReasonDto getReasonHttpStatus() {
        return ErrorReasonDto.builder()
                .isSuccess(false)
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .build();
    }


}
