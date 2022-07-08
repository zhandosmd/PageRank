package com.example.pagerank;
import com.example.pagerank.configurations.LocalData;

public class PageRankJava {
    public int path[][] = new int[10][10];
    public double pagerank[] = new double[10];
    public String type;

    public PageRankJava(String type){
        this.type = type;
    }

    public void calc(double totalNodes) {
        double InitialPageRank;
        double OutgoingLinks = 0;
        double DampingFactor = 0.85; // default
        double TempPageRank[] = new double[10];
        int ExternalNodeNumber;
        int InternalNodeNumber;
        int k = 1; // For Traversing
        int ITERATION_STEP = 1;
        InitialPageRank = 1 / totalNodes;
        System.out.printf(" Total Number of Nodes :" + totalNodes + "\t Initial PageRank  of All Nodes :" + InitialPageRank + "\n");

        // initialization //
        for (k = 1; k <= totalNodes; k++) {
            this.pagerank[k] = InitialPageRank;
        }

        System.out.printf("\n Initial PageRank Values , 0th Step \n");
        for (k = 1; k <= totalNodes; k++) {
            System.out.printf(" Page Rank of " + k + " is :\t" + this.pagerank[k] + "\n");
        }

        while (ITERATION_STEP <= 2) // Iterations
        {
            // Store the PageRank for All Nodes in Temporary Array
            for (k = 1; k <= totalNodes; k++) {
                TempPageRank[k] = this.pagerank[k];
                this.pagerank[k] = 0;
            }

            for (InternalNodeNumber = 1; InternalNodeNumber <= totalNodes; InternalNodeNumber++) {
                for (ExternalNodeNumber = 1; ExternalNodeNumber <= totalNodes; ExternalNodeNumber++) {
                    if (this.path[ExternalNodeNumber][InternalNodeNumber] == 1) { // is there is link between pages
                        k = 1;
                        OutgoingLinks = 0; // Count the Number of Outgoing Links for each ExternalNodeNumber
                        while (k <= totalNodes) {
                            if (this.path[ExternalNodeNumber][k] == 1) {
                                OutgoingLinks = OutgoingLinks + 1; // Counter for Outgoing Links
                            }
                            k = k + 1;
                        }
                        // Calculate PageRank
                        this.pagerank[InternalNodeNumber] += TempPageRank[ExternalNodeNumber] * (1 / OutgoingLinks);
                    }
                }
            }

            System.out.printf("\n After " + ITERATION_STEP + "th Step \n");

            for (k = 1; k <= totalNodes; k++)
                System.out.printf(" Page Rank of " + k + " is :\t" + this.pagerank[k] + "\n");

            ITERATION_STEP = ITERATION_STEP + 1;
        }

        // Add the Damping Factor to PageRank
        for (k = 1; k <= totalNodes; k++) {
            this.pagerank[k] = (1 - DampingFactor) + DampingFactor * this.pagerank[k];
        }

        // Display PageRank
        System.out.printf("\n Final Page Rank : \n");
        if(type.equals("lofo")){
            for (k = 1; k <= totalNodes; k++) {
                System.out.printf(" Page Rank of " + LocalData.LoFoItems.get(k-1).getTitle() + " is :\t" + this.pagerank[k] + "\n");
                LocalData.LoFoItems.get(k-1).setRank(this.pagerank[k]);
            }
        } else if(type.equals("news")){
            for (k = 1; k <= totalNodes; k++) {
                System.out.printf(" Page Rank of " + LocalData.NewsItems.get(k-1).getTitle() + " is :\t" + this.pagerank[k] + "\n");
                LocalData.NewsItems.get(k-1).setRank(this.pagerank[k]);
            }
        }
    }
}
