package com.springboot.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.apache.tomcat.util.http.parser.Authorization;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "Books")
public class Books implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    @NotNull
    private int id;
//    private String author;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Author author;




    @Pattern(regexp = "^[A-Z][A-z0-9_-]{3,19}$" , message="first letter of title should be CAPITAL")
    private String title;

    public Books(int id, Author author, String title) {
        this.id = id;
        this.author = author;
        this.title = title;
    }

    public Books() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
