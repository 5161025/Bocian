package com.websarva.wings.android.bocian.listItem;

import static com.websarva.wings.android.bocian.beans.Constants.Num.ZERO;

public class FixturesListItem {
    private long id;
    private int fixturesId;
    private String name;
    private String count;

    public FixturesListItem() {
        this.id     = ZERO;
        this.fixturesId     = ZERO;
        this.name = null;
        this.count = null;
    }



    public long     getId()                 { return id; }
    public int      getFixturesId()         { return fixturesId; }
    public String   getName()               { return name; }
    public String   getCount()                { return count; }

    public void     setId(long id)                  { this.id = id; }
    public void     setFixturesId(int fixturesId)   { this.fixturesId = fixturesId; }
    public void     setName(String name)            { this.name = name; }
    public void     setCount(String count)          { this.count = count; }
}
