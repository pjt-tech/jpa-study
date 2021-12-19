package jpabook3.jpashop3.domain.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter @Setter
@DiscriminatorColumn(name = "A")
@Entity
public class Album extends Item {

    private String artist;
    private String etc;
}
