package com.example.crypto_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crypto_app.apis.ApiUtilities;
import com.example.crypto_app.apis.apiinterface;
import com.example.crypto_app.databinding.DashboardBinding;
import com.example.crypto_app.databinding.SignupActivityBinding;
import com.example.crypto_app.marketmodel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dashboard_activity extends AppCompatActivity {
    private static final String TAG = "dashboard_activity";
    TextView profile_name, account_balancee;
    String email;

    DashboardBinding binding;

   ImageView imageView5;
    RecyclerView recyclerView;
    cryptoadapter adapter;
    ArrayList<CryptoModel> cryptoList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        profile_name = findViewById(R.id.textView9);
        account_balancee = findViewById(R.id.textView8);
        recyclerView = findViewById(R.id.RecyclerViewnew);
        imageView5 = findViewById(R.id.imageView5);

        email = getIntent().getStringExtra("key_email");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager); // Set layout manager
        adapter = new cryptoadapter(this, cryptoList);
        recyclerView.setAdapter(adapter);

        getuserdetails();
        gettopcurrency();

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashboard_activity.this, add_recie_data.class);
                startActivity(intent);
            }
        });
    }

    public void getuserdetails() {
        database_model dbhelper = new database_model(this);
        ArrayList<userModal> al = dbhelper.getLoggedInuserdetail(email);
        if (!al.isEmpty()) {
            userModal userModal = al.get(0);
            profile_name.setText(userModal.getFirstname());
            account_balancee.setText(String.valueOf(userModal.getAccountbalance()));
        }
    }

    public void gettopcurrency() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    apiinterface apiInterface = ApiUtilities.getInstance().create(apiinterface.class);
                    Call<marketmodel> call = apiInterface.getMarketData();
                    call.enqueue(new Callback<marketmodel>() {
                        @Override
                        public void onResponse(Call<marketmodel> call, Response<marketmodel> response) {
                            if (response.isSuccessful()) {
                                marketmodel marketModel = response.body();
                                if (marketModel != null) {
                                    CryptoDatabaseModel dbHelper = new CryptoDatabaseModel(dashboard_activity.this);
                                    ArrayList<CryptoModel> tempList = new ArrayList<>();
                                    for (marketmodel.CryptoCurrencyList data : marketModel.getData().getCryptoCurrencyList()) {
                                        int id = data.getId();
                                        String name = data.getName();
                                        String symbol = data.getSymbol();
                                        double price = data.getQuotes().get(0).getPrice();
                                        double percentChange24h = data.getQuotes().get(0).getPercentChange24h();
                                        double turnover = data.getQuotes().get(0).getTurnover();

                                        // Check if the ID already exists in the database
                                        if (!dbHelper.checkIfDataExists(id)) {
                                            // Insert only if ID does not exist
                                            dbHelper.insertCryptoData(id,name, symbol, price, percentChange24h, turnover);
                                            CryptoModel cryptoModel = new CryptoModel(id, name, symbol, price, percentChange24h, turnover);
                                            tempList.add(cryptoModel);
                                        } else {
                                            // Handle case where ID already exists (update or skip)
                                            Log.d(TAG, "ID " + id + " already exists, skipping insertion or updating...");
                                            CryptoModel cryptoModel = new CryptoModel(id, name, symbol, price, percentChange24h, turnover);
                                            tempList.add(cryptoModel);
                                        }
                                    }

                                    cryptoList.addAll(tempList);
                                    adapter.notifyDataSetChanged();

                                    System.out.println(cryptoList);

                                    Log.d(TAG, "Data inserted into the database");
                                }
                            } else {
                                Log.e(TAG, "Failed to get market data");
                            }
                        }


                        @Override
                        public void onFailure(Call<marketmodel> call, Throwable t) {
                            Log.e(TAG, "Exception in gettopcurrency", t);
                        }
                    });
                } catch (Exception e) {
                    Log.e(TAG, "Exception in gettopcurrency", e);
                }
            }
        }).start();
    }




}
