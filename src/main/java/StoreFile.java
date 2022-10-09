import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class StoreFile {
        public static void writeToFile(String Text) {
            try {
                File f = new File("C:\\data\\duke.txt");
                PrintWriter pw = new PrintWriter(f);
                pw.write(Text);
                pw.close();
                System.out.println("Save file to Hard Drive Successfully !.");

            }catch(FileNotFoundException e){
                System.out.println("Save file Error");
            }
        }

        public static void readToFile(){

            try{
                File f = new File("C:\\data\\duke.txt");
                Scanner s = new Scanner(f);
                try{
                    while(s.hasNext()){
                        System.out.println(s.nextLine());
                    }
                }
                catch(Exception e){
                    System.out.println("Read file Error");
                }
            }
            catch (FileNotFoundException e){
                System.out.println("File is not found");
            }
        }

    }
