package com.gudilov.lunchvotingsystem.common.utils;

import com.gudilov.lunchvotingsystem.common.web.json.JsonUtil;
import mockit.Mock;
import mockit.MockUp;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;
import java.time.*;
import java.util.List;

public class TestUtil {
    public static String getContent(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString();
    }

    public static <T> T readFromJson(ResultActions action, Class<T> clazz) throws UnsupportedEncodingException {
        return readFromJsonMvcResult(action.andReturn(), clazz);
    }

    public static <T> T readFromJsonMvcResult(MvcResult result, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.readValue(getContent(result), clazz);
    }

    public static <T> List<T> readListFromJsonMvcResult(MvcResult result, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.readValues(getContent(result), clazz);
    }

    public static void shiftTime(LocalDateTime dateTime) {
        Clock clock = Clock.fixed(dateTime.toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));

        new MockUp<LocalDate>() {
            @Mock
            public LocalDate now() {
                return LocalDate.now(clock);
            }
        };
        new MockUp<LocalTime>() {
            @Mock
            public LocalTime now() {
                return LocalTime.now(clock);
            }
        };
        new MockUp<LocalDateTime>() {
            @Mock
            public LocalDateTime now() {
                return LocalDateTime.now(clock);
            }
        };
    }

}
