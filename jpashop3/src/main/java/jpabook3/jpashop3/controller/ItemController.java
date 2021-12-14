package jpabook3.jpashop3.controller;

import jpabook3.jpashop3.domain.item.Book;
import jpabook3.jpashop3.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createItemForm(Model model) {

        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String createItem(BookForm bookForm) {
        Book book = new Book();
        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setStockQuantity(bookForm.getStockQuantity());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());

        itemService.join(book);

        return "redirect:/items";
    }

    @GetMapping("/items")
    public String getItemList(Model model) {

        model.addAttribute("items",itemService.findItemList());
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {

        Book findItem = (Book)itemService.findOne(itemId);

        BookForm form = new BookForm();
        form.setName(findItem.getName());
        form.setPrice(findItem.getPrice());
        form.setAuthor(findItem.getAuthor());
        form.setStockQuantity(findItem.getStockQuantity());
        form.setIsbn(findItem.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId")Long itemId, @ModelAttribute("form") BookForm form) {
        itemService.updateItem(itemId, form);

        return "redirect:/items";
    }
}
