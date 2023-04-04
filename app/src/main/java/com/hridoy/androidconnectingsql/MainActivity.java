package com.hridoy.androidconnectingsql;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView Id = (TextView)findViewById(R.id.edittexid);
        TextView Name = (TextView) findViewById(R.id.edittextname);
        TextView Address= (TextView) findViewById(R.id.edittextaddress);
        Button btnInsert = (Button) findViewById(R.id.btnadd);


        btnInsert.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Connection connection = connectionclass();
              try {  if (connection != null) {
                  String sqlinsert = "Insert into worker values ('" +Id.getText().toString() + "','" + Name.getText().toString() + "','" + Address.getText().toString() + "')";
                  Statement st = connection.createStatement();
                  ResultSet rs = st.executeQuery(sqlinsert);
              }


              }  catch (Exception exception) {
                  Log.e("Error", exception.getMessage());
              }  }
          });
}
    @SuppressLint("NewApi")
    private Connection connectionclass() {
        Connection connection = null;

        String ip = "192.168.100.73", port = "1433", username = "sa", password = "hridoy123", databasename = "worker";
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionUrl = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + databasename + ";User=" + username + ";password=" + password + ";";
            connection = DriverManager.getConnection(connectionUrl);
        } catch (Exception exception) {
            Log.e("Error", exception.getMessage());
        }
        return connection;

    }
}
