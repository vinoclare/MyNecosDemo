package com.example.demo.Controller;


import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * @author shifengqiang 2022/3/15 6:03 PM
 */
public class TestHttps {
    private static SSLContext ctx;
    static {
        try {
            ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] arg0,
                                               String arg1) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] arg0,
                                               String arg1) throws CertificateException {
                }
            };
            ctx.init(null, new TrustManager[] { tm }, null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        // 在这儿加了 setSSLContext(ctx)
        CloseableHttpClient httpClient = HttpClientBuilder.create().setSSLContext(ctx).build();
        try {
            String url = "http://127.0.0.1:8890/casTest2/user2";
            HttpGet get = new HttpGet(url);

            HttpResponse response = httpClient.execute(get);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
