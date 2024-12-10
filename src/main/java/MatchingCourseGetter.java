import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;

import java.util.Set;

public class MatchingCourseGetter {

    public static MatchingCourse getMatchingCourse(Document courseDocument, String cwiczeniaUrl) {
        String courseName = getCourseName(courseDocument);
        Set<String> InstructorNames = getInstructorName(cwiczeniaUrl);

        return new MatchingCourse(courseName, InstructorNames);
    }

    private static Set<String> getInstructorName(String url) {
        Set<String> instructorNames = new HashSet<>();
        try {
            Document cwiczeniaDoc = Jsoup.connect(url).get();
            Elements tables = cwiczeniaDoc.select("table");

            for (Element table : tables) {
                Elements headers = table.select("th");
                for (Element header : headers) {
                    if (header.text().equals("Prowadzący")) {
                        Elements rows = table.select("tr");

                        for (Element row : rows) {
                            Elements links = row.select("td:nth-child(3) a");
                            for (Element link : links) {
                                instructorNames.add(link.text());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println();
        }

        return instructorNames;
    }

    private static String getCourseName(Document courseDocument) {
        Element row = courseDocument.selectFirst("tr:contains(Nazwa przedmiotu:)");
        String courseName = "";

        if (row != null) {
            Element courseNameElement = row.selectFirst("td:nth-of-type(2)");
            if (courseNameElement != null) {
                courseName = courseNameElement.text();
            }
        }
        return courseName;
    }
}
