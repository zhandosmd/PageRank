package com.example.pagerank.controllers;

import com.example.pagerank.configurations.LocalData;
import com.example.pagerank.models.Post;
import com.example.pagerank.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String home(Model model){
        return "main2";
    }

    @GetMapping("/opinions")
    public String opinions(Model model) {
        return "opinions2";
    }

    @GetMapping("/analytics")
    public String analytics(Model model) {
        return "analytics2";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Страница про нас");
        return "about";
    }
}
