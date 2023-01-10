package com.ecommerce.category.core;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.category.model.dto.category.CategoryDto;

public class TestUtils {

    // Insert Test Value
    public static List<CategoryDto> getInsertCategory() {

        List<CategoryDto> categoryDtos = new ArrayList<>();

        // 1번 테스트
        CategoryDto categoryDto1 = new CategoryDto();
        categoryDto1.setName("name" + TimeUtils.getTimeCd());
        categoryDtos.add(categoryDto1);

        //// 2번 테스트
        CategoryDto categoryDto2 = new CategoryDto();
        categoryDto2.setName("name" + TimeUtils.getTimeCd());
        categoryDto2.setSort(-100); // 1로 저장 되어야함

        categoryDtos.add(categoryDto2);
        // // 3번 테스트
        // CategoryDto categoryDto3 = new CategoryDto();
        // categoryDto3.setName("name" + TimeUtils.getTimeCd());
        // categoryDto3.setSort(10000); // 마지막 숫자로 저장 되어야 함
        // categoryDtos.add(categoryDto3);
        // // 4번 테스트
        // CategoryDto categoryDto4 = new CategoryDto();
        // categoryDto4.setName("name" + TimeUtils.getTimeCd());
        // categoryDto4.setSort(3);
        // categoryDtos.add(categoryDto4);

        return categoryDtos;
    }
}
