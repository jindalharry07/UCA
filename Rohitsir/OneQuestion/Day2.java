import java.util.*;

// 📄 Robot Message Logger
// You are designing a logging system for a robot that receives a continuous stream of messages. Each message has:
// - A timestamp (an integer, in seconds)
// - A string content (e.g., "Hello", "Bye", etc.)
//
// The robot must only log a message if it hasn't seen the same message content within the last 10 seconds.

// The robot must only log a message if it hasn't seen the same message content within the last 10 seconds and in future 10.

public class Day2 {
  class Message {
    public String msg;
    public int timeStamp;

    public Message(String msg, int timeStamp) {
      this.msg = msg;
      this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
      return "[" + timeStamp + ", \"" + msg + "\"]";
    }
  }

  interface MessageTracker {
    Message getMessage();
  }

  class RobotTracker {
    MessageTracker messageTracker;
    private Map<String, Integer> lastPrinted;

    public RobotTracker(MessageTracker messageTracker) {
      this.messageTracker = messageTracker;
      lastPrinted = new HashMap<>();
    }

    public void pollMessage() {
      while (true) {
        Message message = messageTracker.getMessage();
        shouldPrintMessage(message);
      }
    }

    public void shouldPrintMessage(Message message) {
      String msg = message.msg;
      int time = message.timeStamp;

      if (!lastPrinted.containsKey(msg) || time - lastPrinted.get(msg) >= 10) {
        // Print/log the message if not printed in the last 10 seconds
        System.out.println(message);
        lastPrinted.put(msg, time);
      }
      // Else: skip
    }
  }

  public static void main(String[] args) {
    Day2 day2 = new Day2();
    RobotTracker robotTracker = day2.new RobotTracker(null);

    robotTracker.shouldPrintMessage(day2.new Message("hello", 1)); // print
    robotTracker.shouldPrintMessage(day2.new Message("hello", 2)); // skip
    robotTracker.shouldPrintMessage(day2.new Message("bye", 8)); // print
    robotTracker.shouldPrintMessage(day2.new Message("hello", 12)); // print
    robotTracker.shouldPrintMessage(day2.new Message("bye", 13)); // skip
  }
}
