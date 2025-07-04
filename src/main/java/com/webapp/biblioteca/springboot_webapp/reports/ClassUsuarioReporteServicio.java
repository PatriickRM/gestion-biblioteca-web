package com.webapp.biblioteca.springboot_webapp.reports;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.webapp.biblioteca.springboot_webapp.models.Usuario;
import com.webapp.biblioteca.springboot_webapp.service.UsuarioService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service  //para ejecutar ek report
public class ClassUsuarioReporteServicio implements IReporteUsuario{
	
	@Autowired
	private UsuarioService usuarioservicio;
	
	public void exportJapertReport(HttpServletResponse response) throws JRException,IOException{
		List<Usuario> listar=(List<Usuario>) usuarioservicio.ListarUsuarios();
		File file=ResourceUtils.getFile("classpath:Reportes/Reportes_Usuarios.jrxml");
		JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource=new JRBeanCollectionDataSource(listar);
		//para ingresar parametros..desde una bd ,etc.
		Map<String,Object> parametros=new HashMap();
		parametros.put("createBy","Simplifying Tech");
		JasperPrint jasperprint=JasperFillManager.fillReport(jasperReport,parametros,datasource);
		
		
		//*******Para visualizar el pdf directamente desde java
	    String titleTransactionBy = "Reportes de Usuarios";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	    var dateTimeNow = LocalDateTime.now().format(formatter);
		var fileName = titleTransactionBy.replace(" ", "") + dateTimeNow;
		  JRPdfExporter exporter = new JRPdfExporter();
	      exporter.setExporterInput(new SimpleExporterInput(jasperprint));
	      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
	      response.setContentType("application/pdf");
	      response.setHeader(
	      HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".pdf;");
	      exporter.exportReport();
		
	}
}
