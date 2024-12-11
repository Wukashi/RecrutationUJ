import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResultSaverTest {
    @Test
    public void testGetTop10_withDuplicatesAtPositions8to13() {
        List<MatchingCourse> matchingCourses = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            matchingCourses.add(new MatchingCourse("Course " + i, new HashSet<>(List.of("Instructor " + i))));
        }
        for (int i = 8; i <= 13; i++) {
            matchingCourses.add(new MatchingCourse("Course", new HashSet<>(List.of("Instructor 20"))));
        }

        List<MatchingCourse> result = ResultSaver.getTop10(matchingCourses);

        assertTrue(result.size() >= 10, "The result list should contain at least 10 elements.");

    }
}
