package com.springboot.helper;

import com.springboot.entities.User;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;

public class CSVHelper {

    public static String TYPE = "text/csv";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };

    public static boolean hasCsvFormat(MultipartFile file){
        return TYPE.equals((file.getContentType()));
    }

    public static List<User> csvToUser(InputStream is){

        try (
            BufferedReader fileReader= new BufferedReader(new InputStreamReader(is,"UTF-8"));
            CSVParser csvParser= new CSVParser(fileReader,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());){

            List<User> userslist= new ArrayList<>();

            Iterable<CSVRecord>csvRecords= csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                User u = new User(
                        Integer.parseInt(csvRecord.get("id")),
                        csvRecord.get("name"),
                        csvRecord.get("city"),
                        csvRecord.get("status")
                );

                userslist.add(u);
            }
            return userslist;
        }catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }

    }
}
