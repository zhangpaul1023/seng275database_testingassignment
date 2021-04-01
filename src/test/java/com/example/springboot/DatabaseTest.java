package com.example.springboot;

import com.example.springboot.data.StudentRepository;
import com.example.springboot.domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DatabaseTest {

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void setUp(){
    }

    @Test
    public void createTest() {
        Student student = new Student("Shuhao","Zhang", 1);
        studentRepository.save(student);
        assertFalse(studentRepository.findByLastName("Zhang").isEmpty());
        assertFalse(studentRepository.findAllActiveStudents().isEmpty());
    }

    @Test
    public void deleteTest() {
        Student studentToBeDelete = new Student("Yuanyuan","Liu", 1);
        studentRepository.save(studentToBeDelete);
        studentRepository.delete(studentToBeDelete);
        assertTrue(studentRepository.findByLastName("Liu").isEmpty());
    }

    @Test
    public void findByLastNameTest() {
        Student student1 = new Student("Shuhao","Zhang", 1);
        Student student2 = new Student("Yuanyuan","Liu", 1);
        Student student3 = new Student("Quan","Wang", 1);
        Student student4 = new Student("Wenbin","Song", 1);
        Student student5 = new Student("Qianhe","Sun", 1);
        Student student6 = new Student("Shuqi","Liao", 1);
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);
        studentRepository.save(student5);
        studentRepository.save(student6);
        assertFalse(studentRepository.findByLastName("Zhang").isEmpty());
    }

    @Test
    public void findAllActiveStudentsTest() {
        Student student1 = new Student("Shuhao","Zhang", 1);
        Student student2 = new Student("Yuanyuan","Liu", 1);
        Student student3 = new Student("Quan","Wang", 1);
        Student student4 = new Student("Wenbin","Song", 0);
        Student student5 = new Student("Qianhe","Sun", 0);
        Student student6 = new Student("Shuqi","Liao", 0);
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);
        studentRepository.save(student5);
        studentRepository.save(student6);
        List<Student> trueActiveList = new ArrayList<Student>();
        trueActiveList.add(student1);
        trueActiveList.add(student2);
        trueActiveList.add(student3);
        List<Student> activeList = studentRepository.findAllActiveStudents();
        assertEquals(trueActiveList, activeList);
    }

    @Test
    public void sortedFirstNamesTest() {
        Student student1 = new Student("a","Zhang", 1);
        Student student2 = new Student("b","Liu", 1);
        Student student3 = new Student("c","Wang", 1);
        Student student4 = new Student("d","Song", 0);
        Student student5 = new Student("e","Sun", 0);
        Student student6 = new Student("f","Liao", 0);
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);
        studentRepository.save(student5);
        studentRepository.save(student6);
        List<Student> trueSortList = new ArrayList<Student>();
        trueSortList.add(student1);
        trueSortList.add(student2);
        trueSortList.add(student3);
        trueSortList.add(student4);
        trueSortList.add(student5);
        trueSortList.add(student6);
        assertEquals(trueSortList, studentRepository.findAllFirstNameSort());
    }
}
