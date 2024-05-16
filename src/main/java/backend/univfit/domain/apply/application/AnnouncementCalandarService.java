package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.api.dto.response.AnnouncementCalandarInfo;
import backend.univfit.domain.apply.api.dto.response.AnnouncementCalandarYearMonthDayResponse;
import backend.univfit.domain.apply.api.dto.response.AnnouncementCalandarYearMonthResponse;
import backend.univfit.domain.apply.entity.AnnouncementEntity;
import backend.univfit.domain.apply.entity.ApplyEntity;
import backend.univfit.domain.apply.repository.ApplyJpaRepository;
import backend.univfit.domain.member.entity.Member;
import backend.univfit.domain.member.repository.MemberJpaRepository;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementCalandarService {
    private final MemberJpaRepository memberJpaRepository;
    private final ApplyJpaRepository applyJpaRepository;
    public AnnouncementCalandarYearMonthResponse getDayList(MemberInfoObject mio, Integer year, Integer month) {
        Member member = memberJpaRepository.findById(mio.getMemberId()).get();

        List<ApplyEntity> applyEntityList = applyJpaRepository.findAllByMember(member);

        ArrayList<Integer> dayList = new ArrayList<>();
        ArrayList<Integer> lastDayList = new ArrayList<>();

        LocalDate now = LocalDate.now();

        for(ApplyEntity ae : applyEntityList){
            AnnouncementEntity announcementEntity = ae.getAnnouncementEntity();
            if(announcementEntity.getEndDocumentDate().getYear() == year && announcementEntity.getEndDocumentDate().getMonthValue() == month){
                if(now.isAfter(announcementEntity.getEndDocumentDate())){
                    //지원마감일 지났을 때
                    lastDayList.add(ae.getAnnouncementEntity().getEndDocumentDate().getDayOfMonth());
                }
                else{
                    dayList.add(ae.getAnnouncementEntity().getEndDocumentDate().getDayOfMonth());
                }
            }
        }

        return AnnouncementCalandarYearMonthResponse.of(lastDayList,dayList);
    }

    public AnnouncementCalandarYearMonthDayResponse getAnnouncement(MemberInfoObject mio, Integer year, Integer month, Integer day) {
        Member member = memberJpaRepository.findById(mio.getMemberId()).get();

        List<ApplyEntity> applyEntityList = applyJpaRepository.findAllByMember(member);

        ArrayList<AnnouncementCalandarInfo> aciArrayList = new ArrayList<>();

        for(ApplyEntity ae : applyEntityList){
            AnnouncementEntity announcementEntity = ae.getAnnouncementEntity();
            if(announcementEntity.getEndDocumentDate().getYear() == year && announcementEntity.getEndDocumentDate().getMonthValue() == month &&
                    announcementEntity.getEndDocumentDate().getDayOfMonth() == day){
                AnnouncementCalandarInfo aci = AnnouncementCalandarInfo.of(
                        announcementEntity.getId(),
                        announcementEntity.getEndDocumentDate(),
                        "더미 링크", //아직 엔티티에 반영안되어서 이렇게 함
                        announcementEntity.getScholarShipName(),
                        announcementEntity.getScholarShipFoundation(),
                        announcementEntity.getApplicationPeriod()
                );
                aciArrayList.add(aci);
            }
        }

        return AnnouncementCalandarYearMonthDayResponse.of(aciArrayList);

    }

    public AnnouncementCalandarYearMonthDayResponse getAnnouncementList(MemberInfoObject mio, Integer year, Integer month) {
        Member member = memberJpaRepository.findById(mio.getMemberId()).get();

        List<ApplyEntity> applyEntityList = applyJpaRepository.findAllByMember(member);

        ArrayList<AnnouncementCalandarInfo> aciArrayList = new ArrayList<>();

        for(ApplyEntity ae : applyEntityList){
            AnnouncementEntity announcementEntity = ae.getAnnouncementEntity();
            if(announcementEntity.getEndDocumentDate().getYear() == year && announcementEntity.getEndDocumentDate().getMonthValue() == month){
                AnnouncementCalandarInfo aci = AnnouncementCalandarInfo.of(
                        announcementEntity.getId(),
                        announcementEntity.getEndDocumentDate(),
                        "더미 링크", //아직 엔티티에 반영안되어서 이렇게 함
                        announcementEntity.getScholarShipName(),
                        announcementEntity.getScholarShipFoundation(),
                        announcementEntity.getApplicationPeriod()
                );
                aciArrayList.add(aci);
            }
        }

        return AnnouncementCalandarYearMonthDayResponse.of(aciArrayList);


    }
}
