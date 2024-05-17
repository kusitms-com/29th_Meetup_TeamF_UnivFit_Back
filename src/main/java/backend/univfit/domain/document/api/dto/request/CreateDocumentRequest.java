package backend.univfit.domain.document.api.dto.request;

import backend.univfit.domain.document.entity.DocumentEntity;
import backend.univfit.domain.member.entity.Member;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateDocumentRequest(
        @NotNull
        String documentName,
        LocalDate issuedDate,
        String issuer,
        String memo
) {
    public static DocumentEntity toEntity(String documentName, LocalDate issuedDate, String issuer, String memo, Member member) {
        return DocumentEntity.of(null,
                documentName,
                issuedDate,
                issuer,
                memo,
                member);
    }
}
