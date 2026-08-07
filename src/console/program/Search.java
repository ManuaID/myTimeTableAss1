package console.program;

import java.util.ArrayList;
import java.util.Scanner;

public class Search {

    StudentEnrollment curStudentEnrollment = new StudentEnrollment();

    Data data = new Data();
    ArrayList<data_record> datas = new ArrayList<>();
    Scanner scnr = new Scanner(System.in);

    public Search() {
        datas = data.getData("course-1.csv");
    }

    public ArrayList<data_record> getCourseList(String input) {
        ArrayList<data_record> search_results = new ArrayList<>();
        String inputLower = input.toLowerCase();

        for (data_record data : datas) {
            String courseName = data.course_name.toLowerCase();
            if (courseName.contains(inputLower)) {
                search_results.add(data);
            }
        }

        return search_results;
    }

    public void printList(String input) {
        ArrayList<data_record> results = getCourseList(input);

        String banner = new String(new char[50]).replace('\u0000', '-');   

        System.out.println(banner);        
        System.out.println("Select from matching list");
        System.out.println(banner);

        for(int i = 0; i < results.size(); ++i) {
            System.out.printf("  %d) %s\n", i + 1, results.get(i).course_name);
        }

        System.out.print("Please select: ");
        int desiredCourse = scnr.nextInt();

        if(desiredCourse >= 1 && desiredCourse <= results.size()) {
            curStudentEnrollment.enroll(results.get(desiredCourse - 1)); 
        }
    }
}
