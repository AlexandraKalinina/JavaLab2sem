package ru.parser.simbirSoft.models;

public class Word {
    private Long id;
    private String word;
    private Integer count;

    public Word(Long id, String word, Integer count) {
        this.id = id;
        this.word = word;
        this.count = count;
    }

    public Word(String word, Integer count) {
        this.word = word;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
