package com.websarva.wings.android.bocian.listItem;

import static com.websarva.wings.android.bocian.beans.Constants.Num.ZERO;

public class AddEmployeeListItem {
    private long id;
    private int empId;
    private String name;
    private String division;
    private String section;
    private String post;
    private boolean checked;

    public AddEmployeeListItem() {
        this.id     = ZERO;
        this.empId     = ZERO;
        this.name = null;
        this.division = null;
        this.section = null;
        this.post = null;
        this.checked = false;
    }

    public long     getId()                 { return id; }
    public int     getEmpId()                 { return empId; }
    public String   getName()               { return name; }
    public String   getDivision()           { return division; }
    public String   getSection()            { return section; }
    public String   getPost()               { return post; }
    public boolean isChecked()              { return checked; }

    public void     setId(long id)                  { this.id = id; }
    public void     setEmpId(int empId)             { this.empId = empId; }
    public void     setName(String name)            { this.name = name; }
    public void     setDivision(String division)    { this.division = division; }
    public void     setSection(String section)      { this.section = section; }
    public void     setPost(String post)            { this.post = post; }
    public void     setChecked(boolean checked)         { this.checked = checked; }

}