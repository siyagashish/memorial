import java.io.*;
import java.sql.*;

import org.apache.poi.xssf.usermodel.*;


public class excel_to_sql {
	
	static void convert(String S) {
		Sql sql = new Sql("soldiers");
		try {
			FileInputStream fstream = new FileInputStream(S);
			XSSFWorkbook workbook = new XSSFWorkbook(fstream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFRow row ;
			
			for(int i=0;i<=sheet.getLastRowNum();i++) {
				row = sheet.getRow(i);
				
				String a = row.getCell(0).toString();
				int b = (int)row.getCell(1).getNumericCellValue();
				int c = (int)row.getCell(2).getNumericCellValue();
				
				String q1 = "Insert into data (name,circle,arc) values (?,?,?)";
				
				PreparedStatement stmt = sql.con.prepareStatement(q1);
				stmt.setString(1, a);
				stmt.setInt(2, b);
				stmt.setInt(3, c);
				stmt.executeUpdate();
					
			}
			
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}
	
	public static void main(String [] args) {
		excel_to_sql.convert("/Users/siyagashish/Desktop/Book1.xlsx");
	}

}
