package io.rachidassouani.myinbox.emaillist;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;

import static org.springframework.data.cassandra.core.mapping.CassandraType.Name.TEXT;
import static org.springframework.data.cassandra.core.mapping.CassandraType.Name.BOOLEAN;
import static org.springframework.data.cassandra.core.mapping.CassandraType.Name.LIST;

@Table("messages_by_user_folder")
public class EmailListItem {
    @PrimaryKey
    private EmailListItemKey id;

    @CassandraType(type = LIST, typeArguments = TEXT)
    private List<String> to;

    @CassandraType(type = TEXT)
    private String subject;

    @CassandraType(type = BOOLEAN)
    private boolean isUnread;

    @Transient
    private String agoTimeString;

    public EmailListItemKey getId() {
        return id;
    }

    public void setId(EmailListItemKey id) {
        this.id = id;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isUnread() {
        return isUnread;
    }

    public void setUnread(boolean unread) {
        isUnread = unread;
    }

    public String getAgoTimeString() {
        return agoTimeString;
    }

    public void setAgoTimeString(String agoTimeString) {
        this.agoTimeString = agoTimeString;
    }
}