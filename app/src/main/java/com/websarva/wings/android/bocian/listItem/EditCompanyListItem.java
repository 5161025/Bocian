package com.websarva.wings.android.bocian.listItem;

public class EditCompanyListItem {
    public static final int ZERO = 0;

    private long id;
    private String name;
    private String post;

    public EditCompanyListItem() {
        this.id     = ZERO;
        this.name = null;
        this.post = null;
    }



    public long     getId()                 { return id; }
    public String   getName()               { return name; }
    public String   getPost()               { return post; }

    public void     setId(long id)                  { this.id = id; }
    public void     setName(String name)            { this.name = name; }
    public void     setPost(String post)            { this.post = post; }
}