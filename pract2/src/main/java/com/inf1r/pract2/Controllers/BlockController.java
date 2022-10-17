package com.inf1r.pract2.Controllers;

import com.inf1r.pract2.Models.Post;
import com.inf1r.pract2.Models.Prepod;
import com.inf1r.pract2.Models.Student;
import com.inf1r.pract2.repo.PostRepos;
import com.inf1r.pract2.repo.PrepodRepos;
import com.inf1r.pract2.repo.StudentRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlockController {
    @Autowired
    private PostRepos postRepos;

    @Autowired
    private PrepodRepos prepodRepos;

    @Autowired
    private StudentRepos studentRepos;

    @GetMapping("/")
    public String Main(Model model){
        return "Home";
    }

    @GetMapping("/blog")
    public String GetBlog(Model model){
        Iterable<Post> posts = postRepos.findAll();
        model.addAttribute("posts", posts);
        return "block-main";

    }
    @GetMapping("/students")
    public String GetStudent(Model model){
        Iterable<Student> students = studentRepos.findAll();
        model.addAttribute("students",students);
        return "student-main";
    }
    @GetMapping("/prepods")
    public String GetPrepod(Model model){
        Iterable<Prepod> prepods = prepodRepos.findAll();
        model.addAttribute("prepods",prepods);
        return "prepod-main";
    }
    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        return "block-add";
    }
    @GetMapping("/students/add")
    public String studentAdd(Model model){
        return "student-add";
    }
    @GetMapping("/prepods/add")
    public String prepodAdd(Model model){
        return "prepod-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam(value = "title") String title,
                              @RequestParam(value = "anons") String anons,
                              @RequestParam(value = "full_text") String full_text, Model model){
        Post post = new Post(title,anons,full_text);
        postRepos.save(post);
        return "redirect:/blog";
    }
    @PostMapping("/students/add")
    public String studentAdd(@RequestParam(value = "Surname") String Surname,
                             @RequestParam(value = "Name") String Name,
                             @RequestParam(value = "MiddleName") String MiddleName,
                             @RequestParam(value = "Group") String Groups,
                             @RequestParam(value = "Birthday") String Birthday, Model model){
        Student student = new Student(Surname,Name,MiddleName,Groups,Birthday);
        studentRepos.save(student);
        return "redirect:/students";
    }
    @PostMapping("/prepods/add")
    public String prepodAdd(@RequestParam(value = "Surname") String Surname,
                            @RequestParam(value = "Name") String Name,
                            @RequestParam(value = "MiddleName") String MiddleName,
                            @RequestParam(value = "Predmets") String Items,
                            @RequestParam(value = "Grafic") String Chart, Model model){
        Prepod prepod = new Prepod(Surname,Name,MiddleName,Items,Chart);
        prepodRepos.save(prepod);
        return "redirect:/prepods";
    }
    @GetMapping("/blog/filter")
    public String blogFilter(Model model){
        return "block-filter";
    }
    @GetMapping("/students/filter")
    public String studentFilter(Model model){
        return "student-filter";
    }
    @GetMapping("/prepods/filter")
    public String prepodFilter(Model model){
        return "prepod-filter";
    }

    @PostMapping("/blog/filter/result")
    public String blogResult(@RequestParam String title, Model model){
        List<Post> result = postRepos.findByTitleContains(title);
        model.addAttribute("result", result);
        return "block-filter";
    }
    @PostMapping("/students/filter/result")
    public String studentResult(@RequestParam String surname, Model model){
        List<Student> result = studentRepos.findBySurnameContains(surname);
        model.addAttribute("result", result);
        return "student-filter";
    }
    @PostMapping("/prepods/filter/result")
    public String prepodResult(@RequestParam String surname, Model model){
        List<Prepod> result = prepodRepos.findBySurname(surname);
        model.addAttribute("result", result);
        return "prepod-filter";
    }

}
