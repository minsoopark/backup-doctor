package sgen.backup.dr.network.requests;

import android.os.AsyncTask;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import sgen.backup.dr.network.RequestConfig;

import java.util.ArrayList;
import java.util.List;

public class LoginRequest extends AsyncTask<String, Void, HttpResponse> {
    public interface LoginRequestCallback {
        public void onComplete(JSONObject json);
        public void onFail();
    }

    private LoginRequestCallback callback;

    public LoginRequest(LoginRequestCallback callback) {
        this.callback = callback;
    }

    @Override
    protected HttpResponse doInBackground(String... contents) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = null;
        try {
            String url = RequestConfig.BASE_URL + "/logined/user/";
            HttpPost request = new HttpPost(url);
            List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
            list.add(new BasicNameValuePair("login_id", contents[0]));
            list.add(new BasicNameValuePair("login_pwd", contents[1]));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list);
//            StringEntity params = new StringEntity(
//                    "{" +
//                            "\"login_id\":\"" + contents[0] + "\"" +
//                            "\"login_pwd\":\"" + contents[1] + "\"" +
//                            "}");
            request.addHeader("Content-Type", "application/x-www-form-urlencoded");
            request.addHeader("Accept", "application/json");
            request.setEntity(entity);

            response = httpClient.execute(request);

        } catch (Exception ex) {
            callback.onFail();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }

        return response;
    }

    @Override
    protected void onPostExecute(HttpResponse response) {
        if(response.getStatusLine().getStatusCode() >= 400) {
            callback.onFail();
        } else {
            try {
                HttpEntity entity = response.getEntity();
                JSONObject json = new JSONObject(EntityUtils.toString(entity));
                callback.onComplete(json);
            } catch (Exception e) {
                callback.onFail();
            }
        }
    }
}
