package edu.usm.healthsystem.report;

import java.util.ArrayList;
import java.util.List;

public class ReportHeaderGenerator {
    public static List<String[]> generateHeader(String clinic, String district, String region) {
        List<String[]> reportHeader = new ArrayList<>();

        reportHeader.add(new String[] {"Family Planning Monthly Report", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""});
        reportHeader.add(new String[] {"Clinic: ", clinic, "", "", "", "", "", "", "", "", "", "", "", "", "", ""});
        reportHeader.add(new String[] {"District: ", district, "", "", "", "", "", "", "", "", "", "", "", "", "", ""});
        reportHeader.add(new String[] {"Region: ", region, "", "", "", "", "", "", "", "", "", "", "", "", "", ""});
        reportHeader.add(new String[] {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""});
        reportHeader.add(new String[] {"STOCK", "", "LO-FEM", "Overette", "Male Condom", "Female Condom", 
                "Copper T", "Micro G", "Micr - N", "Postinor 2", "Sampoo", "Depo", "Vasectomy", "LAM", "Natural", "Norigynon"});

        return reportHeader;
    }
}