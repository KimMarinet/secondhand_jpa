package org.koreait.tests;

import org.junit.jupiter.api.Test;
import org.koreait.global.libs.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UtilsPrintThumbTest {

    @Autowired
    private Utils utils;

    @Test
    void test1(){
        System.out.println(utils.printThumb(1L));
        //String url = utils.printThumb(1L);
        //System.out.println(url);

        /* 확인 결과 : infoService.get(seq) 단계에서 가져올 이미지가 없으면 Error발생 예외처리로 인해 다음 코드는 자연스럽게 넘어가게 되어 url은 null을 유지 */
    }
}
