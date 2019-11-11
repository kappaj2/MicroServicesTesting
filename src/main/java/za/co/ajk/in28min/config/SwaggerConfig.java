package za.co.ajk.in28min.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    public static final ApiInfo DEFAULT = ApiInfo.DEFAULT;
//
//    public static final Contact DEFAULT_CONTACT = new Contact("Andre", "www.ajk.co.za", "kappaj@gmail.com");
//    public static final Set<String> DEFAULT_CONSUME_AND_PRODUCES = new HashSet<>(Arrays.asList("application/json","application/xml"));
//
//    private static Collection<VendorExtension> vendorExtensions = new ArrayList<>();
//    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Awesome API Title",
//            "Api Description",
//            "1.0",
//            "urn:tos",
//            DEFAULT_CONTACT,
//            "Apache 2.0",
//            "http://www.apache.org/license/LICENSE-2.0",
//            vendorExtensions);
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(DEFAULT_API_INFO)
//                .produces(DEFAULT_CONSUME_AND_PRODUCES)
//                .consumes(DEFAULT_CONSUME_AND_PRODUCES);
//    }
//}
