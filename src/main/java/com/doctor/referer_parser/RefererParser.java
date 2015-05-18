package com.doctor.referer_parser;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;

/**
 * 从http reffer 里面获取关键字，并提取中文
 * 
 * @author doctor
 *
 * @time 2015年5月18日
 */
public class RefererParser {

	public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException {

		String refererUrl = "http://m.aicai.com/p/vmall.do";
		System.out.println(getKeyWord(refererUrl));
		refererUrl = "http://m.aicai.com/p/toutiao.do?vt=5&agentId=2333789";
		System.out.println(getKeyWord(refererUrl));

		refererUrl = "http://m1.baidu.com/from=942d/s?word=15070%E6%9C%9F%E8%83%9C%E8%B4%9F%E5%BD%A9&sa=tb&ts=0934861&t_kt=53";
		System.out.println(getKeyWord(refererUrl));
		refererUrl = "https://www.baidu.com/link?url=VX10ANhmzaeQJwd6RpRFFgfco-NiY0dKPoxe5qat_4jphpB0eiKgBjavWlS-OWOa&wd=%E8%83%9C%E8%B4%9F%E5%BD%A9%20%E6%96%B0%E6%B5%AA&issp=1&f=8&ie=utf-8&tn=baiduhome_pg&inputT=3268&oq=url%20%20";
		System.out.println(getKeyWord(refererUrl));

		System.out.println("/////////");
		refererUrl = "http://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=monline_dg&wd=%E6%96%B0%E6%B5%AA%E5%BD%A9%E7%A5%A8&rsv_pq=b696fdc40000363a&rsv_t=2829ceWdMJsdUztZA2mw8SBQvXKTJhTO%2B4FGnxRh71T9ThZdiQ8ZbB0GhydBdH8t2w&rsv_enter=1&rsv_sug3=8&rsv_sug1=8&rsv_sug2=0&inputT=11&rsv_sug4=3887&rsv_sug=1&bs=html%E9%A6%96%E8%A1%8C%E7%BC%A9%E8%BF%9B";
		System.out.println(getChinese(getKeyWord(refererUrl)));

		System.out.println("/////////");
		refererUrl = "http://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=monline_dg&wd=%E6%96%B0%E6%B5%AA%E5%BD%A9%E7%A5%A8%20caipiao%20%20%20%2C&rsv_pq=e8ed54cd0000c1c3&rsv_t=dbdeTCYnb9Wn8bVW7DQPEBBV7I5375CmQnMradi3%2BtvPGXWzk%2F1SerPZqf75Faugjg&rsv_enter=1&inputT=4744&rsv_sug3=17&rsv_sug1=12&rsv_sug2=0&rsv_sug4=5882&bs=%E6%96%B0%E6%B5%AA%E5%BD%A9%E7%A5%A8";

		System.out.println(getKeyWord(refererUrl));
		System.out.println(getChinese(getKeyWord(refererUrl)));

		System.out.println("=============search.aol");
		refererUrl = "http://search.aol.com/aol/search?s_it=topsearchbox.search&s_chn=prt_aol20&v_t=comsearch&q=%E6%96%B0%E6%B5%AA%E5%BD%A9%E7%A5%A8+caipiao+++%2C";

		System.out.println(getKeyWord(refererUrl));
		System.out.println(getChinese(getKeyWord(refererUrl)));

		System.out.println("=============baidu");
		refererUrl = "http://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=monline_dg&wd=%E6%96%B0%E6%B5%AA%20%E5%BD%A9%E7%A5%A8%20%E7%BD%91&rsv_pq=964ca37a00015711&rsv_t=f20flRQL%2FiDQHho2cKC7kCqbZY%2FK8vdyavn1ccP0kw3uAClvp9DKFv8aGtr6Rk5YIw&rsv_enter=1&inputT=334&rsv_sug3=45&rsv_sug1=20&rsv_sug2=0&rsv_sug4=648&bs=%E6%96%B0%E6%B5%AA%20%E5%BD%A9%E7%A5%A8%20%E7%BD%91%20dd";

		System.out.println(getKeyWord(refererUrl));
		System.out.println(getChinese(getKeyWord(refererUrl)));

	}

	private static String getKeyWord(String reffer) throws MalformedURLException, UnsupportedEncodingException {
		// 要支持很多，参考https://github.com/snowplow/referer-parser/blob/master/java-scala/src/main/resources/referers.yml
		HashSet<String> keyWord = Sets.newHashSet("query", "wd", "word", "kw", "k", "q");
		URL url = new URL(reffer);

		if (StringUtils.isBlank(url.getQuery())) {
			return null;
		}
		final Map<String, String> queryMap = Splitter.on("&").withKeyValueSeparator("=").split(url.getQuery());
		if (queryMap.isEmpty()) {
			return null;
		}

		List<Entry<String, String>> list = queryMap.entrySet().stream().filter(p -> keyWord.contains(p.getKey())).collect(Collectors.toList());
		if (list.isEmpty()) {
			return null;
		}
		Entry<String, String> entry = list.get(0);

		// 编码问题部分有待验证，不同引擎规则到底是怎么样的
		String encode = queryMap.get("ie");
		if (StringUtils.isNotBlank(encode)) {
			if (encode.equalsIgnoreCase("gb2312")) {
				encode = "gbk";
			}
		} else {
			encode = "utf-8";
		}
		String result = URLDecoder.decode(entry.getValue(), encode);
		return result;
	}

	private static String getChinese(final String content) {
		return content.replaceAll("[a-zA-Z]", " ").replaceAll("\\s+", " ");
	}
}
