package ecse202;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;

import java.util.ArrayList;

public class FlipImage extends GraphicsProgram {
    public static void main(String[] args) {
        new FlipImage().start(args);
    }
    public void  run(){
        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(44);
        println(intList.get(0));
    GImage original = new GImage("C:\\Users\\printer2\\IdeaProjects\\ecse202_3\\src\\ecse202\\pic.jpg");
    GImage flipped = flippedVertical(original);
    add(flipped,50,50);
    }
    private GImage flippedVertical(GImage originalImage) {
        int[][] array = originalImage.getPixelArray();
        for (int i = 0; i < array.length/2; i++) {
            int end = array.length - i - 1;
            int[] temp = array[i];
            array[i] = array[end];
            array[end] = temp;
        }
        return new GImage(array);
    }
}
