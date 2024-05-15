package backend.univfit.domain.apply.api.dto.response;

public record ScholarShipFoundationResponse(
        String foundationInformation
) {
    public static ScholarShipFoundationResponse of(String foundationInformation) {
        return new ScholarShipFoundationResponse(foundationInformation);
    }
}
