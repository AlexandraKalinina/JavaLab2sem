package utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.*;
import java.util.Map;

public class Pdf {
    public void addPdf(User user, String type, String packageName) throws IOException, DocumentException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("templates/" + type)));
        //создание текстового документа
        //кладем все данные туда, а потом конвертация в pdf
        String helpFileName = RandomStringUtils.random(5, true, true) + ".txt";
        PrintWriter pw = new PrintWriter(new FileWriter("created/txt/" + helpFileName));
        while (br.ready()) {
            String line = br.readLine();
            Map<String, Object> map = user.getParameters();
            for (Map.Entry<String, Object> e :
                    map.entrySet()) {
                if (e.getValue() != null) {
                    line = line.replaceAll("\\{" + e.getKey() + "}", e.getValue().toString());
                }
            }
            pw.println(line);
        }
        br.close();
        pw.close();
        //здесь должна быть запись в пдф
        //создание папки с pdf файлом
        String folderName = "created/" + packageName;
        String allFileName = folderName + "/" + RandomStringUtils.random(5, true, true) + ".pdf";
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdir();
        }
        createPdf(allFileName, helpFileName, folderName);
    }

    public void createPdf(String allFileName, String helpFileName, String folderName) throws IOException, DocumentException {
        //создание pdf документа
        File file = new File(allFileName);
        PDDocument doc = PDDocument.load(file);
        PDPage page = doc.getPage(1);
        PDPageContentStream contentStream = new PDPageContentStream(doc, page);
        contentStream.beginText();
        contentStream.setFont( PDType1Font. TIMES_ROMAN , 16 );
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(25, 725);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("created/txt/" + helpFileName)));
        while (br.ready()) {
            String str = br.readLine();
            contentStream.showText(str);
            contentStream.newLine();
        }
        br.close();
        contentStream.close();
        doc.save( folderName + "/" + RandomStringUtils.random(5, true, true) + ".pdf");
        doc.close();
       /* Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(allFileName));
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("created/txt/" + helpFileName)));
        //запись в pdf текстовый файл
        while (br.ready()) {
            String str = br.readLine();
            Chunk chunk = new Chunk(str, font);
            document.add(chunk);
        }
        document.close();
        br.close();*/
    }
}
