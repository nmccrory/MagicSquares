package magicsquaredemo;

/**
 * This program is able to generate and test for magic squares.
 * CSC 1350 Project #4<br>
 * @author Nick McCrory
 * @since 11/10/2015
 * 
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class MagicSquareDemo {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> magic_square = new ArrayList();
        
        System.out.println("              MAGIC SQUARE APPLICATION              ");
        System.out.println("===================================================");
        System.out.printf("Generate a Magic Square.........................[1]%n"
                + "Test for a Magic Square.........................[2]%n"
                + "Quit the Program................................[0]%n");
        System.out.print("\nSelect an option -> ");
        int selected = input.nextInt();
        
        if(selected == 0){
            System.exit(0);
        }else if(selected == 1){
            try{
                System.out.print("Enter the dimension of the magic square -> ");
                int dimension = input.nextInt();

                if(dimension < 0 || dimension % 2 == 0){
                    System.out.println("The program can only generate a magic square \nwhose dimension is a postive odd number.");
                    System.exit(0);
                }

                System.out.printf("Enter the name of the output file -> ");
                String outputFile = input.next();

                int row = dimension-1;
                int col = dimension/2;
                int temp = dimension * dimension +1;
                int i,k;

                for(i=0; i<dimension*dimension;i++){
                    magic_square.add(0);
                }

                for(i=1; i<temp; i++){
                    magic_square.set(dimension*row+col, i);

                    row = (row+1)%dimension;
                    col = (col+1)%dimension;

                    if(magic_square.get(dimension*row +col)!= 0){
                        row = (row + dimension - 2)%dimension;
                        col = (col + dimension - 1)%dimension;
                    }
                }

                for(i=0; i<dimension; i++){
                    for(k=0; k<dimension; k++){
                        System.out.print(magic_square.get(k+dimension*i)+"\t");
                    }
                    System.out.println();
                }
                System.out.println("A "+ dimension +" x "+ dimension +" magic square.");

                PrintWriter output = new PrintWriter(new File(outputFile));
                for(i=0; i< dimension; i++){
                    for(k=0; k<dimension; k++){
                        output.print(magic_square.get(k+dimension*i)+ "\t");
                    }
                    output.print('\n');
                }
                output.close();
            }
            catch(IOException err){
                System.out.println(err);
            }
        }else if(selected == 2){
            try{
                System.out.printf("Enter the name of the input file -> ");
                String inputFile = input.next();
                
                Scanner inFile = new Scanner(new File(inputFile));
                magic_square.add(inFile.nextInt());
                
                while(inFile.hasNextInt()){
                    magic_square.add(inFile.nextInt());
                }
                
                int n = (int) Math.round(Math.sqrt(magic_square.size()));
                int i,k;
                
                for(i=0; i<n; i++){
                    for(k=0; k<n; k++){
                        System.out.print(magic_square.get(k + n*i)+("\t"));
                    }
                    System.out.println();
                }
                
                if(n*n != magic_square.size()){
                    System.out.println("This is not a perfect square, therefore "
                            + "it is not a magic square.");
                }
                
                for(i=1; i<=magic_square.size(); i++){
                    if(!magic_square.contains((Integer) i)){
                        System.out.println("This perfect square doesn't contain "
                                + "all numbers from 1 to n^2, it is therefore"
                                + "not a perfect square.");
                    }    
                }
                
                int rowSums[] = new int[n];
                int colSums[] = new int[n];
                int sumDiagMajor = 0;
                int sumDiagMinor = 0;
                int row, col;
                
                for(i=0; i<magic_square.size(); i++){
                    row = i/n;
                    col = i%n;
                    rowSums[row] += magic_square.get(i);
                    colSums[col] += magic_square.get(i);
                       
                    if(row == col)
                        sumDiagMajor += magic_square.get(i);

                    if(row + col == n - 1)
                        sumDiagMinor += magic_square.get(i);
                }
                
                if(sumDiagMajor == sumDiagMinor){
                    int sum = sumDiagMajor;
                    boolean isMagic = true;
                    
                    for(i=0; i<n; i++){
                        if(rowSums[i] != sum || colSums[i] != sum){
                            isMagic = false;
                        }
                        
                    }
                    if(isMagic){
                        System.out.println("\nA " + n +" x "+ n +" a magic square.");
                    }else{
                        System.out.println("\nNot a magic square.");
                    }
                }
                else{
                    System.out.println("\nNot a magic square.");
                }
            }catch(IOException err){
                System.out.println(err);
            }
        }
    }
    
}
