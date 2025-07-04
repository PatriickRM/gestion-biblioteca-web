package com.webapp.biblioteca.springboot_webapp.reports;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;


import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
public class ClassPrestamoReporteServicio implements IReportePrestamo{
    

    @Autowired
private DataSource dataSource;

public void exportJapertReport(HttpServletResponse response) throws JRException, IOException {
    File file = ResourceUtils.getFile("classpath:Reportes/Reporte_Prestamos.jrxml");
    JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

    // Conexión segura usando el DataSource inyectado de Spring Boot
        try (Connection conn = dataSource.getConnection()) {

            // Parámetros del reporte
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("createBy", "Simplifying Tech");

            // Generar el JasperPrint usando la conexión
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);

            // Nombre del archivo generado
            String titleTransactionBy = "Reportes de Prestamos";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String dateTimeNow = LocalDateTime.now().format(formatter);
            String fileName = titleTransactionBy.replace(" ", "") + dateTimeNow;
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

            response.setContentType("application/pdf");
            response.setHeader(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=" + fileName + ".pdf"
            );

            exporter.exportReport();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
