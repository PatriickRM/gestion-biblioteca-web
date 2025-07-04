package com.webapp.biblioteca.springboot_webapp.reports;

import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

public interface IReportePrestamo {
    
    void exportJapertReport(HttpServletResponse response) throws JRException, IOException;
}
