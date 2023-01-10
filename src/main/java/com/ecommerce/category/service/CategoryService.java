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

@Service
@Transactional
@AllArgsConstructor
public class CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    /**
     * @param categoryDto
     * @return
     * @desc 기본으로 id가 null임을 체크 하므로 save시 select 해도 관련 데이터가 없으므로 insert 실행 isNew를
     *       사용하는 방법도 있다.
     */

    // Create
    public CategoryDto insert(CategoryDto categoryDto) {
        Long parent = categoryDto.getParent();
        categoryDto.setInsertData(parent);
        // 같은 부모가진 카테고리 Count
        Integer cnt = this.categoryRepository.countByParent(categoryDto.getParent());
        // 추가 이므로 +1 하여 데이터 셋팅
        categoryDto.setSort(cnt + 1);

        // 데이터 저장
        this.categoryRepository.save(categoryDto);

        return categoryDto;
    }

    // READ
    /**
     * @param categoryVos  -> 최초에는 비어 있는 categoryVos가 넘어온다.
     * @param parent       ->
     * @param categoryDtos -> parent의 와 연결된 모든 child 리스트
     * @return
     * @desc 재귀함수로 자식 데이터가 존재 하면 CategoryVo의 chidList에 Add 한다.
     */
    public List<CategoryVo> setCategoryList(List<CategoryVo> categoryVos, Long parent, List<CategoryDto> categoryDtos) {
        // 같은 parent가 존재 할 경우
        categoryDtos.stream().filter(category -> category.getParent().equals(parent)).forEach(category -> {
            // Child List를 생성한다.
            List<CategoryVo> chidList = new ArrayList<>();
            // 같은 parent가 존재 하는 id를 기점으로 재귀를 하며 id를 parent로 가진 지식 데이터를 가져온다.
            chidList = this.setCategoryList(chidList, category.getId(), categoryDtos);
            // 현재 id의 데이터와 child 데이터를 셋팅 한다.
            CategoryVo categoryVo = new CategoryVo(category, chidList);
            // return 될 값에 add 한다.
            categoryVos.add(categoryVo);
            // TODO 테스트 필요_다음 검색을 위해 삭제 한다.
            // categoryDtos.remove(category);
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
            CategoryDto curCategoryDto = categoryOpt.get();
            // 수정될 데이터
            String name = categoryDto.getName();
            Integer sort = categoryDto.getSort();
            Long parent = categoryDto.getParent();
            // 현재 데이터
            Long curParent = curCategoryDto.getParent();

            if (name != null) { // 이름 변경
                curCategoryDto.setName(name);
            }
            if (sort != null) { // 순서 변경
                sort = this.updateSort(curCategoryDto, sort);
                curCategoryDto.setSort(sort);
            } else if (parent != null && !curParent.equals(parent)) { // 부모 변경
                // 기존 데이터 변경
                // 기존 parent와 sort 가 더 컸던 데이터를 읽어 온다.
                List<CategoryDto> sortList = this.categoryRepository.findByParentAndSortGreaterThan(
                        curParent,
                        curCategoryDto.getSort());
                // 불러온 데이터를 Sort -1 한다.
                sortList.forEach(categorySort -> categorySort.setSort(categorySort.getSort() - 1));

                // 부모 변경
                // 변경될 parent에 마지막 위치로 이동시킨다.
                Integer cnt = this.categoryRepository.countByParent(parent);
                curCategoryDto.setSort(cnt + 1);
                curCategoryDto.setParent(parent);
            }

            curCategoryDto.setModDate(new Date()); // 수정일
            return curCategoryDto;
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

    // Delete
    public void delete(Long id) {
        this.categoryMapper.deleteChild(id);
    }
}