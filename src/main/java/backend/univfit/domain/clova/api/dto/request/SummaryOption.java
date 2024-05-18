package backend.univfit.domain.clova.api.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SummaryOption {
    private Document document;
    private Option option;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Document {
        private String title;
        private String content;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Option {
        private String language;
        private String model;
        private int tone;
        private int summaryCount;
    }

    public static SummaryOption of(Document document, Option option) {
        return new SummaryOption(document, option);
    }
}