package com.doctor.httpclient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;

/**
 * 获得ip信息（城市、省份等）
 * 
 * @author doctor
 *
 * @time 2015年4月29日
 */
public class QueryIpInfo {
	private static final String queryUrl = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=";
	private static final Gson gson = new Gson();

	public static void main(String[] args) throws IOException {
		String ip = "202.114.031.2551";
		String content = Request.Get(queryUrl + ip).execute().returnContent().asString(StandardCharsets.UTF_8);

		@SuppressWarnings("unchecked")
		Map<String, String> fromJson = gson.fromJson(content, Map.class);

		String country = fromJson.get("country");
		String province = fromJson.get("province");
		String city = fromJson.get("city");
		String isp = fromJson.get("isp");

		IpInfo ipInfo = gson.fromJson(content, IpInfo.class);
		System.out.println(ipInfo);
	}

	private static class IpInfo {
		private double ret;
		private double start;
		private double end;
		private String country;
		private String province;
		private String city;
		private String district;
		private String isp;
		private String type;
		private String desc;

		public double getRet() {
			return ret;
		}

		public void setRet(double ret) {
			this.ret = ret;
		}

		public double getStart() {
			return start;
		}

		public void setStart(double start) {
			this.start = start;
		}

		public double getEnd() {
			return end;
		}

		public void setEnd(double end) {
			this.end = end;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getIsp() {
			return isp;
		}

		public void setIsp(String isp) {
			this.isp = isp;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		@Override
		public String toString() {

			return ToStringBuilder.reflectionToString(this);
		}

	}
}
