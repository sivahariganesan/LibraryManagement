package com.library.modules.controller;

import com.library.modules.Service.BookService;
import com.library.modules.Service.UserService;
import com.library.modules.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestBookController
{
    @Autowired
    BookService bookService;

    @RequestMapping(value = "/fetchBook", method = RequestMethod.GET)
    public ResponseEntity<List> getParams(@RequestParam(value = "bookName",required = false ) String bookName,
                                          @RequestParam(value = "query",required = false ) String query,
                                          @RequestParam(value = "skip",required = false ) Integer startIndex,
                                          @RequestParam(value = "limit",required = false ) Integer limit)
    {
        System.out.println(bookName);
        bookName = bookName == null ? query.trim() != null ? query : bookName : bookName;
        limit = limit == null ? 25 : limit;
        startIndex = startIndex==null?0:startIndex;
        List bookList = bookService.getBooks(bookName,startIndex,limit);
        if (bookList.size() > 0)
        {
            return new ResponseEntity<List>(bookList, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<List>(bookList, HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public ResponseEntity<String> addBook(@RequestParam String bookName,
                                          @RequestParam String bookTag,
                                          @RequestParam String groupTag,
                                          @RequestParam String author,
                                          @RequestParam Long rackNumber,
                                          @RequestParam String rackName,
                                          @RequestParam String publication,
                                          @RequestParam Integer copies)
    {
        Book book=new Book(bookName,bookTag,groupTag,author,publication,rackName,rackNumber,copies);
        boolean isBookAdded=bookService.addBook(book);
        String responseMsg=isBookAdded?"Successfully added the book":"Failed to add the book";
        HttpStatus responseStatus=isBookAdded?HttpStatus.CREATED:HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<String>(responseMsg,responseStatus);
    }
}