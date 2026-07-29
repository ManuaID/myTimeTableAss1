package console.program;

import java.util.ArrayList;
import java.util.Scanner;

public class Search {

    Data data = new Data();
    ArrayList<data_record> datas = new ArrayList<>();

    public Search() {
        datas = data.getData("course-1.csv");
    }

    public ArrayList<String> getCourseList(String input) {
        Boolean courseExists = false;
        String inputString;

        ArrayList<String> search_results = new ArrayList<>();
        Scanner scnr = new Scanner(System.in);

        while(!courseExists) {

            for (data_record data : datas) {
                if (data.course_name.contains(inputString)) {
                    search_results.add(data.course_name);
                    courseExists = true;
                }
            }
        }
        
        scnr.close();
        return search_results;
    }

    public void printList(String input) {
        ArrayList<String> results = getCourseList(input);

        for(int i = 0; i < results.size(); ++i) {
            
        }
    }
}
