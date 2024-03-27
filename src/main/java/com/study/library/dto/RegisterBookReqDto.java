package com.study.library.dto;

import com.study.library.entity.Book;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class RegisterBookReqDto {
    private String isbn;
    
    @Min(value = 1, message = "숫자만 입력 가능합니다")
    private int bookTypeId;
    
    @Min(value = 1, message = "숫자만 입력 가능합니다")
    private int categoryId;

    @NotBlank(message = "값(도서명)을 입력하세요") // 공백, null
    private String bookName;

    @NotBlank(message = "값(저자명)을 입력하세요")
    private String authorName;

    @NotBlank(message = "값(출판사)을 입력하세요")
    private String publisherName;

    @NotBlank(message = "값(이미지)을 입력하세요")
    private String coverImgUrl;

    public Book toEntity() {
        return Book.builder()
                .bookName(bookName)
                .authorName(authorName)
                .publisherName(publisherName)
                .bookTypeId(bookTypeId)
                .categoryId(categoryId)
                .coverImgUrl(coverImgUrl)
                .isbn(isbn)
                .build();
    }
}


//    @NotNull // Null 체크
//    @Null    // Null 이어야함
//    @Empty   // 빈 값 체크