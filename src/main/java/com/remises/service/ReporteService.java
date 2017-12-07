package com.remises.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.remises.model.Chofer;
import com.remises.model.Viaje;
import com.remises.repository.ViajeRepository;

@Service
public class ReporteService {
	
	@Autowired
	private ViajeRepository repository; 
	
	public FileOutputStream createXLS (String title, List<String> headers, List<Viaje> body, String nameFile) {

		
		
		
		return null;
	}
	
	

	public FileOutputStream reporte(Chofer chofer) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(0, "Saldo");
        
        // Tipo de Fuente a usar
        Font font = workbook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        
        // Estilos para la cabecera
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerStyle.setFont(font);
        
        // Estilos para el pie de pagina
        CellStyle totalStyle = workbook.createCellStyle();
        totalStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        totalStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        
        
        // Lista de CAMPOS HEADERS
        String[] headers = new String[]{
            "Cliente",
            "Origen",
            "Destino",
            "Precio"
        };
        
        // Lista con el Cuerpo del Excel
        List<Viaje> saldos = new ArrayList<Viaje>();
        saldos = repository.findByChofer(chofer);

        // Arma el Registro Cabecera
        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; ++i) {
            String header = headers[i];
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }

        // Arma el Cuerpo del Excel
        for (int i = 0; i < saldos.size(); ++i) {
            Viaje saldo = saldos.get(i);            
            HSSFRow dataRow = sheet.createRow(i + 1);
            dataRow.createCell(0).setCellValue(saldo.getCliente().getApellido());
            dataRow.createCell(1).setCellValue(saldo.getOrigen());
            dataRow.createCell(2).setCellValue(saldo.getDestino());
            dataRow.createCell(3).setCellValue(saldo.getPrecio());
        }
        
        // Arma el Pie de Pagina
        HSSFRow dataRow = sheet.createRow(1 + saldos.size());
        HSSFCell total = dataRow.createCell(3);
        total.setCellType(Cell.CELL_TYPE_FORMULA);
        total.setCellStyle(totalStyle);
        total.setCellFormula(String.format("SUM(D2:D%d)", 1 + saldos.size()));

        FileOutputStream file = new FileOutputStream("saldos.xls");
        workbook.write(file);
        file.close();
        workbook.close();
        
        return file;
    }
	
	
	

}
