package backend.univfit.domain.coverletter.service;

import backend.univfit.domain.apply.entity.AnnouncementEntity;
import backend.univfit.domain.apply.entity.ApplyEntity;
import backend.univfit.domain.apply.repository.ApplyJpaRepository;
import backend.univfit.domain.coverletter.api.dto.CoverLetterQuestionsResponse;
import backend.univfit.domain.member.entity.Member;
import backend.univfit.domain.member.repository.MemberJpaRepository;
import backend.univfit.global.argumentResolver.MemberInfoObject;
import backend.univfit.global.error.exception.CoverLetterException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

import static backend.univfit.global.error.status.ErrorStatus.COVER_LETTER_MEMBER_NOT_MATCH;

@Service
@RequiredArgsConstructor
public class CoverLetterService {
    private final MemberJpaRepository memberJpaRepository;
    private final ApplyJpaRepository applyJpaRepository;


    public CoverLetterQuestionsResponse showCoverLetterQuestions(Long applyId, MemberInfoObject mio) {
        ApplyEntity applyEntity = applyJpaRepository.findById(applyId).get();
        Member member = memberJpaRepository.findById(mio.getMemberId()).get();
        //만약 다른 멤버가 저장한 공고에 접근하려할 때 에러처리
        if(applyEntity.getMember() != member){
            throw new CoverLetterException(COVER_LETTER_MEMBER_NOT_MATCH);
        }

        //질문 가져오기
        AnnouncementEntity announcementEntity = applyEntity.getAnnouncementEntity();
        String[] questions = announcementEntity.getCoverLetterQuestion().split("\n");

        //응답객체 생성

        ArrayList<String> coverLetterQuestionsArrayList = new ArrayList<>(Arrays.asList(questions));

        return CoverLetterQuestionsResponse.of(coverLetterQuestionsArrayList);

    }
}
