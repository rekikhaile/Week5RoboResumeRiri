package com.riri.displayresume.controller;

import com.riri.displayresume.model.*;
import com.riri.displayresume.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;

@Controller
public class ResumeController {
    @Autowired
    PersonalRepo personalRepo;
    @Autowired
    EduRepo eduRepo;
   @Autowired
    ExperienceRepo experienceRepo;
    @Autowired
    SkillRepo skillRepo;
    @Autowired
    SummaryRepo summaryRepo;
    @Autowired
    ReferenceRepo refRepo;
    @Autowired
    ViewRepo viewRepo;
    @Autowired
    CoverLetterRepo coverLetterRepo;
    @Autowired
    UserService userService;
    @Autowired
    JobRepo jobRepo;
    @Autowired
    OrganizationRepo orgRepo;


    @RequestMapping("/")
    public String listpersonal(Model model) {
    model.addAttribute("jobss",jobRepo.findAll());
        return "index";
    }

    @RequestMapping("/index")
    public String homepersonal(Model model) {
        return "index";
    }

    @RequestMapping("/employerindex")
    public String employerHome(Model model) {
        return "indexemployer";
    }


    @GetMapping("/addpersonal")
    public String personalForm(Model model){
        model.addAttribute("personal",new Personal());
        return "personalform";
    }

    //for testing purpose only
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal, Model model) {
       //String currentuser = principal.getName();
       model.addAttribute("currentuser", principal.getName());
        System.out.println( "usert: "+principal.getName());
        return "index";
    }

    @PostMapping("/postpersonal")
    public String processPersonalForm(@Valid @ModelAttribute("personal") Personal personal,
                                      BindingResult result){
        if(result.hasErrors()){
            return "personalform";
        }
        personalRepo.save(personal);
        return "redirect:/addeducation";

    }
    @GetMapping("/addeducation")
    public String educationForm(Model model){
        model.addAttribute("education",new Education());
        return "educationform";
    }

    @PostMapping("/posteducation")
    public String processEducationForm(@Valid @ModelAttribute("education") Education education,
                                       BindingResult result){
        if(result.hasErrors()){
            return "educationform";
        }
        eduRepo.save(education);
        return "redirect:/addeducation";

    }

    @GetMapping("/addexperience")
    public String experienceForm(Model model){
        model.addAttribute("experience",new Experience());
        return "experienceform";
    }

    @PostMapping("/postexperience")
    public String processExperienceForm(@Valid @ModelAttribute("experience") Experience experience,
                                        BindingResult result){
        if(result.hasErrors()){
            return "experienceform";
        }
        experienceRepo.save(experience);
        return "redirect:/addexperience";

    }

    @GetMapping("/addskill")
    public String skillForm(Model model){
        model.addAttribute("skill",new Skill());
        return "skillform";
}

    @PostMapping("/postskill")
    public String processSkillForm(@Valid @ModelAttribute("skill") Skill skill,
                                        BindingResult result){
        if(result.hasErrors()){
            return "skillform";
        }
        skillRepo.save(skill);
        return "redirect:/addskill";

    }


    @GetMapping("/addsummary")
    public String summaryForm(Model model){
        model.addAttribute("summary",new Summary());
        return "summary";
    }

    @PostMapping("/addsummary")
    public String processSummaryForm(@Valid @ModelAttribute("summary") Summary summary,
                                   BindingResult result){
        if(result.hasErrors()){
            return "summary";
        }
        summaryRepo.save(summary);
        return "redirect:/addpersonal";

    }


    @GetMapping("/addreference")
    public String referenceForm(Model model){
        model.addAttribute("reference",new Reference());
        return "referenceform";
    }

    @PostMapping("/addreference")
    public String processRefForm(@Valid @ModelAttribute("reference") Reference reference,
                                   BindingResult result){
        if(result.hasErrors()){
            return "referenceform";
        }
        refRepo.save(reference);
        return "redirect:/addreference";

    }

    //for testing purpose only
    @GetMapping("/addview")
    public String viewForm(Model model){
        model.addAttribute("skill",new View());
        return "printresume";
    }

    @PostMapping("/addview")
    public String processViewForm(@Valid @ModelAttribute("view") View view,
                                   BindingResult result){
        if(result.hasErrors()){
            return "printresume";
        }
       viewRepo.save(view);
        return "redirect:/addview";

    }


    @GetMapping("/coverletter")
    public String viewLetter(Model model){
        model.addAttribute("cvrltr",new CoverLetter());
        return "coverletterform";
    }

    @PostMapping("/coverletter")
    public String processCoverLetter(@Valid @ModelAttribute("cvrltr") CoverLetter coverLetter,
                                  BindingResult result){
        if(result.hasErrors()){
            return "coverletterform";
        }
        coverLetterRepo.save(coverLetter);
        return "redirect:/";

    }

    @GetMapping("/printletter")
    public String displayCoverLetter(Model model){
        model.addAttribute("coverletters",coverLetterRepo.findAll());
        return "printcoverletter";
    }

    @GetMapping("/displayresume")
    public String displayResume(Model model){
        model.addAttribute("summaries", summaryRepo.findAll());
        model.addAttribute("personals", personalRepo.findAll());
        model.addAttribute("educations", eduRepo.findAll());
        model.addAttribute("skills", skillRepo.findAll());
        model.addAttribute("experiences", experienceRepo.findAll());
        model.addAttribute("references",refRepo.findAll());

        return "printresume";
    }


    @GetMapping("/printletteremployer")
    public String employerDisplayCoverLetter(Model model){
        model.addAttribute("coverletters",coverLetterRepo.findAll());
        return "employerviewcoverletter";
    }

    @GetMapping("/displayresumeemployer")
    public String employerDisplayResume(Model model){
        model.addAttribute("summaries", summaryRepo.findAll());
        model.addAttribute("personals", personalRepo.findAll());
        model.addAttribute("educations", eduRepo.findAll());
        model.addAttribute("skills", skillRepo.findAll());
        model.addAttribute("experiences", experienceRepo.findAll());
        model.addAttribute("references",refRepo.findAll());

        return "employerprintresume";
    }

    @RequestMapping("/register")
    public String showRegistration(Model model){
        model.addAttribute("registration", new User());
        return "registration";

    }

    @RequestMapping("/login")
    public String showLogin(Model model){
        return "login";
    }




}
