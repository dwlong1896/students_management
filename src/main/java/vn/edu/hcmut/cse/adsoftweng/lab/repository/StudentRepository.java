package vn.edu.hcmut.cse.adsoftweng.lab.repository;

import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    // Kế thừa JpaRepository để có sẵn các hàm findAll(), findById(), save(),... [cite: 54, 56]
    // Hàm tìm kiếm theo tên (tùy chọn) [cite: 55]
    List<Student> findByNameContainingIgnoreCase(String keyword);
}