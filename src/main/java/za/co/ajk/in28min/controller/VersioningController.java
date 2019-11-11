package za.co.ajk.in28min.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.ajk.in28min.model.Name;
import za.co.ajk.in28min.model.PersonV1;
import za.co.ajk.in28min.model.PersonV2;

@RestController
public class VersioningController {

    /*
        First two are easy to use with browsers, as well as caching is easy.
     */
    //uri - Twitter
    @GetMapping("/v1/person")
    public PersonV1 getPersonV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("/v2/person")
    public PersonV2 getPersonV2(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    //  Param   -   AWS uses it
    @GetMapping(value = "/person/param", params="version=1" )
    public PersonV1 paramV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "/person/param", params="version=2" )
    public PersonV2 paramV2(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    //  header  - needs more work on documentation, and needs header plugin capability to execute.
    @GetMapping(value = "/person/header", headers="X-API-VERSION=1" )
    public PersonV1 headerV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "/person/header", headers="X-API-VERSION=2" )
    public PersonV2 headerV2(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    //  produces
    //  Accept type versioning
    //  MimeType versioning
    //
    @GetMapping(value = "/person/produces", produces="application/vnd.company.app-v1+json" )
    public PersonV1 producesV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "/person/produces", produces="application/vnd.company.app-v2+json" )
    public PersonV2 producesV2(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}
