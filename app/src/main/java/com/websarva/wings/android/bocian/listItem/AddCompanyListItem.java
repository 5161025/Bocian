package com.websarva.wings.android.bocian.listItem;

import static com.websarva.wings.android.bocian.beans.Constants.Num.ZERO;

public class AddCompanyListItem {

    private long id;
    private int CompanyId;
    private String name;
    private String count;

    public AddCompanyListItem() {
        this.id     = ZERO;
        this.CompanyId = ZERO;
        this.name = null;
        this.count = null;
    }

    public long     getId()                 { return id; }
    public int      getCompanyId()             { return CompanyId; }
    public String   getName()               { return name; }
    public String   getCount()               { return count; }

    public void     setId(long id)                  { this.id = id; }
    public void     setCompanyId(int companyId)           { this.CompanyId = companyId; }
    public void     setName(String name)            { this.name = name; }
    public void     setCount(String count)            { this.count = count; }
}