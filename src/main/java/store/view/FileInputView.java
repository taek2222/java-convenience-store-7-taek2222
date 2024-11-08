package store.view;

import static store.constant.ErrorMessage.FILE_CONTAINS_BLANK_CONTENT;
import static store.constant.ErrorMessage.FILE_NOT_FOUND;
import static store.validation.CommonValidator.validateBlank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import store.exception.FileException;

public class FileInputView {

    private static final String DIRECTORY_PATH = "src/main/resources/";
    private static final String LINE_DELIMITER = ",";

    public List<List<String>> readFile(String fileName) {
        BufferedReader fileReader = openFileReader(fileName);
        return parseLines(fileReader);
    }

    private BufferedReader openFileReader(String fileName) {
        try {
            File file = new File(DIRECTORY_PATH + fileName);
            FileReader fileReader = new FileReader(file);
            return new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            throw new FileException(FILE_NOT_FOUND, fileName);
        }
    }

    private List<List<String>> parseLines(BufferedReader fileReader) {
        return fileReader.lines()
                .map(this::parseLine)
                .toList();
    }

    private List<String> parseLine(String line) {
        return Arrays.stream(line.split(LINE_DELIMITER))
                .peek(element -> validateBlank(element, FILE_CONTAINS_BLANK_CONTENT))
                .toList();
    }
}
