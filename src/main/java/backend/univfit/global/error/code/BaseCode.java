package backend.univfit.global.error.code;

import backend.univfit.global.error.dto.ReasonDto;

public interface BaseCode {
    public ReasonDto getReason();

    public ReasonDto getReasonHttpStatus();
}
