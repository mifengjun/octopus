package org.lvgo.example;

import org.junit.jupiter.api.Test;
import org.lvgo.octopus.core.Data;
import org.lvgo.octopus.core.Duplicate;
import org.lvgo.octopus.core.Duplicator;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class WeiBoExtractorTest {

    @Test
    public void extract() {
        WeiBoExtractor weiBoExtractor = new WeiBoExtractor();
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,und;q=0.7");
        headers.put("Cache-Control", "max-age=0");
        headers.put("Connection", "keep-alive");
        headers.put("Host", "weibo.com");
        headers.put("Cookie", "SINAGLOBAL=1912405174942.4287.1575878978928; UOR=finance.eastmoney.com,widget.weibo.com,www.takefoto.cn; SCF=AltzmpVceIgtF9o2uktQUPUxZIr2odHBhPv-FXKShjhGgnUjG5tC45OkH0dg8vRi0Zu5J6uwUPHBMkbWoluIBmI.; SUHB=0oUZ47iTUbWz0c; YF-V5-G0=125128c5d7f9f51f96971f11468b5a3f; _s_tentry=weibo.com; Apache=8291333746808.027.1575949692311; ULV=1575949692323:2:2:2:8291333746808.027.1575949692311:1575878978951; wb_view_log_2475178231=1366*7681; Ugrow-G0=9ec894e3c5cc0435786b4ee8ec8a55cc; SUB=_2AkMqs7zPdcPxrAFVn_0Uy2Pgb4xH-jyZZtU5An7uJhMyAxgv7kpUqSVutBF-XJrPcvmLpnFOrYvJYfZGfCIU4Plk; SUBP=0033WrSXqPxfM72wWs9jqgMF55529P9D9WhUpQHPlCgqsck1sd.PjG_B5JpVF02RSoqRShefeKM0; YF-Page-G0=b7e3c62ec2c0b957a92ff634c16e7b3f|1575957496|1575957485; webim_unReadCount=%7B%22time%22%3A1575957500798%2C%22dm_pub_total%22%3A0%2C%22chat_group_client%22%3A0%2C%22allcountNum%22%3A0%2C%22msgbox%22%3A0%7D");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
        headers.put("Sec-Fetch-Mode", "navigate");
        headers.put("Sec-Fetch-Site", "same-origin");
        headers.put("Sec-Fetch-User", "?1");
        headers.put("Upgrade-Insecure-Requests", "1");
        Duplicate duplicate = Duplicator.url("https://weibo.com/u/5537039775")
                .headers(headers).copy();
        Data data = weiBoExtractor.extract(duplicate);
    }

}