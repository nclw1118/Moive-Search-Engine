
/*
 * Name: Xue Wang
 * PID:  A15908778
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Search Engine implementation.
 *
 * @author Xue Wang
 * @since  05/11/2020
 */

public class SearchEngine {
    private static int MAX=5;
    private static final int BEGINNING=2;

    /**
     * Populate BSTrees from a file
     *
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @returns false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName) {
        //open the file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                for (int i = 0; i < MAX; i++) {
                    // read lines and store them according to descriptions in the write-up
                    String movie = scanner.nextLine().toLowerCase();
                    String[] actors = scanner.nextLine().toLowerCase().split(" ");
                    String[] producers = scanner.nextLine().toLowerCase().split(" ");
                    String rating = scanner.nextLine();
                    scanner.nextLine();
                    // populate three trees with the information you just read
                    for (String actor : actors) {
                        if (!movieTree.findKey(actor)) {
                            movieTree.insert(actor);
                            movieTree.insertData(actor, movie);
                        }
                        else {
                            movieTree.insertData(actor, movie);
                        }
                        if (!ratingTree.findKey(actor.toLowerCase())) {
                            ratingTree.insert(actor.toLowerCase());
                            ratingTree.insertData(actor.toLowerCase(), rating);
                        }
                        else {
                            if (!ratingTree.findDataList(actor.toLowerCase()).contains(rating)) {
                                ratingTree.insertData(actor.toLowerCase(), rating);
                            }
                        }
                    }
                    for(String producer: producers){
                        if(!studioTree.findKey(producer)){
                            studioTree.insert(producer);
                            studioTree.insertData(producer,movie);
                        }
                        else{
                            studioTree.insertData(producer,movie);
                        }
                    }
                }
            }
            scanner.close();
        }
        // catch exception if the file cannot be opned
        catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * Search a query in a BST
     *
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {

        // search and output individual results
        // process query
        String[] objects=query.toLowerCase().split(" ");

        // dealing with the simple case: there is only one object in query
        if(objects.length==1){
            if(searchTree.findKey(objects[0])==false){
                print(objects[0],null);
            }
            else {
                LinkedList<String> data=searchTree.findDataList(objects[0]);
                print(objects[0],data);
            }
        }
        else{
            // determine if there's someone in the query
            // cannot be found in the database
            boolean notFound=false;
            for(String o: objects){
                if(searchTree.findKey(o)==false){
                    print(query,null);
                    notFound=true;
                    break;
                }
            }
            // if everyone can be found
            if(notFound==false){
                // search and output intersection results
                LinkedList<String> data=new LinkedList<String>();
                for(String movie:searchTree.findDataList(objects[0])){
                    boolean check=true;
                    for(int i=0; i<objects.length;i++){
                        if(!searchTree.findDataList(objects[i]).contains(movie)){
                            check=false;
                        }
                    }
                    if(check==true){
                        data.add(movie);
                    }
                }
                // print the intersection of the query
                print(query,data);
                //print the individual information
                for (int j = 0; j < objects.length; j++) {
                    LinkedList<String> newData=new LinkedList<String>();
                    for(String movie:searchTree.findDataList(objects[j])){
                        if(!data.contains(movie)){
                            newData.add(movie);
                        }
                    }
                    // if the information has already appeared in former search,
                    // then delete it from this person's data
                    for(int i = 0;i<newData.size();i++){
                        String item = newData.get(i);
                        for(int k=0; k<j;k++){
                            if(searchTree.findDataList(objects[k]).contains(item)){
                                newData.remove(item);
                            }
                        }
                    }
                    // if the person has info to be print, then print it
                    if(newData.size()!=0){
                        print(objects[j],newData);
                    }
                }
            }
            // if there's someone in the query cannot be found
            else{
                for(int k=0; k<objects.length;k++){
                    //print the information that can be found
                    if(searchTree.findKey(objects[k])){
                        print(objects[k], (searchTree.findDataList(objects[k])));
                    }
                    else{
                        //print the cannot-be-found if that one cannot be found
                        print(objects[k],null);
                    }
                }
            }
        }
    }

    /**
     * Print output of query
     *
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // initialize search trees
        BSTree<String> movieTree= new BSTree<String>();
        BSTree<String> studioTree= new BSTree<String>();
        BSTree<String> ratingTree= new BSTree<String>();
        // process command line arguments

        String file=args[0];
        String actors= "";
        for(int i=BEGINNING; i<args.length; i++){
            actors+=args[i]+ " ";
        }
        actors=actors.substring(0,actors.length()-1);
        // populate search trees
        populateSearchTrees(movieTree,studioTree,ratingTree,file);
        // choose the right tree to query
        if(args[1].equals("0")){
            searchMyQuery(movieTree,actors);
        }
        else if(args[1].equals("1")){
            searchMyQuery(studioTree,actors);
        }
        else if(args[1].equals("2")) {
            searchMyQuery(ratingTree, actors);
        }
    }
}
