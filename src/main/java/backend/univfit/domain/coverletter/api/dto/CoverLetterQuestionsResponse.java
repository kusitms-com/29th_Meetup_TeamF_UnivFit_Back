package backend.univfit.domain.coverletter.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CoverLetterQuestionsResponse {
    private ArrayList<String> coverLetterQuestionList;

    public static CoverLetterQuestionsResponse of(
            ArrayList<String> coverLetterQuestionList
    ){
        return CoverLetterQuestionsResponse.builder()
                .coverLetterQuestionList(coverLetterQuestionList)
                .build();
    }
}
