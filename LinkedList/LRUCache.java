class LRUCache {
    class Node{
        int key, value;
        Node prev, next;
        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    private final Map<Integer, Node> cache = new HashMap<>();
    private final int cacheCapacity;
   //sentinel nodes head and tail
    private final Node head = new Node(0, 0);
    private final Node tail = new Node(0, 0);
    public LRUCache(int capacity) {
        cacheCapacity = capacity;
        head.next = tail;
        tail.prev = head;
    }
    public void insertAtfront(Node node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    public int get(int key) {
        //if key doesnt exist, return -1
        if(!cache.containsKey(key))
            return -1;
        //insert node at front as it is most recently used
        Node node = cache.get(key);
        removeNode(node);
        insertAtfront(node);
        return node.value;
    }

    public void put(int key, int value) {
        //if already exists, remove
        if(cache.containsKey(key)){
            Node oldNode = cache.get(key);
            removeNode(oldNode);
            cache.remove(key);
        }
        //insert new node at front and put in cache
        Node node = new Node(key, value);
        cache.put(key, node);
        insertAtfront(node);
        //if cache capacity exceeds, evict last node
        if(cache.size() > cacheCapacity){
            Node lastNode = tail.prev;
            cache.remove(lastNode.key);
            removeNode(lastNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */