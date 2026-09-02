package com.example.coupang.service;

import com.example.coupang.entity.Order;
import com.example.coupang.entity.Orderitem;
import com.example.coupang.entity.Product;
import com.example.coupang.entity.Users;
import com.example.coupang.repository.OrderRepository;
import com.example.coupang.repository.OrderitemRepository;
import com.example.coupang.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.util.*;

@Service
public class OrderService {
    //orderitem , order db 저장하고
    //product 테이블 개수 줄이고
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderitemRepository orderitemRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;

    public void order(Users user, Product product, int count){

        if(count>product.getStock()){
            throw new IllegalArgumentException("상품제고가 부족합니다.");
        }

        //order 랑 orderitem 엔티티 생성하기
        Order order = new Order();
        order.setStatus("ready");
        order.setUsers(user);
        order.setTotalPrice(count*product.getPrice());
        order.setOrderDate(LocalDate.now());

        Orderitem orderItem = new Orderitem();
        orderItem.setCount(count);
        orderItem.setTotalPrice(count*product.getPrice());
        orderItem.setProduct(product);
        orderItem.setOrder(order);
        orderItem.setStatus("ready");

        //product 제고 빼기
        productService.decreaseStock(product.getId(),count);

        orderRepository.save(order);
        orderitemRepository.save(orderItem);
    }


    public void cart(Users user, Product product, int count){

        if(count > product.getStock()){
            throw new IllegalArgumentException("상품제고가 부족합니다.");
        }

        Order order = orderRepository.findByUsers_idAndStatusStartsWith(user.getId(),"cart");
        //order 엔티티 만들기
        if(order == null) {
            order = new Order();
            order.setStatus("cart");
            order.setUsers(user);
            //order.setTotalPrice();
            orderRepository.save(order);
        }

        //orderitem 엔티티 생성하기

        Orderitem orderItem = new Orderitem();
        orderItem.setCount(count);
        orderItem.setTotalPrice(count*product.getPrice());
        orderItem.setProduct(product);
        orderItem.setStatus("cart");
        orderItem.setOrder(order);
        orderitemRepository.save(orderItem);


    }

    public List<Orderitem> getCartList(Users user){
        Order order = orderRepository.findByUsers_idAndStatusStartsWith(user.getId(),"cart");
        if (order != null){
           int orderId = order.getId();
           List<Orderitem> orderitems = orderitemRepository.findByOrder_id(orderId);
           return orderitems;
        }
        List<Orderitem> noExistOrderitems = new ArrayList<>();
        return noExistOrderitems;
    }

    public void cartToOrder(Users user, List<Integer> orderitems, List<Integer> counts) {

        Order order = new Order();
        order.setUsers(user);
        order.setStatus("ready");
        order.setOrderDate(LocalDate.now());
        orderRepository.save(order);
        for(int i = 0 ; i<orderitems.size(); i++){
           Optional<Orderitem> optionalOrderitem = orderitemRepository.findById(orderitems.get(i));
           Orderitem orderitem = optionalOrderitem.get();
            Optional<Product> product = productRepository.findById(orderitem.getProduct().getId());

            if(counts.get(i)>product.get().getStock()){
                throw new IllegalArgumentException("상품제고가 부족합니다.");
            }

            orderitem.setStatus("ready");
           orderitem.setOrder(order);
           orderitem.setCount(counts.get(i));

           int price = product.get().getPrice();
           orderitem.setTotalPrice(counts.get(i)*price);
           orderitemRepository.save(orderitem);

           int stock = product.get().getStock() - counts.get(i);
           product.get().setStock(stock);
           productRepository.save(product.get());
        }

    }

    public List<String> getProductNames(List<Integer> orderitems){
        List<String> productNames = new ArrayList<>();
        for(int i = 0 ;i<orderitems.size();i++){
            Optional<Orderitem> orderitem = orderitemRepository.findById(orderitems.get(i));
            productNames.add(orderitem.get().getProduct().getName());
        }
        return productNames;
    }

//    public List<Integer> getProductCounts(List<Integer> orderitems){
//        List<Integer> productCounts = new ArrayList<>();
//        for(int i = 0 ;i<orderitems.size();i++){
//            Optional<Orderitem> orderitem = orderitemRepository.findById(orderitems.get(i));
//            productCounts.add(orderitem.get().getCount());
//        }
//        return productCounts;
//    }

    public List<Integer> getPrices(List<Integer> orderitems, List<Integer> counts){
        List<Integer> prices = new ArrayList<>();
        for(int i = 0 ;i<orderitems.size();i++){
            Optional<Orderitem> orderitem = orderitemRepository.findById(orderitems.get(i));
            int price = orderitem.get().getProduct().getPrice();
            prices.add(price * counts.get(i));
        }
        return prices;
    }

    public List<List<Orderitem>> getOrderitems(Users user){
        List<Order> orderList = orderRepository.findByUsers_id(user.getId());
        //order 가 cart인 경우는 제외
        for(int i = 0 ; i<orderList.size() ; i++){
            if(orderList.get(i).getStatus().equals("cart")){
                orderList.remove(i);
            }
        }
        //주문 날짜 기준 오름차순 정렬
        orderList.sort(Comparator.comparing(Order::getOrderDate).reversed());

        System.out.println(" service orderList : "+orderList);
//        List<Users> orderUserList = new ArrayList<>();
//        for(int i = 0 ;i<orderList.size();i++){
//            orderUserList.add(orderList.get(i).getUsers());
//        }
//
//        System.out.println("orderUserList : "+orderUserList);

        List<List<Orderitem>> orderitemsList = new ArrayList<>();

        for(int i = 0 ;i<orderList.size();i++){
            List<Orderitem> orderitems = orderitemRepository.findByOrder_id(orderList.get(i).getId());
            orderitemsList.add(orderitems);
            System.out.println(i + " 번째 service orderitemsList : "+orderitemsList);
        }
        System.out.println("service orderitemsList : "+orderitemsList);

        return orderitemsList;
    }

}
