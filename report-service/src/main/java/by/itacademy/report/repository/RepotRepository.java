package by.itacademy.report.repository;

import by.itacademy.report.repository.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepotRepository extends JpaRepository<ReportEntity, String> {

    @Override
    ReportEntity saveAndFlush(ReportEntity entity);

    @Override
    boolean existsById(String id);
}
