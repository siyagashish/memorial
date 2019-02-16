import java.io.*;
import java.sql.*;

import org.apache.poi.xssf.usermodel.*;

public class excel_to_sql {

	static void convert(String S,Sql sql) {
		try {
			FileInputStream fstream = new FileInputStream(S);
			XSSFWorkbook workbook = new XSSFWorkbook(fstream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFRow row;

			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);

				String name = row.getCell(0).toString();
				int circle = (int) row.getCell(1).getNumericCellValue();
				int arc = (int) row.getCell(2).getNumericCellValue();
				int column = (int) row.getCell(3).getNumericCellValue();
				int row1 = (int) row.getCell(4).getNumericCellValue();

				String q1 = "Insert into data (name,circle,arc,colum,raw) values (?,?,?,?,?)";

				PreparedStatement stmt = sql.con.prepareStatement(q1);
				stmt.setString(1, name);
				stmt.setInt(2, circle);
				stmt.setInt(3, arc);
				stmt.setInt(4, column);
				stmt.setInt(5, row1);
				stmt.executeUpdate();
				
			}
			workbook.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
