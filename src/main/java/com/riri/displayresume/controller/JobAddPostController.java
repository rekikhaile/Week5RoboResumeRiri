package com.riri.displayresume.controller;

import com.riri.displayresume.model.Job;
import com.riri.displayresume.model.Organization;
import com.riri.displayresume.model.Skill;
import com.riri.displayresume.repositories.JobRepo;
import com.riri.displayresume.repositories.OrganizationRepo;
import com.riri.displayresume.repositories.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/jobs")
public class JobAddPostController {

    @Autowired
    JobRepo jobRepo;
    @Autowired
    OrganizationRepo orgRepo;
    @Autowired
    SkillRepo skillRepo;

    @RequestMapping("/")
    public String listjobb(Model model) {
        model.addAttribute("jobss",jobRepo.findAll());
        return "index";
    }

    @RequestMapping("/index")
    public String homejobb(Model model) {
        return "index";
    }
    @GetMapping("/addjob")
    public String addJobs(Model model){

        model.addAttribute("job",  new Job());
        //model.addAttribute(new Job());
        model.addAttribute("orgas", orgRepo.findAll());
        model.addAttribute("skillies",skillRepo.findAll());
        return "addjobform";
    }

    @PostMapping("/addjob")
  /* public String postAddJobs(@Valid @ModelAttribute("job") Job job,
                                     BindingResult result, Model model) {*/
    public String postAddJobs(@ModelAttribute @Valid Job job,
                              BindingResult result, @RequestParam String orgId, Model model,HttpServletRequest request) {
        if (result.hasErrors()) {
            return "addjobform";
        }
        Organization orga = orgRepo.findOne(Long.parseLong(orgId));
        job.setOrg(orga);
        model.addAttribute("orgaName", orga.getOrgName());
        //model.addAttribute("reqskills", skillRepo.findAllByJobs(job));
        Skill reqskill = new Skill();
        String[] ids = request.getParameterValues("skillname");

        for (String id :
                ids) {
            reqskill.setId(skillRepo.findSkillById(new Long(id)).getId());
            reqskill.setSkillName(skillRepo.findSkillById(new Long(id)).getSkillName());
            reqskill.setSkillLevel(skillRepo.findSkillById(new Long(id)).getSkillLevel());
            System.out.println("skill info"+skillRepo.findSkillById(new Long(id)).getSkillName());
            job.addSkill(reqskill);
        }


        jobRepo.save(job);
        //return "redirect:/"+job.getId();
        return "redirect:/jobs/displayjobs";

    }

   /*@GetMapping("/addskilltoajob/{jobId}")
    public String addSkillstoaJob(Model model, @PathVariable long jobId){

        Job thisjob = jobRepo.findOne(jobId);
        AddMenuItemForm form = new AddMenuItemForm(skillRepo.findAll(),thisjob);
        model.addAttribute("jobtitile", thisjob.getJobName());
        model.addAttribute("form",form);
        return "addjobform";
    }

    @PostMapping("/addskilltoajob")
    public String addSkillstoaJob(@ModelAttribute @Valid AddMenuItemForm form,
                              BindingResult result, @RequestParam String orgId, Model model) {
        if (result.hasErrors()) {
            return "/addskilltoajob";
        }
       Skill theSkill = skillRepo.findOne(form.getSkillId());
        Job theJob = jobRepo.findOne(form.getJobId());
        theJob.addSkillstoaJob(theSkill);
        return "redirect:/displayjobs/" + theJob.getId();

    }*/

    @GetMapping("/jobsinorg")
    public String ajobsinOrganzation(Model model, @RequestParam long id){

        Organization org = orgRepo.findOne(id);
        Set<Job> jobs = org.getJobs(); // automatically populated as hibernate fetches from the database and to popukate
        model.addAttribute("jobsinorgs",jobs ); //jobs in an organization
        model.addAttribute("title","Jobs in Organization"+ org.getOrgName());
        return "redirect:/";
    }

    @GetMapping("/removejob")
    public String removeJobs(Model model){
        model.addAttribute("jobstoremove",jobRepo.findAll());
        return "/removejob";
    }
    @PostMapping("/removejob")
    public String processRemoveJob(@RequestParam long[] ids) {
        for(long id : ids) {
            jobRepo.delete(id);
        }
        return "redirect:";
    }

    //@GetMapping("/displayjobs/{jobId}") // contents of a single job
    @GetMapping("/displayjobs")
    //public String DisplayJobsbyid(Model model, @PathVariable long jobId){
    public String DisplayJobsbyid(Model model){
        //Job ajob = jobRepo.findOne(jobId);
       model.addAttribute("jobs",jobRepo.findAll());
        //model.addAttribute("skillies",ajob.getSkillsets());
       // model.addAttribute("jobId", ajob.getId());

        return "displayjobs";
    }


}
