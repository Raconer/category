package com.ecommerce.category.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ecommerce.category.model.dto.category.CategoryDto;
import com.ecommerce.category.model.vo.category.CategoryVo;
import com.ecommerce.category.repository.CategoryRepository;
import com.ecommerce.category.repository.mapper.CategoryMapper;
import com.ecommerce.category.repository.spec.CategorySpec;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class CategoryService {

    CategoryRepository categoryRepository;

    CategoryMapper categoryMapper;

    // Save
    public CategoryDto save(CategoryDto categoryDto) {
        if (categoryDto.getId() == null) { // Insert
            log.info("Category Insert  -> name : {}, parent : {}", categoryDto.getName(), categoryDto.getParent());
            Integer cnt = this.categoryRepository.countByParent(categoryDto.getParent());
            categoryDto.setInsertData(cnt + 1, categoryDto.getParent());
            this.categoryRepository.save(categoryDto);
        } else { // Update
            categoryDto = this.update(categoryDto);
        }

        return categoryDto;
    }

    // READ
    public List<CategoryVo> setCategoryList(List<CategoryVo> categoryVos, Long parent, List<CategoryDto> categoryDtos) {
        categoryDtos.stream().filter(category -> category.getParent().equals(parent)).forEach(category -> {
            List<CategoryVo> tmpVos = new ArrayList<>();
            tmpVos = this.setCategoryList(tmpVos, category.getId(), categoryDtos);
            CategoryVo categoryVo = new CategoryVo(category, tmpVos);
            categoryVos.add(categoryVo);
        });

        return categoryVos;
    }

    public List<CategoryVo> getList(Long parent) {
        // return
        List<CategoryVo> categoryVos = new ArrayList<>();
        List<CategoryDto> categoryDtos = categoryMapper.findByChild(parent);

        if (!categoryDtos.isEmpty()) {
            categoryVos = this.setCategoryList(categoryVos, parent, categoryDtos);
        }
        return categoryVos;
    }

    // UPDATE
    public CategoryDto update(CategoryDto categoryDto) {
        Optional<CategoryDto> categoryOpt = this.categoryRepository.findById(categoryDto.getId());

        if (categoryOpt.isPresent()) { // 데이터 가 존재 할 경우
            CategoryDto curDto = categoryOpt.get();
            // 수정될 데이터
            String name = categoryDto.getName();
            Integer sort = categoryDto.getSort();
            Long parent = categoryDto.getParent();
            // 현재 데이터
            Long curParent = curDto.getParent();

            if (name != null) { // 이름 변경
                curDto.setName(name);
            }
            if (sort != null) { // 순서 변경
                sort = this.updateSort(curDto, sort);
                curDto.setSort(sort);
            } else if (parent != null && !curParent.equals(parent)) { // 부모 변경

                Integer cnt = this.categoryRepository.countByParent(parent);
                // 기존에 있던 parent 리스트 Sort 수정
                List<CategoryDto> sortList = this.categoryRepository.findByParentAndSortGreaterThan(
                        curParent,
                        curDto.getSort());

                sortList.forEach(categorySort -> {
                    categorySort.setSort(categorySort.getSort() - 1);
                });

                // ****************************************
                curDto.setSort(cnt + 1);
                curDto.setParent(parent);
            }

            curDto.setModDate(new Date()); // 수정일
            return curDto;
        }
        return categoryDto;
    }

    // 순서 변경
    public int updateSort(CategoryDto categoryDto, Integer sort) {

        Long id = categoryDto.getId();
        int from = categoryDto.getSort();
        // Sort Min 설정
        sort = sort < 1 ? 1 : sort; // 1 미만 은 1
        int to = sort;
        int add = -1; // 기존보다 높을 경우
        if (to < from) { // 기존 보다 낮을 경우
            to = from;
            from = sort;
            add = 1;
        }

        // Parent와 변경 범위 데이터 가져오는 쿼리
        Specification<CategoryDto> categorySpec = CategorySpec.findUpdateSort(categoryDto.getParent(), from, to);

        // 정렬
        List<CategoryDto> list = this.categoryRepository.findAll(categorySpec, Sort.by(Sort.Direction.ASC, "sort"));

        // 리스트 sort 변경
        for (CategoryDto category : list) {
            category.setModDate(new Date());
            if (!category.getId().equals(id)) {
                category.setSort(category.getSort() + add);
            }
        }

        // 기존 Id의 sort가 max보다 크면 max로 변경
        int size = list.size() - 1;
        int maxSort = list.get(size).getSort();

        if (maxSort < sort) {
            sort = maxSort + 1;
        }

        return sort;
    }
}