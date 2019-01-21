package com.websarva.wings.android.bocian.listItem;

import static com.websarva.wings.android.bocian.beans.Constants.Num.ZERO;

public class ParticipantsListItem {
    private long id;
    private int participantsId;
    private String name;
    private String post;

    public ParticipantsListItem() {
        this.id     = ZERO;
        this.participantsId     = ZERO;
        this.name = null;
        this.post = null;
    }



    public long     getId()                 { return id; }
    public int      getParticipantsId()     { return participantsId; }
    public String   getName()               { return name; }
    public String   getPost()               { return post; }

    public void     setId(long id)                  { this.id = id; }
    public void     setParticipantsId(int participantsId) { this.participantsId = participantsId; }
    public void     setName(String name)            { this.name = name; }
    public void     setPost(String post)            { this.post = post; }
}