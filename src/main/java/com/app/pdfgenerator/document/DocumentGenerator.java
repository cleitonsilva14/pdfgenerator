package com.app.pdfgenerator.document;


import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.source.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class DocumentGenerator {
    public String htmlToPDF(String processedHtml){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {

            PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
            DefaultFontProvider defaultFont = new DefaultFontProvider(false, true, false);

            ConverterProperties converterProperties = new ConverterProperties();

            converterProperties.setFontProvider(defaultFont);

            HtmlConverter.convertToPdf(processedHtml, pdfWriter, converterProperties);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSS");
            String timestamp = LocalDateTime.now(ZoneId.systemDefault()).format(formatter);

            String pathFile = String.format("../pdfgenerator/PDF/%s.pdf", timestamp);

            FileOutputStream fileOutputStream = new FileOutputStream(pathFile);

            byteArrayOutputStream.writeTo(fileOutputStream);
            byteArrayOutputStream.close();

            byteArrayOutputStream.flush();
            fileOutputStream.close();

            return null;

        }catch (Exception ex){
            System.out.println(ex);
        }

        return null;

    }
}
