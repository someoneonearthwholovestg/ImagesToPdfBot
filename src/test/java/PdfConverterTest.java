import org.junit.Test;

import java.nio.file.NotDirectoryException;
import java.util.List;

import static org.junit.Assert.*;

public class PdfConverterTest {

    @Test
    public void manipulatePdf() throws NotDirectoryException {
        PdfConverter.manipulatePdf(PdfConverter.ORIGIN, PdfConverter.DEST);
    }

    @Test
    public void manipulatePdf1() {
        PdfConverter.manipulatePdf(List.of(PdfConverter.IMG1, PdfConverter.IMG2), PdfConverter.DEST);
    }
}