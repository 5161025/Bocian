package com.websarva.wings.android.bocian.listItem;

public class SelectedCompanyListItem {
    public static final int ZERO = 0;

    private long id;
    private String name;

    public SelectedCompanyListItem() {
        this.id     = ZERO;
        this.name = null;
    }

    public long     getId()                 { return id; }
    public String   getName()               { return name; }

    public void     setId(long id)                  { this.id = id; }
    public void     setName(String name)            { this.name = name; }
}
