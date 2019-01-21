package com.websarva.wings.android.bocian.listItem;

public class AddEmployeeListItem {
    public static final int ZERO = 0;

    private long id;
    private String name;
    private String division;
    private String section;
    private String post;
    private boolean checked;

    public AddEmployeeListItem() {
        this.id     = ZERO;
        this.name = null;
        this.division = null;
        this.section = null;
        this.post = null;
        this.checked = false;
    }

    public long     getId()                 { return id; }
    public String   getName()               { return name; }
    public String   getDivision()           { return division; }
    public String   getSection()            { return section; }
    public String   getPost()               { return post; }
    public boolean isChecked()              { return checked; }

    public void     setId(long id)                  { this.id = id; }
    public void     setName(String name)            { this.name = name; }
    public void     setDivision(String division)    { this.division = division; }
    public void     setSection(String section)      { this.section = section; }
    public void     setPost(String post)            { this.post = post; }
    public void     setChecked(boolean checked)         { this.checked = checked; }

}