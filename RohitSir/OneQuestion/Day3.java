import java.util.HashMap;

// Hash Map O(n)
// priority Queue  in deletion and wishlist O(n)

// Tree set 
// Doubly linklist  O(1)

public class Day3 {
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
