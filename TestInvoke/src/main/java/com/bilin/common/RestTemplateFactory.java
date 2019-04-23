package com.bilin.common;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * 
 * @author xiaobin.ma
 *
 */

@Configuration
@ConditionalOnClass({ RestTemplate.class, HttpClient.class })
public class RestTemplateFactory {
	@Value("${remote.maxTotalConnect:0}")
	private int maxTotalConnect; //连接池的最大连接数默认为0
	@Value("${remote.maxConnectPerRoute:200}")
	private int maxConnectPerRoute;// 单个主机的最大连接数
	@Value("${remote.connectTimeout:2000}")
	private int connectTimeout;// 连接超时默认2s
	@Value("${remote.readTimeout:3000}")
	private int readTimeout; // 读取超时默认3s
	@Autowired
	private ProxyConfig proxyConfig;

	/**
	 * 创建Http客户端工厂
	 * @return
	 */
	private ClientHttpRequestFactory createFactory() {
		if (maxTotalConnect <= 0) {
			SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
			factory.setConnectTimeout(connectTimeout);
			factory.setReadTimeout(readTimeout);
			if (proxyConfig.getEnabled().booleanValue()) {
				SocketAddress address = new InetSocketAddress(proxyConfig.getHost(),proxyConfig.getPort().intValue());
				Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
				factory.setProxy(proxy);
			}
			return factory;
		}
		HttpClient httpClient = null;
		if (proxyConfig.getEnabled().booleanValue()) {
			httpClient = HttpClientBuilder.create()
					.setProxy(new HttpHost(proxyConfig.getHost(), proxyConfig.getPort().intValue(), "http"))
					.setMaxConnTotal(maxTotalConnect).setMaxConnPerRoute(maxConnectPerRoute).build();
		} else {
			httpClient = HttpClientBuilder.create().setMaxConnTotal(maxTotalConnect)
					.setMaxConnPerRoute(maxConnectPerRoute).build();
		}

		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
		factory.setConnectTimeout(connectTimeout);
		factory.setReadTimeout(readTimeout);
		return factory;
	}

	/**
	 * 初始化RestTemplate，并加入spirng bean 工厂
	 * @return
	 */
	@Bean(name = { "restTemplateClient" })
	@ConditionalOnMissingBean(name = { "restTemplateClient" })
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate(createFactory());
		List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
		
		// 重新设置StringHttpMessageConverter 字符集为UTF-8 ，解决中文乱码问题
		HttpMessageConverter<?> converterTarget = null;
		for (HttpMessageConverter<?> item : converterList) {
			if (StringHttpMessageConverter.class == item.getClass()) {
				converterTarget = item;
				break;
			}
		}
		if (null != converterTarget) {
			converterList.remove(converterTarget);
		}
		converterList.add(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		//FastJson 转换器		
		converterList.add(new FastJsonHttpMessageConverter());
		return restTemplate;
	}
}
