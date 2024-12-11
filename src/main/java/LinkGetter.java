import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class LinkGetter {

    public static String getCourseLinkFromElement(Element element) {
        String url = "";

        for (Node child : element.childNodes()) {
            if (child instanceof Element childElement) {
                if (childElement.text().contains("Strona przedmiotu")) {
                    Element link = ((Element) child).selectFirst("a");
                    if (link != null) {
                        url = link.attr("href");
                        break;
                    }
                }
            }
        }

        return url;
    }

}
