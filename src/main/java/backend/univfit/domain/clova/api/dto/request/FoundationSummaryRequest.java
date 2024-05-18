package backend.univfit.domain.clova.api.dto.request;

public record FoundationSummaryRequest(
        String title,
        String content
) {
    public static SummaryOption.Document toDocument(String title, String content) {
        return new SummaryOption.Document(title, content);
    }
}
