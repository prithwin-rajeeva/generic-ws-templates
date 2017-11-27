package com.pronet.rest.controller;

import com.pronet.service.UtilService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by prith on 11-06-2017.
 */
@RestController
@RequestMapping("/util")
public class UtilWebService {

    @Resource(name = "utilServiceImpl")
    UtilService utilService;

    @RequestMapping(value = "/suffix/{prefix}", method = RequestMethod.GET)
    public @ResponseBody List<String> getSuffixes(@PathVariable(name = "prefix") String prefix) {
        return utilService.getSuffixes(prefix);
    }
}
