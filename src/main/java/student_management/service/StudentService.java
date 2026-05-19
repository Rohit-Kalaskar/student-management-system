package student_management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import student_management.model.Student;
import student_management.repository.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repo;

    public Page<Student> getStudents(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        if (search != null && !search.isBlank()) {
            return repo.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(search, search, pageable);
        }
        return repo.findAll(pageable);
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Student not found: " + id));
    }

    public Student save(Student student) {
        return repo.save(student);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public long count() {
        return repo.count();
    }
}
