package de.doal.haw.mensa;

import java.util.ArrayList;

public class Mensa {

    static int STUDENTS_NUM = 20;
    static int CASHIERS_NUM = 3;
    static int OPENING_TIME = 5000; // in millisecond
    static int EATING_TIME = 500; // in millisecond
    static int PAYING_TIME = 200; // in millisecond

    static ArrayList<Cashier> availableCashiers = new ArrayList<>();
    static ArrayList<Student> payingStudents = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        init();

        System.err.println("----- Mensa is opening -----");

        availableCashiers.forEach(Cashier::startWorking);

        System.err.println("----- Mensa is open -----");

        payingStudents.forEach(Student::pay);

        Thread.sleep(OPENING_TIME);

        System.err.println("----- Mensa closing -----");

        availableCashiers.forEach(Thread::interrupt);
        for (Cashier cashier : availableCashiers) cashier.join();
        payingStudents.forEach(Thread::interrupt);
        for (Student student : payingStudents) student.join();

        System.err.println("----- Mensa is closed and no students left -----");
    }


    private static void init() {
        for (int i = 0; i < CASHIERS_NUM; i++)
            availableCashiers.add(new Cashier("Cashier_"+i, PAYING_TIME));

        for (int i = 0; i < STUDENTS_NUM; i++)
            payingStudents.add(new Student("Student_"+i, EATING_TIME, availableCashiers));
    }

}

