
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class LRU_case<K,V> extends LinkedHashMap<K, V> implements Map<K, V> {

    private static final long serialVersionUID = 1L;

    public LRU_case(int initialCapacity,
               float loadFactor,
               boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }

    
    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        // TODO Auto-generated method stub
        if(size() > 5){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        Random r = new Random();
        LRU_case<Integer, Integer> lru = new LRU_case<>(
                16, 0.75f, true);

        int N = 1;
        int M = (r.nextInt(20)  + 1);
        System.out.println(N + " " + M);
        ArrayList<Integer> list = new ArrayList<>();
         for (int i = 0; i < M; i++) {
            double choose = Math.random();
            if(choose >= 0.3){
                System.out.print("put");
                int key = (int)(Math.random() * 10 + 1);
                int value = (int)(Math.random() * 10 + 1);
                System.out.println(" " + key + " " + value);
                lru.put(key,value);
            }
            else {
                System.out.print("get");
                int key = (int)(Math.random() * 10 + 1);
                if(lru.get(key) == null){
                    list.add(-1);
                }
                else {
                    list.add(lru.get(key));
                }
                System.out.println(" "+ key);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }
}
