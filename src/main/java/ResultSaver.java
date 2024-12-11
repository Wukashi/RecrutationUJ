import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ResultSaver {
    public static void saveResults(List<MatchingCourse> matchingCourses) {
        List<MatchingCourse> top10 = getTop10(matchingCourses);
        saveToTxtFile(top10);
    }

    private static void saveToTxtFile(List<MatchingCourse> top10) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String fileName = "top10List_" + now.format(formatter) + ".txt";
        appendToFile(fileName, "Polskie nazwy przedmiotów z największą liczbą liter" +
                " wspólnych w imieniu i nazwisku ćwiczeniowca dla tego przedmiotu:\n");
        for(int i = 0; i < top10.size(); i++){
            if(i == 10) {
                appendToFile(fileName, "=".repeat(40) + " ex aequo " + "=".repeat(40));
            }

            String content = "Miejsce " + (i + 1) + ". kurs: " + top10.get(i).getCourseName() +
                    ", prowadzący: " + top10.get(i).getMostMatchingInstructor() +
                    ", liczba wspólnych liter: " + top10.get(i).getNumber();

            appendToFile(fileName, content);
        }
    }

    public static void appendToFile(String fileName, String text) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(text + System.lineSeparator());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    static List<MatchingCourse> getTop10(List<MatchingCourse> matchingCourses){
        List<MatchingCourse> top10 = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            top10.add(matchingCourses.get(i));
            if(i == 9) {
                int j = 10;

                while (j < matchingCourses.size() && matchingCourses.get(i).getNumber() == matchingCourses.get(j).getNumber()){
                    top10.add(matchingCourses.get(j));
                    j++;
                }
            }
        }
        return top10;
    }
}
