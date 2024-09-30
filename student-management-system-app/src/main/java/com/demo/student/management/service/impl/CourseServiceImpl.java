package com.demo.student.management.service.impl;

import com.demo.student.management.entity.Course;
import com.demo.student.management.entity.CourseSchedule;
import com.demo.student.management.entity.Student;
import com.demo.student.management.repository.CourseRepository;
import com.demo.student.management.repository.StudentRepository;
import com.demo.student.management.service.CourseService;
import com.demo.student.management.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final JwtUtil jwtUtil;
    @Override
    public void registerToCourse(Long courseId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Student student = studentRepository.findByEmail(username);
        courseRepository.findById(courseId);
        ArrayList<Course> courseArrayList = new ArrayList<>();
        courseArrayList.add(courseRepository.findById(courseId).get());
        student.setEnrolledCourses(courseArrayList);
        studentRepository.save(student);


    }

    @Cacheable(value = "courses", key = "'allCourses'")
    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public byte[] getCourseScheduleAsPdf(Long courseId) {

        Course course = courseRepository.getById(courseId);
        List<CourseSchedule> courseScheduleList = course.getSchedule();
        return generateCourseSchedulePdf(courseScheduleList,course.getCourseName());
    }

    public byte[] generateCourseSchedulePdf(List<CourseSchedule> courseSchedules, String courseName) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Add title
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 28);
            contentStream.setNonStrokingColor(Color.black);
            contentStream.newLineAtOffset(25, 700);
            contentStream.showText("Course Schedule :   "+courseName);
            contentStream.endText();

            // Add header
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 18);
            contentStream.newLineAtOffset(25, 665);
            contentStream.setNonStrokingColor(Color.darkGray);
            contentStream.showText("Schedule Time                       Instructor");
            contentStream.newLineAtOffset(25, 665);
            contentStream.endText();

            float yPosition = 640;

            // Add schedule data
            for (CourseSchedule schedule : courseSchedules) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 16);
                contentStream.newLineAtOffset(25, yPosition);
                contentStream.setNonStrokingColor(Color.darkGray);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm a");
                String readableDate = schedule.getSchedule().format(formatter);// Adjust yPosition for each course entry
                contentStream.showText(readableDate + "                 " + schedule.getInstructor());
                contentStream.endText();
                yPosition -= 20; // Move cursor down for next line
            }

            contentStream.close();
            document.save(byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }


}
