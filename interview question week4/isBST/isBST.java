    # only one function in BST
    
    private boolean isBST(Node a, Key min, Key max) {
        if (a == null) return true;
        if (min != null && a.key.compareTo(min) < 0) return false;
        if (max != null && a.key.compareTo(max) > 0) return false;
        return isBST(a.left, min, a.key) && isBST(a.right, a.key, max);
    }
