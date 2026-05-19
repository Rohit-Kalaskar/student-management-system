package student_management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import student_management.service.StudentService;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final StudentService studentService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error != null) {
            model.addAttribute("errorMsg", "Invalid username or password. Please try again.");
        }
        if (logout != null) {
            model.addAttribute("logoutMsg", "You have been logged out successfully.");
        }
        return "login";
    }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("totalStudents", studentService.count());
        return "dashboard";
    }
}
