package com.example.springboot.data;

import com.example.springboot.domain.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findByLastName(String lastName);
    Student findById(long id);

    @Query("Select s from Student s where s.activeStatus = 1")
    List<Student> findAllActiveStudents();

    List<Student> findAll();

    @Query("Select s from Student s order by s.firstName")
    List<Student> findAllFirstNameSort();
}
