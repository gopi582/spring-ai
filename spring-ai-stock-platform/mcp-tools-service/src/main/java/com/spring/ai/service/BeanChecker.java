package com.spring.ai.service;

import org.springframework.stereotype.Component;

import com.spring.ai.tools.CompanyTools;
import com.spring.ai.tools.MarketMoversTools;
import com.spring.ai.tools.NewsTools;
import com.spring.ai.tools.StockTools;

@Component
public class BeanChecker {

    public BeanChecker(
            StockTools stockTools,
            CompanyTools companyTools,
            NewsTools newsTools,
            MarketMoversTools marketMoversTools) {

        System.out.println("StockTools bean created");
        System.out.println("CompanyTools bean created");
        System.out.println("NewsTools bean created");
        System.out.println("MarketMoversTools bean created");
    }
}
