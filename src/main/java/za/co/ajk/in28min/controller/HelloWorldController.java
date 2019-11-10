package za.co.ajk.in28min.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello world";
    }

//  Using RequestHeader to get locale
//    @GetMapping("/hello-world-int")
//    public String hellowWorldInt(@RequestHeader(name="Accept-Language", required = false) Locale locale) {
//        return messageSource.getMessage("good.morning.message", null,  locale);
//    }

    @GetMapping("/hello-world-int")
    public String helloWorldInt() {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

}
