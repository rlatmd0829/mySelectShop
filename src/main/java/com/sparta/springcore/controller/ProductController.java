package com.sparta.springcore.controller;

import com.sparta.springcore.model.Product;
import com.sparta.springcore.dto.ProductMypriceRequestDto;
import com.sparta.springcore.repository.ProductRepository;
import com.sparta.springcore.dto.ProductRequestDto;
import com.sparta.springcore.security.UserDetailsImpl;
import com.sparta.springcore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    // 로그인한 회원이 등록한 상품들 조회
    @GetMapping("/api/products")
    public List<Product> getProducts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return productService.getProducts(userId);
    }


    // 신규 상품 등록
    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 로그인 되어 있는 ID
        Long userId = userDetails.getUser().getId();

        Product product = productService.createProduct(requestDto, userId);
        // 응답 보내기
        return product;
    }

    @PutMapping("/api/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto){
        return productService.update(id, requestDto);
    }

    // (관리자용) 등록된 모든 상품 목록 조회
    @Secured("ROLE_ADMIN") // 어드민 권한을 가진 사용자만 이 API를 사용할 수 있다.
    @GetMapping("/api/admin/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}