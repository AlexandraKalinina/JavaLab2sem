package ru.itis.hateoas.sem.libraryhateoas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.hateoas.sem.libraryhateoas.model.Book;
import ru.itis.hateoas.sem.libraryhateoas.service.BookService;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class BooksTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        when(bookService.download(7L)).thenReturn(downloadBook());
    }

    @Test
    public void bookDownloadTest() throws Exception {
        mockMvc.perform(put("/books/7/download")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(downloadBook().getName()))
                .andExpect(jsonPath("$.text").value(downloadBook().getText()))
                .andExpect(jsonPath("$.state").value(downloadBook().getState()))
                .andExpect(jsonPath("$.type").value(downloadBook().getType()))
                .andExpect(jsonPath("$.size").value(downloadBook().getSize()))

                .andDo(document("download_book", responseFields(
                        fieldWithPath("id").description("id").optional(),
                        fieldWithPath("name").description("Название книги"),
                        fieldWithPath("text").description("Техст книги"),
                        fieldWithPath("state").description("Состояние книги"),
                        fieldWithPath("type").description("Тип книги"),
                        fieldWithPath("size").description("Размер книги"),
                        fieldWithPath("authors").description("автор").optional(),
                        fieldWithPath("genres").description("жанры").optional(),
                        fieldWithPath("messages").description("сообщения").optional(),
                        fieldWithPath("_links.loaded.href").description("ссылки").optional(),
                        fieldWithPath("_links.delete.href").description("ссылки").optional()
                        /*fieldWithPath("loaded").description("loaded").optional(),
                        fieldWithPath("href").description("href").optional(),
                        fieldWithPath("delete").description("удаленные").optional(),
                        fieldWithPath("href").description("href").optional()*/

                )));
    }
    private Book downloadBook() {
        return Book.builder()
                .id(7L)
                .name("Java 8")
                .text("ссылка на текст книги")
                .state("unBlocking")
                .size(3L)
                .type("txt")
                .build();
    }
}
