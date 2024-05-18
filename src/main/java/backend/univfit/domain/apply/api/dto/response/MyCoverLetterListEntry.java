package backend.univfit.domain.apply.api.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MyCoverLetterListEntry {
    private Long id;
    private String title;

    public static MyCoverLetterListEntry of(
            Long id,
            String title
    ){
        return MyCoverLetterListEntry.builder()
                .id(id)
                .title(title)
                .build();
    }
}
