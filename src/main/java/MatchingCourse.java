import java.util.LinkedHashSet;
import java.util.Set;

public class MatchingCourse {
    private final String courseName;
    private final Set<String> instructorNames;
    private final String mostMatchingInstructor;
    private int number = 0;

    public MatchingCourse(String courseName, Set<String> instructorNames) {
        this.courseName = courseName;
        this.instructorNames = instructorNames;
        mostMatchingInstructor = setNumber();
    }

    public String setNumber() {
        String mostMatchingInstructor = "";
        for (String instructorName : instructorNames) {
            if (courseName.isEmpty() || instructorName.isEmpty()) {
                return "";
            } else {
                String courseNameTmp = removeDuplicates(courseName.toLowerCase()).replaceAll(" ", "");
                String instructorNameTmp = removeDuplicates(instructorName.toLowerCase())
                        .replaceAll(" ", "").replaceAll("[^\\p{IsLatin}]", "");
                int number = countMatchedLetters(courseNameTmp, instructorNameTmp);
                if(number > this.number){
                    mostMatchingInstructor = instructorName;
                    this.number = number;
                }
            }
        }
        return mostMatchingInstructor;
    }

    static int countMatchedLetters(String courseNameTmp, String instructorNameTmp){
        int matches = 0;

        for(char c : courseNameTmp.toCharArray()){
            if(instructorNameTmp.contains("" + c))
            {
                matches++;
            }
        }

        return matches;
    }

    static String removeDuplicates(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        Set<Character> added = new LinkedHashSet<>();
        StringBuilder result = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (added.add(c)) {
                result.append(c);
            }
        }
        return result.toString();
    }


    public int getNumber() {
        return this.number;
    }

    public String getMostMatchingInstructor() {
        return mostMatchingInstructor;
    }

    public String getCourseName() {
        return courseName;
    }

}
