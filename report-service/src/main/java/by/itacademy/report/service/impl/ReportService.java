package by.itacademy.report.service.impl;

import by.itacademy.report.core.dto.ReportDTO;
import by.itacademy.report.core.dto.ReportDetails;
import by.itacademy.report.core.etypes.ReportType;
import by.itacademy.report.core.etypes.StatusRep;
import by.itacademy.report.repository.RepotRepository;
import by.itacademy.report.repository.entity.ReportEntity;
import by.itacademy.report.service.api.IReportService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReportService implements IReportService {
    private final RepotRepository reportRepository;

    public ReportService(@Qualifier("repotRepository") RepotRepository reportRepository) {
        this.reportRepository = reportRepository;
    }


    @Override
    public ReportEntity prepareReport(ReportType type, ReportDetails reportDetails) {

        UUID uuid = UUID.randomUUID();
        long now = System.currentTimeMillis();

        ReportEntity reportEntity = new ReportEntity();

//        reportEntity.setUuid(uuid.toString())
//                .setDt_create(now)
//                .setDt_update(now)
//                .setStatus(StatusRep.IN_PROCESS.name())
//                .setType(ReportType.JOURNAL_AUDIT.name())
//                .setDescription("")
//                .setUser_uuid(reportEntity.getUser_uuid())
//                .setFrom(reportDetails.getFrom().)
//                .setTo(reportDetails.getTo())
//                .setFile("");


        return null;
    }

    @Override
    public ReportEntity saveAndFlush(ReportEntity entity) {

        UUID uuid = UUID.randomUUID();

//        if (existsById(entity.))
//
//
//
//        ReportEntity reportEntity = reportRepository.saveAndFlush(entity);
//        return reportEntity;

        return null;
    }

    public boolean existsById(String id) {
        return reportRepository.existsById(id);
    }


    private Long getTimeMillis() {


        return null;
    }


}
