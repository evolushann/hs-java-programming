package space.harbour.java.hw3;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class MyHashMap<K, V> implements Map<K, V> {
    private int size = 0;
    private final int BUCKET_SIZE = 10;
    private Object[] buckets = new Object[BUCKET_SIZE];

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(Object key) {
        int hash = key.hashCode() % BUCKET_SIZE;
        if(buckets[hash] == null)
            return false;
        
        if(buckets[hash] instanceof Element) {
            Element<K, V> e = ((Element<K, V>) buckets[hash]);

            if(e.getKey().equals(key))
                return true;
        } else if(buckets[hash] instanceof LinkedList) {
            LinkedList<Element<K, V>> ll = ((LinkedList<Element<K, V>>) buckets[hash]);
            
            for(Element<K, V> e : ll) {
                if(e.getKey().equals(key))
                    return true;
            }
        }
        
        return false;
    }

    public boolean containsValue(Object value) {
        for(int i = 0; i < BUCKET_SIZE; i ++) {
            if(buckets[i] != null) {
                if(buckets[i] instanceof Element) {
                    Element<K, V> e = ((Element<K, V>) buckets[i]);
        
                    if(e.getValue().equals(value))
                        return true;
                } else if(buckets[i] instanceof LinkedList) {
                    LinkedList<Element<K, V>> ll = ((LinkedList<Element<K, V>>) buckets[i]);
                    
                    for(Element<K, V> e : ll) {
                        if(e.getValue().equals(value))
                            return true;
                    }
                }
            }
        }

        return false;
    }

    public V get(Object key) {
        int hash = key.hashCode() % BUCKET_SIZE;
        
        if(buckets[hash] != null) {
            if(buckets[hash] instanceof Element)
                return ((Element<K, V>) buckets[hash]).getValue();

            else if(buckets[hash] instanceof LinkedList) {
                LinkedList<Element<K, V>> ll = (LinkedList<Element<K, V>>) buckets[hash];

                for(Element<K, V> e : ll) {
                    if(e.getKey().equals(key))
                        return e.getValue();
                }
            }
        }

        return null;
    }

    public V put(K key, V value) {
        int hash = key.hashCode() % BUCKET_SIZE;

        if(buckets[hash] == null)
            buckets[hash] = new Element<K,V>(key, value);

        else if(buckets[hash] instanceof Element) {
            Element<K, V> e = (Element<K, V>) buckets[hash];
            LinkedList<Element<K, V>> l = new LinkedList<Element<K, V>>();
            l.add(e);
            l.add(new Element<K, V>(key, value));
            buckets[hash] = l;
        }

        else if(buckets[hash] instanceof LinkedList) {
            ((LinkedList<Element<K, V>>) buckets[hash]).add(new Element<K, V>(key, value));
        }

        size ++;
        return value;
    }

    public V remove(Object key) {
        int hash = key.hashCode() % BUCKET_SIZE;

        if(buckets[hash] == null)
            return null;

        if(buckets[hash] instanceof Element) {
            Element<K, V> element = (Element<K, V>) buckets[hash];

            if(element.getKey().equals(key)) {
                V val = element.getValue();
                buckets[hash] = null;
                size --;
                return val;
            }
        } else if(buckets[hash] instanceof LinkedList) {
            LinkedList<Element<K, V>> ll = (LinkedList<Element<K, V>>) buckets[hash];

            for(Element<K, V> e : ll) {
                if(e.getKey().equals(key)) {
                    V val = e.getValue();
                    ll.remove(e);
                    size --;

                    if(ll.size() == 1)
                        buckets[hash] = ll.getFirst();

                    return val;
                }
            }
        }

        return null;
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        // TODO: maybe next time
    }

    public void clear() {
        buckets = new Object[BUCKET_SIZE];
        size = 0;
    }

    public Set<K> keySet() {
        Set<K> set = new HashSet<K>();
        for(int i = 0; i < BUCKET_SIZE; i ++) {
            if(buckets[i] != null) {
                if(buckets[i] instanceof Element)
                    set.add(((Element<K, V>) buckets[i]).getKey());

                else if(buckets[i] instanceof LinkedList) {
                    for(Element<K, V> e : ((LinkedList<Element<K, V>>) buckets[i]))
                        set.add(e.getKey());
                }
            }
        }
        return set;
    }

    public Collection<V> values() {
        Collection<V> collection = new LinkedList<V>();

        for(int i = 0; i < BUCKET_SIZE; i ++) {
            if(buckets[i] != null) {
                if(buckets[i] instanceof Element)
                    collection.add(((Element<K, V>) buckets[i]).getValue());

                else if(buckets[i] instanceof LinkedList) {
                    for(Element<K, V> e : ((LinkedList<Element<K, V>>) buckets[i]))
                        collection.add(e.getValue());
                }
            }
        }

        return collection;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<Entry<K, V>>();
        
        for(int i = 0; i < BUCKET_SIZE; i ++) {
            if(buckets[i] != null) {
                if(buckets[i] instanceof Element)
                    set.add((Element<K, V>) buckets[i]);

                else if(buckets[i] instanceof LinkedList) {
                    for(Element<K, V> e : ((LinkedList<Element<K, V>>) buckets[i]))
                        set.add(e);
                }
            }
        }

        return set;
    }
    
    public String toString() {
        String ret = "{\n";

        for(int i = 0; i < BUCKET_SIZE; i ++) {
            ret += i + ": ";
            if(buckets[i] != null) {
                if(buckets[i] instanceof Element) {
                    Element<K, V> e = (Element<K, V>) buckets[i];
                    ret += "[" + e.getKey() + ": " + e.getValue() + "]\n";
                } else if(buckets[i] instanceof LinkedList) {
                    ret += "[";

                    for(Element<K, V> e : ((LinkedList<Element<K, V>>) buckets[i]))
                        ret += e.getKey() + ": " + e.getValue() + ", ";

                    ret += "]\n";
                }
            } else {
                ret += "[empty]\n";
            }
        }
        return ret + "}";
    }
    
    public static void main(String[] args) {
        MyHashMap<String, String> mhm = new MyHashMap<>();
        mhm.put("key", "value");
        mhm.put("key", "another value");
        System.out.println(mhm.get("key"));
        mhm.put("omg", "wow");
        System.out.println(mhm.size());
        System.out.println(mhm.toString());
        mhm.remove("key");
        System.out.println(mhm.size());
        System.out.println(mhm.toString());
        System.out.println(mhm.get("omg"));
        mhm.clear();
        System.out.println(mhm.isEmpty());
    }
}