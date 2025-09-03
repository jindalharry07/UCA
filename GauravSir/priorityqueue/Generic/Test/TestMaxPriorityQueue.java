package Generic.Test;
import Generic.MaxPriorityQueueGeneric;

public class TestMaxPriorityQueue {
  static class Car implements Comparable<Car> {
    private int color;
    private int size;

    public Car (int color, int size) {
      this.color = color;
      this.size = size;
    }

    public int getColor() {
      return this.color;
    }

    public int getSize() {
      return this.size;
    }

    @Override
    public int compareTo(Car other) {
      if (this.size < other.size) {
        return -1;
      } else if (this.size > other.size) {
        return 1;
      } else {
        return 0;
      } 
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null || getClass() != obj.getClass()) {
        return false;
      }
      Car car = (Car) obj;
      return color == car.color && size == car.size;
    }
  }

  public static void main(String[] arg) {
    Car car1 = new Car(1, 10);
    Car car2 = new Car(2, 20);
    Car car3 = new Car(3, 15);

    MaxPriorityQueueGeneric<Car> carqueue = new MaxPriorityQueueGeneric<>(10);

    carqueue.insert(car1);
    carqueue.insert(car2);
    carqueue.insert(car3);

    assert carqueue.delMax().equals(car2);
    assert carqueue.delMax().equals(new Car(3, 15));
    assert carqueue.delMax().equals(car1);

    System.out.println("All tests passed!");

  }
}
