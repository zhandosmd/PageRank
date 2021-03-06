package com.example.pagerank.controllers;

import com.example.pagerank.configurations.FileUploadUtil;
import com.example.pagerank.configurations.LocalData;
import com.example.pagerank.models.Post;
import com.example.pagerank.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.*;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(path = {"/allnews", "/search"})
    public String allnews(Model model,
              @RequestParam(required = false, defaultValue = "All", value="type") String type,
              @RequestParam(required = false, defaultValue = "All", value="keyword") String keyword){
        List<Post> posts = new ArrayList<>();
        if(!keyword.equals("All")){
            for (Post lofo: LocalData.LoFoItems){
                if(lofo.getTitle().toLowerCase().contains(keyword.toLowerCase())){
                    posts.add(lofo);
                }
            }
        }else{
            posts.addAll(LocalData.LoFoItems);
        }
        posts.sort(Comparator.comparing(Post::getRank).reversed());
        model.addAttribute("posts", posts);
        if(!type.equals("All")) {
            List<Post> foundPosts = new ArrayList<Post>();
            for(Post post: posts){
                if(post.getType().equals(type)){
                    foundPosts.add(post);
                }
            }
            Iterable<Post> iterable = foundPosts;
            model.addAttribute("posts", iterable);
        }
        List<String> types = new LocalData().types;
        model.addAttribute("types", types);
        model.addAttribute("selectedType", type);
        return "allnews2";
    }

    @GetMapping("/allnews/{query}")
    public String allnewsquery(Model model, @PathVariable(value = "query") String query){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);

        List<String> types = new LocalData().types;
        model.addAttribute("types", types);
        model.addAttribute("selectedType","All");
        return "allnews2";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        List<String> types = new LocalData().typesToAdd;
        model.addAttribute("types", types);
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public RedirectView saveUser(
            @RequestParam String title,
            @RequestParam String anons,
            @RequestParam String full_text,
            @RequestParam String type,
            @RequestParam("image") MultipartFile multipartFile
    ) throws IOException {
        Post post = new Post(title, anons, full_text, type);

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        post.setPhotos(fileName);

        Post savedPost = postRepository.save(post);
        String uploadDir = "user-photos/" + savedPost.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return new RedirectView("/allnews", true);
    }


    @GetMapping("/blog/{id}")
    public String blogByID(@PathVariable(value = "id") long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(
        @PathVariable(value = "id") long id,
        @RequestParam String title,
        @RequestParam String place,
        @RequestParam String description,
        @RequestParam String type,
        Model model)
    {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setPlace(place);
        post.setDescription(description);
        post.setType(type);
        postRepository.save(post);

        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/comment")
    public String blogPostComment(
            @PathVariable(value = "id") long id,
            @RequestParam String comment,
            Model model)
    {
        Post post = postRepository.findById(id).orElseThrow();
        List<String> comments = post.getComments();
        comments.add(comment);
        post.setComments(comments);
        postRepository.save(post);

        return "redirect:/blog/{id}";
    }

    @PostMapping("/blog/{id}/{comment}/delete")
    public String blogPostCommentDelete(
            @PathVariable(value = "id") long id,
            @RequestParam String comment,
            Model model)
    {
        Post post = postRepository.findById(id).orElseThrow();
        List<String> comments = post.getComments();
        System.out.println(comment);
        System.out.println(comments);
        comments.remove(comment);
        post.setComments(comments);
        postRepository.save(post);

        return "redirect:/blog/{id}";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/blog";
    }
}
