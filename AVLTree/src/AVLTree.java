import java.util.Iterator;

public class AVLTree <T extends Comparable<T>> implements Iterable<T> {

    class Node implements TreePrinter.PrintableNode
    {
        int bf; //balancing factor height(node.right)-height(node.left). Used in BST
        T value; //actual value
        int height; //actual height
        Node left,right;
        public Node(T value)
        {
            this.value = value;
        }
        @Override
        public Node getLeft() {
            return left;
        }
        @Override
        public Node getRight()
        {
            return right;
        }

        @Override
        public String getText() {
            return String.valueOf(value);
        }
    }

    Node root;
    private int nodeCount =0;
    public int height()
    {
        if(root==null) return 0;
        else
        {
            return root.height;
        }
    }
    public int size()
    {
        return nodeCount;
    }
    public boolean isEmpty()
    {
        return nodeCount==0;
    }
    public boolean contains(T value)
    {
        return contains(root,value);
    }
    public String toString() {
        return TreePrinter.getTreeDisplay(root);
    }
    public boolean contains(Node root,T value)
    {
        if(root==null) return false;
        int cmp = value.compareTo(root.value);
        if(cmp==0)
        {
            return true;
        }
        else if (cmp<0)
        {
            return contains(root.left,value);
        }
        else
        {
            return contains(root.right,value);
        }
    }
    public boolean insert(T value)
    {
        if(value==null) return false;
        if(!contains(value))
        {
            root = (insert(root,value));
            return true;
        }
        else
        {
            return false;
        }
    }
    private Node insert(Node node,T value)
    {
        if(node==null) return new Node(value);
        int cmp = value.compareTo(node.value);
        if(cmp<0)
        {
            node.left = insert(node.left,value);
        }
        else
        {
            node.right = insert(node.right,value);
        }
        update(node);
        return balance(node);
    }
    private void update(Node node)
    {
        int leftNodeHeight = node.left==null?-1:node.left.height;
        int rightNodeHeight = node.right==null?-1:node.right.height;
        node.height = 1+Math.max(leftNodeHeight,rightNodeHeight);
        node.bf =rightNodeHeight-leftNodeHeight;
    }
    private Node balance(Node node)
    {
        if(node.bf==-2)
        {
            if(node.left.bf<=0)
            {
                return leftLeftRebalance(node);
            }
            else
            {
                return leftRightRebalance(node);
            }
        }
        else if(node.bf ==2)
        {
            if(node.right.bf>=0)
            {
                return rightRightRebalance(node);
            }
            else
            {
                return rightLeftRebalance(node);
            }
        }
        else
        {
            return node;
        }
    }



    private Node rightRightRebalance(Node node) {
        return leftRotation(node);
    }
    private Node rightLeftRebalance(Node node) {
        node.right = rightRotation(node.right);
        return rightRightRebalance(node);
    }



    private Node leftRightRebalance(Node node) {
       node.left = leftRotation(node.left);
       return leftLeftRebalance(node);
    }



    private Node leftLeftRebalance(Node node) {
        return rightRotation(node);
    }

    private Node leftRotation(Node node)
    {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        update(node);
        update(newParent);
        return newParent;

    }
    private Node rightRotation(Node node)
    {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        update(node);
        update(newParent);
        return newParent;

    }
    public boolean remove(T value)
    {
        if(value==null) return false;
        if(contains(root,value))
        {
            root = remove(root,value);
            return true;
        }
        else
        {
            return false;
        }
    }
    private Node remove(Node node,T value)
    {
        if(node==null) return null;
        int cmp = value.compareTo(node.value);
        if(cmp<0)
        {
            node.left = remove(node.left,value);
        }
        else if(cmp>0)
        {
            node.right = remove(node.right,value);
        }
        else
        {
            if(node.left==null)
            {
                Node rightNode = node.right;
                node.value = null;
                node = null;
                return rightNode;
            }
            else if(node.right==null)
            {
                Node leftNode = node.left;
                node.value = null;
                node = null;
                return leftNode;
            }
            else if (node.left!=null && node.right!=null)
            {
                if(node.left.height>node.right.height)
                {
                    T replaceValue = findMax(node.left);
                    node.value = replaceValue;
                    node.left = remove(node.left,replaceValue);
                }
                else
                {
                    T replaceValue = findMin(node.right);
                    node.value = replaceValue;
                    node.right = remove(node.right,replaceValue);
                }
            }
        }
        update(node);
        return balance(node);
    }

    private T findMin(Node node) {
        while(node.left!=null)
        {
            node = node.left;
        }
        return node.value;
    }

    private T findMax(Node node) {
        while(node.right!=null)
        {
            node = node.right;
        }
        return node.value;
    }
    public java.util.Iterator<T> iterator() {

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

                while (trav != null && trav.left != null) {
                    stack.push(trav.left);
                    trav = trav.left;
                }

                Node node = stack.pop();

                if (node.right != null) {
                    stack.push(node.right);
                    trav = node.right;
                }

                return node.value;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }



}