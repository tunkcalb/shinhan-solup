package com.example.solup.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final String loanUrl = "https://finlife.fss.or.kr/finlifeapi/creditLoanProductsSearch.json?auth=44029f97f0df2f1e452d5aa79d3867f5&topFinGrpNo=050000&pageNo=1";
    private final String savingUrl = "https://finlife.fss.or.kr/finlifeapi/savingProductsSearch.json?auth=44029f97f0df2f1e452d5aa79d3867f5&topFinGrpNo=020000&pageNo=1";

    @Autowired
    private RestTemplate restTemplate;

    public Object getLoan() {
        return restTemplate.getForObject(loanUrl, Object.class);
    }

    public Object getSaving() {
        return restTemplate.getForObject(savingUrl, Object.class);
    }
}
