package main;

import SortMachine.FileListCreator;
import SortMachine.Sorter;
import output.ConsoleWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Auriwield on 24.01.2016.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter path(file/directory)");
        FileListCreator creator = new FileListCreator(reader.readLine());
        reader.close();
        creator.createFileList();
        Sorter.sort();
        ConsoleWriter.withdraw();
    }

}

