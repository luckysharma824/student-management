package in.system.studentmanagement.repository;

import in.system.studentmanagement.domain.Student;
import in.system.studentmanagement.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class CustomStudentRepository {
    @Autowired
    private EntityManager em;


    public List<Student> search(Map<String, String> requestParam) {
        List<String> params = Arrays.asList("studentId", "currentSemester", "admissionYear", "branchCode", "course");
        String page = requestParam.getOrDefault("page", "0");
        String pageSize = requestParam.getOrDefault("pageSize", "10");
        int pageInt = Integer.parseInt(page);
        int pageSizeInt = Integer.parseInt(pageSize);
        requestParam.remove("page");
        requestParam.remove("pageSize");
        StringBuilder builder = new StringBuilder("select e from Student e ");
        if (!requestParam.isEmpty()) {
            builder.append("where ");
        }
        int count = 0;
        for (Map.Entry<String, String> entry : requestParam.entrySet()) {
            if (params.contains(entry.getKey())) {
                if (count > 0) {
                    builder.append(" and ");
                }
                builder.append(entry.getKey()).append(" = '").append(entry.getValue()).append("'");
                count++;
            } else {
                throw new ServiceException("Wrong request Parameter : " + entry.getKey());
            }
        }
        Query query = em.createQuery(builder.toString());
        query.setFirstResult(pageInt * pageSizeInt);
        query.setMaxResults(pageSizeInt);
        return query.getResultList();
    }
}
