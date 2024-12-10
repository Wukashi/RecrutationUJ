import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class NextPage {
    public static String getNextPageUrl(Document doc) {
        Element tableNavBar = doc.select("table-nav-bar").first();
        String nextPageUrl = "";

        if (tableNavBar != null) {
            nextPageUrl = tableNavBar.attr("next-page-url");
        }

        return nextPageUrl;
    }
}
