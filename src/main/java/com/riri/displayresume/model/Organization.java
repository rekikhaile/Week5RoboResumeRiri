package com.riri.displayresume.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String orgName;


    @OneToMany(fetch = FetchType.EAGER)
    private Set<Job> jobs;

    public Organization() {
    }

    public Organization(String orgName, Set<Job> jobs) {
        this.orgName = orgName;
        this.jobs = jobs;
    }
}
