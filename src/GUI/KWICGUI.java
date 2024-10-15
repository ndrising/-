package GUI;

import architectures.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class KWICGUI {
    private JFrame frame;
    private JButton btnMainSubroutine;
    private JButton btnObjectOriented;
    private JButton btnEventSystem;
    private JButton btnPipelineFilter;
    private JTextArea outputArea;
    private JTextArea infoArea;
    private JTextArea codeArea;

    public KWICGUI() {
        frame = new JFrame("KWIC 教学软件");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        btnMainSubroutine = new JButton("主程序-子程序架构");
        btnObjectOriented = new JButton("面向对象架构");
        btnEventSystem = new JButton("事件系统架构");
        btnPipelineFilter = new JButton("管道-过滤器架构");

        buttonPanel.add(btnMainSubroutine);
        buttonPanel.add(btnObjectOriented);
        buttonPanel.add(btnEventSystem);
        buttonPanel.add(btnPipelineFilter);

        JPanel textPanel = new JPanel(new GridLayout(1, 2));

        infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText("请选择一种软件体系结构以查看详细信息。");
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setFont(new Font("Serif", Font.PLAIN, 16));

        codeArea = new JTextArea();
        codeArea.setEditable(false);
        codeArea.setText("该架构的关键代码将在此显示。");
        codeArea.setFont(new Font("Serif", Font.PLAIN, 16));

        textPanel.add(new JScrollPane(infoArea));
        textPanel.add(new JScrollPane(codeArea));

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Serif", Font.PLAIN, 16));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setText("KWIC 处理结果将在此显示。");

        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(textPanel, BorderLayout.CENTER);
        frame.add(outputArea, BorderLayout.SOUTH);

        btnMainSubroutine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runMainSubroutine();
            }
        });

        btnObjectOriented.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runObjectOriented();
            }
        });

        btnEventSystem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runEventSystem();
            }
        });

        btnPipelineFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runPipelineFilter();
            }
        });

        frame.setVisible(true);
    }

    private void runMainSubroutine() {
        displayInfo("主程序-子程序架构",
                "该架构是将程序划分为一系列子程序，每个子程序执行特定任务，主程序按顺序调用这些任务。KWIC 过程包括输入、循环移位、字母顺序排序和输出。",
                getMainSubroutineCode());

        executeKWICProcess(new MainSubroutineKWIC());
        displayOutput();
    }

    private void runObjectOriented() {
        displayInfo("面向对象架构",
                "面向对象架构将程序划分为不同的类，每个类负责 KWIC 系统的不同部分。类之间通过方法调用进行协作，完成 KWIC 过程。",
                getObjectOrientedCode());

        executeKWICProcess(new ObjectOrientedKWIC());
        displayOutput();
    }

    private void runEventSystem() {
        displayInfo("事件系统架构",
                "事件系统架构基于观察者模式，某些观察者在事件发生时自动被通知并作出响应。比如，当 KWIC 过程中的某一变化发生时，观察者会对该变化作出反应。",
                getEventSystemCode());

        executeKWICProcess(new EventSystemKWIC());
        displayOutput();
    }

    private void runPipelineFilter() {
        displayInfo("管道-过滤器架构",
                "管道-过滤器架构通过一系列过滤器类逐步处理数据流。KWIC 的过滤器包括输入、移位、排序和输出，每一步都会将数据传递给下一个过滤器。",
                getPipelineFilterCode());

        executeKWICProcess(new PipelineFilterKWIC());
        displayOutput();
    }

    private void displayInfo(String title, String description, String code) {
        infoArea.setText(title + "\n\n" + description);
        codeArea.setText(code);
    }

    private void executeKWICProcess(Object kwicInstance) {
        outputArea.setText("正在运行 KWIC 处理...\n");
        try {
            if (kwicInstance instanceof MainSubroutineKWIC) {
                MainSubroutineKWIC.main(null);
            } else if (kwicInstance instanceof ObjectOrientedKWIC) {
                ObjectOrientedKWIC.main(null);
            } else if (kwicInstance instanceof EventSystemKWIC) {
                EventSystemKWIC.main(null);
            } else if (kwicInstance instanceof PipelineFilterKWIC) {
                PipelineFilterKWIC.main(null);
            }
            outputArea.append("处理完成，输出结果如下：\n");
        } catch (IOException e) {
            outputArea.append("错误：" + e.getMessage() + "\n");
        }
    }

    private void displayOutput() {
        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\YouShanFeiTxt\\demo1output.txt"))) {
            String line;
            outputArea.append("\n=== 输出结果 ===\n");
            while ((line = reader.readLine()) != null) {
                outputArea.append(line + "\n");
            }
        } catch (IOException e) {
            outputArea.append("读取输出文件时发生错误：" + e.getMessage());
        }
    }

    private String getMainSubroutineCode() {
        return "public void main() {\n" +
                "   input();\n" +
                "   shift();\n" +
                "   alphabetize();\n" +
                "   output();\n" +
                "}\n";
    }

    private String getObjectOrientedCode() {
        return "class KWIC {\n" +
                "   Input input = new Input();\n" +
                "   Shift shift = new Shift(input.getLines());\n" +
                "   Alphabetizer alphabetizer = new Alphabetizer(shift.getShiftedLines());\n" +
                "   Output output = new Output(alphabetizer.getSortedLines());\n" +
                "}\n";
    }

    private String getEventSystemCode() {
        return "class KWICSubject {\n" +
                "   addObserver(new AlphabetizerObserver());\n" +
                "   notifyObservers();\n" +
                "}\n";
    }

    private String getPipelineFilterCode() {
        return "class Pipeline {\n" +
                "   Filter input = new InputFilter();\n" +
                "   Filter shift = new ShiftFilter();\n" +
                "   Filter alphabetize = new AlphabetizerFilter();\n" +
                "   Filter output = new OutputFilter();\n" +
                "}\n";
    }

    public static void main(String[] args) {
        new KWICGUI();
    }
}
