package jpabook2.jpashop2.domain.Item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Movie extends Item {
    private String director;
    private String actor;
}