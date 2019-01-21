package com.websarva.wings.android.bocian.listItem;

import static com.websarva.wings.android.bocian.beans.Constants.Num.ZERO;

public class SelectedCompanyListItem {
    private long id;
    private int companyId;
    private String name;

    public SelectedCompanyListItem() {
        this.id     = ZERO;
        this.companyId     = ZERO;
        this.name = null;
    }

    public long     getId()                 { return id; }
    public int      getCompanyId()          { return companyId; }
    public String   getName()               { return name; }

    public void     setId(long id)                  { this.id = id; }
    public void     setCompanyId(int companyId)     { this.companyId = companyId; }
    public void     setName(String name)            { this.name = name; }
}
