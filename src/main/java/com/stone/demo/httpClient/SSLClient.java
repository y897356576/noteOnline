package com.stone.demo.httpClient;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Copyright (C) 1990 2013 南京擎天科技集团
 * SSLClient.java
 * 创建人:zhouzhilong
 * 日期:2016-11-17 10:54
 * 描述:用于进行Https请求的HttpClient
 * 历史:
 */

public class SSLClient extends DefaultHttpClient {

    private static Logger logger = Logger.getLogger(SSLClient.class);

    public SSLClient() {
        super();
        try {
            SSLContext ctx = SSLContext.getInstance("SSL");

            X509TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            ctx.init(null, new TrustManager[]{tm}, null);

            SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            ClientConnectionManager ccm = this.getConnectionManager();

            SchemeRegistry sr = ccm.getSchemeRegistry();

            sr.register(new Scheme("https", 443, ssf));

        } catch (Exception ex) {
            logger.error("https转换出错。",ex);
        }

    }
}