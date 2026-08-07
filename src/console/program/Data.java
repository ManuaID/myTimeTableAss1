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
    public float duration;
    public int num_curr_enrollment;

    public data_record(String course_name, int capacity, String year, String Delivery_mode, String classDates, LocalTime classTime, float durationLec, int num_curr_enrollment) {
        this.course_name = course_name;
        this.capacity = capacity;
        this.year = year;
        this.Delivery_mode = Delivery_mode;
        this.classDates = classDates;
        this.classTime = classTime;
        this.duration = durationLec;
        this.num_curr_enrollment = num_curr_enrollment;
    }

    public void printData() {
        System.out.println("Course Name: " + course_name);
        System.out.println("Capacity: " + capacity);
        System.out.println("Year: " + year);
        System.out.println("Delivery Mode: " + Delivery_mode);
        System.out.println("Class Dates: " + classDates);
        System.out.println("Class Time: " + classTime);
        System.out.println("Duration of Lecture: " + duration);
        System.out.println("Number of current enrollment: " + num_curr_enrollment);
    }
}

public class Data {
    private String fileName;

    private ArrayList<data_record> datas = null;

    String banner = new String(new char[50]).replace('\u0000', '-');

    Scanner sc = new Scanner(System.in);

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

class StudentEnrollment extends Data {
    public ArrayList<data_record> enrolled_class = new ArrayList<>();

    public void enroll(data_record courseClass) {
        for (int i = 0; i < enrolled_class.size(); ++i) {

            if (courseClass.course_name.equals(enrolled_class.get(i).course_name)) {
                System.out.println("You have already enrolled in this course");
                return;
            }

            if (courseClass.Delivery_mode.equals("Face-to-face") && courseClass.capacity <= courseClass.num_curr_enrollment) {
                System.out.println("Sorry. This course has reached its maximum capacity");
                return;
            }

            LocalTime enrolledEndTime = enrolled_class.get(i).classTime.plusHours((long) enrolled_class.get(i).duration);
            LocalTime newEndTime = courseClass.classTime.plusMinutes((long) (courseClass.duration * 60));
            boolean overlaps = courseClass.classTime.isBefore(enrolledEndTime) && enrolled_class.get(i).classTime.isBefore(newEndTime);

            if (overlaps) {
                System.out.printf("You cannot enrol in %s because it conflicts with %s.%n", courseClass.course_name, enrolled_class.get(i).course_name);
                return;
            }
        }

        enrolled_class.add(courseClass);
        System.out.printf("You have enrolled in the course %s\n", courseClass.course_name);
    }

    public void showEnrollment() {
        int i = 1;
        System.out.println(banner);
        System.out.println("You have enrolled into the following course(s):");
        System.out.println(banner);
        printEnrolledList();
    }

    public void Withdraw() {
        
        System.out.println(banner);
        System.out.println("Please choose a course to withdraw:");
        System.out.println(banner);

        printEnrolledList();
        
        System.out.print("Please select: ");
        int withdrawInput = sc.nextInt();

        System.out.printf("You have withdrawn from %s\n", enrolled_class.get(withdrawInput).course_name);

        if(withdrawInput >= 1 && withdrawInput < enrolled_class.size()) {
            enrolled_class.remove(enrolled_class.get(withdrawInput));
        }
        
    }

    private void printEnrolledList() {
        int i = 1;
        for (data_record course: enrolled_class) {
            LocalTime courseEndTime = course.classTime.plusMinutes((long) (course.duration * 60));
            System.out.printf("%d) %s\t%s\t%s %s - %s%n", i, course.course_name, course.Delivery_mode, course.classDates, course.classTime, courseEndTime);
            i++;
        }
    }
}
