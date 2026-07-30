package console.program;

import java.util.ArrayList;
import java.util.Scanner;

public class Search {

    Data data = new Data();
    ArrayList<data_record> datas = new ArrayList<>();
    Scanner scnr = new Scanner(System.in);

    public Search() {
        datas = data.getData("course-1.csv");
    }

    public ArrayList<String> getCourseList(String input) {
        Boolean courseExists = false;

        ArrayList<String> search_results = new ArrayList<>();

        while(!courseExists) {

            for (data_record data : datas) {
                String courseName = data.course_name.toLowerCase();
                String inputLower = input.toLowerCase();
                if (courseName.contains(inputLower)) {
                    search_results.add(data.course_name);
                    courseExists = true;
                }
            }
        }
        
        return search_results;
    }

    public void printList(String input) {
        ArrayList<String> results = getCourseList(input);

        String banner = new String(new char[50]).replace('\u0000', '-');   

        System.out.println(banner);        
        System.out.println("Select from matching list");
        System.out.println(banner);

        for(int i = 0; i < results.size(); ++i) {
            System.out.printf("  %d) %s\n", i + 1, results.get(i));
        }

        System.out.println("Please select: ");
        Integer desiredCourse = scnr.nextInt();

        if(desiredCourse >= 1 && desiredCourse <= results.size()) {
            /* 
                Enroll in a course if the student don't have enough credit for that 
                semester and don't let them enroll if they are overloading or something

                Add what ever enrolled to a csv maybe a db? check on requirement
            */
        }
    }
}
