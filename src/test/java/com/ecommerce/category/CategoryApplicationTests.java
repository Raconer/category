package com.ecommerce.category;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryApplicationTests {


}
// "SELECT c1.id,c1.name,c1.parent,c1.sort,c1.reg_date,count(c2.id) as
// childCnt"+"FROM category c1"+"LEFT JOIN category c2"+"ON c2.parent =
// c1.id"+"where c1.parent = :parent"+"group by c1.id,c2.parent"