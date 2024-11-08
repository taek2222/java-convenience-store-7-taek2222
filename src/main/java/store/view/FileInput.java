package store.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileInput {

    public List<List<String>> readFile(String fileName) {
        BufferedReader fileContents = loadFileContents(fileName);
        return parseFileContents(fileContents);
    }

    private BufferedReader loadFileContents(String fileName) {
        try {
            File file = new File("src/main/resources/" + fileName);
            FileReader fileReader = new FileReader(file);
            return new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
    }

    private List<List<String>> parseFileContents(BufferedReader fileContents) {
        List<List<String>> listList = new ArrayList<>();

        fileContents.lines().forEach(line -> {
            String[] split = line.split(",");
            List<String> list = List.of(split);
            listList.add(list);
        });

        return listList;
    }
}
