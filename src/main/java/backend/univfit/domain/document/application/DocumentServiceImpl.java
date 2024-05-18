package backend.univfit.domain.document.application;

import backend.univfit.domain.document.api.dto.request.CreateDocumentRequest;
import backend.univfit.domain.document.api.dto.request.UpdateDocumentRequest;
import backend.univfit.domain.document.api.dto.response.DocumentDetailResponse;
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

import static backend.univfit.global.error.status.ErrorStatus.*;

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
        if (createDocumentRequest.documentName() == null || createDocumentRequest.documentName().isEmpty()
                || createDocumentRequest.issuedDate() == null) {
            throw new DocumentException(DOCUMENT_INVALID_BODY);
        }
        DocumentEntity document = CreateDocumentRequest.toEntity(
                createDocumentRequest.documentName(), createDocumentRequest.issuedDate(),
                createDocumentRequest.issuer(), createDocumentRequest.memo(), member
        );
        documentJpaRepository.save(document);
    }

    @Override
    public DocumentListResponse getAllDocuments(/**MemberInfoObject memberInfoObject**/) {
        //        Long memberId = memberInfoObject.getMemberId();
        Long memberId = 1L;
        Member member = memberJpaRepository.findById(memberId).orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

        List<DocumentResponse> documentResponseList = documentJpaRepository.findByMember(member)
                .stream()
                .map(d -> DocumentResponse.of(d.getId(), d.getDocumentName(), d.getIssuedDate(), d.getIssuer(),d.getMemo()))
                .toList();

        return DocumentListResponse.of(documentResponseList);
    }

    @Override
    public void deleteDocument(/**MemberInfoObject memberInfoObject**/Long documentId) {
        //        Long memberId = memberInfoObject.getMemberId();
        Long memberId = 1L;
        Member member = memberJpaRepository.findById(memberId).orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
        DocumentEntity document = documentJpaRepository.findByIdAndMember(documentId, member)
                .orElseThrow(() -> new DocumentException(DOCUMENT_NOT_FOUND));

        documentJpaRepository.delete(document);
    }

    @Override
    public DocumentResponse updateDocument(UpdateDocumentRequest updateDocumentRequest,/**MemberInfoObject memberInfoObject**/Long documentId) {
        //        Long memberId = memberInfoObject.getMemberId();
        Long memberId = 1L;
        Member member = memberJpaRepository.findById(memberId).orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

        if (updateDocumentRequest.documentName().isBlank() || updateDocumentRequest.documentName().isEmpty() || updateDocumentRequest.issuedDate() == null) {
            throw new DocumentException(DOCUMENT_INVALID_BODY);
        }

        documentJpaRepository.findByIdAndMember(documentId, member)
                .orElseThrow(() -> new DocumentException(DOCUMENT_NOT_FOUND));

        DocumentEntity updateDocument = documentJpaRepository.save(UpdateDocumentRequest.toEntity(documentId, updateDocumentRequest.documentName(), updateDocumentRequest.issuedDate(),
                updateDocumentRequest.issuer(), updateDocumentRequest.memo(), member));

        return DocumentResponse.of(updateDocument.getId(), updateDocumentRequest.documentName(), updateDocument.getIssuedDate(),
                updateDocument.getIssuer(), updateDocument.getMemo());
    }

    @Override
    public DocumentDetailResponse getDocument(/**MemberInfoObject memberInfoObject**/Long documentId) {
        //        Long memberId = memberInfoObject.getMemberId();
        Long memberId = 1L;
        Member member = memberJpaRepository.findById(memberId).orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

        DocumentEntity document = documentJpaRepository.findByIdAndMember(documentId, member)
                .orElseThrow(() -> new DocumentException(DOCUMENT_NOT_FOUND));

        return DocumentDetailResponse.of(document.getId(), document.getDocumentName(), document.getIssuedDate(),
                document.getIssuer(), document.getMemo());
    }
}
