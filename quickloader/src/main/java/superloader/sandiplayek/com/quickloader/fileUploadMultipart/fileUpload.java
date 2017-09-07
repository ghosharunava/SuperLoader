package superloader.sandiplayek.com.quickloader.fileUploadMultipart;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import superloader.sandiplayek.com.quickloader.util.Util;

/**
 * Created by Android on 06-09-2017.
 */

public class fileUpload {
    public int fileUpload(final Context context, final String selectedFilePath, String SERVER_URL, final String user_id_key, final String user_id, final getResponse get_response) {
        int serverResponseCode = 0;
        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File selectedFile = new File(selectedFilePath);
        String[] parts = selectedFilePath.split("/");
        final String fileName = parts[parts.length - 1];

        if (!selectedFile.isFile()) {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    HashMap<String, String> params = new HashMap<>();
                    params=null;
                    get_response.onResponse("Source File Doesn't Exist",params);
                    //tvFileName.setText("Source File Doesn't Exist: " + selectedFilePath);
                }
            });
            return 0;
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                URL url = new URL(SERVER_URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);//Allow Inputs
                connection.setDoOutput(true);//Allow Outputs
                connection.setUseCaches(false);//Don't use a cached Copy
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                connection.setRequestProperty("uploaded_file", selectedFilePath);
                // connection.setRequestProperty("user_id","4");

                //creating new dataoutputstream
                dataOutputStream = new DataOutputStream(connection.getOutputStream());

                //writing bytes to data outputstream
                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                        + selectedFilePath + "\"" + lineEnd);

                dataOutputStream.writeBytes(lineEnd);
                //returns no. of bytes present in fileInputStream
                bytesAvailable = fileInputStream.available();
                //selecting the buffer size as minimum of available bytes or 1 MB
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                //setting the buffer as byte array of size of bufferSize
                buffer = new byte[bufferSize];

                //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                //loop repeats till bytesRead = -1, i.e., no bytes are left to read
                while (bytesRead > 0) {
                    //write the bytes read from inputstream
                    dataOutputStream.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                dataOutputStream.writeBytes(lineEnd);
                dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                serverResponseCode = connection.getResponseCode();
                String serverResponseMessage = connection.getResponseMessage();
                //Log.i(TAG, "Server Response is: " + serverResponseMessage + ": " + serverResponseCode);
                //response code of 200 indicates the server status OK
                if (serverResponseCode == 200) {
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(Edit_Profile.this, "FileUpload Success", Toast.LENGTH_SHORT).show();
                            if (Util.isConnected(context)) {
                                HashMap<String, String> params = new HashMap<>();
                                params.put(user_id_key, user_id);
                                get_response.onResponse("OK",params);
                                //getUserProfileDetails(params);
                            } else {
                                /*if (currentapiVersion >= Build.VERSION_CODES.LOLLIPOP) {*/
                                Toast.makeText(context, "Internet Connection Unavailable", Toast.LENGTH_SHORT).show();
                                HashMap<String, String> params = new HashMap<>();
                                params=null;
                                get_response.onResponse("Internet Error",params);
                                /*}
                                else{
                                    Toast.makeText(getApplicationContext(),"Internet connection unavailable.", Toast.LENGTH_LONG).show();
                                }*/

                            }
                            //tvFileName.setText("File Upload completed.\n\n You can see the uploaded file here: \n\n" + "http://coderefer.com/extras/uploads/"+ fileName);
                        }
                    });
                }
                //closing the input and output streams
                fileInputStream.close();
                dataOutputStream.flush();
                dataOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /*if (currentapiVersion >= Build.VERSION_CODES.LOLLIPOP) {*/
                        HashMap<String, String> params = new HashMap<>();
                        params=null;
                        get_response.onResponse("File Not Found",params);
                        Toast.makeText(context, "File Not Found", Toast.LENGTH_SHORT).show();

                        /*}
                        else{
                            Toast.makeText(getApplicationContext(),"File Not Found", Toast.LENGTH_LONG).show();
                        }*/
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
                /*if (currentapiVersion >= Build.VERSION_CODES.LOLLIPOP) {*/
                HashMap<String, String> params = new HashMap<>();
                params=null;
                get_response.onResponse("URL ERROR",params);
                Toast.makeText(context, "URL ERROR", Toast.LENGTH_SHORT).show();
                /*}
                else{
                    Toast.makeText(getApplicationContext(),"URL error!", Toast.LENGTH_LONG).show();
                }*/
            } catch (IOException e) {
                e.printStackTrace();
                /*if (currentapiVersion >= Build.VERSION_CODES.LOLLIPOP) {*/
                HashMap<String, String> params = new HashMap<>();
                params=null;
                get_response.onResponse("Cannot Read/Write File",params);
                Toast.makeText(context, "Cannot Read/Write File", Toast.LENGTH_SHORT).show();
                /*}
                else{
                    Toast.makeText(getApplicationContext(),"Cannot Read/Write File!", Toast.LENGTH_LONG).show();
                }*/
            }
            //dialogg.dismiss();
            //dialog1.dismiss();
            //hidepDialog();
            return serverResponseCode;
        }
    }

    public interface getResponse{
        void onResponse(String response_text,HashMap<String,String> hashMap);
    }
}
