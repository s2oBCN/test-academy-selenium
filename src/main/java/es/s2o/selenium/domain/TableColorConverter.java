package es.s2o.selenium.domain;

import cucumber.deps.com.thoughtworks.xstream.converters.basic.StringConverter;

public class TableColorConverter extends StringConverter {
    public Object fromString(String color) {
        return color.startsWith("#") ? color : Colors.valueOf(color).getColorCode();
    }
}
