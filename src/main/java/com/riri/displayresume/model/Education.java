package com.riri.displayresume.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Education {

    @NotNull
    @Size(min=2,message="Enter at least {min} characters")
    private String title;

    @NotNull
    @Size(min=2,message="Enter at least {min} characters")
    private String level;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
