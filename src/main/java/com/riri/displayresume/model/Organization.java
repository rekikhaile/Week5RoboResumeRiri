package com.riri.displayresume.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String orgName;
    private String orgDesc;


    @OneToMany
    @JoinColumn(name = "org_id") //which column on the job table tells jobs owned by the organization object
    private Set<Job> jobs = new HashSet<>();

    public Organization() {
    }

    public Organization(String orgName, String orgDesc) {
        this.orgName = orgName;
        this.orgDesc = orgDesc;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }
}
