package backend.univfit.domain.document.api.dto.request;


import backend.univfit.domain.document.entity.DocumentEntity;
import backend.univfit.domain.member.entity.Member;

import java.time.LocalDate;

public record UpdateDocumentRequest(
        //        @NotNull(message = "필수 항목을 입력해주세요.")
        String documentName,

        //        @NotNull(message = "필수 항목을 입력해주세요.")
        LocalDate issuedDate,

        String issuer,
        String memo
) {
    public static DocumentEntity toEntity(Long documentId,String documentName, LocalDate issuedDate, String issuer, String memo, Member
            member) {
        return DocumentEntity.of(documentId,
                documentName,
                issuedDate,
                issuer,
                memo,
                member);
    }
}
