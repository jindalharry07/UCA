import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import java.util.List;


// Hash Map O(n)
// priority Queue  in deletion and wishlist O(n)

// Tree set 
// Doubly linklist  O(1)

/*
Data Structure	                 Supports inc/dec in O(1)?	        Supports getMaxKey/getMinKey in O(1)?	     Notes
Only Map	                     ✅ Yes	                           ❌ No (O(n))	                              Simple but not efficient for max/min
Only PriorityQueue	             ❌ No (update is O(n))	           ✅ Yes (peek is O(1))	                      Needs help from Map to work
Map + PriorityQueue	             ❌ No (still O(n) on update)	   ✅ Yes	                                  Works, but not O(1)
Optimized Design (DLL + Map)	 ✅ Yes	                           ✅ Yes	                                  Best design, truly O(1)
*/
public class Day3 {
    // Only Map
    
    /*
    static class ProductTracker {
        
        HashMap<String, Integer> mpp;

        public ProductTracker() {
            mpp = new HashMap<>();
        }

        public void wishlit(String productName) {
            mpp.put(productName, mpp.getOrDefault(productName, 0) + 1);
        }

        public void delist(String productName) {
            if (mpp.containsKey(productName)) {
                int freq = mpp.get(productName);
                if (freq <= 1) {
                    mpp.remove(productName);
                } else {
                    mpp.put(productName, freq - 1);
                }
            }
        }

        public String getMaxProduct() {
            String maxProduct = null;
            int maxFreq = Integer.MIN_VALUE;
            for (String key : mpp.keySet()) {
                if (mpp.get(key) > maxFreq) {
                    maxFreq = mpp.get(key);
                    maxProduct = key;
                }
            }
            return maxProduct;
        }

        public String getMinProduct() {
            String minProduct = null;
            int minFreq = Integer.MAX_VALUE;
            for (String key : mpp.keySet()) {
                if (mpp.get(key) < minFreq) {
                    minFreq = mpp.get(key);
                    minProduct = key;
                }
            }
            return minProduct;
        }

    }
    */

    // Only PriorityQueue
    static class ProductTracker {
        static class Pair {
            String key;
            int freq;

            Pair(String key, int freq) {
                this.key = key;
                this.freq = freq;
            }
        }

        PriorityQueue<Pair> maxHeap;
        PriorityQueue<Pair> minHeap;

        public ProductTracker() {
            maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.freq, a.freq));
            minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.freq, b.freq));
        }

        private void updateHeap(PriorityQueue<Pair> heap, String productName, boolean increment) {
            List<Pair> temp = new ArrayList<>();
            boolean found = false;

            while (!heap.isEmpty()) {
                Pair curr = heap.poll();
                if (curr.key.equals(productName)) {
                    curr.freq += increment ? 1 : -1;
                    found = true;
                }
                if (curr.freq > 0) {
                    temp.add(curr);
                }
            }

            if (!found && increment) {
                temp.add(new Pair(productName, 1));
            }

            heap.addAll(temp);
        }

        public void wishlit(String productName) {
            updateHeap(maxHeap, productName, true);
            updateHeap(minHeap, productName, true);
        }

        public void delist(String productName) {
            updateHeap(maxHeap, productName, false);
            updateHeap(minHeap, productName, false);
        }

        public String getMaxProduct() {
            return maxHeap.isEmpty() ? null : maxHeap.peek().key;
        }

        public String getMinProduct() {
            return minHeap.isEmpty() ? null : minHeap.peek().key;
        }

    }

    public static void main(String[] args) {
        ProductTracker pt = new ProductTracker();
        pt.wishlit("a");
        pt.wishlit("a");
        pt.wishlit("a");

        pt.wishlit("b");
        pt.wishlit("b");
        System.out.println(pt.getMaxProduct());
        System.out.println(pt.getMinProduct());
        pt.delist("a");
        pt.delist("a");
        System.out.println(pt.getMaxProduct());
        System.out.println(pt.getMinProduct());
        pt.delist("a");
        pt.delist("a");
        System.out.println(pt.getMaxProduct());
        System.out.println(pt.getMinProduct());
    }
}
