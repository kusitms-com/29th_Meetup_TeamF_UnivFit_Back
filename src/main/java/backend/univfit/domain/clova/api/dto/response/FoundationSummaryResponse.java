package backend.univfit.domain.clova.api.dto.response;

public record FoundationSummaryResponse(
        String summary
) {
    public static FoundationSummaryResponse of(String summary) {

        return new FoundationSummaryResponse(summary);
    }
}
