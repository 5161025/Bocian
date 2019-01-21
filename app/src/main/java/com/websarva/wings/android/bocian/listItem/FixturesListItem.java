package com.websarva.wings.android.bocian.listItem;

public class FixturesListItem {
    public static final int ZERO = 0;

    private long id;
    private String name;
    private String count;

    public FixturesListItem() {
        this.id     = ZERO;
        this.name = null;
        this.count = null;
    }

    public long     getId()                 { return id; }
    public String   getName()               { return name; }
    public String   getCount()                { return count; }

    public void     setId(long id)                  { this.id = id; }
    public void     setName(String name)            { this.name = name; }
    public void     setCount(String count)          { this.count = count; }
}
