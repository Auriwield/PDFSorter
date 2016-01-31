package extclasses;

import SortMachine.FileListCreator;
import SortMachine.Sorter;
import output.ConsoleWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Dinozavrik on 30.01.2016.
 */
public class ArgumentParser {
    private static ArgumentParser ourInstance = new ArgumentParser();

    public static ArgumentParser getInstance() {
        return ourInstance;
    }

    boolean isArgTUsed = false, isArgAUsed = false, isArgRUsed = false, isArgCUsed = false;
    public boolean isFUsed = false;
    private ArgumentParser() {
    }

    public void parseArgs(String[] args) throws IOException {
        int c = 0;
        String query = "";
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].toLowerCase();
            if(args[i].equals("/f")) {
                try {
                    query = args[i + 1];
                    isFUsed = true;
                } catch (IndexOutOfBoundsException e) {
                    System.err.println("Incorrect '/F' query");
                }
            }
        }
        if(isFUsed) Sorter.find(query);
        else Sorter.parse();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "/t":
                    if (!isArgTUsed) {
                        ConsoleWriter.withdrawTextPdfs();
                        c++;
                        isArgTUsed = true;
                    }
                    break;
                case "/a":
                    if (!isArgAUsed) {
                        ConsoleWriter.withdrawAll();
                        c++;
                        isArgAUsed = true;
                    }
                    break;
                case "/c":
                    try {
                        Sorter.copy(args[i + 1]);
                        isArgCUsed = true;
                    } catch (IndexOutOfBoundsException e) {
                        System.err.println("Incorrect '/C' path");
                    } catch (FileNotFoundException e) {
                        System.err.println("dir. path is incorrect");
                    }
                    break;
                case "/r":
                    if(!isArgRUsed)
                        renameAllTextPdfs();
                    isArgRUsed = true;
                    break;
            }
        }
        if(c == 0)
            ConsoleWriter.withdraw();
        if (Sorter.isSuccessfulCopy() && isArgCUsed)
            System.out.println("Files successful copied");

    }

    private static void renameAllTextPdfs() {
        Sorter.getTextPdfs()
                .stream()
                .forEach(Pdf::rename);
    }
}
