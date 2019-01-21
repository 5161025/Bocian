package com.websarva.wings.android.bocian.listItem;

import static com.websarva.wings.android.bocian.beans.Constants.Num.ZERO;

public class AddCustomerListItem {

    private long id;
    private int CustomerId;
    private String name;
    private String post;
    private boolean checked;

    public AddCustomerListItem() {
        this.id     = ZERO;
        this.CustomerId     = ZERO;
        this.name = null;
        this.post = null;
        this.checked = false;
    }

    public long     getId()                 { return id; }
    public int      getCustomerId()         { return CustomerId; }
    public String   getName()               { return name; }
    public String   getPost()               { return post; }
    public boolean isChecked()              { return checked; }

    public void     setId(long id)                      { this.id = id; }
    public void     setCustomerId(int customerId)       { CustomerId = customerId; }
    public void     setName(String name)                { this.name = name; }
    public void     setPost(String post)                { this.post = post; }
    public void     setChecked(boolean checked)         { this.checked = checked; }
}