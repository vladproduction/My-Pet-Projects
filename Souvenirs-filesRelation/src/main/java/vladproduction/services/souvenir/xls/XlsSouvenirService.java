package vladproduction.services.souvenir.xls;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vladproduction.models.Producer;
import vladproduction.models.Souvenir;
import vladproduction.services.producer.ProducerService;
import vladproduction.services.souvenir.AbstractSouvenirService;

import java.io.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class XlsSouvenirService extends AbstractSouvenirService {

    public XlsSouvenirService(ProducerService producerService) {
        super(producerService);
    }

    @Override
    protected void saveToFile(Set<Souvenir> souvenirs, File file) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Souvenirs");
        int rowNumber = 0;
        for (Souvenir souvenir : souvenirs) {
            Row row = sheet.createRow(rowNumber);
            int cellNumber = 0;
            Cell cell0 = row.createCell(cellNumber);
            cell0.setCellValue(souvenir.getName());
            cellNumber++;
            Cell cell1 = row.createCell(cellNumber);
            cell1.setCellValue(souvenir.getPrice());
            cellNumber++;
            Cell cell2 = row.createCell(cellNumber);
            cell2.setCellValue(souvenir.getCreationDate());
            Producer producer = souvenir.getProducer();
            if (producer!=null){
                cellNumber++;
                Cell cell3 = row.createCell(cellNumber);
                cell3.setCellValue(producer.getName());
                cellNumber++;
                Cell cell4 = row.createCell(cellNumber);
                cell4.setCellValue(producer.getCountry());
            }
            rowNumber++;
        }
        try(OutputStream out = new FileOutputStream(file)){
            workbook.write(out);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Set<Souvenir> loadFromFile(File file) {

        if(!file.exists()){
            return new HashSet<>();
        }
        Set<Souvenir> result = new HashSet<>();
        try(InputStream in = new FileInputStream(file)){
            Workbook workbook = new XSSFWorkbook(in);
            Sheet sheet = workbook.getSheet("Souvenirs");
            for (Row row : sheet){
                Cell cell0 = row.getCell(0);
                Cell cell1 = row.getCell(1);
                Cell cell2 = row.getCell(2);
                Cell cell3 = row.getCell(3);
                Cell cell4 = row.getCell(4);
                String name = cell0.getStringCellValue();
                Double price = cell1.getNumericCellValue();
                Date producingDate = cell2.getDateCellValue();
                Producer producer = new Producer.ProducerBuilder()
                        .name(cell3.getStringCellValue()).country(cell4.getStringCellValue()).build();

                Souvenir souvenir = new Souvenir();
                souvenir.setName(name);
                souvenir.setPrice(price);
                souvenir.setCreationDate(producingDate);
                souvenir.setProducer(producer);
                result.add(souvenir);
            }
            return result;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
