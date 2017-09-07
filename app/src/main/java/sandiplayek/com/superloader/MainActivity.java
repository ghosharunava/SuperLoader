package sandiplayek.com.superloader;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import superloader.sandiplayek.com.quickloader.MyView;
import superloader.sandiplayek.com.quickloader.customprogress.MyCustomProgressDialog;
import superloader.sandiplayek.com.quickloader.parser.PostDataParserObjectRequest;
import superloader.sandiplayek.com.quickloader.permissions.SinglePremissionGranter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    String url="http://dev-api.psspluat.online/AEPS/?action=login";
    String pass="",phone="", succ ="",msg="";
    EditText et_ph,et_pass;
    Button btn_chk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findIds();
        check_permission();
    }

    private void check_permission() {
        new SinglePremissionGranter(MainActivity.this, 2, new SinglePremissionGranter.GetPermissionResult() {
            @Override
            public void getPermissionMessage(String permissionStatus) {
                if(permissionStatus.equals("OK")){
                    Toast.makeText(MainActivity.this, ""+permissionStatus, Toast.LENGTH_SHORT).show();
                }else{
                    check_permission();
                }
            }
        });
    }

    private void findIds() {
        et_ph=(EditText)findViewById(R.id.et_ph);
        et_pass=(EditText)findViewById(R.id.et_pass);
        btn_chk=(Button) findViewById(R.id.btn_chk);
        btn_chk.setOnClickListener(this);
    }

    public void check_login(){
        HashMap<String,String>hashMap=new HashMap<>();
        hashMap.put("username", phone);
        hashMap.put("password", pass);
        new PostDataParserObjectRequest(MainActivity.this, url, hashMap, true, new PostDataParserObjectRequest.OnPostObjectResponseListner() {
            @Override
            public void onPostObjectResponse(JSONObject response) {
                try {
                    succ = response.getString("responseCode");
                    msg = response.getString("message");
                    if (succ.equals("1")) {
                        Toast.makeText(MainActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v==btn_chk){
            phone=et_ph.getText().toString().trim();
            pass=et_pass.getText().toString().trim();
            if(phone.equals("")||pass.equals("")){
                Toast.makeText(this, "Enter all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            check_login();
        }
    }
}
