package com.example.pagerank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class PageRankApplication {

    public static void main(String[] args) {
        SpringApplication.run(PageRankApplication.class, args);

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the Number of LoFo items \n");
        int numberOfLoFo= in.nextInt();
        PageRankJava p = new PageRankJava("lofo");
        System.out.println("Enter the Adjacency Matrix with 1->PATH & 0->NO PATH Between two LoFo items: \n");
        for (int i = 1; i <= numberOfLoFo; i++)
            for (int j = 1; j <= numberOfLoFo; j++) {
                p.path[i][j] = in .nextInt();
                if (j == i)
                    p.path[i][j] = 0;
            }
        p.calc(numberOfLoFo);


        System.out.println("Enter the Number of News items \n");
        int numberOfNews = in.nextInt();
        PageRankJava p2 = new PageRankJava("news");
        System.out.println("Enter the Adjacency Matrix with 1->PATH & 0->NO PATH Between two News items: \n");
        for (int i = 1; i <= numberOfNews; i++)
            for (int j = 1; j <= numberOfNews; j++) {
                p.path[i][j] = in .nextInt();
                if (j == i)
                    p.path[i][j] = 0;
            }
        p.calc(numberOfNews);
    }

}
