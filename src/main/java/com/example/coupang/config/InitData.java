package com.example.coupang.config;

import com.example.coupang.entity.Category;
import com.example.coupang.entity.Product;
import com.example.coupang.entity.Users;
import com.example.coupang.repository.CategoryRepository;
import com.example.coupang.repository.ProductRepository;
import com.example.coupang.repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InitData {

    @Bean
    CommandLineRunner init(UsersRepository usersRepository, ProductRepository productRepository, CategoryRepository categoryRepository, PasswordEncoder encoder){

        return args -> {
            if(usersRepository.count() == 0 ){

                Users admin = Users.builder()
                                .name("admin@naver.com")
                                        .password(encoder.encode("1111"))
                                                .role("ROLE_ADMIN")
                                                        .name("kimdohoon")
                                                                    .build();
                usersRepository.save(admin);
            }
            if(productRepository.count() == 0 ){
                List<Product> products = new ArrayList<>();
                Product product = Product.builder()
                        .name("삼성 노트북")
                        .price(1000000)
                        .description("삼성에서 개발한 최신 노트북입니다.")
                        .stock(5)
                        .build();

                products.add(product);

                product = Product.builder()
                        .name("msi 노트북")
                        .price(1500000)
                        .description("msi에서 개발한 최신 게이밍 노트북입니다.")
                        .stock(10)
                        .build();

                products.add(product);

                product = Product.builder()
                        .name("체인소맨 24권")
                        .price(4500)
                        .description("체인소맨 단행본 24권입니다.")
                        .stock(30)
                        .build();

                products.add(product);

                products.add(Product.builder()
                        .name("스위치")
                        .price(800000)
                        .description("닌텐도에서 개발한 스위치입니다.")
                        .stock(30)
                        .build());

                products.add(Product.builder()
                        .name("아이폰 16")
                        .price(1200000)
                        .description("애플의 최신 스마트폰입니다.")
                        .stock(50)
                        .build());

                products.add(Product.builder()
                        .name("갤럭시 S25")
                        .price(1100000)
                        .description("삼성의 최신 스마트폰입니다.")
                        .stock(40)
                        .build());

                products.add(Product.builder()
                        .name("맥북 프로")
                        .price(2500000)
                        .description("애플 고성능 노트북입니다.")
                        .stock(20)
                        .build());

                products.add(Product.builder()
                        .name("LG 그램")
                        .price(1500000)
                        .description("가벼운 LG 노트북입니다.")
                        .stock(35)
                        .build());

                products.add(Product.builder()
                        .name("아이패드 프로")
                        .price(1400000)
                        .description("애플 태블릿 제품입니다.")
                        .stock(25)
                        .build());

                products.add(Product.builder()
                        .name("갤럭시 탭")
                        .price(900000)
                        .description("삼성 태블릿 제품입니다.")
                        .stock(30)
                        .build());

                products.add(Product.builder()
                        .name("에어팟 프로")
                        .price(350000)
                        .description("애플 무선 이어폰입니다.")
                        .stock(60)
                        .build());

                products.add(Product.builder()
                        .name("소니 헤드폰")
                        .price(450000)
                        .description("소니 노이즈 캔슬링 헤드폰입니다.")
                        .stock(15)
                        .build());

                products.add(Product.builder()
                        .name("닌텐도 게임팩")
                        .price(60000)
                        .description("닌텐도 스위치 게임 타이틀입니다.")
                        .stock(100)
                        .build());

                products.add(Product.builder()
                        .name("게이밍 키보드")
                        .price(150000)
                        .description("기계식 게이밍 키보드입니다.")
                        .stock(70)
                        .build());

                products.add(Product.builder()
                        .name("게이밍 마우스")
                        .price(80000)
                        .description("고성능 게이밍 마우스입니다.")
                        .stock(80)
                        .build());

                products.add(Product.builder()
                        .name("모니터 32인치")
                        .price(400000)
                        .description("32인치 UHD 모니터입니다.")
                        .stock(20)
                        .build());

                products.add(Product.builder()
                        .name("블루투스 스피커")
                        .price(120000)
                        .description("휴대용 블루투스 스피커입니다.")
                        .stock(45)
                        .build());

                products.add(Product.builder()
                        .name("로봇 청소기")
                        .price(700000)
                        .description("자동 청소 로봇입니다.")
                        .stock(18)
                        .build());

                products.add(Product.builder()
                        .name("공기청정기")
                        .price(500000)
                        .description("미세먼지 제거 공기청정기입니다.")
                        .stock(22)
                        .build());

                products.add(Product.builder()
                        .name("커피 머신")
                        .price(300000)
                        .description("가정용 커피 머신입니다.")
                        .stock(12)
                        .build());

                products.add(Product.builder()
                        .name("전기 면도기")
                        .price(180000)
                        .description("남성용 전기 면도기입니다.")
                        .stock(55)
                        .build());

                products.add(Product.builder()
                        .name("무선 충전기")
                        .price(50000)
                        .description("스마트폰 무선 충전기입니다.")
                        .stock(90)
                        .build());

                products.add(Product.builder()
                        .name("외장 SSD")
                        .price(200000)
                        .description("고속 외장 저장장치입니다.")
                        .stock(40)
                        .build());

                products.add(product);

                product = Product.builder()
                        .name("비비고 냉동 만두")
                        .price(10000)
                        .description("비비고 냉동 만두 입니다.")
                        .stock(50)
                        .build();

                products.add(product);

                products.add(Product.builder()
                        .name("삼성 TV")
                        .price(1300000)
                        .description("삼성 55인치 스마트 TV입니다.")
                        .stock(25)
                        .build());

                products.add(Product.builder()
                        .name("LG OLED TV")
                        .price(2200000)
                        .description("LG OLED 고화질 TV입니다.")
                        .stock(15)
                        .build());

                products.add(Product.builder()
                        .name("다이슨 청소기")
                        .price(800000)
                        .description("무선 프리미엄 청소기입니다.")
                        .stock(20)
                        .build());

                products.add(Product.builder()
                        .name("애플 워치")
                        .price(600000)
                        .description("애플 스마트 워치입니다.")
                        .stock(35)
                        .build());

                products.add(Product.builder()
                        .name("갤럭시 워치")
                        .price(350000)
                        .description("삼성 스마트 워치입니다.")
                        .stock(40)
                        .build());

                products.add(Product.builder()
                        .name("키보드 마우스 세트")
                        .price(90000)
                        .description("사무용 키보드 마우스 세트입니다.")
                        .stock(70)
                        .build());

                products.add(Product.builder()
                        .name("USB C 허브")
                        .price(45000)
                        .description("노트북 연결용 USB 허브입니다.")
                        .stock(100)
                        .build());

                products.add(Product.builder()
                        .name("웹캠")
                        .price(70000)
                        .description("화상회의용 웹캠입니다.")
                        .stock(50)
                        .build());

                products.add(Product.builder()
                        .name("마이크")
                        .price(150000)
                        .description("방송용 콘덴서 마이크입니다.")
                        .stock(30)
                        .build());

                products.add(Product.builder()
                        .name("캡처보드")
                        .price(250000)
                        .description("게임 방송용 캡처보드입니다.")
                        .stock(10)
                        .build());

                products.add(Product.builder()
                        .name("플레이스테이션 5")
                        .price(700000)
                        .description("소니 콘솔 게임기입니다.")
                        .stock(20)
                        .build());

                products.add(Product.builder()
                        .name("엑스박스 시리즈 X")
                        .price(650000)
                        .description("마이크로소프트 게임 콘솔입니다.")
                        .stock(18)
                        .build());

                products.add(Product.builder()
                        .name("닌텐도 프로 컨트롤러")
                        .price(80000)
                        .description("닌텐도 스위치 전용 컨트롤러입니다.")
                        .stock(60)
                        .build());

                products.add(Product.builder()
                        .name("그래픽카드 RTX 5070")
                        .price(900000)
                        .description("고성능 그래픽카드입니다.")
                        .stock(12)
                        .build());

                products.add(Product.builder()
                        .name("CPU 라이젠 7")
                        .price(400000)
                        .description("AMD 고성능 CPU입니다.")
                        .stock(25)
                        .build());

                products.add(Product.builder()
                        .name("DDR5 메모리 32GB")
                        .price(150000)
                        .description("고성능 메모리입니다.")
                        .stock(80)
                        .build());

                products.add(Product.builder()
                        .name("NVMe SSD 1TB")
                        .price(120000)
                        .description("빠른 저장장치입니다.")
                        .stock(90)
                        .build());

                products.add(Product.builder()
                        .name("외장 하드 2TB")
                        .price(100000)
                        .description("대용량 외장 저장장치입니다.")
                        .stock(45)
                        .build());

                products.add(Product.builder()
                        .name("스마트 전구")
                        .price(30000)
                        .description("IoT 스마트 조명입니다.")
                        .stock(100)
                        .build());

                products.add(Product.builder()
                        .name("전자책 리더기")
                        .price(250000)
                        .description("전자책 전용 리더기입니다.")
                        .stock(30)
                        .build());

                products.add(Product.builder()
                        .name("블루투스 이어폰")
                        .price(90000)
                        .description("무선 블루투스 이어폰입니다.")
                        .stock(75)
                        .build());

                products.add(Product.builder()
                        .name("보조 배터리")
                        .price(40000)
                        .description("휴대용 고속 충전 배터리입니다.")
                        .stock(120)
                        .build());

                products.add(Product.builder()
                        .name("충전 케이블")
                        .price(15000)
                        .description("고속 충전 USB 케이블입니다.")
                        .stock(200)
                        .build());

                products.add(Product.builder()
                        .name("스마트 체중계")
                        .price(60000)
                        .description("블루투스 스마트 체중계입니다.")
                        .stock(40)
                        .build());

                products.add(Product.builder()
                        .name("전동 칫솔")
                        .price(100000)
                        .description("음파 전동 칫솔입니다.")
                        .stock(35)
                        .build());

                products.add(Product.builder()
                        .name("캠핑 랜턴")
                        .price(50000)
                        .description("휴대용 LED 랜턴입니다.")
                        .stock(55)
                        .build());

                products.add(Product.builder()
                        .name("블렌더")
                        .price(90000)
                        .description("가정용 믹서기입니다.")
                        .stock(25)
                        .build());

                products.add(Product.builder()
                        .name("에어프라이어")
                        .price(150000)
                        .description("대용량 에어프라이어입니다.")
                        .stock(30)
                        .build());

                products.add(Product.builder()
                        .name("전기포트")
                        .price(40000)
                        .description("빠른 가열 전기포트입니다.")
                        .stock(60)
                        .build());

                products.add(Product.builder()
                        .name("스마트 도어락")
                        .price(250000)
                        .description("디지털 스마트 도어락입니다.")
                        .stock(15)
                        .build());

                products.add(product);

                product = Product.builder()
                        .name("애플 에어팟")
                        .price(100000)
                        .description("애플에서 개발한 에어팟입니다.")
                        .stock(5)
                        .build();

                products.add(product);

                productRepository.saveAll(products);
            }
            if(categoryRepository.count() == 0 ){
                List<Category> categoryList = new ArrayList<>();

                Category category = new Category();

                category = new Category();
                category.setName("가전");
                categoryList.add(category);

                category = new Category();
                category.setName("컴퓨터");
                categoryList.add(category);

                category = new Category();
                category.setName("디지털");
                categoryList.add(category);

                category = new Category();
                category.setName("휴대폰");
                categoryList.add(category);

                category = new Category();
                category.setName("가구");
                categoryList.add(category);

                category = new Category();
                category.setName("생활용품");
                categoryList.add(category);

                category = new Category();
                category.setName("주방용품");
                categoryList.add(category);

                category = new Category();
                category.setName("식품");
                categoryList.add(category);

                category = new Category();
                category.setName("의류");
                categoryList.add(category);

                category = new Category();
                category.setName("신발");
                categoryList.add(category);

                category = new Category();
                category.setName("잡화");
                categoryList.add(category);

                category = new Category();
                category.setName("화장품");
                categoryList.add(category);

                category = new Category();
                category.setName("스포츠");
                categoryList.add(category);

                category = new Category();
                category.setName("취미");
                categoryList.add(category);

                category = new Category();
                category.setName("자동차용품");
                categoryList.add(category);

                categoryRepository.saveAll(categoryList);
            }
        };
    }
}
