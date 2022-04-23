import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class BSTreeTester {
    BSTree t1;
    BSTree t2;
    BSTree t3;
    Iterator<String> iter1;
    Iterator<String> iter2;
    Iterator<String> iter3;

    @Before
    public void setUp() throws Exception {
        t1=new BSTree();
        t2=new BSTree();
        t1.insert(3);
        t1.insert(1);
        t1.insert(2);
        t3=new BSTree();
        iter1=t1.iterator();
        iter2=t2.iterator();
        iter3=t3.iterator();

    }

    @Test
    public void getRoot() {
        assertEquals(t1.getRoot().getKey(),3);
        assertEquals(t2.getRoot(),null);
        t3.insert(99);
        assertEquals(t3.getRoot().key,99);
    }

    @Test
    public void getSize() {
        assertEquals(t1.getSize(),3);
        assertEquals(t2.getSize(),0);
        t2.insert(1);
        assertEquals(t2.getSize(),1);
    }

    @Test(expected = NullPointerException.class)
    public void insert() {
        t2.insert(null);
    }

    @Test
    public void findKey() {
        assertTrue(t1.findKey(1));
        assertFalse(t2.findKey(3));
        assertEquals(true,t1.findKey(2));

    }

    @Test
    public void insertData() {
        t1.insertData(1,99);
        assertEquals(1,t1.findDataList(1).size());
        t1.insertData(1,99);
        t1.insertData(1,99);
        assertEquals(3,t1.findDataList(1).size());
        t2.insert(5);
        t2.insertData(5,555);
        assertEquals(1,t2.findDataList(5).size());
    }

    @Test(expected = NullPointerException.class)
    public void insertData2(){
        t1.insertData(1,null);
    }

    @Test(expected = NullPointerException.class)
    public void insertData3(){
        t1.insertData(null,555);
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertData4(){
        t1.insertData(55,555);
    }

    @Test(expected = NullPointerException.class)
    public void findDataList() {
        t1.findKey(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findDataList2(){
        t1.findKey(888);
    }

    @Test
    public void findDataList3(){
        t1.insertData(1,99);
        t1.insertData(1,99);
        assertEquals(t1.findDataList(1).size(),2);
        t2.insert(2);
        assertEquals(t2.findDataList(2).size(),0);
        t3.insert(4);
        assertEquals(t3.findDataList(4).size(),0);
    }

    @Test
    public void findHeight() {
        t2.insert(1);
        t2.insert(2);
        t2.insert(3);
        assertEquals(t2.findHeight(),2);
        assertEquals(t1.findHeight(),2);
        assertEquals(t3.findHeight(),-1);
    }

    @Test
    public void leafCount() {
        assertEquals(t1.leafCount(),1);
        assertEquals(t2.leafCount(),0);
        assertEquals(t3.leafCount(),0);
    }

    @Test
    public void hasNext(){
        assertEquals(true, iter1.hasNext());
        assertEquals(false, iter2.hasNext());
        assertEquals(false, iter3.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void Next(){
        iter2.next();
    }

    @Test
    public void Next2(){
        assertEquals(new Integer(1),iter1.next());
        assertEquals(new Integer(2), iter1.next());
        assertEquals(new Integer(3),iter1.next());
    }

    @Test
    public void intersection(){
        t2.insert(1);
        t2.insert(2);
        t2.insert(3);
        iter2=t2.iterator();
        ArrayList<Integer> arr1=new ArrayList<Integer>();
        arr1.add(1);
        arr1.add(2);
        arr1.add(3);
        assertEquals(arr1,t1.intersection(iter1,iter2));

        t1.insert(4);
        t1.insert(5);
        iter1=t1.iterator();
        iter2=t2.iterator();
        assertEquals(arr1,t1.intersection(iter1,iter2));

        ArrayList<Integer> arr2=new ArrayList<Integer>();
        iter1=t1.iterator();
        assertEquals(arr2,t1.intersection(iter1, iter3));
    }

    @Test
    public void levelcount(){
        assertEquals(t2.levelCount(1),-1);
        assertEquals(t1.levelCount(1),1);
        assertEquals(t1.levelCount(2),1);
    }

}