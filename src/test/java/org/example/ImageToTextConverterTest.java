package org.example;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class ImageToTextConverterTest {
    @Test
    public void testImageToTextConversion() throws URISyntaxException {
        File imageFile = new File(ImageToTextConverterTest.class.getResource("/img/img.png").toURI());
        ITesseract instance = new Tesseract(); // JNA Interface Mapping
        // Set the path of the tessdata folder containing the language data files
        File directory = new File(ImageToTextConverterTest.class.getResource("/OCR Data").toURI());
        instance.setDatapath(directory.getPath());
        try {
            String result = instance.doOCR(imageFile);
            assertNotNull(result);
            assertTrue(result.contains(outputText), "OCR result should be: "+outputText);
        } catch (TesseractException e) {
            fail("OCR conversion failed: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidImageFile() throws URISyntaxException {
        File imageFile = new File("/img/invalid.png");
        ITesseract instance = new Tesseract(); // JNA Interface Mapping
        // Set the path of the tessdata folder containing the language data files
        File directory = new File(ImageToTextConverterTest.class.getResource("/OCR Data").toURI());
        instance.setDatapath(directory.getPath());
        assertThrows(TesseractException.class, () -> instance.doOCR(imageFile), "Invalid image file should throw TesseractException");
    }

    private final String outputText = "What is OCR (Optical Character Recognition)?\n" +
            "\n" +
            "Optical Character Recognition (OCR) is the process that converts an image of text into a machine—readable text format. For example,\n" +
            "if you scan a form or a receipt,your computer saves the scan as an image file. You cannot use a text editor to edit, search, or count\n" +
            "the words in the image file. However, you can use OCR to convert the image into a text document with ts contents stored as text\n" +
            "data.";
}