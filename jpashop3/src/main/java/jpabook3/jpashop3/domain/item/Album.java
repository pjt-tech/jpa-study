package jpabook3.jpashop3.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

@Getter @Setter
@DiscriminatorColumn(name = "A")
@Entity
public class Album extends Item {

    private String artist;
    private String etc;
}
