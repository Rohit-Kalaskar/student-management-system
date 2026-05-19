package student_management.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import student_management.model.Student;
import student_management.service.StudentService;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public String list(@RequestParam(defaultValue = "") String search,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       Model model) {
        Page<Student> studentPage = studentService.getStudents(search, page, size);
        model.addAttribute("students", studentPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", studentPage.getTotalPages());
        model.addAttribute("totalItems", studentPage.getTotalElements());
        model.addAttribute("search", search);
        return "students";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("isEdit", false);
        return "student-form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute Student student,
                       BindingResult result,
                       Model model,
                       RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", false);
            return "student-form";
        }
        try {
            studentService.save(student);
            ra.addFlashAttribute("successMsg", "Student added successfully!");
            return "redirect:/students";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("isEdit", false);
            model.addAttribute("errorMsg", "⚠️ Student with this email or phone number already exists! Please use different values.");  // ← UPDATED
            return "student-form";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.findById(id));
        model.addAttribute("isEdit", true);
        return "student-form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute Student student,
                         BindingResult result,
                         Model model,
                         RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", true);
            return "student-form";
        }
        try {
            student.setId(id);
            studentService.save(student);
            ra.addFlashAttribute("successMsg", "Student updated successfully!");
            return "redirect:/students";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("isEdit", true);
            model.addAttribute("errorMsg", "⚠️ Student with this email or phone number already exists! Please use different values.");  // ← UPDATED
            return "student-form";
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        studentService.deleteById(id);
        ra.addFlashAttribute("successMsg", "Student deleted successfully!");
        return "redirect:/students";
    }
}