import java.util.*;

public class Day1 {
    static class Pair {
        int ele, min,max;

        Pair(int e, int mi,int ma) {
            ele = e;
            min = mi;
            max = ma;
        }
    }

    static class MinMaxStack {
        Stack<Pair>st;
        MinMaxStack(){
            st=new Stack<>();
        }

        public void push(int val) {
            if(st.isEmpty()){
                st.push(new Pair(val,val,val));
            }else{
                int currmin=(val<st.peek().min)?val:st.peek().min;
                int currmax=(val>st.peek().max)?val:st.peek().max;
                st.push(new Pair(val,currmin,currmax));
            }
        }

        public int pop() {

            int val=st.peek().ele;
            st.pop();
            return val;
        }

        public int top() {
            return st.peek().ele;
        }

        public int getMin() {
            return st.peek().min;
        }

        public int getMax() {
            return st.peek().max;
        }
    }

    public static void main(String[] args) {
        MinMaxStack s1=new MinMaxStack();
        s1.push(6);
        s1.push(-1);
        s1.push(5);
        s1.push(3);
        System.out.println(s1.top());
        System.out.println(s1.getMin());
        System.out.println(s1.getMax());
    }

}
