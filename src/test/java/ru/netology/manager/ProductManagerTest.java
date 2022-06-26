package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Smartphone;
import ru.netology.domain.Product;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Book book1 = new Book(1, "Книга1", 10, "Перумов");
    private Book book2 = new Book(2, "Книга1", 20, "Никитин");
    private Book book3 = new Book(3, "Книга2", 30, "Дивов");
    private Smartphone smartphone1 = new Smartphone(4, "Смартфон1", 100, "Xiaomi");
    private Smartphone smartphone2 = new Smartphone(5, "Смартфон2", 200, "Samsung");
    private Smartphone smartphone3 = new Smartphone(6, "Смартфон3", 300, "Nokia");

    @BeforeEach
    public void save() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);
    }

    @Test
    public void shouldSearchNameBook() {
        Product[] actual = manager.searchBy("Книга2");
        Product[] expected = new Product[]{book3};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAuthorBook() {
        Product[] actual = manager.searchBy("Перумов");
        Product[] expected = new Product[]{book1};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchManufacturerSmartphone() {
        Product[] actual = manager.searchBy("Samsung");
        Product[] expected = new Product[]{smartphone2};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchNameSmartphone() {
        Product[] actual = manager.searchBy("Смартфон3");
        Product[] expected = new Product[]{smartphone3};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchMultipleProducts() {
        Product[] actual = manager.searchBy("Книга1");
        Product[] expected = new Product[]{book1, book2};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchNotFoundProduct() {
        Product[] actual = manager.searchBy("Телевизор");
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);
    }
}