import org.junit.Test;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.NotDirectoryException;

public class PdfConverterTest {

    @Test
    public void manipulatePdf() throws NotDirectoryException {
        PdfConverter.manipulatePdf(PdfConverter.ORIGIN, PdfConverter.DEST);
    }

    @Test
    public void rotateImage() throws FileNotFoundException, MalformedURLException {
        System.out.println(PdfConverter.ORIGIN + "/1.jpg");
        PdfConverter.rotateImage(PdfConverter.ORIGIN + "/1.jpg", Rotate.RIGHT);
    }

    @Test
    public void addImageToPdf() throws MalformedURLException, FileNotFoundException {
        PdfConverter.addImageToPdf(PdfConverter.ORIGIN + "/1.jpg", PdfConverter.DEST);
    }

}