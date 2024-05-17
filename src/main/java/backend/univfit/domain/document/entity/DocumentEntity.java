package backend.univfit.domain.document.entity;

import backend.univfit.domain.member.entity.Member;
import backend.univfit.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "document_table")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentName;

    private LocalDate issuedDate;
    private String issuer;
    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static DocumentEntity of(Long id, String documentName, LocalDate issuedDate, String issuer, String memo,
                                    Member member) {
        return new DocumentEntity(id, documentName, issuedDate, issuer, memo, member);
    }

}
