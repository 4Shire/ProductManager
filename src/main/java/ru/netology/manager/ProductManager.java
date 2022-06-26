package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.Data;

import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;


@AllArgsConstructor
@Data
public class ProductManager {
    private ProductRepository items;

    public ProductManager() {
    }

    public void add(Product item) {
        items.save(item);
    }

    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        for (Product item : items.getAll()) {
            if (matches(item, text)) {
                Product[] tmp = new Product[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = item;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Product item, String search) {
        if (item instanceof Book) { // если в параметре product лежит объект класса Book
            Book book = (Book) item; // положем его в переменную типа Book чтобы пользоваться методами класса Book
            if (book.getAuthor().contains(search)) { // проверим есть ли поисковое слово в данных об авторе
                return true;
            }
            if (book.getName().contains(search)) {
                return true;
            }
            return false;
        }
        if (item instanceof Smartphone) {
            Smartphone smartphone = (Smartphone) item;
            if (smartphone.getManufacturer().contains(search)) {
                return true;
            }
            if (smartphone.getName().contains(search)) {
                return true;
            }
            return false;
        }
        return false;
    }
}