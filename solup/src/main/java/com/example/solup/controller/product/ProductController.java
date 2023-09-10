package com.example.solup.controller.product;

import com.example.solup.dto.Response;
import com.example.solup.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//    @CrossOrigin(origins = "https://shinhansolup.duckdns.org/")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;


    @GetMapping("/product/loan")
    public Response<Object> getLoan() {
        Object response = productService.getLoan();
        return new Response<>("200", "대출 상품 조회", response);
    }

    @GetMapping("/product/saving")
    public Response<Object> getSaving() {
        Object response = productService.getSaving();
        return new Response<>("200", "적금 상품 조회", response);
    }
}
