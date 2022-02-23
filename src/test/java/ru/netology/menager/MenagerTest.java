package ru.netology.menager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.Manager;
import ru.netology.repository.Repository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MenagerTest {
//    @Mock

    private Repository repository1 = new Repository();
    //    @InjectMocks
    private Manager menager = new Manager();
    private Book firstB = new Book(1, "firstBook", 1, "Author1");
    private Book secondB = new Book(2, "secondBook", 1, "Author2");
    private Book thirdB = new Book(3, "thirdBook", 1, "Author3");

    private Smartphone firstS = new Smartphone(4, "firstSmart", 1, "manufacturer1");
    private Smartphone secondS = new Smartphone(5, "secondSmart", 1, "manufacturer2");
    private Smartphone thirdS = new Smartphone(6, "thirdSmart", 1, "manufacturer3");

    @BeforeEach
    public void before() {
        for (Book book : Arrays.asList(firstB, secondB, thirdB)) menager.save(book);
        menager.save(firstS);
        menager.save(secondS);
        menager.save(thirdS);
    }

    @ParameterizedTest
    @CsvSource({"firstBook", "Author1,"})
    void matchesBoookrr(String a) {
        Product[] actual = menager.searchBy(a);
        Book[] expented = new Book[]{firstB};
        assertArrayEquals(expented, actual);
    }


    @Test
    public void matchesBoook() {
        Product[] actual = menager.searchBy("firstBook");
        Product[] expented = new Product[]{firstB};
        assertArrayEquals(expented, actual);
    }

    @Test
    public void matchesBook2() {
        Product[] actual = menager.searchBy("Author3");
        Product[] expented = new Product[]{thirdB};
        assertArrayEquals(expented, actual);
    }

    @Test
    public void matchesBook3() {
        Product[] actual = menager.searchBy("Author5");
        Product[] expented = new Product[]{};
        assertArrayEquals(expented, actual);
    }

    @Test
    public void matchesSmartphone1() {
        Product[] actual = menager.searchBy("manufacturer3");
        Product[] expented = new Product[]{thirdS};
        assertArrayEquals(expented, actual);
    }

    @Test
    public void matchesSmartphone2() {
        Product[] actual = menager.searchBy("secondSmart");
        Product[] expented = new Product[]{secondS};
        assertArrayEquals(expented, actual);
    }

    @Test
    public void matchesSmartphone3() {
        Product[] actual = menager.searchBy("manufacturer4");
        Product[] expented = new Product[]{};
        assertArrayEquals(expented, actual);
    }

    @Test
    public void matchesBoookAndSmartphone() {
        Product[] actual1 = menager.searchBy("firstBook");
        Product[] actual2 = menager.searchBy("manufacturer3");
        Product[] actual = new Product[2];
        actual[0] = actual1[0];
        actual[1] = actual2[0];
        Product[] expented = new Product[]{firstB, thirdS};
        assertArrayEquals(expented, actual);
    }
}