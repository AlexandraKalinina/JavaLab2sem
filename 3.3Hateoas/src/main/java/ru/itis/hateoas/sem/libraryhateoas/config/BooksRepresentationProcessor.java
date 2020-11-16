package ru.itis.hateoas.sem.libraryhateoas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoas.sem.libraryhateoas.controllers.BookController;
import ru.itis.hateoas.sem.libraryhateoas.model.Book;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



@Component
public class BooksRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Book>> {

    @Autowired
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<Book> process(EntityModel<Book> model) {
        Book book = model.getContent();
        if (book != null && book.getState().equals("unBlocking")) {
            model.add(linkTo(methodOn(BookController.class).download(book.getId())).withRel("loaded"));
        }
        model.add(links.linkToItemResource(Book.class, book.getId()).
                withRel("delete"));
        /*if (book!= null && book.getState().equals("Blocking")) {
            model.add(links.linkToItemResource(Book.class, book.getId()).
                    withRel("delete"));
        }*/
        return model;
    }
}
