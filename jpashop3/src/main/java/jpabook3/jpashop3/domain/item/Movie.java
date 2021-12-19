package jpabook3.jpashop3.domain.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter @Setter
@DiscriminatorColumn(name = "M")
@Entity
public class Movie extends Item {

    private String director;
    private String actor;
}
