package com.doctor.referer_parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLDecoder;

import com.snowplowanalytics.refererparser.CorruptYamlException;
import com.snowplowanalytics.refererparser.Parser;
import com.snowplowanalytics.refererparser.Referer;

/**
 * @author doctor
 *
 * @time 2015年5月18日 上午10:59:05
 */
public class UseRefererParserInJava {

	public static void main(String[] args) throws IOException, CorruptYamlException, URISyntaxException {

		String refererUrl = "http://www.google.com/search?q=gateway+oracle+cards+denise+linn&hl=en&client=safari";
		String pageUrl = "http:/www.psychicbazaar.com/shop"; // Our current URL

		Parser parser = new Parser();
		Referer referer = parser.parse(refererUrl, pageUrl);
		System.out.println(referer.source);
		System.out.println(referer.term);
		System.out.println("=============");

		refererUrl = "http://search.aol.com/aol/search?s_it=topsearchbox.search&s_chn=prt_aol20&v_t=comsearch&q=%E6%96%B0%E6%B5%AA%E5%BD%A9%E7%A5%A8";
		pageUrl = "http://www.aicai.com";
		referer = parser.parse(refererUrl, "");
		System.out.println(referer.source);
		System.out.println(referer.term);
		System.out.println("=============");

		refererUrl = "http://www.baidu.com/baidu?wd=%CB%AB%C9%AB%C7%F2qq%C8%BA&tn=monline_dg";
		pageUrl = "http://www.aicai.com";
		referer = parser.parse(refererUrl, pageUrl);
		System.out.println(referer.source);
		System.out.println(referer.term);
		System.out.println("=============");

		System.out.println(URLDecoder.decode("%CB%AB%C9%AB%C7%F2qq%C8%BA", "gbk"));

	}

}
