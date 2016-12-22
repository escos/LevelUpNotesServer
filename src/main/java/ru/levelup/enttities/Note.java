package ru.levelup.enttities;

import com.google.gson.annotations.Expose;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import java.util.List;

@Entity("notes")

public class Note implements BaseEntity<String> {

    @Id
    @Expose
    private String id;
    @Expose
    private String title;
    @Expose
    private String body;
    @Expose
    private long created;
    @Expose
    private long updated;
    @Expose
    private String author;
    @Expose
    private List<AccessRight> accessRights;

    public Note() {
    }

    public Note(String id, String tittle, String body, long created, long updated, String author){
        this.id = id;
        this.title = tittle;
        this.body = body;
        this.created = created;
        this.updated = updated;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public long getCreated() {
        return created;
    }

    public long getUpdated() {
        return updated;
    }

    public List<AccessRight> getAccessRights() {
        return accessRights;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public void setAccessRights(List<AccessRight> accessRights) {
        this.accessRights = accessRights;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
