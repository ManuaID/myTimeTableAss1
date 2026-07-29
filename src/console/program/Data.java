package console.program;

import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

class data_record {
    public String course_name;
    public int capacity;
    public String year;
    public String Delivery_mode;
    public String classDates;
    public LocalTime classTime;
    public float durationLec;
    public int num_curr_enrollment;

    public data_record(String course_name, int capacity, String year, String Delivery_mode, String classDates, LocalTime classTime, float durationLec, int num_curr_enrollment) {
        this.course_name = course_name;
        this.capacity = capacity;
        this.year = year;
        this.Delivery_mode = Delivery_mode;
        this.classDates = classDates;
        this.classTime = classTime;
        this.durationLec = durationLec;
        this.num_curr_enrollment = num_curr_enrollment;
    }

    public void printData() {
        System.out.println("Course Name: " + course_name);
        System.out.println("Capacity: " + capacity);
        System.out.println("Year: " + year);
        System.out.println("Delivery Mode: " + Delivery_mode);
        System.out.println("Class Dates: " + classDates);
        System.out.println("Class Time: " + classTime);
        System.out.println("Duration of Lecture: " + durationLec);
        System.out.println("Number of current enrollment: " + num_curr_enrollment);
    }
}

public class Data {
    private String fileName;

    private ArrayList<data_record> datas = null;

    public Data() {
        datas = new ArrayList<>();
    }

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("H:mm");

    public ArrayList<data_record> getData(String fileName) {
        this.fileName = fileName;

        File file = new File(fileName);

        
        try {
            Scanner inputStream = new Scanner(file);
            inputStream.nextLine();


            while(inputStream.hasNextLine()) {
                String data = inputStream.nextLine();
                
                String values[] = data.split(",");
                
                int class_capacity = -1;

                if(!values[1].equals("n/a")) {
                    class_capacity = Integer.parseInt(values[1]);
                }

                data_record value = new data_record(
                    values[0],
                    class_capacity,
                    values[2],
                    values[3],
                    values[4],
                    LocalTime.parse(values[5], TIME_FORMAT),
                    Float.parseFloat(values[6]),
                    Integer.parseInt(values[7])
                );

                datas.add(value);
            }

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datas;
    }

    public int getSize() {
        return datas.size();
    }
}
