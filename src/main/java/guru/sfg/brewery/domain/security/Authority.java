package guru.sfg.brewery.domain.security;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Authority {

    @Id
    @GeneratedValue
    private Integer id;

    private String role;

    @ManyToMany (mappedBy = "authorities")
    private Set<User> users;
}
