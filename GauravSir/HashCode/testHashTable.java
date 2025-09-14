package HashCode;
import HashCode.Hashtable;

class Foo {
  Integer i;
  Boolean b;
  String s;

  Foo(Integer i, Boolean b, String s) {
    this.i = i;
    this.b = b;
    this.s = s;
  }

  @Override
  public int hashCode() {
    int hashVal = 17;
    hashVal = i.hashCode() + 31 * hashVal;
    hashVal = b.hashCode() + 31 * hashVal;
    hashVal = s.hashCode() + 31 * hashVal;
    return hashVal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o){
      return true;
    }
    if (o == null || getClass() != o.getClass()){
      return false;
    }
    Foo f = (Foo) o;
    return (f.i.equals(i)) && (f.b.equals(b)) && (f.s.equals(s));
  }
}

public class testHashTable {
  public static void main(String[] args) {
    Hashtable<Foo, Integer> map = new Hashtable<>();

    Foo key1 = new Foo(42, true, "hello");
    Foo key2 = new Foo(42, true, "hello");
    Foo key3 = new Foo(7, false, "world");

    map.put(key1, 100);
    map.put(key3, 200);

    assert key1.equals(key2) == true;

    assert map.get(key1) == 100;

    assert map.get(key2) == 100;

    assert map.get(key3) == 200;

    assert map.remove(key2) == true;

    assert map.get(key1) == null;

    assert map.get(key3) == 200;

    System.out.println("All assertions passed!");
  }
}
