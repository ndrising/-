package architectures;

import java.io.*;
import java.util.*;

interface Observer {
    void update(List<String> shiftedLines);
}

class ShiftObserver implements Observer {
    @Override
    public void update(List<String> shiftedLines) {
        System.out.println("ShiftObserver: Shifted lines received for processing.");
    }
}

class AlphabetizerObserver implements Observer {
    @Override
    public void update(List<String> shiftedLines) {
        Collections.sort(shiftedLines);
        System.out.println("AlphabetizerObserver: Lines sorted alphabetically.");
    }
}

class KWICSubject {
    private List<Observer> observers = new ArrayList<>();
    private List<String> shiftedLines = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(shiftedLines);
        }
    }

    public void inputAndShift(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] words = line.split(" ");
            for (int i = 0; i < words.length; i++) {
                String shiftedLine = String.join(" ", Arrays.copyOfRange(words, i, words.length)) + " "
                        + String.join(" ", Arrays.copyOfRange(words, 0, i));
                shiftedLines.add(shiftedLine.trim());
            }
        }
        reader.close();
        notifyObservers();
    }

    public List<String> getShiftedLines() {
        return shiftedLines;
    }
}

public class EventSystemKWIC {
    public static void main(String[] args) throws IOException {
        KWICSubject kwic = new KWICSubject();
        kwic.addObserver(new ShiftObserver());
        kwic.addObserver(new AlphabetizerObserver());

        kwic.inputAndShift("D:\\YouShanFeiTxt\\demo1input.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\YouShanFeiTxt\\demo1output.txt"));
        for (String line : kwic.getShiftedLines()) {
            writer.write(line + "\n");
        }
        writer.close();
    }
}
