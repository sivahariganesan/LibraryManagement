package com.library.modules.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class IssuedBooks
{
    @Id
    private Long      issualID;
    private Long      bookID;
    private Long      userID;
    private String    bookTag;
    private boolean   isReturned;
    private Timestamp issuedTime;
    private Timestamp dueTime;
    private Timestamp returnedTime;
    private Date      issuedDate;
    private Date      dueDate;
    private Date      returnedDate;

    public IssuedBooks(Long bookID, Long userID, Timestamp issuedTime)
    {
        this.bookID = bookID;
        this.userID = userID;
        this.issuedTime = issuedTime;
        this.issuedDate = new Date(issuedTime.getTime());
    }
    public Long getIssualID()
    {
        return issualID;
    }

    public void setIssualID(Long issualID)
    {
        this.issualID = issualID;
    }

    public Long getBookID()
    {
        return bookID;
    }

    public void setBookID(Long bookID)
    {
        this.bookID = bookID;
    }

    public Long getUserID()
    {
        return userID;
    }

    public void setUserID(Long userID)
    {
        this.userID = userID;
    }

    public Timestamp getIssuedTime()
    {
        return issuedTime;
    }

    public void setIssuedTime(Timestamp issuedTime)
    {
        this.issuedTime = issuedTime;
        this.issuedDate = new Date(issuedTime.getTime());
    }

    public Date getIssuedDate()
    {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate)
    {
        this.issuedDate = issuedDate;
        this.issuedTime = new Timestamp(issuedDate.getTime());
    }
    public Date getDueDate()
    {
        return dueDate;
    }

    public void setDueDate(Date dueDate)
    {
        this.dueDate=dueDate;
        this.dueTime=new Timestamp(dueDate.getTime());
    }
    public Timestamp getDueTime()
    {
        return this.dueTime;
    }
    public void setDueTime(Timestamp dueTime)
    {
        this.dueTime=dueTime;
        this.dueDate=new Date(dueTime.getTime());
    }
    public String getBookTag()
    {
        return bookTag;
    }
    public void setBookTag(String bookTag)
    {
        this.bookTag=bookTag;
    }
    public boolean getIsReturned()
    {
        return isReturned;
    }
    public void setIsReturned(boolean isReturned)
    {
        this.isReturned=isReturned;
    }
    public Timestamp getReturnedTime()
    {
        return returnedTime;
    }
    public void setReturnedTime(Timestamp returnedTime)
    {
        this.returnedTime=returnedTime;
        this.returnedDate=new Date(returnedTime.getTime());
    }
    public Date getReturnedDate()
    {
        return returnedDate;
    }
    public void setReturnedDate(Date returnedDate)
    {
        this.returnedDate=returnedDate;
        this.returnedTime=new Timestamp(returnedDate.getTime());
    }
}