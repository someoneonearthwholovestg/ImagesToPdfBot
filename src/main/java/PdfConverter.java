
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import java.io.File;

public class PdfConverter {

    public static final String DEST = "./target/test/resources/sandbox/tables/imagesToPdf.pdf";
    public static final String IMG1 = "./src/main/resources/img/f.jpg";
    public static final String IMG2 = "./src/main/resources/img/s.jpg";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PdfConverter().manipulatePdf(DEST);
    }

    public void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        doc.add(new Image(ImageDataFactory.create(IMG1)));
        doc.add(new Image(ImageDataFactory.create(IMG2)));

        doc.close();
    }

}
