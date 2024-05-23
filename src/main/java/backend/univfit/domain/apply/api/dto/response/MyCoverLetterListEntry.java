package backend.univfit.domain.apply.api.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MyCoverLetterListEntry {
    private Long coverLetterId;
    private String title;
    private LocalDateTime localDateTime;

    public static MyCoverLetterListEntry of(
            Long coverLetterId,
            String title,
            LocalDateTime localDateTime
    ) {
        return MyCoverLetterListEntry.builder()
                .coverLetterId(coverLetterId)
                .title(title)
                .localDateTime(localDateTime)
                .build();
    }
}
