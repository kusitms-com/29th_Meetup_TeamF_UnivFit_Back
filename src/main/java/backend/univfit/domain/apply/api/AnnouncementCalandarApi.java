package backend.univfit.domain.apply.api;

import backend.univfit.domain.apply.api.dto.response.AnnouncementCalandarInfo;
import backend.univfit.domain.apply.api.dto.response.AnnouncementCalandarYearMonthDayResponse;
import backend.univfit.domain.apply.api.dto.response.AnnouncementCalandarYearMonthResponse;
import backend.univfit.domain.apply.application.AnnouncementCalandarService;
import backend.univfit.global.ApiResponse;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import backend.univfit.global.argumentResolver.customAnnotation.MemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/announcements")
@RestController
public class AnnouncementCalandarApi {
    private final AnnouncementCalandarService acs;
    @GetMapping("/calandar/{year}/{month}")
    public ApiResponse<AnnouncementCalandarYearMonthResponse> getDayList(@MemberInfo MemberInfoObject mio,
                                                                         @PathVariable(name = "year") Integer year,
                                                                         @PathVariable(name = "month") Integer month){
        return ApiResponse.onSuccess(acs.getDayList(mio, year, month));
    }

    @GetMapping("/calandar/{year}/{month}/{day}")
    public ApiResponse<AnnouncementCalandarYearMonthDayResponse> getAnnouncement(@MemberInfo MemberInfoObject mio,
                                                                                 @PathVariable(name = "year") Integer year,
                                                                                 @PathVariable(name = "month") Integer month,
                                                                                 @PathVariable(name = "day") Integer day){
        return ApiResponse.onSuccess(acs.getAnnouncement(mio, year, month, day));
    }
}
