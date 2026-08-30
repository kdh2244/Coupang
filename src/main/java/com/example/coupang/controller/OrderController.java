package com.example.coupang.controller;

import com.example.coupang.entity.Orderitem;
import com.example.coupang.entity.Product;
import com.example.coupang.entity.Users;
import com.example.coupang.repository.UsersRepository;

import com.example.coupang.service.OrderService;
import com.example.coupang.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/order")
@Getter
@Setter
public class OrderController {

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;


    @PostMapping("/showOrderPage")
    public String showOrderPage(@RequestParam("name") String name,
                                @RequestParam("price") int price,
                                @RequestParam("description")String description,
                                @RequestParam("stock")int stock,
                                @RequestParam("id")int id,
                                @RequestParam("count") int count,
                                Model model){

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setStock(stock);
        product.setId(id);

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        Optional<Users> optionalUser = usersRepository.findByEmail(authentication.getName());
        Users user = optionalUser.orElse(new Users());
        model.addAttribute("user",user);
        model.addAttribute("product",product);
        model.addAttribute("count",count);
        return "defaultOrderPage";
    }

    @PostMapping("/orderComplete")
    public String order(@RequestParam("id") int product_id,
                        @RequestParam("count") int count,
                        Model model){
        //user 정보 , 주문 상품 정보 들어오면
        //orderitem , order db 저장하고
        //product 테이블 개수 줄이고
        //주문 완료 메세지 보여주ㅜ면 됨
        System.out.println("ordercomplete 요청 옴.");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Users> optionalUser = usersRepository.findByEmail(authentication.getName());

        Users user = optionalUser.orElse(new Users());

        Product product = productService.getProduct(product_id);
        orderService.order(user,product,count);

        //model.addAttribute("order",order);
        return "completeOrder";


    }

    @PostMapping("/showCartToOrderPage")
    public String cartOrderComplete(@RequestParam("orderitems") List<Integer> orderitems,
                                    @RequestParam("counts") List<Integer> counts,
                                    Model model){

        System.out.println("otheritemsid : "+orderitems);
        System.out.println("counts : "+counts);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Users> optionalUser = usersRepository.findByEmail(authentication.getName());

        Users user = optionalUser.orElse(new Users());

        //orderService.cartToOrder(user,orderitems,counts);

        model.addAttribute("user",user);
        List<String> productNames = orderService.getProductNames(orderitems);
        model.addAttribute("productNames",productNames);


        model.addAttribute("productCounts", counts);

        List<Integer> prices = orderService.getPrices(orderitems,counts);
        model.addAttribute("prices",prices);

        model.addAttribute("orderitems",orderitems);
        //        model.addAttribute("product",product);
//        model.addAttribute("count",count);
        //전체 가격 전달
        int total = 0;
        for(int i = 0 ; i<prices.size();i++){
            total += prices.get(i) * counts.get(i);
        }
        model.addAttribute("total",total);

        return "cartToOrderPage";
    }

    @PostMapping("/cartToOrderComplete")
    public String cartToOrderComplete(@RequestParam("orderitems") List<Integer> orderitems,
                                    @RequestParam("counts") List<Integer> counts,
                                    Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Users> optionalUser = usersRepository.findByEmail(authentication.getName());

        Users user = optionalUser.orElse(new Users());

        orderService.cartToOrder(user,orderitems,counts);



        return "completeOrder";
    }


    @PostMapping("/cart")
    public String cart(@RequestParam("id") int product_id,
                        @RequestParam("count") int count,
                        Model model){

        System.out.println("cart 요청 옴."+count);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Users> optionalUser = usersRepository.findByEmail(authentication.getName());

        Users user = optionalUser.orElse(new Users());

        Product product = productService.getProduct(product_id);
        orderService.cart(user,product,count);

        //model.addAttribute("order",order);
        return "redirect:/home";


    }

    @GetMapping("/cart")
    public String showCart(Model model){
        //사용자 객체 구하고
        //orderitem 엔티티 가져오기 해당 사용자 id로
        //거기서 status cart인 것만 가져오기
        //그리고 order페이지 리턴
        System.out.println("cart 요청 옴.");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Users> optionalUser = usersRepository.findByEmail(authentication.getName());

        Users user = optionalUser.orElse(new Users());
        List<Orderitem> orderItemList = orderService.getCartList(user);

        model.addAttribute("orderitemlist",orderItemList);

        List<Product> productList = productService.getOrderProducts(orderItemList);
        model.addAttribute("productlist",productList);

        List<Integer> priceList = new ArrayList<>();
        for(int i=0 ; i<productList.size();i++){
            priceList.add(productList.get(i).getPrice());
        }
        model.addAttribute("pricelist",priceList);

        //전체 가격 전달
        int total = 0;
        for(int i = 0 ; i<orderItemList.size();i++){
            total += orderItemList.get(i).getCount() * productList.get(i).getPrice();
        }
        model.addAttribute("total",total);

        return "cart";
    }


}
