package com.springboot.controller;

import com.springboot.entities.Books;
import com.springboot.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

//    @RequestMapping(value = "/books", method = RequestMethod.GET)
//    @ResponseBody
//    public String getBook(){
//        return "this is book section";
//    }

    @Autowired
    private BookService bookService= new BookService();


    @GetMapping("/books")
    public ResponseEntity<List<Books>> getAllBooks(){
        List<Books> list=bookService.getAllBook();
        return new ResponseEntity<List<Books>>(list,HttpStatus.METHOD_NOT_ALLOWED);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable("id") int id){
        Books book = bookService.getBookById(id);
//        if(book == null){
//            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.of(Optional.of(book));

        return new ResponseEntity<Books>(book,HttpStatus.NOT_FOUND);
    }
    
    
    @PostMapping("/books")
    public ResponseEntity<?> addBook(@Valid  @RequestBody Books book){
        Books b= null;
//        try{
//            b=bookService.addBook(book);
//            return ResponseEntity.of(Optional.of(b));
//        }catch(Exception e){
//            e.printStackTrace();
//            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//        return bookService.addBook(book);

        b=bookService.addBook(book);
        return new ResponseEntity<Books>(b,HttpStatus.CREATED);
    }

    @DeleteMapping("/books/{bookId}")
    public String deleteBook(@PathVariable("bookId") int bookId){

        bookService.deleteBook(bookId);
        return "deleted successfully";

    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Books> updateBook(@RequestBody Books book , @PathVariable("bookId") int bookId){
        bookService.updateBook(book,bookId);
        return new ResponseEntity<Books>(book,HttpStatus.CREATED);
    }

    @GetMapping("/books/sort/{field}")
    public ResponseEntity<List<Books>> sortByField(@PathVariable("field") String field){
        List<Books> sortedlist = bookService.sortByField(field);
        return new ResponseEntity<List<Books>>(sortedlist,HttpStatus.CREATED);
    }

    @GetMapping("/books/{offset}/{pagesize}")
    public Page<Books> paginatedBooks(@PathVariable int offset,@PathVariable int pagesize){
        Page<Books> bookswithPagination = bookService.findProductWithPagination(offset, pagesize);

        return bookswithPagination;
    }
}
