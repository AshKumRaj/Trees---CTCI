/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

/**
 *
 * @author Ashish
 */
public class Trees {
     static class Node{
        int data;
        Node left, right;
        Node(int item){
            data = item;
            left=right=null;
        }
    }
  static   class BinaryTree extends Trees{

        private static Node leftMostOfRight(Node inp) {
            if(inp.left == null)
                return inp;
            return leftMostOfRight(inp.left);
        }

        
        ArrayList al = new ArrayList();
        static void validateBST(Node root, ArrayList al) {
            if(root != null){
                validateBST(root.left, al);
                al.add(root.data);
                validateBST(root.right, al);
            }
        }
        Node root;  
        int alk[] = new int[30];
        
    
     static void createMinimalBST(int[] a){
        Node root = createMiniBST(a, 0, a.length-1);
        //System.out.println("The tree in level order traversal is:");
        //printTree(root);
        //printLevelorder(root);
        //printNodesAtD(root);
        System.out.println(checkBalanced(root));
        //System.out.println("The height of the tree is = "+getHeight(root));
    }
     void printNodesAtD(Node root){
         ArrayList<LinkedList<Node>> lists = new ArrayList<LinkedList<Node>>();
         LinkedList<Node> current = new LinkedList<Node>();
         if(root != null)
            current.add(root);
         while(current.size()>0){
             lists.add(current);
             LinkedList<Node> parents = current;
             current = new LinkedList<Node>();
             for(Node parent : parents){
                 if(parent.left != null)
                    current.add(parent.left);
                 if(parent.right != null)
                    current.add(parent.right);
             }
             
         }
         for(LinkedList l : lists){
             Iterator it = l.listIterator();
             while(it.hasNext()){
                 Node tree = (Node) it.next();
                 System.out.print(tree.data+"-->");
                 //it.next();
             }
             System.out.println();
                 
         }
     }
     static int getHeight(Node root){
         if(root == null)
             return 0;
         return Math.max(getHeight(root.left)+1, getHeight(root.right)+1);
     }
     static boolean checkBalanced(Node root){
         if(root == null)
             return true;
         int heightDiff = getHeight(root.left) - getHeight(root.right);
         if(heightDiff <= 1)
             return true;
//         else
//         {
//             if(!checkBalanced(root.left) || !checkBalanced(root.right))
//                 return true;
//         }
         return false;
     }
     //iterative version of level order traversal
     void printLevelorder(Node root){
         Queue<Node> q = new LinkedList<Node>();
         q.add(root);
         while(!(q.isEmpty())){
             Node temp = q.poll();
             System.out.println(temp.data);
             if(temp.left!=null)
                 q.add(temp.left);
             if(temp.right!=null)
                 q.add(temp.right);
         }
     }
     void printTree(Node root){
         if(root!=null){             
             //change these orders to get in-order, pre-order and post-order 
             //traversals of the tree
             printTree(root.left);
             printTree(root.right);
             System.out.println(root.data);
         }
         
     }
     static Node findSuccessor(Node inp, Node parent){
         if(inp.right != null)
             return leftMostOfRight(inp.right);
         if(parent.left == inp)
             return parent;
         
         return null;
     }
     static Node createMiniBST(int[] arr, int start, int end){
        if(start > end)
            return null;
        int mid = (start+end)/2;
        Node root = new Node(arr[mid]);
        //System.out.println(root.data);
        root.left = createMiniBST(arr, start, mid-1);
        root.right = createMiniBST(arr, mid+1, end);
        return root;
    }
     private static Node getAncestorUtil(Node root, Node x, Node y) {
         if(root == x || root == y)
             return root;
         boolean xOnLeft = presentInTree(root.left, x);
            boolean yOnLeft = presentInTree(root.left, y);
            Node n;
            if(xOnLeft && yOnLeft){
                n = root.left;
                return getAncestorUtil(n, x, y);
            }
            if(!xOnLeft && !yOnLeft){
                n = root.right;
                return getAncestorUtil(n, x, y);
            }
                
            return root;
        }
     static Node findAncestor(Node root, Node x, Node y){
         if(x == null || y == null || root == null)
            return null;
         if(!presentInTree(root, x) || !presentInTree(root, y))
             return null;
         return getAncestorUtil(root, x, y);
         
     }
     static boolean presentInTree(Node root, Node p){
         if(root == null)
             return false;
         if(root == p)
             return true;
         return (presentInTree(root.left, p) || presentInTree(root.right, p));
         
     }
     
    
     }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //BinaryTree b = new BinaryTree();
        BinaryTree b = new BinaryTree();
        //Trees tr = new BinaryTree();
        
        int[] a = {1,2,3,4,5,6,7};
        //BinaryTree.createMinimalBST(a);
        b.alk[0] = 4;
        b.root = new Node(4);
        b.root.left = new Node(2);
        b.root.right = new Node(6);
        b.root.left.left = new Node(1);
        b.root.left.right = new Node(3);
        b.root.right.left = new Node(5);
        b.root.right.right = new Node(7);
        b.root.left.left.left = new Node(8);
        b.root.left.left.right = new Node(9);
        b.root.left.right.left = new Node(10);
        b.root.left.right.right = new Node(11);
        Node c = b.root.right.left;
        Node d = b.root.left.left.right;
//        Node c = b.root;
//        Node d = b.root.right.right;
        //Node c = new Node(1);
        System.out.println(BinaryTree.findAncestor(b.root, c, d).data);
        //Node inp = b.root.left.right;
        //Node parent = b.root.left;
        //Node succ = BinaryTree.findSuccessor(inp, parent);
        //System.out.println(succ.data);        
//        BinaryTree.validateBST((b.root), b.al);
//        Object[] arr = b.al.toArray();
//        boolean valid = true;
//        for(int i =1; i<arr.length;i++){
//            if(Integer.valueOf(arr[i-1].toString()) > Integer.valueOf(arr[i].toString())){
//                valid = false;
//                break;
//            }
//                
//        }
//        System.out.println(valid);
    }
    
}
