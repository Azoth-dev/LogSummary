package com.cs.assignment.logAnalysis.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestFiles {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("system"+System.lineSeparator());
        System.out.println("filesystem"+File.separator);
        File file= new File("C:\\Users\\abhus\\work\\inputData\\logFile\\log.txt");
        System.out.println("file.isFile()"+file.isFile());
        FileInputStream ss = new FileInputStream(file);
    }
}
