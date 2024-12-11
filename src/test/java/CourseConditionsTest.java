import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CourseConditionsTest {

    @Test
    public void testFindsCriteria() {
        CourseConditions course = new CourseConditions("polski", true, "http://example.com", true);
        assertTrue(course.findsCriteria(), "Criteria should be met");

        course = new CourseConditions("polski", true, "", true);
        assertFalse(course.findsCriteria(), "Criteria should not be met when URL is empty");

        course = new CourseConditions("polski", false, "http://example.com", true);
        assertFalse(course.findsCriteria(), "Criteria should not be met when 'hasCwiczenia' is false");

        course = new CourseConditions("english", true, "http://example.com", true);
        assertFalse(course.findsCriteria(), "Criteria should not be met when language is not 'polski'");

        course = new CourseConditions("polski", true, "http://example.com", false);
        assertFalse(course.findsCriteria(), "Criteria should not be met when 'isCurrent' is false");
    }
}
