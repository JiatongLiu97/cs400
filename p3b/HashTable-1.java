////////////////////////////////////////////////////////////////////////////////
// Main File:        HashTable.java
// This File:        HashTable.java
// Other Files:      MyProfiler.java
// Semester:         CS 400 Spring 2019
//
// Author:           Jiatong Liu
// Email:            jliu794@wisc.edu
// CS Login:         jiatongl
//
/////////////////////////// OTHER SOURCES OF HELP //////////////////////////////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of
//                   of any information you find.
//////////////////////////// 80 columns wide ///////////////////////////////////


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// TODO: comment and complete your HashTableADT implementation
// DO ADD UNIMPLEMENTED PUBLIC METHODS FROM HashTableADT and DataStructureADT TO YOUR CLASS
// DO IMPLEMENT THE PUBLIC CONSTRUCTORS STARTED
// DO NOT ADD OTHER PUBLIC MEMBERS (fields or methods) TO YOUR CLASS
//
// TODO: implement all required methods
//
// TODO: describe the collision resolution scheme you have chosen
// identify your scheme as open addressing or bucket
//
// TODO: explain your hashing algorithm here 
// NOTE: you are not required to design your own algorithm for hashing,
//       since you do not know the type for K,
//       you must use the hashCode provided by the <K key> object
//       and one of the techniques presented in lecture
//
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
	
	// TODO: ADD and comment DATA FIELD MEMBERS needed for your implementation
	private transient Entry[] table;
	private transient int count;
	private int threshold;
	private double loadFactor;
	private transient int modCount = 0;
		
	// TODO: comment and complete a default no-arg constructor
	public HashTable() {
		this(11, 0.75f);

	}
	
	// TODO: comment and complete a constructor that accepts 
	// initial capacity and load factor threshold
        // threshold is the load factor that causes a resize and rehash
	public HashTable(int initialCapacity, double loadFactorThreshold) {
		
		loadFactor = loadFactorThreshold;
		 if (initialCapacity < 0)
		        throw new IllegalArgumentException("Illegal Capacity: "+
		                                           initialCapacity);
		    if (loadFactor <= 0 || Double.isNaN(loadFactor))
		        throw new IllegalArgumentException("Illegal Load: "+loadFactor);

		    if (initialCapacity==0)
		        initialCapacity = 1;
		    
		    table = new Entry[initialCapacity];
		    threshold = (int)(initialCapacity * loadFactor);

	}

	
	
	
	protected void rehash() {
        int oldCapacity = table.length;
        Entry[] oldMap = table;

        int newCapacity = oldCapacity * 2 + 1;
        Entry[] newMap = new Entry[newCapacity];

        modCount++;
        threshold = (int)(newCapacity * loadFactor);
        table = newMap;
        
        for (int i = oldCapacity ; i-- > 0 ;) {
        	             for (Entry<K,V> old = oldMap[i] ; old != null ; ) {
        	                 Entry<K,V> e = old;
        	                 old = old.next;
        	 
        	                 int index = (e.hash & 0x7FFFFFFF) % newCapacity;
        	                 e.next = newMap[index];
        	                 newMap[index] = e;
        	             }
        	         }
    }

	
	/* (non-Javadoc)
	 * @see DataStructureADT#insert(java.lang.Comparable, java.lang.Object)
	 */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		// TODO Auto-generated method stub
		// Hashtable�в��ܲ���valueΪnull��Ԫ�أ�����
	    if (value == null) {
	        throw new IllegalNullKeyException();
	    }

	    // ����Hashtable���Ѵ��ڼ�Ϊkey�ļ�ֵ�ԡ���
	    // ���á��µ�value���滻���ɵ�value��
	    Entry tab[] = table;
	    int hash = key.hashCode();
	    int index = (hash & 0x7FFFFFFF) % tab.length;
	    for (Entry<K,V> e = tab[index] ; e != null ; e = e.next) {
	        if ((e.hash == hash) && e.key.equals(key)) {
	            V old = e.value;
	            e.value = value;
	            }
	    }

	    // ����Hashtable�в����ڼ�Ϊkey�ļ�ֵ�ԡ���
	    // (01) �����޸�ͳ������+1
	    modCount++;
	    // (02) ����Hashtableʵ�������� > ����ֵ��(��ֵ=�ܵ����� * ��������)
	    //  �����Hashtable�Ĵ�С
	    if (count >= threshold) {
	        // Rehash the table if the threshold is exceeded
	        rehash();

	        tab = table;
	        index = (hash & 0x7FFFFFFF) % tab.length;
	    }

	    // (03) ����Hashtable��index��λ�õ�Entry(����)���浽e��
	    Entry<K,V> e = tab[index];
	    // (04) �������µ�Entry�ڵ㡱���������µ�Entry�����롰Hashtable��indexλ�á���������eΪ���µ�Entry������һ��Ԫ��(������Entry��Ϊ�����ͷ)��        
	    tab[index] = new Entry<K,V>(hash, key, value, e);
	    // (05) ����Hashtable��ʵ��������+1
	    count++;
		
	}

	/* (non-Javadoc)
	 * @see DataStructureADT#remove(java.lang.Comparable)
	 */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException {
		// TODO Auto-generated method stub
		Entry tab[] = table;
	    int hash = key.hashCode();
	    int index = (hash & 0x7FFFFFFF) % tab.length;
	    // �ҵ���key��Ӧ��Entry(����)��
	    // Ȼ�����������ҳ�Ҫɾ���Ľڵ㣬��ɾ���ýڵ㡣
	    for (Entry<K,V> e = tab[index], prev = null ; e != null ; prev = e, e = e.next) {
	        if ((e.hash == hash) && e.key.equals(key)) {
	            modCount++;
	            if (prev != null) {
	                prev.next = e.next;
	            } else {
	                tab[index] = e.next;
	            }
	            count--;
	            V oldValue = e.value;
	            e.value = null;
	            return true;
	        }
	    }
	    return false;
	}

	/* (non-Javadoc)
	 * @see DataStructureADT#get(java.lang.Comparable)
	 */
	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// TODO Auto-generated method stub
		Entry tab[] = table;
	    int hash = key.hashCode();
	    // ��������ֵ��
	    int index = (hash & 0x7FFFFFFF) % tab.length;
	    // �ҵ���key��Ӧ��Entry(����)����Ȼ�����������ҳ�����ϣֵ���͡���ֵ����key����ȵ�Ԫ��
	    for (Entry<K,V> e = tab[index] ; e != null ; e = e.next) {
	        if ((e.hash == hash) && e.key.equals(key)) {
	            return e.value;
	        }
	    }
	    return null;
	}

	/* (non-Javadoc)
	 * @see DataStructureADT#numKeys()
	 */
	@Override
	public int numKeys() {
		// TODO Auto-generated method stub
		return count;
	}

	/* (non-Javadoc)
	 * @see HashTableADT#getLoadFactorThreshold()
	 */
	@Override
	public double getLoadFactorThreshold() {
		// TODO Auto-generated method stub
		return this.threshold;
	}

	/* (non-Javadoc)
	 * @see HashTableADT#getLoadFactor()
	 */
	@Override
	public double getLoadFactor() {
		// TODO Auto-generated method stub
		return this.loadFactor;
	}

	/* (non-Javadoc)
	 * @see HashTableADT#getCapacity()
	 */
	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return table.length;
	}

	/* (non-Javadoc)
	 * @see HashTableADT#getCollisionResolution()
	 */
	@Override
	public int getCollisionResolution() {
		// TODO Auto-generated method stub
		return 7;
	}

	// TODO: add all unimplemented methods so that the class can compile
	private static class Entry<K,V> implements Map.Entry<K,V> {
	    // ��ϣֵ
	    int hash;
	    K key;
	    V value;
	    // ָ�����һ��Entry�����������һ���ڵ�
	    Entry<K,V> next;

	    // ���캯��
	    protected Entry(int hash, K key, V value, Entry<K,V> next) {
	        this.hash = hash;
	        this.key = key;
	        this.value = value;
	        this.next = next;
	    }
	    protected Entry(K key, V value) {
	        this.key = key;
	        this.value = value;
	    }

	    protected Object clone() {
	        return new Entry<K,V>(hash, key, value,
	              (next==null ? null : (Entry<K,V>) next.clone()));
	    }

	    public K getKey() {
	        return key;
	    }

	    public V getValue() {
	        return value;
	    }

	    // ����value����value��null�����׳��쳣��
	    public V setValue(V value) {
	        if (value == null)
	            throw new NullPointerException();

	        V oldValue = this.value;
	        this.value = value;
	        return oldValue;
	    }

	    // ����equals()�������ж�����Entry�Ƿ���ȡ�
	    // ������Entry��key��value����ȣ�����Ϊ������ȡ�
	    public boolean equals(Object o) {
	        if (!(o instanceof Map.Entry))
	            return false;
	        Map.Entry e = (Map.Entry)o;

	        return (key==null ? e.getKey()==null : key.equals(e.getKey())) &&
	           (value==null ? e.getValue()==null : value.equals(e.getValue()));
	    }

	    public int hashCode() {
	        return hash ^ (value==null ? 0 : value.hashCode());
	    }

	    public String toString() {
	        return key.toString()+"="+value.toString();
	    }
	}



		
}
