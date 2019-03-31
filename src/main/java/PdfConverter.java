
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;


public class PdfConverter {

    private static final String DEST = "./target/test/resources/sandbox/tables/imagesToPdf.pdf";
    private static final String IMG1 = "./src/main/resources/img/f.jpg";
    private static final String IMG2 = "./src/main/resources/img/s.jpg";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PdfConverter().manipulatePdf(DEST);
    }

    public void manipulatePdf(String dest) {

        try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
             Document doc = new Document(pdfDoc)) {

            doc.add(new Image(ImageDataFactory.create(IMG1)));
            doc.add(new Image(ImageDataFactory.create(IMG2)));

        } catch (FileNotFoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void manipulatePdf(List<String> images) {
        Document doc = null;
        try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(DEST))) {
            doc = new Document(pdfDoc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (String img : images) {
            try {
                if (doc != null) {
                    doc.add(new Image(ImageDataFactory.create(img)));
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

    }


    private void rotateImage(String img) {
        // TODO: 4/1/2019
        //  All Image should be vertical
    }

    private void scaleImage(String img){
        // TODO: 4/1/2019 scale images to be same size
    }



}
