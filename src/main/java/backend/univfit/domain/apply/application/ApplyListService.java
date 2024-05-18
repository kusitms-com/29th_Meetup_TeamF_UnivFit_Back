package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.api.dto.request.ChangeApplyStatusRequest;
import backend.univfit.domain.apply.api.dto.response.ApplyListDetailResponse;
import backend.univfit.domain.apply.api.dto.response.ApplyListEntry;
import backend.univfit.domain.apply.api.dto.response.ApplyListResponse;
import backend.univfit.domain.apply.api.dto.response.MyCoverLetterListEntry;
import backend.univfit.domain.apply.entity.AnnouncementEntity;
import backend.univfit.domain.apply.entity.ApplyEntity;
import backend.univfit.domain.apply.entity.enums.ApplyStatus;
import backend.univfit.domain.apply.exception.ApplyException;
import backend.univfit.domain.apply.repository.AnnouncementJpaRepository;
import backend.univfit.domain.apply.repository.ApplyJpaRepository;
import backend.univfit.domain.coverletter.entity.CoverLetterEntity;
import backend.univfit.domain.coverletter.repository.CoverLetterJpaRepository;
import backend.univfit.domain.member.entity.Member;
import backend.univfit.domain.member.repository.MemberJpaRepository;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import backend.univfit.global.dto.response.GeneralResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static backend.univfit.global.error.status.ErrorStatus.APPLY_MEMBER_NOT_MATCH;

@Service
@RequiredArgsConstructor
public class ApplyListService {
    private final ApplyJpaRepository applyJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final AnnouncementJpaRepository announcementJpaRepository;
    private final CoverLetterJpaRepository coverLetterJpaRepository;

    public ApplyListResponse getAllApplyList(MemberInfoObject mio) {
        Member member = memberJpaRepository.findById(mio.getMemberId()).get();

        List<ApplyEntity> applyEntityList = applyJpaRepository.findAllByMember(member);
        ArrayList<ApplyListEntry> applyList = new ArrayList<>();
        for(ApplyEntity ae : applyEntityList){
            String applyStatus = "미입력";
            if(ae.getApplyStatus() == ApplyStatus.PASS){
                applyStatus = "합격";
            }
            if(ae.getApplyStatus() == ApplyStatus.FAIL){
                applyStatus = "불합격";
            }

            ApplyListEntry applyListEntry = ApplyListEntry.of(
                    ae.getId(),
                    ae.getAnnouncementEntity().getEndDocumentDate(),
                    "더미 링크",
                    ae.getAnnouncementEntity().getScholarShipName(),
                    ae.getAnnouncementEntity().getScholarShipFoundation(),
                    ae.getAnnouncementEntity().getApplicationPeriod(),
                    applyStatus
            );

            applyList.add(applyListEntry);
        }

        return ApplyListResponse.of(applyList);
    }

    public ApplyListResponse getPassApplyList(MemberInfoObject mio) {
        Member member = memberJpaRepository.findById(mio.getMemberId()).get();

        List<ApplyEntity> applyEntityList = applyJpaRepository.findAllByMember(member);
        ArrayList<ApplyListEntry> applyList = new ArrayList<>();
        for(ApplyEntity ae : applyEntityList){
            String applyStatus = "";
            if(ae.getApplyStatus() == ApplyStatus.PASS){
                applyStatus = "합격";
            }
            else{
                continue;
            }

            ApplyListEntry applyListEntry = ApplyListEntry.of(
                    ae.getId(),
                    ae.getAnnouncementEntity().getEndDocumentDate(),
                    "더미 링크",
                    ae.getAnnouncementEntity().getScholarShipName(),
                    ae.getAnnouncementEntity().getScholarShipFoundation(),
                    ae.getAnnouncementEntity().getApplicationPeriod(),
                    applyStatus
            );

            applyList.add(applyListEntry);
        }

        return ApplyListResponse.of(applyList);
    }

    public ApplyListResponse getFailApplyList(MemberInfoObject mio) {
        Member member = memberJpaRepository.findById(mio.getMemberId()).get();

        List<ApplyEntity> applyEntityList = applyJpaRepository.findAllByMember(member);
        ArrayList<ApplyListEntry> applyList = new ArrayList<>();
        for(ApplyEntity ae : applyEntityList){
            String applyStatus = "";
            if(ae.getApplyStatus() == ApplyStatus.FAIL){
                applyStatus = "불합격";
            }
            else{
                continue;
            }

            ApplyListEntry applyListEntry = ApplyListEntry.of(
                    ae.getId(),
                    ae.getAnnouncementEntity().getEndDocumentDate(),
                    "더미 링크",
                    ae.getAnnouncementEntity().getScholarShipName(),
                    ae.getAnnouncementEntity().getScholarShipFoundation(),
                    ae.getAnnouncementEntity().getApplicationPeriod(),
                    applyStatus
            );

            applyList.add(applyListEntry);
        }

        return ApplyListResponse.of(applyList);
    }

    public ApplyListDetailResponse getApplyListDetail(MemberInfoObject mio, Long applyId) {
        Member member = memberJpaRepository.findById(mio.getMemberId()).get();

        ApplyEntity applyEntity = applyJpaRepository.findById(applyId).get();
        if(applyEntity.getMember() != member){
            throw new ApplyException(APPLY_MEMBER_NOT_MATCH);
        }

        String applyStatus = "미입력";
        if(applyEntity.getApplyStatus() == ApplyStatus.PASS){
            applyStatus = "합격";
        }
        if(applyEntity.getApplyStatus() == ApplyStatus.FAIL){
            applyStatus = "불합격";
        }

        //자소서 목록 불러오기
        List<CoverLetterEntity> coverLetterList = coverLetterJpaRepository.findAllByApplyEntity(applyEntity);

        ArrayList<MyCoverLetterListEntry> myCoverLetterList = new ArrayList<>();
        for(CoverLetterEntity coverLetterEntity : coverLetterList){
            Long id = coverLetterEntity.getId();
            String title = coverLetterEntity.getTitle();

            MyCoverLetterListEntry myCoverLetterListEntry = MyCoverLetterListEntry.of(id, title);
            myCoverLetterList.add(myCoverLetterListEntry);
        }

        return ApplyListDetailResponse.of(
                applyEntity.getId(),
                applyStatus,
                applyEntity.getAnnouncementEntity().getScholarShipImage(),
                applyEntity.getAnnouncementEntity().getScholarShipName(),
                applyEntity.getAnnouncementEntity().getScholarShipFoundation(),
                applyEntity.getAnnouncementEntity().getApplicationPeriod(),
                myCoverLetterList
        );

    }

    public GeneralResponse changeApplyStatus(MemberInfoObject mio, Long applyId, ChangeApplyStatusRequest cas) {
        Member member = memberJpaRepository.findById(mio.getMemberId()).get();

        ApplyEntity applyEntity = applyJpaRepository.findById(applyId).get();

        if(applyEntity.getMember() != member){
            throw new ApplyException(APPLY_MEMBER_NOT_MATCH);
        }
        int applyStatusIndex = cas.getApplyStatus();
        ApplyStatus[] applyStatusArray = ApplyStatus.values();
        ApplyStatus applyStatus = applyStatusArray[applyStatusIndex];

        applyEntity.setApplyStatus(applyStatus);

        applyJpaRepository.save(applyEntity);

        return GeneralResponse.of();
    }
}
