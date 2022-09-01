package spring.cache.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx", nullable = false)
    private Long idx;

    @Column(name = "user_id", nullable = false)
    private String id;

    @Column(name = "user_name", nullable = false)
    private String name;

}
