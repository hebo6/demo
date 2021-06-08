package com.example.demo.office.ppt;

import com.example.demo.utils.TextUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xslf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.openxmlformats.schemas.drawingml.x2006.chart.*;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlide;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class PptUtils {

    public static void main(String[] args) throws Exception {
        String source = System.getProperty("user.home") + "/Desktop/替换前.pptx";
        String target = System.getProperty("user.home") + "/Desktop/替换后.pptx";

        XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(source));

        //替换
        replace(ppt, PptUtils::myProcessor);

        save(ppt, target);
    }

    private static String myProcessor(String key) {
        return "软件" + StringUtils.upperCase(key) + "部";
    }

    public static void replace(XMLSlideShow ppt, Function<String, Object> processor) throws IOException, InvalidFormatException {
        replaceGraphicFrame(ppt, processor);
        replaceTextShape(ppt, processor);
    }

    private static void replaceGraphicFrame(XMLSlideShow ppt, Function<String, Object> processor) throws IOException, InvalidFormatException {
        List<XSLFSlide> slides = ppt.getSlides();
        for (XSLFSlide slide : slides) {
            for (XSLFShape shape : slide) {
                if (shape instanceof XSLFGraphicFrame) {
                    XSLFGraphicFrame gf = (XSLFGraphicFrame) shape;
                    XSLFChart chart = gf.getChart();
                    if (chart == null) {
                        continue;
                    }
                    XSSFWorkbook workbook = chart.getWorkbook();
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    int num = sheet.getLastRowNum();
                    for (int i = 0; i < num; i++) {
                        XSSFRow row = sheet.getRow(i);
                        XSSFCell cell = row.getCell(0);
                        if (cell == null) {
                            continue;
                        }
                        String text = cell.getStringCellValue();
                        if (StringUtils.contains(text, "${")) {
                            String newText = TextUtils.replace(text, processor);
                            cell.setCellValue(newText);
                            refreshChartAxis(chart, i, newText);
                        }
                    }
                }
            }
        }
    }

    /**
     * 变更坐标轴标题
     */
    private static void refreshChartAxis(XSLFChart chart, int i, String newText) {
        CTPlotArea plotArea = chart.getCTChart().getPlotArea();
        List<CTBarChart> barChartList = plotArea.getBarChartList();
        if (CollectionUtils.isEmpty(barChartList)) {
            List<CTPieChart> pieChartList = plotArea.getPieChartList();
            if (CollectionUtils.isEmpty(pieChartList)) {
                return;
            }
            CTPieSer ctPieSer = pieChartList.get(0).getSerList().get(0);
            List<CTStrVal> ptList = ctPieSer.getCat().getStrRef().getStrCache().getPtList();
            CTStrVal ctStrVal = ptList.get(i - 1);
            ctStrVal.setIdx(i - 1);
            ctStrVal.setV(newText);
        } else {
            CTBarSer ser = barChartList.get(0).getSerList().get(0);
            List<CTStrVal> ptList = ser.getCat().getStrRef().getStrCache().getPtList();
            CTStrVal ctStrVal = ptList.get(i - 1);
            ctStrVal.setIdx(i - 1);
            ctStrVal.setV(newText);
        }
    }

    private static void replaceTextShape(XMLSlideShow ppt, Function<String, Object> processor) {
        List<XSLFSlide> slides = ppt.getSlides();
        for (XSLFSlide slide : slides) {
            CTSlide ctSlide = slide.getXmlObject();
            //noinspection HttpUrlsUsage
            XmlObject[] allText = ctSlide.selectPath(
                    "declare namespace a='http://schemas.openxmlformats.org/drawingml/2006/main' " +
                            ".//a:t"
            );
            for (XmlObject xmlObject : allText) {
                XmlString xmlString = (XmlString) xmlObject;
                String text = xmlString.getStringValue();
                if (StringUtils.contains(text, "${")) {
                    String newText = TextUtils.replace(text, processor);
                    xmlString.setStringValue(newText);
                }
            }
        }
    }

    private static void save(XMLSlideShow ppt, String file) throws IOException {
        try (ppt; FileOutputStream out = new FileOutputStream(file)) {
            ppt.write(out);
        }
    }
}
