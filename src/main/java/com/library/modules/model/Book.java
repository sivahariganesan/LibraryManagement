package com.library.modules.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book
{
    @Id
    private Long   bookID;
    private String bookTag;
    private String groupTag;
    private String bookName;
    private Long   rackNumber;
    private String publication;
    private int    copies;
    private String rackName;
    private String author;

    public Book(String bookName, String author)
    {
        this.bookName = bookName;
        this.author = author;
    }

    public Book(String bookName,String bookTag,String groupTag, String author, String publication, String rackName, Long rackNumber, int copies)
    {
        this.bookName       =   bookName;
        this.bookTag        =   bookTag;
        this.groupTag       =   groupTag;
        this.author         =   author;
        this.publication    =   publication;
        this.rackName       =   rackName;
        this.rackNumber     =   rackNumber;
        this.copies         =   copies;
    }

    public Long getBookID()
    {
        return bookID;
    }

    public void setBookID(Long bookID)
    {
        this.bookID = bookID;
    }

    public Long getRackNumber()
    {
        return rackNumber;
    }

    public void setRackNumber(Long rackNumber)
    {
        this.rackNumber = rackNumber;
    }

    public String getRackName()
    {
        return rackName;
    }

    public void setRackName(String rackName)
    {
        this.rackName = rackName;
    }

    public String getBookName()
    {
        return bookName;
    }

    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getPublication()
    {
        return publication;
    }

    public void setPublication(String publication)
    {
        this.publication = publication;
    }

    public int getCopies()
    {
        return copies;
    }

    public void setCopies(int copies)
    {
        this.copies = copies;
    }

    public String getBookTag()
    {
        return bookTag;
    }
    public void setBookTag(String bookTag)
    {
        this.bookTag=bookTag;
    }

    public String getGroupTag()
    {
        return groupTag;
    }
    public void setGroupTag(String groupTag)
    {
        this.groupTag=groupTag;
    }
}

