package es.s2o.selenium.pages;

import net.serenitybdd.core.pages.PageObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class PageObjectBase extends PageObject {
    private Document document;

    protected Document getDocument() {
        if (document == null) {
            String pageSource = getDriver().getPageSource();
            document = Jsoup.parse(pageSource);
        }
        return document;
    }

    protected void resetDocument() {
        document = null;
    }
}
