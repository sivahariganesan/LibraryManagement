package com.library.modules.controller;

import com.library.modules.Service.BookService;
import com.library.modules.Service.UserService;
import com.library.modules.Utility.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class RestUserController
{
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<String> getParams(@RequestParam String userName,
                                            @RequestParam String password,
                                            @RequestParam String roleName)
    {
        System.out.println(userName);
        System.out.println(password);
        System.out.println(roleName);
        boolean    isUserAdded    = userService.addUserDetails(userName, password, roleName);
        String     responseMsg    = isUserAdded ? "Successfully Created the User" : "Failed to Add User";
        HttpStatus responseStatus = isUserAdded ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<String>(responseMsg, responseStatus);
    }

    @RequestMapping(value = "/fetchUsers", method = RequestMethod.GET)
    private ResponseEntity<List> fetchUsers()
    {
        List       usersList      = userService.retrieveUsers(null);
        HttpStatus responseStatus = usersList.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<List>(usersList, responseStatus);

    }

    @RequestMapping(value = "/fetchIssuedBooks", method = RequestMethod.GET)
    public ResponseEntity<List> fetchIssuedBooks(@RequestParam(value = "userID", required = false) Long userID,
                                                 @RequestParam(value = "isClientCall", required = false) boolean isClientCall,
                                                 @RequestParam(value = "isHistory", required = false) boolean isHistory)
    {
        if(isClientCall)
        {
            Authentication auth      = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("Auth Check: "+auth.getAuthorities().contains("LIBRARIAN"));
            Iterator authItr=auth.getAuthorities().iterator();
            boolean isAdmin=false;
            while(authItr.hasNext())
            {
                Object authority= authItr.next();
                SimpleGrantedAuthority admin=new SimpleGrantedAuthority("ADMIN");
                SimpleGrantedAuthority librarian=new SimpleGrantedAuthority("LIBRARIAN");
                isAdmin=authority.equals(admin)||authority.equals(librarian);
            }
            if(userID==null && !isAdmin)
            {
                userID=getUserIDFromAuth();
            }
        }
        List       booksList      = bookService.fetchIssuedBookDetails(userID,isHistory);
        HttpStatus responseStatus =  HttpStatus.OK ;
        return new ResponseEntity<List>(booksList, responseStatus);
    }

    @RequestMapping(value = "/issueBook", method = RequestMethod.POST)
    public ResponseEntity<String> issueBook(@RequestParam(value = "userID", required = false) Long userID, @RequestParam Long bookID)
    {
        int copies = bookService.getCopiesAvailableForBook(bookID);
        userID = userID == null ? getUserIDFromAuth() : userID;
        String responseMsg;
        HttpStatus responseStatus;
        boolean isBookIssued=false;
        if(copies>0)
        {
            isBookIssued=bookService.issueBook(bookID,userID,copies);
        }

        responseMsg = copies == 0 ? "Sorry,Not Enough copies available to Lend!!" : copies == -1 ? "Requested Book Details Not Found"
                : isBookIssued ? "Book has been issued and due date is" + DateTimeUtil.getFormattedDateAfter(30) :
                "Sorry,Failed to issue Book, please try again later";
        responseStatus = (copies > 0 && isBookIssued) ? HttpStatus.OK : copies == -1 ? HttpStatus.NOT_FOUND :
                !isBookIssued ? HttpStatus.INTERNAL_SERVER_ERROR:HttpStatus.INTERNAL_SERVER_ERROR;

        return new ResponseEntity<String>(responseMsg,responseStatus);
    }
    @RequestMapping(value = "/returnBook", method = RequestMethod.POST)
    public ResponseEntity<String> returnBook(@RequestParam Long issualID, @RequestParam Long bookID)
    {
        int copies = bookService.getCopiesAvailableForBook(bookID);
        String responseMsg;
        HttpStatus responseStatus;
        System.out.println("In Return Book for "+bookID+" with Issual ID"+issualID);
        boolean isBookReturned=bookService.returnBook(bookID,issualID,copies);


        responseMsg = isBookReturned? "Book has been returned successfully":
                "Sorry,Failed to return Book, please try again later";
        responseStatus = (isBookReturned) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        return new ResponseEntity<String>(responseMsg,responseStatus);
    }

    private Long getUserIDFromAuth()
    {
        Long           userID    =null;
        Authentication auth      = SecurityContextHolder.getContext().getAuthentication();
        Object         principal = auth.getPrincipal();
        if(principal instanceof UserDetails)
        {
            String                                      name            = ((UserDetails) principal).getUsername();
            System.out.println(name);
            List<com.library.modules.model.UserDetails> userDetailsList = userService.retrieveUsers(name);
            userID=userDetailsList.get(0).getUserID();
        }
        return userID;
    }
}
