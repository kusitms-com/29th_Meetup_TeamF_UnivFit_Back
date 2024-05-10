package backend.univfit.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NaverSocialLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "naverSocialLogin_id")
    private Long id;

    private String naverNumber;

    @OneToOne
    @Column(name = "member_id")
    private Member member;
}
