package com.riri.displayresume.controller;

import com.riri.displayresume.model.AddMenuItemForm;
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
        model.addAttribute("orgas", orgRepo.findAll());
        model.addAttribute("skillies",skillRepo.findAll());
        return "addjobform";
    }

    @PostMapping("/addjob")
  /* public String postAddJobs(@Valid @ModelAttribute("job") Job job,
                                     BindingResult result, Model model) {*/
    public String postAddJobs(@ModelAttribute @Valid Job job,
                              BindingResult result,@RequestParam String orgId,
                                         Model model,HttpServletRequest request) {
        if (result.hasErrors()) {
            return "addjobform";
        }
        Organization orga = orgRepo.findOne(Long.parseLong(orgId));
        //Skill oneskill = skillRepo.findOne(Long.parseLong(skillId));
        job.setOrg(orga);
       // job.addSkill(oneskill);

        model.addAttribute("orgaName", orga.getOrgName());
       // model.addAttribute("reqskill", oneskill);

        jobRepo.save(job);

        //job.setSkillsets(skillRepo.findAllByJobs(job));
        Skill reqskill = new Skill();
        String[] ids = request.getParameterValues("skillname");

        for (String id :
                ids) {
            reqskill.setId(skillRepo.findSkillById(new Long(id)).getId());
            reqskill.setSkillName(skillRepo.findSkillById(new Long(id)).getSkillName());
            reqskill.setSkillLevel(skillRepo.findSkillById(new Long(id)).getSkillLevel());
            System.out.println("skill info"+skillRepo.findSkillById(new Long(id)).getSkillName());

            job.addSkill(reqskill);
            jobRepo.save(job);
            model.addAttribute("reqskill",skillRepo.findSkillById(new Long(id)).getSkillName());
        }
        return "redirect:/jobs/addjob";
    }


    @GetMapping("/displayjobs") // contents of a single job
    //@GetMapping("/displayjobs")
    //public String DisplayJobsbyid(@PathVariable("id") long id, Model model){
    public String DisplayJobsbyid(Model model){
       // Job ajob = jobRepo.findOne(id);
       // model.addAttribute("ajob",ajob);
       model.addAttribute("jobs",jobRepo.findAll());
       // model.addAttribute("skillsinjob",ajob.getSkillsets());
       // model.addAttribute("jobId", ajob.getId());

        return "displayjobs";
    }


}
