package org.example;

import net.sourceforge.tess4j.*;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class ImageToTextConverter {
    public static void main(String[] args) throws URISyntaxException {
        File imageFile = new File(ImageToTextConverter.class.getResource("/img/img.png").toURI());
        ITesseract instance = new Tesseract(); // JNA Interface Mapping
        // Set the path of the tessdata folder containing the language data files
        File directory=new File(ImageToTextConverter.class.getResource("/OCR Data").toURI());
        instance.setDatapath(directory.getPath());
        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}