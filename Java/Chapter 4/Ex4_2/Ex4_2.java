public class Ex4_2 {
    public static void main(String[] args) {
        int firstArray[] = {1, 2, 3, 4},
            secondArray[] = {5, 6, 7, 8, 9, 10},
            myArray[], i;
        
        myArray = firstArray;
        
        System.out.println("First Array:");
        for (i = 0; i < myArray.length - 1; i++)
            System.out.print(myArray[i] + "\t");
        System.out.println(myArray[i]);
        
        myArray = secondArray;
        
        System.out.println("Second Array:");
        for (i = 0; i < myArray.length - 1; i++)
            System.out.print(myArray[i] + "\t");
        System.out.println(myArray[i]);
        
        myArray = new int[3];
        for (i = 0; i < 3; i++)
            myArray[i] = i + 101;
        
        System.out.println("Final Array:");
        for (i = 0; i < myArray.length - 1; i++)
            System.out.print(myArray[i] + "\t");
        System.out.println(myArray[i]);
    }
}