package space.harbour.java.hw3;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class MyHashMap<K, V> implements Map<K, V> {
    private int size = 0;
    private Object[] list = new Object[10];

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(Object key) {
        int hash = key.hashCode() % 10;
        if(list[hash] == null)
            return false;
        
        if(list[hash] instanceof Element) {
            Element<K, V> e = ((Element<K, V>) list[hash]);

            if(e.getKey().equals(key))
                return true;
        } else if(list[hash] instanceof LinkedList) {
            LinkedList<Element<K, V>> ll = ((LinkedList<Element<K, V>>) list[hash]);
            
            for(Element<K, V> e : ll) {
                if(e.getKey().equals(key))
                    return true;
            }
        }
        
        return false;
    }

    public boolean containsValue(Object value) {
        for(int i = 0; i < 10; i ++) {
            if(list[i] != null) {
                if(list[i] instanceof Element) {
                    Element<K, V> e = ((Element<K, V>) list[i]);
        
                    if(e.getValue().equals(value))
                        return true;
                } else if(list[i] instanceof LinkedList) {
                    LinkedList<Element<K, V>> ll = ((LinkedList<Element<K, V>>) list[i]);
                    
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
        int hash = key.hashCode() % 10;
        
        if(list[hash] != null) {
            if(list[hash] instanceof Element)
                return ((Element<K, V>) list[hash]).getValue();

            else if(list[hash] instanceof LinkedList) {
                LinkedList<Element<K, V>> ll = (LinkedList<Element<K, V>>) list[hash];

                for(Element<K, V> e : ll) {
                    if(e.getKey().equals(key))
                        return e.getValue();
                }
            }
        }

        return null;
    }

    public V put(K key, V value) {
        int hash = key.hashCode() % 10;

        if(list[hash] == null)
            list[hash] = new Element<K,V>(key, value);

        else if(list[hash] instanceof Element) {
            Element<K, V> e = (Element<K, V>) list[hash];
            LinkedList<Element<K, V>> l = new LinkedList<Element<K, V>>();
            l.add(e);
            l.add(new Element<K, V>(key, value));
            list[hash] = l;
        }

        else if(list[hash] instanceof LinkedList) {
            ((LinkedList<Element<K, V>>) list[hash]).add(new Element<K, V>(key, value));
        }

        size ++;
        return value;
    }

    public V remove(Object key) {
        int hash = key.hashCode() % 10;

        if(list[hash] == null)
            return null;

        if(list[hash] instanceof Element) {
            Element<K, V> element = (Element<K, V>) list[hash];

            if(element.getKey().equals(key)) {
                V val = element.getValue();
                list[hash] = null;
                size --;
                return val;
            }
        } else if(list[hash] instanceof LinkedList) {
            LinkedList<Element<K, V>> ll = (LinkedList<Element<K, V>>) list[hash];
            
            for(Element<K, V> e : ll) {
                if(e.getKey().equals(key)) {
                    V val = e.getValue();
                    ll.remove(e);
                    size --;
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
        list = new Object[10];
        size = 0;
    }

    public Set<K> keySet() {
        Set<K> set = new HashSet<K>();
        for(int i = 0; i < 10; i ++) {
            if(list[i] != null) {
                if(list[i] instanceof Element)
                    set.add(((Element<K, V>) list[i]).getKey());

                else if(list[i] instanceof LinkedList) {
                    for(Element<K, V> e : ((LinkedList<Element<K, V>>) list[i]))
                        set.add(e.getKey());
                }
            }
        }
        return set;
    }

    public Collection<V> values() {
        Collection<V> collection = new LinkedList<V>();

        for(int i = 0; i < 10; i ++) {
            if(list[i] != null) {
                if(list[i] instanceof Element)
                    collection.add(((Element<K, V>) list[i]).getValue());

                else if(list[i] instanceof LinkedList) {
                    for(Element<K, V> e : ((LinkedList<Element<K, V>>) list[i]))
                        collection.add(e.getValue());
                }
            }
        }

        return collection;
    }

    public Set<Entry<K, V>> entrySet() {
		Set<Entry<K, V>> set = new HashSet<Entry<K, V>>();
        for(int i = 0; i < 10; i ++) {
            if(list[i] != null) {
                if(list[i] instanceof Element)
                    set.add((Element<K, V>) list[i]);

                else if(list[i] instanceof LinkedList) {
                    for(Element<K, V> e : ((LinkedList<Element<K, V>>) list[i]))
                        set.add(e);
                }
            }
        }
        return set;
    }
    
    public String toString() {
        String ret = "{\n";

        for(int i = 0; i < 10; i ++) {
            ret += i + ": ";
            if(list[i] != null) {
                if(list[i] instanceof Element) {
                    Element<K, V> e = (Element<K, V>) list[i];
                    ret += "[" + e.getKey() + ": " + e.getValue() + "]\n";
                } else if(list[i] instanceof LinkedList) {
                    ret += "[";

                    for(Element<K, V> e : ((LinkedList<Element<K, V>>) list[i]))
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
        System.out.println(mhm.toString());
        mhm.remove("key");
        System.out.println(mhm.toString());
        System.out.println(mhm.get("omg"));
    }
}