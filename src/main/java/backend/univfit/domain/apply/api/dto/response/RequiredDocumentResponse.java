package backend.univfit.domain.apply.api.dto.response;

import java.util.List;

public record RequiredDocumentResponse(
        Long id,
        String documentName,
        String requiredOptions,
        Boolean memberIsHave

) {
    public static RequiredDocumentResponse of(Long id, String document, String requiredOptions,Boolean memberIsHave) {
        return new RequiredDocumentResponse(id, document, requiredOptions, memberIsHave);
    }
}
