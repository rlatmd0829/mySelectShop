package com.sparta.springcore.service;

import com.sparta.springcore.dto.ItemDto;
import com.sparta.springcore.dto.ProductRequestDto;
import com.sparta.springcore.model.Product;
import com.sparta.springcore.dto.ProductMypriceRequestDto;
import com.sparta.springcore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@Service // 서비스임을 선언합니다.
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public Long update(Long id, ProductMypriceRequestDto requestDto) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        product.update(requestDto);
        return id;
    }

    public List<Product> getProducts(Long userId){
        return productRepository.findAllByUserId(userId);
    }

    @Transactional
    public Long updateBySearch(Long id, ItemDto itemDto){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        product.updateByItemDto(itemDto);
        return id;
    }

    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public Product createProduct(ProductRequestDto requestDto, Long userId ) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto, userId);
        productRepository.save(product);
        return product;
    }


    // 모든 상품 조회 (관리자용)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
