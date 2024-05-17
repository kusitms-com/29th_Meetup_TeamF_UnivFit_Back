package backend.univfit.domain.apply.application;

import backend.univfit.domain.apply.api.dto.response.ApplyListEntry;
import backend.univfit.domain.apply.api.dto.response.ApplyListResponse;
import backend.univfit.domain.apply.entity.ApplyEntity;
import backend.univfit.domain.apply.entity.enums.ApplyStatus;
import backend.univfit.domain.apply.repository.ApplyJpaRepository;
import backend.univfit.domain.member.entity.Member;
import backend.univfit.domain.member.repository.MemberJpaRepository;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplyListService {
    private final ApplyJpaRepository applyJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

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
}
