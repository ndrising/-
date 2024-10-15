package architectures;

import java.io.*;
import java.util.*;

interface Filter {
    List<String> process(List<String> input);
}

class InputFilter implements Filter {
    @Override
    public List<String> process(List<String> input) {
        return input;
    }
}

class ShiftFilter implements Filter {
    @Override
    public List<String> process(List<String> lines) {
        List<String> shiftedLines = new ArrayList<>();
        for (String line : lines) {
            String[] words = line.split(" ");
            for (int i = 0; i < words.length; i++) {
                String shiftedLine = String.join(" ", Arrays.copyOfRange(words, i, words.length)) + " "
                        + String.join(" ", Arrays.copyOfRange(words, 0, i));
                shiftedLines.add(shiftedLine.trim());
            }
        }
        return shiftedLines;
    }
}

class AlphabetizerFilter implements Filter {
    @Override
    public List<String> process(List<String> lines) {
        Collections.sort(lines);
        return lines;
    }
}

class OutputFilter implements Filter {
    @Override
    public List<String> process(List<String> lines) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\YouShanFeiTxt\\demo1output.txt"));
            for (String line : lines) {
                writer.write(line + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}

public class PipelineFilterKWIC {
    public static void main(String[] args) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("D:\\YouShanFeiTxt\\demo1input.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();

        Filter inputFilter = new InputFilter();
        Filter shiftFilter = new ShiftFilter();
        Filter alphabetizerFilter = new AlphabetizerFilter();
        Filter outputFilter = new OutputFilter();

        List<String> processedLines = inputFilter.process(lines);
        processedLines = shiftFilter.process(processedLines);
        processedLines = alphabetizerFilter.process(processedLines); outputFilter.process(processedLines); } }
