// pom.xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>spring-di-demo</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <spring.version>5.3.20</spring.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
    </dependencies>
</project>

// src/main/java/com/example/model/Course.java
package com.example.model;

public class Course {
    private String name;
    private String code;

    public Course(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}

// src/main/java/com/example/model/Student.java
package com.example.model;

public class Student {
    private String name;
    private int id;
    private Course course;

    public Student(String name, int id, Course course) {
        this.name = name;
        this.id = id;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public void displayInfo() {
        System.out.println("Student: " + name + " (ID: " + id + ")");
        System.out.println("Enrolled in: " + course);
    }
}

// src/main/java/com/example/config/AppConfig.java
package com.example.config;

import com.example.model.Course;
import com.example.model.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Course javaCourse() {
        return new Course("Java Programming", "CS101");
    }
    
    @Bean
    public Student student() {
        // Here we're injecting the javaCourse bean into the student bean
        return new Student("John Doe", 12345, javaCourse());
    }
}

// src/main/java/com/example/Application.java
package com.example;

import com.example.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.config.AppConfig;

public class Application {
    public static void main(String[] args) {
        // Create the Spring application context using our Java-based configuration
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        // Get the student bean from the context
        Student student = context.getBean(Student.class);
        
        // Display student information 
        student.displayInfo();
    }
}
