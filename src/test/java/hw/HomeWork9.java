package hw;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import models.Home;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

public class HomeWork9 {

    private final ClassLoader cl = HomeWork9.class.getClassLoader();

    @DisplayName("Чтение PDF-файла")
    @Test
    void readingFilePdfTypeTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(requireNonNull(cl.getResourceAsStream("test.zip")))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    PDF pdf = new PDF(zis);
                   assertThat(pdf.text).contains("ТЕХНИЧЕСКОЕ ИНТЕРВЬЮ");

                }
            }
        }

    }

    @DisplayName("Чтение XLS-файла")
    @Test
    void readingFileXlsTypeTest() throws Exception{
        try (ZipInputStream zis = new ZipInputStream(requireNonNull(cl.getResourceAsStream("test.zip")))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xlsx")) {
                    XLS xls = new XLS(zis);
                    String value = xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
                    assertThat(value).isEqualTo("Евгений");
                }
            }
        }

    }

    @DisplayName("Чтение CSV-файла")
    @Test
    void readingFileCsvTypeTest() throws Exception {

        try (ZipInputStream zis = new ZipInputStream(requireNonNull(cl.getResourceAsStream("test.zip")))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {
                    CSVReader csv = new CSVReader(new InputStreamReader(zis));
                    List<String[]> values = csv.readAll();
                    assertThat(values.get(0)).isEqualTo(new String[]{"\uFEFFЕвгений"});
                }
            }
        }
    }

    @DisplayName("Чтение Json-файла")
    @Test
    void readingFileJsonTypeTest() throws Exception {

        try (InputStream is = cl.getResourceAsStream("testJson.json")) {

            ObjectMapper mapper = new ObjectMapper();
            Home home = mapper.readValue(is, Home.class);
            assertThat(home.getTitle()).isEqualTo("home");
            assertThat(home.getAddress().getCountry()).isEqualTo("Israel");
            assertThat(home.getAddress().getCity()).isEqualTo("Ashdod");
            assertThat(home.getAddress().getStreet()).isEqualTo("Dov Gur");
            assertThat(home.getAddress().getHouseNumber()).isEqualTo(38);
            assertThat(home.getAddress().getApartmentNumber()).isEqualTo(2);
    }
}
}


