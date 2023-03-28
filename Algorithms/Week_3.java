package prac1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

public class Week_3 {
    public HashMap<Integer, Node> map;
    public static class Node{
        int value;
        int key;
        Node prev;
        Node next;
        Node( int key,int value){
            this.key=key;
            this.value=value;
            this.prev=null;
            this.next=null;
        }
    }
    Week_3(){
        map=new HashMap<>();
    }
    public int blastJars(int [] p) {
        int days=0;
        Stack<Node> st = new Stack<>();
        
        for (int i = 0; i < p.length; i++) {
            insert(new Node(i,p[i]));
        }
        
        while(true) {
            Collection<Integer> values = map.keySet();
            ArrayList<Integer> listOfValues = new ArrayList<>(values);
            for (int i = 0; i < map.size()-1; i++) {
                if (map.get(listOfValues.get(i+1)).value< map.get(listOfValues.get(i)).value) {
                    st.push(map.get(listOfValues.get(i)));
                }
            }
            if (st.isEmpty())
                break;
            else {
                days++;
                while (!st.isEmpty()) {
                    remove(st.pop());
                }
            }
            
        }
        
        return days;
    }
    Node head=null;
    Node tail=null;
    
    public void insert(Node newnode) {
        map.put(newnode.key, newnode);
        if(head==null) {
            head=tail=newnode;
        }    
        else {
            tail.next=newnode;
            newnode.prev=tail;
            tail=newnode;
        }
    }
    public void remove(Node node) {
        map.remove(node.key);
        if(node ==tail) {
            node.prev.next=null;
            tail=node.prev;
            node.prev=null;
        }
        else if(node==head && tail==head) {
            head=tail=null;
        }    
        else if(node ==head) {
        	node.next.prev = null;
            head=node.next;
            node.next=null;
        }
        else {
//            System.out.println("removed"+node);
            node.prev.next=node.next;
            node.next.prev=node.prev;
            node.next=null;
            node.prev=null;
        }
    }
    public static void main(String [] args) {
//        System.out.println(new Q3sir().blastJars(new int [] {3,6,2,7,5}));
        System.out.println(new Week_3().blastJars(new int [] {1,7,3,9,2}));
//    	ArrayList<Integer> listOfValues = new ArrayList<>();
//    System.out.println(new Solution().poisonousPlants(new int [] {1,7,3,9,2}));
    }
}