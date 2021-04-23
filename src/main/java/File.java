import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class File {
    private final List<String[]> arrayList = new ArrayList<String[]>();

    public File(String filepath) throws IOException {
        fullFillMap(filepath);
    }

    public int getArrayLength() {
        return arrayList.size();
    }

    public void fullFillMap(String filepath) throws IOException {
        int index = 0;
        String line = "";
        String[] pair = new String[5];
        int linesCounter = 0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath),"Unicode"));
        //"Cp1252"));
        line = reader.readLine();

        while (line != null) {
            pair = line.split(Pattern.quote("|"));
            arrayList.add(index, pair);
            index++;
            line = reader.readLine();
            linesCounter++;
        }

        System.out.println(linesCounter + " lines uploaded to array\n" +
            "Array size: " + arrayList.size());
        reader.close();
    }

    public String[] answers(int number) {

        String[] line = new String[6];
        line = arrayList.get(number);

        String[] answer;

        if (line[0].equals("3")) {
            answer = new String[3];
            answer[0] = line[2];
            answer[1] = line[3];
            answer[2] = line[4];
        } else {
            answer = new String[2];
            answer[0] = "a) Verdadero.";
            answer[1] = "b) Falso.";
        }

        return answer;
    }

    public String question(int number) {
        return arrayList.get(number)[1];
    }

    public int correctOption(int number) {
        String correctAnswer = arrayList.get(number)[arrayList.get(number).length - 1];
        int answer = 0;

        if (correctAnswer.equals("a")) {
            answer = 0;
        } else if (correctAnswer.equals("b")) {
            answer = 1;
        } else if (correctAnswer.equals("c")) {
            answer = 2;
        }
        return answer;
    }
}
