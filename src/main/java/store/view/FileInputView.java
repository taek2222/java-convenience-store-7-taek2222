package store.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class FileInputView {

    public List<List<String>> readFile(String fileName) {
        BufferedReader fileReader = openFileReader(fileName);
        return parseLines(fileReader);
    }

    private BufferedReader openFileReader(String fileName) {
        try {
            File file = new File("src/main/resources/" + fileName);
            FileReader fileReader = new FileReader(file);
            return new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
    }

    private List<List<String>> parseLines(BufferedReader fileReader) {
        return fileReader.lines()
                .map(this::parseLine)
                .toList();
    }

    private List<String> parseLine(String line) {
        return Arrays.stream(line.split(","))
                .toList();
    }
}
