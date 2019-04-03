
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.AffineTransform;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
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
    public static final String DEST = "./src/main/resources/pdf/imagesToPdf.pdf";
    public static final String ORIGIN = "./src/main/resources/img";
    private static final List<String> FORMATS = List.of(".png", ".jpg", ".jpeg");

    private PdfConverter() {
        assert false;
    }

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
                    .filter(f -> f.matches(".*\\.jp[e]?g$") || f.matches(".*\\.png$"))
                    .collect(Collectors.toCollection(ArrayList::new));

            manipulatePdf(imagesPath, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void manipulatePdf(String originDir) throws NotDirectoryException {
        manipulatePdf(originDir, DEST);
    }

    private static void rotateImage(Image img, double degree) {
        Image image = img.setRotationAngle(degree * 10);

    }

    public static void rotateImage(String img, Rotate degree) throws FileNotFoundException, MalformedURLException {
        File imageFile = new File(img);
        if (!imageFile.exists()) {
            throw new FileNotFoundException("This image do not exist");
        }

        Image image = new Image(ImageDataFactory.create(img));

        if (degree == Rotate.RIGHT) {
            rotateImage(image, 90.0);
        } else if (degree == Rotate.LEFT) {
            rotateImage(image, -90.0);
        }
    }

    public static void addImageToPdf(String img, String pdf) {


        try (PdfDocument pdfDoc = new PdfDocument(new PdfReader("C:\\Users\\ASUS\\Desktop\\Programming\\ImagesToPdfBot\\src\\main\\resources\\pdf\\res.pdf")
                , new PdfWriter(pdf));
             Document doc = new Document(pdfDoc)) {
            ImageData imageData = ImageDataFactory.create(img);
            Image image = new Image(imageData);


            AffineTransform at = AffineTransform.getTranslateInstance(36, 300);
            at.concatenate(AffineTransform.getScaleInstance(image.getImageScaledWidth(),
                    image.getImageScaledHeight()));

            PdfCanvas canvas = new PdfCanvas(pdfDoc.getFirstPage());
            float[] matrix = new float[6];
            at.getMatrix(matrix);
            canvas.addImage(imageData, matrix[0], matrix[1], matrix[2], matrix[3], matrix[4], matrix[5]);
            addImageToPdf(image, doc);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void addImageToPdf(Image img, Document pdf) {
        pdf.add(img);
        pdf.flush();
    }

    private static void scaleImage(String img) {
        // TODO: 4/1/2019 scale images to be same size
    }


}
