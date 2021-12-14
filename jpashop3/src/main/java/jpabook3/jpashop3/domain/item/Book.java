package jpabook3.jpashop3.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter @Setter
@DiscriminatorColumn(name = "B")
@Entity
public class Book extends Item {

    private String author;
    private String isbn;
}
