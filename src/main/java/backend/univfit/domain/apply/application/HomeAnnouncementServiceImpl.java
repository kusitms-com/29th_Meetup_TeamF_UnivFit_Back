package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.api.dto.response.*;
import backend.univfit.domain.apply.entity.AnnouncementEntity;
import backend.univfit.domain.apply.repository.AnnouncementJpaRepository;
import backend.univfit.domain.apply.repository.LikeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeAnnouncementServiceImpl implements HomeAnnouncementService {
    private final AnnouncementJpaRepository announcementJpaRepository;
    private final LikeJpaRepository likeJpaRepository;

    @Override
    public PopularAnnouncementListResponse getPopularAnnouncements() {
        List<PopularAnnouncementResponse> responses = likeJpaRepository.findTopPopularAnnouncements(PageRequest.of(0, 3))
                .stream()
                .map(objects -> {
                    AnnouncementEntity announcement = (AnnouncementEntity) objects[0];
                    Long likeCount = (Long) objects[1];
                    String like = likeCount + "명이 찜했어요!";
                    return new PopularAnnouncementResponse(
                            announcement.getId(),
                            announcement.getScholarShipName(),
                            announcement.getScholarShipFoundation(),
                            announcement.getApplicationPeriod(),
                            like
                    );
                })
                .toList();

        return new PopularAnnouncementListResponse(responses);
    }

    @Override
    public AnnouncementListBySearchResponse getAnnouncementsBySearch(String q) {
        List<AnnouncementBySearchResponse> announcementBySearchResponseList = announcementJpaRepository.findBySearchCriteria(q)
                .stream()
                .map(findAnnouncement -> AnnouncementBySearchResponse.of(
                        findAnnouncement.getId(), findAnnouncement.getScholarShipName(),
                        findAnnouncement.getScholarShipFoundation(), findAnnouncement.getApplicationPeriod()
                )).toList();

        return AnnouncementListBySearchResponse.of(announcementBySearchResponseList);
    }
}
