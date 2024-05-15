package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.api.dto.response.RequiredDocumentResponse;
import backend.univfit.domain.apply.entity.AnnouncementEntity;
import backend.univfit.domain.apply.entity.RequiredDocumentEntity;
import backend.univfit.domain.apply.exception.AnnouncementException;
import backend.univfit.domain.apply.exception.RequiredDocumentException;
import backend.univfit.domain.apply.repository.AnnouncementJpaRepository;
import backend.univfit.domain.apply.repository.RequiredDocumentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static backend.univfit.global.error.status.ErrorStatus.ANNOUNCEMENT_NOT_FOUND;
import static backend.univfit.global.error.status.ErrorStatus.REQUIRED_DOCUMENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class RequiredDocumentServiceImpl implements RequiredDocumentService {
    private final RequiredDocumentJpaRepository requiredDocumentJpaRepository;
    private final AnnouncementJpaRepository announcementJpaRepository;

    @Override
    public List<RequiredDocumentResponse> getRequiredDocumentList(Long announcementId) {
        AnnouncementEntity ae = announcementJpaRepository.findById(announcementId)
                .orElseThrow(() -> new AnnouncementException(ANNOUNCEMENT_NOT_FOUND));

        return requiredDocumentJpaRepository.findByAnnouncementEntity(ae).stream()
                .map(r -> RequiredDocumentResponse.of(r.getId(),r.getDocumentName(), String.valueOf(r.getRequiredOptions())))
                .toList();
    }

    @Override
    public RequiredDocumentResponse getRequiredDocument(Long announcementId, Long requiredDocumentId) {
        AnnouncementEntity ae = announcementJpaRepository.findById(announcementId)
                .orElseThrow(() -> new AnnouncementException(ANNOUNCEMENT_NOT_FOUND));

        return requiredDocumentJpaRepository.findByAnnouncementEntity(ae)
                .stream()
                .filter(rd -> rd.getId() == requiredDocumentId)
                .map(rd -> RequiredDocumentResponse.of(rd.getId(), rd.getDocumentName(), String.valueOf(rd.getRequiredOptions())))
                .toList().get(0);
    }
}
