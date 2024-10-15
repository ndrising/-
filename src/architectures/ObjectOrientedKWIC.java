package architectures;

import java.io.*;
import java.util.*;

class Input {
    private List<String> lines = new ArrayList<>();

    public void input(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
    }

    public List<String> getLines() {
        return lines;
    }
}

class Shift {
    private List<String> shiftedLines = new ArrayList<>();

    public void shift(List<String> lines) {
        for (String line : lines) {
            String[] words = line.split(" ");
            for (int i = 0; i < words.length; i++) {
                String shiftedLine = String.join(" ", Arrays.copyOfRange(words, i, words.length)) + " "
                        + String.join(" ", Arrays.copyOfRange(words, 0, i));
                shiftedLines.add(shiftedLine.trim());
            }
        }
    }

    public List<String> getShiftedLines() {
        return shiftedLines;
    }
}

class Alphabetizer {
    public void alphabetize(List<String> lines) {
        Collections.sort(lines);
    }
}

class Output {
    public void output(List<String> lines, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (String line : lines) {
            writer.write(line + "\n");
        }
        writer.close();
    }
}

public class ObjectOrientedKWIC {
    public static void main(String[] args) throws IOException {
        Input input = new Input();
        input.input("D:\\YouShanFeiTxt\\demo1input.txt");

        Shift shift = new Shift();
        shift.shift(input.getLines());

        Alphabetizer alphabetizer = new Alphabetizer();
        alphabetizer.alphabetize(shift.getShiftedLines());

        Output output = new Output();
        output.output(shift.getShiftedLines(), "D:\\YouShanFeiTxt\\demo1output.txt");
    }
}
