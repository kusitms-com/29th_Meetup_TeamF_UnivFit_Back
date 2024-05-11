package backend.univfit.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoSocialLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kakaoSocialLogin_id")
    private Long id;

    private String kakaoNumber;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}