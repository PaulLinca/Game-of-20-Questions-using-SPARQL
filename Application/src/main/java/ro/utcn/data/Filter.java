package ro.utcn.data;

public class Filter {
    public String attribute;
    public String value;

    public Filter(String attribute, String value) {
        this.attribute = attribute;
        this.value = value;
    }

    public String positiveFilter() {
        return "   ?character " + attribute + " " + value + " .\n";
    }

    public String negativeFilter() {
        return "   FILTER NOT EXISTS { ?character " + attribute + " " + value + "}.\n";
    }
}
