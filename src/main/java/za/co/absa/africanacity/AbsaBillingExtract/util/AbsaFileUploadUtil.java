package za.co.absa.africanacity.AbsaBillingExtract.util;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AbsaFileUploadUtil {

    public static Response uploadFile(File file, String BASE_URL) throws FileNotFoundException {
        BasicHttpContext localContext = new BasicHttpContext();
        HttpClient httpClient = HttpClientBuilder.create().build();
        ApacheHttpClient4Engine engine = new ApacheHttpClient4Engine(httpClient, localContext);
        ResteasyClient client = new ResteasyClientBuilder().httpEngine(engine).build();

        InputStream fileInStream = new FileInputStream(file);
        String contentDisposition = "attachment; filename=" + file.getName();

        return client.target(BASE_URL)
                .path("post")
                .request()
                .header("Content-Disposition", contentDisposition)
                .post(Entity.entity(fileInStream, MediaType.APPLICATION_OCTET_STREAM));
    }
}
