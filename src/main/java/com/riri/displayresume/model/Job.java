package com.riri.displayresume.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String jobName;

    private String jobDesc;

    @ManyToMany(fetch = FetchType.EAGER)
    //This needs to be instantiated in the construtor so you can use it to add and remove individual skills
    @JoinTable(joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skillsets;

    @ManyToOne(fetch = FetchType.LAZY)
    private Set<Organization> orgs;

    public Job() {
        this.skillsets = new HashSet<>();
    }

    public Job(String jobName, String jobDesc, Set<Skill> skillsets, Set<Organization> orgs) {
        this.jobName = jobName;
        this.jobDesc = jobDesc;
        this.skillsets = skillsets;
        this.orgs = orgs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public Set<Skill> getSkillsets() {
        return skillsets;
    }

    public void setSkillsets(Set<Skill> skillsets) {
        this.skillsets = skillsets;
    }

    public Set<Organization> getOrgs() {
        return orgs;
    }

    public void setOrgs(Set<Organization> orgs) {
        this.orgs = orgs;
    }
}
