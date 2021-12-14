package jpabook3.jpashop3.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

@Getter @Setter
@DiscriminatorColumn(name = "M")
@Entity
public class Movie extends Item {

    private String director;
    private String actor;
}
