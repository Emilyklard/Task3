package entity;
import lombok.*;

import java.util.Objects;
@Builder
public class Book {
    private Long id;
    private String name;
    private Short year;
    private Short pages;
    private Integer author_id;
    public Book(Long id, String name, Short year, Short pages, Integer author_id) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.pages = pages;
        this.author_id = author_id;
    }
    public Book(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public Short getPages() {
        return pages;
    }

    public void setPages(Short pages) {
        this.pages = pages;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                ", author_id=" + author_id +
                '}';
    }
}
