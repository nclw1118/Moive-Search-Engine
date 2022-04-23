
/*
 * Name: Xue Wang
 * PID:  A15908778
 */
import java.util.*;

/**
 * Binary search tree implementation.
 *
 * @author Xue Wang
 * @since  5/07/2020
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node
    private static final int SECOND_CASE=2;

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            //initialize the variables
            this.key = key;
            this.dataList = dataList;
            this.left = left;
            this.right = right;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            //initialize the variables
            this.key = key;
            this.left = left;
            this.right = right;
            this.dataList = new LinkedList<T>();
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            //return the key
            return this.key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            //return the left child
            return this.left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            //re turn the right child
            return this.right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            //return the datalist
            return this.dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setleft(BSTNode newleft) {
            // set the left with the input
            this.left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setright(BSTNode newright) {
            //set the right with the input
            this.right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            // set the datalist with the new data
            this.dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            // add the given info to the datalist
            this.dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            //if the data is not in the datalist, return false
            if (dataList.contains(data) == false) {
                return false;
            } else {
                // remove the data, return true
                dataList.remove(data);
                return true;
            }
        }
    }
    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        // initiate the variables
        root=null;
        nelems=0;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        //return the root
        return this.root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        // return the size of the tree
        return this.nelems;
    }



    /**
     * Insert a key into BST
     *
     * @param key
     * @return true if insertion is successful and false otherwise
     */
    public boolean insert(T key) {
        // input validation check
        if (key == null){
            throw new NullPointerException();
        }
        BSTNode toAdd = new BSTNode(null, null, key);
        // if the key already exists, return false
        if (this.findKey(key)){
            return false;
        }
        // if the tree is empty, add the element to the root
        if (root == null){
            this.root = toAdd;
        }
        else{
            BSTNode cur = this.root;
            while (cur != null){
                int num=toAdd.key.compareTo(cur.key);
                //if the key is larger to the node to add
                if (num == -1){
                    // if the current's left is empty, insert it to the left of cur node
                    if (cur.getLeft() == null){
                        cur.setleft(toAdd);
                        break;
                    }
                    else{
                        // if the left is not empty, cur= cur's left
                        cur = cur.getLeft();
                    }
                }
                else{
                    //if the key is smaller to the node to add
                    // if the current's right is empty, insert it to the right of cur node
                    if (cur.getRight() == null){
                        cur.setright(toAdd);
                        break;
                    }
                    else{
                        // if the right is not empty, cur= cur's right
                        cur = cur.getRight();
                    }
                }
            }
        }
        // increase the size of tree by 1, return true
        nelems += 1;
        return true;
    }

    /**
     * the helper method of the find method
     * @param key the key of the node to be find
     * @return the node found
     */
    private BSTNode findHelper( T key){
        // it does the same thing as find method,
        // just return the found node instead of true/false
        // which can be used in method after
        BSTNode cur =root;
        while (cur != null) {
            if (key.compareTo(cur.getKey())==0) {
                return cur;
            } else if (key.compareTo(cur.getKey()) == -1) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return  null;
    }
    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        // input validation check
        if(key==null){
            throw new NullPointerException();
        }
        BSTNode cur =this.root;
        while (cur!= null) {
            // if the cur equals key to found, it is found, return true
            if (key.compareTo(cur.key)==0) {
                return true;
            }
            // if cur is larger than the key, go to the left
            else if (key.compareTo(cur.key) == -1) {
                cur = cur.left;
            }
            //if cur is smaller than the key, go to the right
            else {
                cur = cur.right;
            }
        }
        //if the key cannot be found, return false
        return false;
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If either key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        //input validation check
        if(key==null||data==null){
            throw new NullPointerException();
        }
        if(findKey(key)==false){
            System.out.println("illgal inserData");
            throw new IllegalArgumentException();
        }
        // insert data to the datalist of the given key
        BSTNode target=findHelper(key);
        target.getDataList().add(data);

    }

    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        //input validation check
        if(key==null){
            throw new NullPointerException();
        }
        if(findKey(key)==false){
            System.out.println("illegal findDataList");

            throw new IllegalArgumentException();
        }
        //return the datalist of the given key
        BSTNode target = findHelper( key);
        return target.getDataList();
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        // special cases when the size of the tree is equals to 1,2, or 3
        if(nelems==0){
            return -1;
        }
        if(nelems==1){
            return 0;
        }
        if(nelems==SECOND_CASE){
            return 1;
        }
        else{
            //call the helper method for findHeight
            return findHeightHelper(root);
        }
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        //initiate the variables
        int sum = 0;
        int left = 0;
        int right = 0;
        int max = 0;
        // if we can still go more left, return 1 and go left
        if (root.getLeft() != null) {
            left = 1+findHeightHelper(root.left);
        }
        // if we can still go more right, return 1 and go right
        if (root.getRight() != null) {
            right = 1+findHeightHelper(root.right);
        }
        // choose the larger one between the left and right and return
        if (left > right) {
            return left;
        } else {
            return right;

        }
    }

    /**
     * Return the number of leaf nodes in the tree
     *
     * @return The number of leaf nodes in the tree
     */
    public int leafCount() {
        //call the helper method
        return leafCountHelper(root);
    }

    /**
     * Helper for the leafCount method
     *
     * @param root Root node
     * @return The number of leaf nodes in the tree
     */
    private int leafCountHelper(BSTNode root) {
        //special case when the tree is empty, there is no leaf
        if(root==null){
            return  0;
        }
        //when one of the leaf of the root is null, there is only one leaf
        if(root.left==null&&root.right==null){
            return 1;
        }
        // go farther left and right to count the number of leaves
        return leafCountHelper(root.left)+leafCountHelper(root.right);
    }

    /* * * * * BST Iterator * * * * */

    /**
     * an Iterator for BST
     *
     * @author Xue Wang
     * @since 05/11/2020
     */
    public class BSTree_Iterator implements Iterator<T> {
        // instance variable stack
        private Stack<BSTNode> stack;

        /**
         * constructor of the iterator
         */
        public BSTree_Iterator() {
            //initiate the instance variable
            stack=new Stack();
            BSTNode cur=root;
            //push the left-most elements to the stack
            while(cur!=null){
                stack.push(cur);
                cur=cur.getLeft();
            }
        }

        /**
         * Returns false if the Stack is empty, true otherwise
         * @returnfalse if the Stack is empty, true otherwise
         */
        public boolean hasNext() {
            //Returns false if the Stack is empty
            if(stack.isEmpty()){
                return false;
            }
            else{
                //otherwise, return true
                return true;
            }
        }

        /**
         * Returns the next item in the BST
         * @return the next item in the BST
         * @throws NoSuchElementException if there is no next item
         */
        public T next() {
            //throws NoSuchElementException if there is no next item
            if(this.hasNext()==false){
                System.out.println("illegal no such");

                throw new NoSuchElementException();
            }
            // use the stack to implement the next method
            BSTNode cur= stack.pop();
            T out= cur.key;
            if(cur.right !=null){
                cur=cur.right;
                while (cur!=null){
                    stack.push(cur);
                    cur=cur.left;
                }
            }
            // return the next node
            return out;
        }

    }

    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }

    /* * * * * Extra Credit Methods * * * * */

    /**
     * returns the intersection of two BSTrees
     * @param iter1 the first BSTree iterator
     * @param iter2 the second BSTree iterator
     * @return the intersection of two BSTrees
     */
    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        // initiate arrays to store data in the trees and the array to return
        ArrayList<T> arr1=new ArrayList<T>();
        ArrayList<T> arr2=new ArrayList<T>();
        ArrayList<T> out= new ArrayList<T>();
        //add two trees' data into the arrays
        while(iter1.hasNext()){
            arr1.add(iter1.next());
        }
        while(iter2.hasNext()){
            arr2.add(iter2.next());
        }
        //find intersection of the two arrays and add the overlapped element to the output
        for(int i=0;i<arr1.size();i++){
            if(arr2.contains(arr1.get(i))){
                out.add(arr1.get(i));
            }
        }
        //return the arrayList that stores the intersection
        return out;
    }

    /**
     * counts the number of nodes at a given level
     * @param level the given level at which the number of nodes to be counted
     * @return the number of nodes at a given level
     */
    public int levelCount(int level) {
        // call the helper method with cur=root, curLevel=0
        return countHelper(root,0, level);
    }
    private int countHelper(BSTNode cur, int curLevel, int level){
        // if the given level exceeds the total level of the tree,
        // return -1
        if(cur==null){
            return -1;
        }
        // if the curLevel equals to the object level, return 1
        if (curLevel == level){
            return 1;
        }
        // count the number of nodes at left and right recursively and add them together
        int left = 0;
        int right = 0;
        if (cur.left != null){
            left = countHelper(cur.left, curLevel+1, level);
        }
        if (cur.right != null){
            right = countHelper(cur.right, curLevel+1, level);
        }
        return left + right;
    }

}