package com.riri.displayresume.model;

import java.util.Set;

public class AddMenuItemForm {

    private long jobId;

    private long skillId;

    private Iterable<Skill> theskills;

    private Job job;

    public AddMenuItemForm() {
    }

    public AddMenuItemForm(Iterable<Skill> theskills, Job job) {
        this.theskills = theskills;
        this.job = job;
    }

    public AddMenuItemForm(Set<Skill> theskills, Job job) {
        this.theskills = theskills;
        this.job = job;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public long getSkillId() {
        return skillId;
    }

    public void setSkillId(long skillId) {
        this.skillId = skillId;
    }

    public Iterable<Skill> getTheskills() {
        return theskills;
    }

    public void setTheskills(Iterable<Skill> theskills) {
        this.theskills = theskills;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
