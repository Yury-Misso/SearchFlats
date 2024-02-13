package by.itacademy.report.service.api;

import by.itacademy.report.core.etypes.ReportType;
import by.itacademy.report.core.dto.ReportDTO;
import by.itacademy.report.core.dto.ReportDetails;
import by.itacademy.report.repository.entity.ReportEntity;
import org.springframework.stereotype.Service;

@Service
public interface IReportService {

    ReportEntity prepareReport(ReportType type, ReportDetails reportDetails);

    ReportEntity saveAndFlush(ReportEntity entity);

    boolean existsById(String id);

}
