package vn.edu.hcmut.cse.adsoftweng.lab.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Luu y: su dung @Controller, KHONG dung ,→ @RestController
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.hcmut.cse.adsoftweng.lab.service.StudentService;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import java.util.List;
@Controller
@RequestMapping("/students")
public class StudentWebController {
    @Autowired
    private StudentService service;

    // Route: GET http://localhost:8080/students
    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        List<Student> students;
        if (keyword != null && !keyword.isEmpty()) {
            // Can viet them ham searchByName trong Service/Repository
            students = service.searchByName(keyword);
        } else {
            students = service.getAll();
        }
        model.addAttribute("dsSinhVien", students);
        return "students";
    }

    // 1.2 Trang Chi Tiết [cite: 16, 17]
    @GetMapping("/{id}")
    public String getStudentDetail(@PathVariable int id, Model model) {
        model.addAttribute("student", service.getById(id));
        return "student-detail";
    }

    // 1.3 Form Thêm Mới [cite: 24]
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    // 1.3 Form Chỉnh Sửa [cite: 27]
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("student", service.getById(id));
        return "student-form";
    }

    // Lưu dữ liệu (Dùng chung cho Thêm & Sửa) [cite: 26, 30]
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        service.save(student);
        return "redirect:/students";
    }

    // Xóa sinh viên [cite: 20, 22]
    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        service.delete(id);
        return "redirect:/students";
    }
}