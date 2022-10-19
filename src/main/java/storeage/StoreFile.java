package storeage;

import java.io.File;
import java.io.FileWriter;

/**
 * First time shows 'File is not exist'
 * when user enter task ,save file to Hard Drive and shows save file successfully
 *
 */
public class StoreFile {
        public static void writeToFile(String Text) {
            File f = new File("C:\\data\\duke.txt");
            File dirs = new File("C:\\data");
            if(!dirs.exists()){
                dirs.mkdirs();
            }

            try {
                FileWriter pw = new FileWriter(f);
                pw.write(Text);
                pw.close();
                System.out.println("Save file to Hard Drive Successfully !.");

            }catch(Exception e){
                System.out.println("File is not exist");
            }
        }

//        public static void readToFile(){
//
//            try{
//                File f = new File("C:\\data\\duke.txt");
//                Scanner s = new Scanner(f);
//                try{
//                    while(s.hasNext()){
//                        System.out.println(s.nextLine());
//                    }
//                }
//                catch(Exception e){
//                    System.out.println("Read file Error");
//                }
//            }
//            catch (FileNotFoundException e){
//                System.out.println("File is not found");
//            }
//        }

    }
