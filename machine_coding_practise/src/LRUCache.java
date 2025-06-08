import java.util.HashMap;
import java.util.Map;

public interface LRUCache<K,V> {

    public void put(K key, V value);
    public V get(K key);
}

class LRUCacheImpl<K,V> implements LRUCache<K,V> {
    private int capacity;
    private Map<K, Node> map = new HashMap<>();
    private DoublyLinkedList dll = new DoublyLinkedList();

    LRUCacheImpl(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(K key, V val) {
        if (map.containsKey(key)) {
            Node alreadyExistingNode = map.get(key);
            dll.remove(alreadyExistingNode);
            alreadyExistingNode.val = val;
            dll.addAtHead(alreadyExistingNode);
        } else {
            if (map.size() == capacity) {
                Node last = dll.removeLast();
                map.remove(last.key);
            }
            Node n = new Node(val, key);
            map.put(key, n);
            dll.addAtHead(n);
        }
    }

    public synchronized V get(K key) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            dll.remove(n);
            dll.addAtHead(n);
            return n.val;
        }
        return null;
    }


    private class Node {
        V val;
        K key;
        Node prev = null , next = null;
        Node(V val, K key) {
            this.val = val;
            this.key = key;
        }
    }

    private class DoublyLinkedList {
        Node head = null;
        Node tail = null;

        void addAtHead(Node n) {
            if (n == null) return;
            if (head == null) {
                head = n;
                tail = n;
            } else {
                Node prevHead = head;
                head = n;
                head.next = prevHead;
                prevHead.prev = head;
            }
        }

        public void remove(Node n) {
            if (head == null) return;
            if (head == tail && head == n) {
                head = null;
                tail = null;
                return;
            }
            if (head == n) {
                Node prevHead = head;
                Node newHead = prevHead.next;
                prevHead.next = null;
                newHead.prev = null;
                head = newHead;
                return;
            }
            if (tail == n) {
                Node prevTail = tail;
                Node secondLast = tail.prev;
                prevTail.prev = null;
                secondLast.next = null;
                tail = secondLast;
                return;
            }

            Node prevElement = n.prev;
            Node nextElement = n.next;
            n.next = null;
            n.prev = null;
            prevElement.next = nextElement;
            nextElement.prev = prevElement;
        }

        Node removeLast() {
            if (head == tail) {
                Node toReturn = head;
                head = null; tail = null;
                return toReturn;
            } else {
                Node last = tail;
                Node secondLast = tail.prev;
                secondLast.next = null;
                last.prev = null;
                tail = secondLast;
                return last;
            }
        }
    }

}

