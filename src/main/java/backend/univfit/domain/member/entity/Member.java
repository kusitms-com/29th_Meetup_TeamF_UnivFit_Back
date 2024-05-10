package backend.univfit.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String nickName;

    @OneToOne
    @JoinColumn(name = "memberPrivateInfo_id")
    private MemberPrivateInfo memberPrivateInfo;


}