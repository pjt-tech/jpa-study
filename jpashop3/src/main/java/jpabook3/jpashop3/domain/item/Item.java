package jpabook3.jpashop3.domain.item;

import jpabook3.jpashop3.domain.Category;
import jpabook3.jpashop3.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;
    private int stockQuantity;

    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//
    public void addStockQuantity(int stockQuantity) {
        this.stockQuantity += stockQuantity;
    }

    public void removeStockQuantity(int stockQuantity) {
        int count = this.stockQuantity - stockQuantity;
        if(count < 0) {
            throw new NotEnoughStockException("현재 재고를 초과했습니다.");
        }
        this.stockQuantity = count;
    }
}
