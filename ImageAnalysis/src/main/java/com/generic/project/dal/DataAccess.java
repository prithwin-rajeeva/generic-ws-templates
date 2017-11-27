package com.generic.project.dal;

import com.generic.project.vo.URLReport;

/**
 * data access layer primarily an http resource getter.
 */
public interface DataAccess {
    /**
     * gets the url report for a certain URL
     * @param url the url
     * @return the corresponding report for the URL.
     */
    URLReport getURLReport(String url);
}
