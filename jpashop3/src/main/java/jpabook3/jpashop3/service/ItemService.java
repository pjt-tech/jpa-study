package jpabook3.jpashop3.service;

import jpabook3.jpashop3.domain.item.Item;
import jpabook3.jpashop3.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void join(Item item) {
        itemRepository.save(item);
    }

    public Item findOne(Long id) {
       return itemRepository.findOne(id);
    }

    public List<Item> findItemList() {
        return itemRepository.findAll();
    }
    @Transactional
    public void updateItem(Long id) {
        Item findItem = itemRepository.findOne(id);
    }
}
