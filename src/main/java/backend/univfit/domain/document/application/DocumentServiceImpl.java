package backend.univfit.domain.document.application;

import backend.univfit.domain.document.api.dto.request.CreateDocumentRequest;
import backend.univfit.domain.document.api.dto.response.DocumentListResponse;
import backend.univfit.domain.document.api.dto.response.DocumentResponse;
import backend.univfit.domain.document.entity.DocumentEntity;
import backend.univfit.domain.document.exception.DocumentException;
import backend.univfit.domain.document.repository.DocumentJpaRepository;
import backend.univfit.domain.member.entity.Member;
import backend.univfit.domain.member.exception.MemberException;
import backend.univfit.domain.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static backend.univfit.global.error.status.ErrorStatus.DOCUMENT_NOT_FOUND;
import static backend.univfit.global.error.status.ErrorStatus.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentServiceImpl implements DocumentService {
    private final DocumentJpaRepository documentJpaRepository;
    private final MemberJpaRepository memberJpaRepository;


    @Override
    public void createDocuments(/**MemberInfoObject memberInfoObject,**/ CreateDocumentRequest createDocumentRequest) {
//        Long memberId = memberInfoObject.getMemberId();
        Long memberId = 1L;
        Member member = memberJpaRepository.findById(memberId).orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
        DocumentEntity document = CreateDocumentRequest.toEntity(
                createDocumentRequest.documentName(), createDocumentRequest.issuedDate(),
                createDocumentRequest.issuer(), createDocumentRequest.memo(), member
        );

        documentJpaRepository.save(document);
    }

    @Override
    public DocumentListResponse getAllDocuments() {
        Long memberId = 1L;
        Member member = memberJpaRepository.findById(memberId).orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

        List<DocumentResponse> documentResponseList = documentJpaRepository.findByMember(member)
                .stream()
                .map(d -> DocumentResponse.of(d.getId(), d.getDocumentName(), d.getIssuedDate(), d.getIssuer()))
                .toList();

        return DocumentListResponse.of(documentResponseList);
    }
}
