package backend.univfit.domain.member.application;

import backend.univfit.domain.apply.repository.ApplyJpaRepository;
import backend.univfit.domain.member.entity.Member;
import backend.univfit.domain.member.repository.MemberJpaRepository;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import backend.univfit.global.dto.response.GeneralResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberJpaRepository memberJpaRepository;
    private final ApplyJpaRepository applyJpaRepository;

    @Transactional
    public GeneralResponse deleteMemberApplication(MemberInfoObject mio, Long id) {
        Member member = memberJpaRepository.findById(mio.getMemberId()).get();

        applyJpaRepository.deleteApplyEntityByMemberAndId(member, id);
        return GeneralResponse.of();
    }
}
