package com.tci.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class FileReaderUtil {
    //delimitador
    private String delimiter;
    //pula a primeira linha
    private boolean skip;
    //linhas do arquivo como Objeto[]
    private List<Object[]> lines;

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public void skipHeader() {
        this.skip = true;
    }

    public List<Object[]> read(InputStream is)throws Exception {
        lines = new ArrayList<Object[]>();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String record = br.readLine();
        while(record != null){
            if (skip) {
                skip = false;
                record = br.readLine();
                continue;
            }
            String[] columns = record.split(delimiter);
            lines.add(columns);
            record = br.readLine();
        }
        return lines;
    }
    public List<String> readLine(InputStream is)throws Exception {
        List<String>lines = new ArrayList<String>();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String record = br.readLine();
        while(record != null){
            if (skip) {
                skip = false;
                record = br.readLine();
                continue;
            }
            lines.add(record);
            record = br.readLine();
        }
        return lines;
    }

    public List<Object[]> read(File file) throws Exception {
        return read(new FileInputStream(file));
    }
    public List<String> readLine(File file) throws Exception {
        return readLine(new FileInputStream(file));
    }
}