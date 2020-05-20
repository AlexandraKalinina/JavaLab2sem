package ru.spring.semestrovka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.File;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "book")
@Slf4j
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String text;

    private String type;
    private Long size;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "books")
    private List<Author> authors;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( name="book_genre",
            joinColumns = @JoinColumn(name="book_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="genre_id", referencedColumnName="id")
    )
    private List<Genre> genres;

    @OneToMany(mappedBy = "book")
    private List<Message> messages;

    @Transient
    private File sourceFile;

    @PostLoad
    public void loadFile() {
        // persistent(path) -> transient(sourceFile, fileName)
        sourceFile = new File(text);
        name = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf("."));
        log.info("Load file for " + name);
    }
    @PreUpdate
    public void updateFileInformation() {
        // transient(sourceFile) -> persistent(path, size)
        this.text = sourceFile.getPath();
        this.size = sourceFile.length();
        this.name = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf("."));
        log.info("Update file information for " + name);
    }


    public Book(Long id, String name, String text, List<Author> authors, List<Genre> genres) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.authors = authors;
        this.genres = genres;
    }

    public Book(String name) {
        this.name = name;
    }
}
