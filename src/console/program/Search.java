package console.program;

import java.util.ArrayList;

public class Search {

    Data data = new Data();
    ArrayList<data_record> datas = new ArrayList<>();

    public Search() {
        datas = data.getData("course-1.csv");
    }

    public ArrayList<String> getCourseList(String input) {
        Boolean courseExists = false;

        ArrayList<String> search_results = new ArrayList<>();

        while(!courseExists) {

            for (data_record data : datas) {
                if (data.course_name.contains(input)) {
                    search_results.add(data.course_name);
                    courseExists = true;
                }
            }
        }
        
        return search_results;
    }

    public void printList(String input) {
        ArrayList<String> results = getCourseList(input);

        for(int i = 0; i < results.size(); ++i) {
            System.out.printf("  %d) %s", i + 1, results.get(i));
        }
        System.out.println("\n");
    }
}
