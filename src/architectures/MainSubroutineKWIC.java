package architectures;

import java.io.*;
import java.util.*;

public class MainSubroutineKWIC {
    private List<String> lines = new ArrayList<>();
    private List<String> shiftedLines = new ArrayList<>();

    // 读取输入文件
    public void input(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
    }

    // 循环移位
    public void shift() {
        for (String line : lines) {
            String[] words = line.split(" ");
            for (int i = 0; i < words.length; i++) {
                String shiftedLine = String.join(" ", Arrays.copyOfRange(words, i, words.length)) + " "
                        + String.join(" ", Arrays.copyOfRange(words, 0, i));
                shiftedLines.add(shiftedLine.trim());
            }
        }
    }

    // 按字母顺序排序
    public void alphabetize() {
        Collections.sort(shiftedLines);
    }

    // 输出到文件
    public void output(String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (String line : shiftedLines) {
            writer.write(line + "\n");
        }
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        MainSubroutineKWIC kwic = new MainSubroutineKWIC();
        kwic.input("D:\\YouShanFeiTxt\\demo1input.txt");
        kwic.shift();
        kwic.alphabetize();
        kwic.output("D:\\YouShanFeiTxt\\demo1output.txt");
    }
}
