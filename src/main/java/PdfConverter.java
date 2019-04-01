
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Morteza Taghaddomi
 * This class convert Images to PDF file
 **/
public class PdfConverter {
    private PdfConverter() {
        assert false;
    }

    public static final String DEST = "./src/main/resources/pdf/imagesToPdf.pdf";
    public static final String IMG1 = "./src/main/resources/img/f.jpg";
    public static final String IMG2 = "./src/main/resources/img/s.jpg";
    public static final String ORIGIN = "./src/main/resources/img";
    private static final List<String> FORMATS = List.of(".png", ".jpg");

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        manipulatePdf(List.of(IMG1, IMG2));
//        manipulatePdf(DEST);
    }

//    public static void manipulatePdf(String dest) {
//
//        try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
//             Document doc = new Document(pdfDoc)) {
//
//            doc.add(new Image(ImageDataFactory.create(IMG1)));
//            doc.add(new Image(ImageDataFactory.create(IMG2)));
//
//        } catch (FileNotFoundException | MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * @param images images file addresses
     *               Use this method to generate images to pdf file
     */
    public static void manipulatePdf(List<String> images) {
        manipulatePdf(images, DEST);
    }

    public static void manipulatePdf(List<String> images, String dest) {
        File destFile = new File(dest);
        destFile.getParentFile().mkdirs();

        try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
             Document doc = new Document(pdfDoc)) {

            for (String img : images) {
                doc.add(new Image(ImageDataFactory.create(img)));
            }

        } catch (FileNotFoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public static void manipulatePdf(String originDir, String dest) throws NotDirectoryException {
        File origin = new File(originDir);
//        File file = new File(dest);
//        file.getParentFile().mkdirs();

        if (!origin.isDirectory()) {
            throw new NotDirectoryException("OriginDir must be a directory");
        }

        try (Stream<Path> files = Files.list(origin.toPath())) {
            List<String> imagesPath = files
                    .map(Path::toString)
                    .filter(f -> f.matches(".*\\.jp[e]?g$") || f.matches(".*\\.jpg$"))
                    .collect(Collectors.toCollection(ArrayList::new));

            manipulatePdf(imagesPath, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void manipulatePdf(String originDir) throws NotDirectoryException {
        manipulatePdf(originDir, DEST);

    }

    private static void rotateImage(String img) {
        // TODO: 4/1/2019
        //  All Image should be vertical
    }

    private static void scaleImage(String img) {
        // TODO: 4/1/2019 scale images to be same size
    }


}
