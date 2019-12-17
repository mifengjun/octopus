package org.lvgo.example;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.lvgo.octopus.bean.OctopusPage;
import org.lvgo.octopus.bean.OctopusProxy;
import org.lvgo.octopus.core.Extractor;
import org.lvgo.octopus.core.IpProxy;
import org.lvgo.octopus.core.Octopus;

import java.util.ArrayList;
import java.util.List;

/**
 * //TODO 描述此类作用
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/17 9:52
 */
public class MyIpProxy implements IpProxy {
    @Override
    public void init(OctopusProxy octopusProxy) {
        String url = "https://www.xicidaili.com/nn";
        Octopus.init().url(url).pageDown(true).pageSize(100).get().extractor(new Extractor() {
            @Override
            public OctopusPage getPageInfo(Octopus octopus) {
                Document document = octopus.getDocument();


                Element pagination = document.getElementsByClass("pagination").first();

                Elements a = pagination.getElementsByTag("a");
                Element element = a.get(a.size() - 2);
                OctopusPage octopusPage = new OctopusPage();
                int page = octopus.getPage();

                int totalPage = Integer.valueOf(element.text());

                octopusPage.setPageTotal(totalPage);

                // 组装分页地址
                List<String> urls = new ArrayList<>();
                // 将源地址补充到地址池
                urls.add(octopus.getUrl());
                // 通过分页大小获取分页数据
                for (int i = 1; i < (page != 0 ? page : totalPage); i++) {
                    String url = octopus.getUrl() + "/" + i;
                    urls.add(url);
                }

                octopusPage.setUrls(urls);
                return octopusPage;
            }

            @Override
            public void extract(Octopus octopus) {
                Element ipList = octopus.getDocument().getElementById("ip_list");

                Elements tr = ipList.getElementsByTag("tr");

                concurrentHandle(octopus, tr);
            }

            @Override
            public void elementHandle(Octopus octopus, Element element) {
                Elements td = element.getElementsByTag("td");
                if (td.isEmpty()) {
                    return;
                }
                System.out.println(td.get(1).text() + " : " + td.get(2).text());
            }
        }).start();
    }
}
