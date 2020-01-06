package leetcode;

import java.util.HashMap;

class LRUNode {
    String key;
    Object value;
    LRUNode prev;
    LRUNode next;
    public LRUNode(String key, Object value) {
        this.key = key;
        this.value = value;
    }
}
public class LRUCache {
    private HashMap<String, LRUNode> map;
    private int capacity;
    private LRUNode head;
    private LRUNode tail;
    public void set(String key, Object value) {
        LRUNode node = map.get(key);
        if (node != null) {
            node = map.get(key);
            node.value = value;
            remove(node, false);
        } else {
            node = new LRUNode(key, value);
            if (map.size() >= capacity) {
                remove(tail, true);
            }
            map.put(key, node);
        }
        setHead(node);
    }
    public Object get(String key) {
        LRUNode node = map.get(key);
        if (node != null) {
            remove(node, false);
            setHead(node);
            return node.value;
        }
        return null;
    }
    private void setHead(LRUNode node) {
        // 先从链表中删除该元素
        if (head != null) {
            node.next = head;
            head.prev = node;
        }
        head = node;
        if (tail == null) {
            tail = node;
        }
    }
    private void remove(LRUNode node, boolean flag) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
        node.next = null;
        node.prev = null;
        if (flag) {
            map.remove(node.key);
        }
    }
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<String, LRUNode>();
    }
}
