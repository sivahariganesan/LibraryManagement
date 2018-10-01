package com.library.modules.model;



import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BookGroup
{
    @Id
    private String groupTag;
    private String groupName;
    public String getGroupTag()
    {
        return groupTag;
    }
    public void setGroupTag(String groupTag)
    {
        this.groupTag=groupTag;
    }
    public String getGroupName()
    {
        return groupName;
    }
    public void setGroupName(String groupName)
    {
        this.groupName=groupName;
    }

}
