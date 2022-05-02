package V2UPLOAD;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Auswertung {
    public static long fileCount;
    public static String root = "YOUR:\\ROOT\\DIR\\USERS";
    public static int A = 0;
    public static int B = 0;
    public static int C = 0;

    public static void main(String[] args) throws Exception{
        try (Stream<Path> files = Files.list(Paths.get("YOUR:/ROOT/DIR/USERS"))) {
            fileCount = files.count();
        }
        System.out.print("Teilnehmer: ");
        System.out.println(fileCount);
        File dir = new File(root);
        File[] directoryListing = dir.listFiles();
        Users user;
        if (directoryListing != null) {
            String[] optionAs = {"Hund", "Apple", "Knoppers", "Pizza", "Meer", "Pepsi", "Schokoeis", "Zitroneneistee", "McDonald's", "süßes Popcorn", "Friends"};
            String[] optionBs = {"Katze", "Android", "Hanuta", "Burger", "Berge", "Cola", "Vanilleeis", "Pfirsicheistee", "Burger King", "salziges Popcorn", "HIMYM"};
            for (int i = 0; i < 11; i++){
                for (File child : directoryListing) {
                    user = (Users) SNL2.load(child.toString());
                    int[] vars = {user.hundOderKatze, user.appleOderAndroid, user.knoHan, user.piBU, user.meBe, user.peCo, user.schoVa, user.ziPfi, user.mcB, user.sueSa, user.friHimym};
                    if (vars[i] == 0){
                        A++;
                    }
                    if (vars[i] == 1){
                        B++;
                    }
                    if (vars[i] == 2){
                        C++;
                    }
                }
                System.out.print("Für ");
                System.out.print(optionAs[i]);
                System.out.print(" haben ");
                System.out.print(A);
                System.out.print(" gestimmt, für ");
                System.out.print(optionBs[i]);
                System.out.print(" ");
                System.out.print(B);
                System.out.print(" und ");
                System.out.print(C);
                System.out.println(" haben sich enthalten.");
                A = 0;
                B = 0;
                C = 0;
            }
        }
    }
}
