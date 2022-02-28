package es.s2o.selenium.core.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PageObjectBase extends PageObject {
    @FindBy(css = "#content > div > h2")
    private WebElementFacade title;

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

    public String getTitle(){
        return title.getText();
    }
}
