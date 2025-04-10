package com.example.weatherforecast.util;

import com.example.weatherforecast.model.Daily;
import com.example.weatherforecast.model.Hourly;
import com.example.weatherforecast.model.WeatherResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExcelExporter {

    public void export(WeatherResponse weather, String type, HttpServletResponse response) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Weather Data");

        Row header = sheet.createRow(0);

        if ("daily".equalsIgnoreCase(type)) {
            header.createCell(0).setCellValue("Дата");
            header.createCell(1).setCellValue("Макс. температура (°C)");
            header.createCell(2).setCellValue("Мін. температура (°C)");
            header.createCell(3).setCellValue("Макс. вітер (км/год)");
            header.createCell(4).setCellValue("Ймовірність опадів (%)");

            Daily daily = weather.getDaily();
            for (int i = 0; i < daily.getTime().size(); i++) {
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(daily.getTime().get(i));
                row.createCell(1).setCellValue(daily.getTemperature_2m_max().get(i));
                row.createCell(2).setCellValue(daily.getTemperature_2m_min().get(i));
                row.createCell(3).setCellValue(daily.getWind_speed_10m_max().get(i));
                row.createCell(4).setCellValue(daily.getPrecipitation_probability_max().get(i));
            }

            for (int i = 0; i <= 5; i++) {
                sheet.autoSizeColumn(i);
            }

        } else {
            header.createCell(0).setCellValue("Час");
            header.createCell(1).setCellValue("Температура (°C)");

            Hourly hourly = weather.getHourly();
            for (int i = 0; i < hourly.getTime().size(); i++) {
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(hourly.getTime().get(i));
                row.createCell(1).setCellValue(hourly.getTemperature_2m().get(i));
            }

            for (int i = 0; i <= 1; i++) {
                sheet.autoSizeColumn(i);
            }
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
