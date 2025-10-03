
class Block {
  String name;
  int start, size;
  boolean free;
  Block next;

  Block(String name, int start, int size, boolean free) {
    this.name = name;
    this.start = start;
    this.size = size;
    this.free = free;
  }
}

class MemoryManager {
  private Block head;
  private int TOTAL_MEMORY;

  public MemoryManager(int _size) {
    this.TOTAL_MEMORY = _size;
    head = new Block("FREE", 0, _size, true);
  }

  public int usedMemory() {
    int used = 0;
    Block curr = head;
    while (curr != null) {
      if (curr.free != true) {
        used += curr.size;
      }
      curr = curr.next;
    }
    return used;
  }

  public int getTotalMemory() {
    return TOTAL_MEMORY;
  }

  private void resize(int _newSize) {
    if (_newSize <= TOTAL_MEMORY) {
      return;
    }

    int used = usedMemory();

    TOTAL_MEMORY = _newSize;
    defragment();
    Block curr = head;

    while (curr.next != null) {
      curr = curr.next;
    }

    if (curr.free == true) {
      curr.size = TOTAL_MEMORY - curr.start;
    } else {
      curr.next = new Block("FREE", used, TOTAL_MEMORY - used, true);
    }

    System.out.println("Memory expanded to " + TOTAL_MEMORY);
  }

  private void shrinkIfNeeded() {
    int used = usedMemory();

    if (used <= TOTAL_MEMORY / 2) {
      int newSize = TOTAL_MEMORY * 3 / 4;

      if (newSize < used) {
        return;
      }

      TOTAL_MEMORY = newSize;
      defragment();
      Block curr = head;
      while (curr.next != null) {
        curr = curr.next;
      }
      if (curr.free) {
        curr.size = TOTAL_MEMORY - curr.start;
      }
      System.out.println("Memory shrunk to " + TOTAL_MEMORY);
    }
  }

  public void allocate(String prog, int n) {
    Block curr = head;

    while (curr != null) {
      if (curr.free == true && curr.size >= n) { // new free block
        Block newBlock = new Block(prog, curr.start, n, false);

        curr.start += n;
        curr.size -= n;

        newBlock.next = curr;

        if (head == curr) {
          head = newBlock;
        } else {
          Block prev = head;
          while (prev.next != curr) {
            prev = prev.next;
          }
          prev.next = newBlock;
        }

        if (curr.size == 0) {
          Block temp = curr.next;
          newBlock.next = temp;
        }

        System.out.println("Program " + prog + " allocated " + n + " units starting at " + newBlock.start + "!");

        return;
      }
      curr = curr.next;
    }

    // if no memory → resize
    System.out.println("Not enough memory, expanding...");
    resize(TOTAL_MEMORY * 2);
    allocate(prog, n); // retry after resize
  }

  public void deallocate(String prog) {
    Block curr = head;

    while (curr != null) {
      if (curr.free == false && curr.name.equals(prog)) {
        curr.name = "FREE";
        curr.free = true;
        System.out.println("Program " + prog + " deallocated !!");

        // defragment(); // auto defrag after deallocation
        shrinkIfNeeded();
        return;
      }
      curr = curr.next;
    }
    System.out.println("Program " + prog + " not found!");
  }

  public void defragment() {
    int currAdd = 0;
    Block curr = head;

    while (curr != null) {
      if (curr.free == false) {
        curr.start = currAdd;
        currAdd += curr.size;
      }
      curr = curr.next;
    }

    Block freeBlock = new Block("FREE", currAdd, TOTAL_MEMORY - currAdd, true);

    Block newhead = null, tail = null;
    curr = head;

    while (curr != null) {
      if (curr.free == false) {
        if (newhead == null) {
          newhead = curr;
        } else {
          tail.next = curr;
        }

        tail = curr;
      }
      curr = curr.next;
    }

    if (newhead == null) {
      newhead = freeBlock;
    } else {
      tail.next = freeBlock;
    }

    head = newhead;
    System.out.println("Memory Defragmented !");
  }

  public void display() {
    System.out.println("\nMemory Layout:");
    Block curr = head;
    while (curr != null) {
      System.out.println("[" + curr.name + " | Start: " + curr.start + " | Size: " + curr.size + "]");
      curr = curr.next;
    }
    System.out.println();
  }
}

public class implementation {
  public static void main(String[] args) {
    MemoryManager mm = new MemoryManager(20);

    mm.allocate("P1", 10);
    mm.allocate("P2", 8);
    assert mm.usedMemory() == 18 : "Used memory should be 18 after two allocations";

    mm.allocate("P3", 5); // should expand
    assert mm.usedMemory() == 23 : "Used memory should be 23 after expansion";
    assert mm.getTotalMemory() > 20 : "Memory should have expanded";

    mm.deallocate("P1");
    assert mm.usedMemory() == 13 : "Used memory should be 13 after deallocating P1";

    mm.deallocate("P2");
    mm.deallocate("P3"); // now memory mostly free → should shrink
    assert mm.getTotalMemory() < 40 : "Memory should have shrunk after freeing programs";

    System.out.println("All test cases passed!");
  }
}
