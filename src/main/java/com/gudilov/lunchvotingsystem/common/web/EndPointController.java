package com.gudilov.lunchvotingsystem.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RestController
@RequestMapping("")
public class EndPointController {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @RequestMapping("/endpoints")
    public @ResponseBody
    Object showEndpointsAction() {
        return requestMappingHandlerMapping.getHandlerMethods().keySet().stream().map(t ->
                t.getPatternsCondition().getPatterns().toArray()[0] + " " + (t.getMethodsCondition().getMethods().size() == 0 ? "GET" : t.getMethodsCondition().getMethods().toArray()[0])
        ).sorted().toArray();
    }
}

