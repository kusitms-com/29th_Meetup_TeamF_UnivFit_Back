package backend.univfit.domain.apply.api.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MyCoverLetterListEntry {
    private Long coverLetterId;
    private String title;

    public static MyCoverLetterListEntry of(
            Long coverLetterId,
            String title
    ){
        return MyCoverLetterListEntry.builder()
                .coverLetterId(coverLetterId)
                .title(title)
                .build();
    }
}
