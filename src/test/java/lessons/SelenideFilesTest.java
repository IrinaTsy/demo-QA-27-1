package lessons;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import models.Glossary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideFilesTest {

    //скачивание, загрузка и чтение файлов разных типов (файлы скачиваются в папку build/downloads)

    static {

        Configuration.fileDownload = FileDownloadMode.PROXY; //если атрибут href отсутствует для кнопки download
                                                            //трафик проходит через прокси-сервер селенида и
                                                            //анализируется селенидом
    }

    //скачивание, чтение файла
    @Test
    void downloadFileTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloaded =
                $(".react-blob-header-edit-and-raw-actions [href*='main/README.md']")
                        .download(); // href*='main/README.md - *= обозначает, что в ссылке/атрибуте есть часть нужной строки
                                    // не нужно вписывать всю строку целиком
                                    //наличие атрибута href - обязательное условие для кнопки download

        try (InputStream is = new FileInputStream(downloaded)) {
            byte[] data = is.readAllBytes();
            String dataAsString = new String(data, StandardCharsets.UTF_8);
            Assertions.assertTrue(dataAsString.contains("Contributions to JUnit 5 are both welcomed and appreciated"));
        } // == String dataAsString = FileUtils readFileToString(downloaded,StandardCharsets.UTF_8);
    }

    //загрузка файла на сайт
    @Test
    void uploadFileTest() {
        open("https://fineuploader.com/demos.html");
        $("input[type='file']").uploadFromClasspath("cat.png"); //файлы загружаются из папки resources
        $(".qq-file-name").shouldHave(text("cat.png"));
    }


    private ClassLoader cl = SelenideFilesTest.class.getClassLoader();
    private static final Gson gson = new Gson();

    //загрузка PDF-файлов
    @Test
    void pdfFileParsingTest() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloaded = $("[href*='junit-user-guide-5.10.1.pdf']").download();
        PDF pdf = new PDF(downloaded);
        Assertions.assertEquals("Stefan Bechtold, Sam Brannen, Johannes Link, Matthias Merdes, Marc Philipp, Juliette de Rancourt, Christian Stein", pdf.author);
    }

    //загрузка XL-файлов
    @Test
    void xlsFileParsingTest() throws Exception {
        open("https://excelvba.ru/programmes/Teachers?ysclid=lfcu77j9j9951587711");
        File downloaded = $("[href='https://ExcelVBA.ru/sites/default/files/teachers.xls']").download();
        XLS xls = new XLS(downloaded);

        String actualValue = xls.excel.getSheetAt(0).getRow(3).getCell(2).getStringCellValue();

        Assertions.assertTrue(actualValue.contains("Суммарное количество часов планируемое на штатную по всем разделам"));
    }

    //чтение CSV-файлов
    @Test
    void csvFileParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("example.csv");
             CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {

            List<String[]> data = csvReader.readAll();
            Assertions.assertEquals(2, data.size());
            Assertions.assertArrayEquals(
                    new String[] {"Selenide", "https://selenide.org"},
                    data.get(0)
            );
            Assertions.assertArrayEquals(
                    new String[] {"JUnit 5","https://junit.org"},
                    data.get(1)
            );
        }
    }


    //нахождение архивных ZIP-файлов по названиям
    @Test
    void zipFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("sample.zip")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                System.out.println(entry.getName());
            }
        }
    }

    @Test
    void jsonFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("glossary.json")
        )) {
            JsonObject actual = gson.fromJson(reader, JsonObject.class);

            Assertions.assertEquals("example glossary", actual.get("title").getAsString());
            Assertions.assertEquals(234234, actual.get("ID").getAsInt());

            JsonObject inner = actual.get("glossary").getAsJsonObject();

            Assertions.assertEquals("SGML", inner.get("SortAs").getAsString());
            Assertions.assertEquals("Standard Generalized Markup Language", inner.get("GlossTerm").getAsString());
        }
    }

    @Test
    void jsonFileParsingImprovedTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("glossary.json")
        )) {
            Glossary actual = gson.fromJson(reader, Glossary.class);

            Assertions.assertEquals("example glossary", actual.getTitle());
            Assertions.assertEquals(234234, actual.getID());
            Assertions.assertEquals("SGML", actual.getGlossary().getSortAs());
            Assertions.assertEquals("Standard Generalized Markup Language", actual.getGlossary().getGlossTerm());
        }
    }
}
