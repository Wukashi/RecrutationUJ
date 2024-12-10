import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CourseConditionsGetter {
    public static CourseConditions getCourseConditions(Document doc) {
        CourseConditions courseConditions = new CourseConditions();

        setLanguage(doc, courseConditions);
        setHasCwiczeniaUrlAndTerm(doc, courseConditions);

        return courseConditions;

    }

    private static void setHasCwiczeniaUrlAndTerm(Document doc, CourseConditions courseConditions) {
        boolean hasCwiczenia = false;
        String url = "";
        boolean isCurrent = false;

        Element frame = doc.selectFirst("usos-frame.zajecia");
        if (frame != null) {
            Element header = frame.selectFirst("h2");
            if (header != null && header.text().contains("Zajęcia w cyklu") &&
                    (header.text().contains("Semestr zimowy 2024/2025") || header.text().contains("Rok akademicki 2024/2025"))) {
                isCurrent = true;
                Element typZajecTd = doc.selectFirst("td.strong:contains(Typ zajęć:)");
                if (typZajecTd != null) {
                    Element valueTd = typZajecTd.nextElementSibling();
                    if (valueTd != null) {
                        if (valueTd.text().contains("Ćwiczenia")) {
                            hasCwiczenia = true;
                            Element link = valueTd.select("div:contains(Ćwiczenia) a").first();
                            if (link != null) {
                                url = link.attr("href");
                            }
                        }
                    }
                }
            }
        }
        courseConditions.setHasCwiczenia(hasCwiczenia);
        courseConditions.setCwiczeniaUrl(url);
        courseConditions.setIsCurrent(isCurrent);
    }

    private static void setLanguage(Document doc, CourseConditions courseConditions) {
        String language = "";
        Element keyTd = doc.selectFirst("td.strong:contains(Język prowadzenia:)");
        if (keyTd != null) {
            Element valueTd = keyTd.nextElementSibling();
            if (valueTd != null) {
                language = valueTd.text().trim();
            }
        }
        courseConditions.setLanguage(language);
    }
}
