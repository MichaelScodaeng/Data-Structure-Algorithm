import java.util.Iterator;
import java.util.Stack;

class BinarySearchTree <T extends Comparable<T>>{
    private int nodeCount = 0;
    private Node root = null;
    private class Node
    {
        T data;
        Node left,right;
        public Node(Node left, Node right, T data)
        {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    public boolean isEmpty()
    {
        return nodeCount==0;
    }
    public int size()
    {
        return nodeCount;
    }
    public boolean add(T elem)
    {
        if(contains(elem))
        {
            return false;
        }
        else
        {
            root = add(root,elem);
            nodeCount++;
            return true;

        }
    }
    public Node add(Node node,T elem)
    {
        if(node == null)
        {
            node = new Node(null,null,elem);
        }
        else
        {
            if(elem.compareTo(node.data)<0)
            {
                node.left = add(node.left,elem);
            }
            else
            {
                node.right = add(node.right,elem);
            }
        }
        return node;
    }
    public boolean remove(T elem)
    {
        if(!contains(elem))
        {
            return false;
        }
        else
        {
            root = remove(root,elem);
            nodeCount--;
            return true;
        }
    }
    public Node remove(Node node,T elem)
    {
        if(node == null)
        {
            return null;
        }
        int cmp = elem.compareTo(node.data);
        if(cmp<0)
        {
            node.left = remove(node.left,elem);
        }
        else if (cmp>0)
        {
            node.right = remove(node.right,elem);
        }
        else
        {
            if(node.left ==null && node.right==null)
            {
                node.data = null;
                node = null;
                return null;

            }
            else if(node.left ==null)
            {
                Node rightChild = node.right;
                node.data = null;
                node = null;
                return rightChild;
            }
            else if(node.right == null)
            {
                Node leftChild = node.left;
                node.data = null;
                node = null;
                return leftChild;
            }
            else
            {

                //find the largest value of left side
                Node temp = digLargest(node.left);
                node.data =temp.data;
                node.left = remove(node.left,temp.data);

                //Otherwise, find the smallest value of right side
                /*
                Node temp = digSmallest(node.right);
                node.data =temp.data;
                node.right = remove(node.right,temp.data);
                */

            }
        }
        return node;
    }
    private Node digSmallest(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // Helper method to find the rightmost node (which has the largest value)
    private Node digLargest(Node node) {
        while (node.right != null) node = node.right;
        return node;
    }
    public boolean contains(T elem) {
        return contains(root, elem);
    }
    public boolean contains(Node node,T elem)
    {
        if(node == null)
        {
            return false;
        }
        else if (elem.compareTo(node.data)<0)
        {
            return contains(node.left,elem);
        }
        else if (elem.compareTo(node.data)>0)
        {
            return contains(node.right,elem);

        }
        else
        {
            return true;
        }
    }
    public int height()
    {
        return height(root);
    }
    public int height(Node node)
    {
        if(node==null)
        {
            return 0;
        }
        else
        {
            return Math.max(height(node.left),height(node.right))+1;
        }
    }
    enum TraversalOrder
    {
        PRE_ORDER,
        IN_ORDER,
        POST_ORDER,
        LEVEL_ORDER
    }
    public java.util.Iterator<T> traverse(TraversalOrder order)
    {
        switch (order){
            case PRE_ORDER: return preOrderTraversal();
            case IN_ORDER: return inOrderTraversal();
            case POST_ORDER: return postOrderTraversal();
            case LEVEL_ORDER: return levelOrderTraversal();
            default: return null;
        }

    }
    // Returns as iterator to traverse the tree pre order
    private java.util.Iterator<T> preOrderTraversal() {

        final int expectedNodeCount = nodeCount;
        final java.util.Stack<Node> stack = new java.util.Stack<>();
        stack.push(root);

        return new java.util.Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
                Node node = stack.pop();
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // Returns as iterator to traverse the tree in order
    private java.util.Iterator<T> inOrderTraversal() {

        final int expectedNodeCount = nodeCount;
        final java.util.Stack<Node> stack = new java.util.Stack<>();
        stack.push(root);

        return new java.util.Iterator<T>() {
            Node trav = root;

            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {

                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();

                // Dig left
                while (trav != null && trav.left != null) {
                    stack.push(trav.left);
                    trav = trav.left;
                }

                Node node = stack.pop();

                // Try moving down right once
                if (node.right != null) {
                    stack.push(node.right);
                    trav = node.right;
                }

                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // Returns as iterator to traverse the tree in post order
    private java.util.Iterator<T> postOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final java.util.Stack<Node> stack1 = new java.util.Stack<>();
        final java.util.Stack<Node> stack2 = new java.util.Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            Node node = stack1.pop();
            if (node != null) {
                stack2.push(node);
                if (node.left != null) stack1.push(node.left);
                if (node.right != null) stack1.push(node.right);
            }
        }
        return new java.util.Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
                return root != null && !stack2.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
                return stack2.pop().data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // Returns as iterator to traverse the tree in level order
    private java.util.Iterator<T> levelOrderTraversal() {

        final int expectedNodeCount = nodeCount;
        final java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.offer(root);

        return new java.util.Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
                return root != null && !queue.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
                Node node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

}