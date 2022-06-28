package com.nexign.test.lottery.impl.services;

import com.nexign.test.lottery.api.services.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class RandomServiceImpl implements RandomService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Long getRandomNumber(Long startFrom, Long maxValue) {
        final String url = String.format(
                "https://www.random.org/integers/?num=1&min=%d&max=%d&col=1&base=10&format=plain&rnd=new",
                startFrom,
                maxValue
        );
        try {
            final String response = this.restTemplate.getForObject(url, String.class);
            return Long.parseLong(response.replaceAll("\\s", ""));
        } catch (Exception e) {
            Random rand = new Random();
            return startFrom + rand.nextInt((int) (maxValue - startFrom));
        }

    }
}
