package com.example.demo.office.ppt;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlide;

import java.io.FileInputStream;
import java.io.IOException;

public class PptUtils {
    public static void main(String[] args) throws IOException {
        //create a new empty slide show
        XMLSlideShow ppt = new XMLSlideShow(new FileInputStream("~/Desktop/a.ppt"));
        //add first slide
        XSLFSlide blankSlide = ppt.createSlide();

        CTSlide xmlObject = blankSlide.getXmlObject();
        System.out.println("xmlObject = " + xmlObject);
    }
}
