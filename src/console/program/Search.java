package console.program;

public class Search {
    private String input;

    Data data = new Data();

    public Search() {
        data.getData("course-1.csv");
    }

    public boolean courseExists(String input) {
        Boolean courseExists = false;
        while(!courseExists) {
            System.out.print("");
        }
        return false;
    }
}
