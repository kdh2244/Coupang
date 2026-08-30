package com.example.coupang.service;

import com.example.coupang.entity.Category;
import com.example.coupang.entity.Orderitem;
import com.example.coupang.entity.Product;
import com.example.coupang.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.coupang.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public void save(Product product){
        productRepository.save(product);
    }

    public void saveWithCategories(Product product , List<Integer> addCategoryIds){

        List<Category> categoryList = new ArrayList<>();

        for(int i = 0 ; i<addCategoryIds.size();i++){
            Optional<Category> optionalCategory = categoryRepository.findById(addCategoryIds.get(i));
            Category category = optionalCategory.get();

            categoryList.add(category);
        }
        product.setCategoryList(categoryList);
        productRepository.save(product);
    }

    public Page<Product> list(){
        Pageable pageable = PageRequest.of(0,8);
        Page<Product> products = productRepository.findAll(pageable);
        return products;
    }

    public Page<Product> getPagelist(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber,8);
        Page<Product> products = productRepository.findAll(pageable);
        return products;
    }

    public Product getProduct(int id){
        Optional<Product> product = productRepository.findById(id);
        return product.get();
    }

    public List<Product> getOrderProducts(List<Orderitem> orderitems){
        List<Product> products = new ArrayList<>();
        for(int i = 0 ; i<orderitems.size();i++){
            Product product = orderitems.get(i).getProduct();
            int id = product.getId();
            products.add(getProduct(id));
        }
        return products;
    }

    public Page<Product> searchProducts(String keyword,int page,String categoryId){

        if(!categoryId.equals("")){

            int categoryIdn = Integer.parseInt(categoryId);
            //category 에 맞는 product id가져오기
            Optional<Category> optionalCategory =  categoryRepository.findById(categoryIdn);
            Category category = optionalCategory.get();

            List<Product> productList = category.getProductList();
            List<Integer> productIdList = new ArrayList<>();
            for(int i = 0 ;i < productList.size() ; i++){
                productIdList.add(productList.get(i).getId());
            }
            System.out.println("productIdLIst : " + productIdList);

            Pageable pageable = PageRequest.of(page,8);
            // 카테고리에 포함되는 product들에서 검색
            Page<Product> products = productRepository.findByIdInAndNameContaining(productIdList,keyword,pageable);
            return products;
        }

        List<Integer> productIdList = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for(int i = 0 ; i<products.size() ; i++){
            productIdList.add(products.get(i).getId());
        }

        System.out.println("productIdList : "+productIdList);


        Pageable pageable = PageRequest.of(page,8);
        // 카테고리에 포함되는 product들에서 검색
        Page<Product> searchproducts = productRepository.findByIdInAndNameContaining(productIdList,keyword,pageable);
        return searchproducts;

    }



    @Transactional
    public void decreaseStock(int product_id,int count){
        Optional<Product> product = productRepository.findById(product_id);
        product.get().setStock(product.get().getStock()-count);
    }

    @Transactional
    public void updateProduct(Product updateProduct,int productId, List<Integer> addCategoryIds){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product product = optionalProduct.get();

        product.setName(updateProduct.getName());
        product.setPrice(updateProduct.getPrice());
        product.setDescription(updateProduct.getDescription());
        product.setStock(updateProduct.getStock());

        List<Category> categoryList = new ArrayList<>();
        //category 추가
        for(int i = 0 ; i<addCategoryIds.size();i++){
            Optional<Category> optionalCategory = categoryRepository.findById(addCategoryIds.get(i));
            Category category = optionalCategory.get();

            categoryList.add(category);
        }
        product.getCategoryList().clear();
        product.setCategoryList(categoryList);


        return;
    }

}
