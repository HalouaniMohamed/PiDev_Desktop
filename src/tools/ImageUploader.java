package tools;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;

public class ImageUploader {

    private static final String UPLOAD_URL = "http://127.0.0.1:8000/uploadProductsImage";

    public String uploadImage(File imageFile) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(UPLOAD_URL);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("image", imageFile, ContentType.create("image/jpeg", Charset.forName("UTF-8")), imageFile.getName());
        HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String imageName = null;
        try {
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                String jsonString;
                try {
                    jsonString = EntityUtils.toString(responseEntity);
                    JSONObject jsonObject = new JSONObject(jsonString);
                    imageName = jsonObject.getString("imageName");
                } catch (ParseException ex) {
                    System.out.println("error parsing");
                }

            }
        } finally {
            response.close();
            httpClient.close();
        }
        System.out.println("im done");
        return imageName;
    }

}
