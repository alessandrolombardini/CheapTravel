package project.CheapTravel;

import java.io.IOException;
import java.util.Optional;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class WebRequest implements Runnable {

    private final String url;
    private Optional<HttpEntity> response;
    
    public WebRequest(final String url) {
        this.url = url;
    }

    /**
     * Request of URL page and save it ad HttpEntity
     */
    public void run() {
        final CloseableHttpClient client = HttpClientBuilder.create().build();
        final HttpGet httpGet = new HttpGet(this.url);
        try {
            this.response = Optional.ofNullable(client.execute(httpGet).getEntity());
        } catch (IOException e) {
            this.response = Optional.empty();
        }
    }
    
    public Optional<HttpEntity> getRespone() {
        return this.response;
    }
}
