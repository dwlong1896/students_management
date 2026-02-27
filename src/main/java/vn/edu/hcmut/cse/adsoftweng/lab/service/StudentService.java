package vn.edu.hcmut.cse.adsoftweng.lab.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.repository.StudentRepository;
import java.util.List;
@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;
    public List<Student> getAll(){
        return repository.findAll();
    }

    public Student getById(int id){
        return repository.findById(id).orElse(null);
    }

    public List<Student> searchByName(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword);
    }

    // Lưu sinh viên (Yêu cầu 1.3 - Dùng chung cho cả Thêm và Sửa)
    public void save(Student student) {
        repository.save(student);
    }

    // Xóa sinh viên (Yêu cầu 1.2)
    public void delete(int id) {
        repository.deleteById(id);
    }
}
