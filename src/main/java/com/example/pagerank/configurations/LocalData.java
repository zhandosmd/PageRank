package com.example.pagerank.configurations;

import com.example.pagerank.models.News;
import com.example.pagerank.models.Post;

import java.util.Arrays;
import java.util.List;

public class LocalData {
    public List<String> types = Arrays.asList("All", "Gadget", "Document", "Clothes", "ID Card", "Credit Card", "Accessories");
    public List<String> typesToAdd = Arrays.asList("Gadget", "Document", "Clothes", "ID Card", "Credit Card", "Accessories", "Other");

    static public  List<Post> LoFoItems = Arrays.asList(
            new Post("IPhone 11", "Red Canteen", "Color: white, with black case", "Gadget"),
            new Post("T-Shirt", "F102", "with Arctic Monkeys pic", "Clothes"),
            new Post("Charger", "Red Canteen", "Acer Nitro 5", "Gadget"),
            new Post("Cup", "F203", "Size normal, color white", "Other"),
            new Post("Glass", "G302", "Dos company, with dark-blue case", "Accessories"));

    static public List<News> NewsItems = Arrays.asList(
            new News("The course \"Summer School 2022: RECHARGE\" will begin", 0, "Malika Azamatkyzy",
                    "The Center for Excellence and Innovation of the Suleyman Demirel University invites you to the 72 and 108-hour advanced training program \"Summer School 2022: RECHARGE\". " +
                        "\nIn the first part, which will take place between 18.07-12.08 in an asynchronous online format, in addition to general pedagogy, special tracks (directions) related to the professional areas of the participants will be presented. " +
                        "The tasks that will be given during the online part can be uploaded to the platform until August 31st. Upon successful completion of this program, the participant will be awarded a 72-hour short-term certificate of excellence."),
            new News("SDU Graduation Ceremony\n", 0, "Rauan Tulekbai", "Suleyman Demirel University holds an Olympiad among school graduates from countries near and far abroad. This year the Olympiad will" +
                        " be held online on July 4-10, registration is open for the following countries:"),
            new News("A meeting was held with representatives of the University of Science and Technology MISiS", 0, "Zhussupali Salamat",
                    "Today, the Suleyman Demirel University was visited by a delegation of the University of Science and Technology MISiS. The bilateral meeting was held with the participation of the managing departments of SDU and MISIS, where issues of " +
                        "IT support and automation, organization of the educational process were discussed.\nThe doors of our university are always open to representatives of domestic and foreign educational institutions and future cooperation.")
    );
}
