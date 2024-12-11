import java.io.IOException;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FetchDOM {
    public static void main(String[] args) {
        String url = "https://www.usosweb.uj.edu.pl/kontroler.php?_action=katalog2%2Fprzedmioty%2FszukajPrzedmiotu";
        Set<MatchingCourse> matchingCourses = new HashSet<>();

        long startTime = System.nanoTime();
        String[] spinner = {"|", "/", "-", "\\"};
        int spinnerIndex = 0;
        do {
            try {
                Document courseListDoc = Jsoup.connect(url).get();
                Elements elements = courseListDoc.select(".odd_row, .even_row");

                for (Element element : elements) {
                    String linkToCourse = LinkGetter.getCourseLinkFromElement(element);
                    Document courseDocument = Jsoup.connect(linkToCourse).get();

                    CourseConditions courseConditions = CourseConditionsGetter.getCourseConditions(courseDocument);

                    if (courseConditions.findsCriteria()) {
                        matchingCourses.add(MatchingCourseGetter
                                .getMatchingCourse(courseDocument, courseConditions.getCwiczeniaUrl()));
                    }
                }

                url = NextPage.getNextPageUrl(courseListDoc);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            long elapsedMinutes = (System.nanoTime() - startTime) / 1_000_000_000 / 60;
            System.out.print("\rElapsed time: " + elapsedMinutes + " min " + spinner[spinnerIndex++ % spinner.length]);
        } while (null != url && !url.isEmpty());

        List<MatchingCourse> matchingCourseList = new ArrayList<>(matchingCourses.stream().toList());
        matchingCourseList.sort(Comparator.comparing(MatchingCourse::getNumber).reversed());

        ResultSaver.saveResults(matchingCourseList);
    }
}
