package backend.univfit.domain.document.api.dto.response;

import java.time.LocalDate;

public record DocumentResponse(
        Long documentId,
        String documentName,
        LocalDate issuedDate,
        String issuer
) {
    public static DocumentResponse of(Long documentId, String documentName, LocalDate issuedDate, String issuer) {
        return new DocumentResponse(documentId, documentName, issuedDate, issuer);
    }
}
