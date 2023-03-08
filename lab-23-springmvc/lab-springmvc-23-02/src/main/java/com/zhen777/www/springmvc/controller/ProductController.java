package com.zhen777.www.springmvc.controller;

import com.zhen777.www.springmvc.vo.ProductVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 产品 Controller
 */
@Deprecated
@RestController
@RequestMapping("/products")
public class ProductController {

    @PostMapping(value = "/add",
        consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ProductVO add(@RequestBody ProductVO product) {
        return product;
    }

}
