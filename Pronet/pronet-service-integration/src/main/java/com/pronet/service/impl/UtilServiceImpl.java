package com.pronet.service.impl;

import com.pronet.eao.UtilEAO;
import com.pronet.service.UtilService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by prith on 11-06-2017.
 */
@Service("utilServiceImpl")
public class UtilServiceImpl implements UtilService {

    @Resource(name = "utilEAO")
    UtilEAO utilEAO;

    public List<String> getSuffixes(String prefix) {
        return utilEAO.getSuffixes(prefix);
    }
}
