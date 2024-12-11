import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MatchingCourseTest {
    @Test
    public void testRemoveDuplicates() {
        assertEquals("abc", MatchingCourse.removeDuplicates("aabbcc"), "Should remove duplicate characters");

        assertEquals("ban", MatchingCourse.removeDuplicates("banana"), "Should remove duplicate characters");

        assertNull(MatchingCourse.removeDuplicates(null), "Should return null for null input");

    }
    @Test
    public void testCountMatchedLetters() {
        assertEquals(4, MatchingCourse.countMatchedLetters("java", "vasja"), "Should match the correct number of letters");

        assertEquals(3, MatchingCourse.countMatchedLetters("aaa", "aaab"), "Should count duplicate letters correctly");
    }
}
