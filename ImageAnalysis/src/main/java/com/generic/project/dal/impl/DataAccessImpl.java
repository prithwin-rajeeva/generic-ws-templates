package com.generic.project.dal.impl;

import com.generic.project.vo.URLReport;
import com.generic.project.dal.DataAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * data access layer primarily an http resource getter.
 */
@Service
public class DataAccessImpl implements DataAccess{

    private static Logger logger = LoggerFactory.getLogger(DataAccessImpl.class);

    private  static final String GET = "GET";

    @Value("${url.max.retries}")
    private int max;

    /**
     * gets the url report for a certain URL
     * @param input the url
     * @return the corresponding report for the URL.
     */
    public URLReport getURLReport(String input) {
        logger.debug("input = {}",input);
        StringBuilder result = new StringBuilder();
        URL url;
        String page;
        int imageCount;
        int retryCount = 0;
        BufferedReader rd = null;
        while(true) {
            try {
                url = new URL(input);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod(GET);
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                break;
            } catch (IOException e) {
                retryCount++;
                if(retryCount >= max) return new URLReport(input , -1);
            } finally {
                if(rd!=null) try {
                    rd.close();
                } catch (IOException ignored) { }
            }
        }
        page = result.toString();
        imageCount = getImageCount(page);
        logger.info("{} <==> {}",input , imageCount);
        return new URLReport(input , imageCount);
    }

    /**
     * given the full html of a page retrieves the number of images in the page.
     * @param page the url of the given page
     * @return the number of images on the html page
     */
    public int getImageCount(String page) {
        int imageCount = 0;
        Pattern pattern = Pattern.compile("<\\s*(img){1}");
        Matcher matcher = pattern.matcher(page);
        while(matcher.find()) imageCount++;
        return imageCount;
    }


}
