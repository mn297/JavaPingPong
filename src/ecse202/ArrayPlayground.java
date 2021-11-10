package ecse202;

import acm.program.GraphicsProgram;

public class ArrayPlayground extends GraphicsProgram {
    public static void main(String[] args) {
        new ArrayPlayground().start(args);
    }
    public void run() {
        int[]  myArr = {1,2,3,4,5};
        set(myArr,55,2);
        for(int i = 0; i < myArr.length; i++){
            println(myArr[i]);
        }
    }
    public void set(int[] myArray, int value, int position) {
        myArray[position] = value;
    }
}
