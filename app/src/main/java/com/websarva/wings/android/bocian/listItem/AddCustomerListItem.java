package com.websarva.wings.android.bocian.listItem;

public class AddCustomerListItem {
    public static final int ZERO = 0;

    private long id;
    private String name;
    private String post;
    private boolean checked;

    public AddCustomerListItem() {
        this.id     = ZERO;
        this.name = null;
        this.post = null;
        this.checked = false;
    }

    public long     getId()                 { return id; }
    public String   getName()               { return name; }
    public String   getPost()               { return post; }
    public boolean isChecked()              { return checked; }

    public void     setId(long id)                      { this.id = id; }
    public void     setName(String name)                { this.name = name; }
    public void     setPost(String post)                { this.post = post; }
    public void     setChecked(boolean checked)         { this.checked = checked; }
}