package com.example.kazi.parkingapplication;

import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kazi.parkingapplication.Model.ParkingModel;
import com.example.kazi.parkingapplication.Services.API;
import com.example.kazi.parkingapplication.Services.InteractorService;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    API apiParking;

   /* TextView textview_name;
      TextView textview_Cost;
      Button button2; */

     Integer id;
     Unbinder unbinder;

    @BindView(R.id.textView_name) TextView textview_name;
    @BindView(R.id.textView_Cost) TextView textview_Cost;
    @BindView(R.id.button2) Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);
        id = Integer.parseInt(getIntent().getStringExtra("String"));

        getDetails();
    }

    public void getDetails(){

        apiParking = InteractorService.getConnection();

        apiParking.getInfo(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ParkingModel>() {
                    @Override
                    public void onCompleted() {

                        return;
                    }

                    @Override
                    public void onError(Throwable e) {

                        return;
                    }

                    @Override
                    public void onNext(ParkingModel parkingModel) {

                        setupView(parkingModel);

                        Log.i("Debugging", "made it to onNext");

                    }


                });

    }

    public void setupView(ParkingModel parkingModel){

        //textview_name = (TextView) findViewById(R.id.textView_name);
        //textview_Cost = (TextView) findViewById(R.id.textView_Cost);


        textview_name.setText("Name "+parkingModel.getName());
        textview_Cost.setText("Cost "+parkingModel.getCostPerMinute());
        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                reserveParking();
            }
        });

    }

    public void reserveParking(){

        apiParking = InteractorService.getConnection();

        apiParking.reserve(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ParkingModel>() {
                    @Override
                    public void onCompleted() {

                        return;
                    }

                    @Override
                    public void onError(Throwable e) {

                        reservedorNot(true);
                        return;
                    }

                    @Override
                    public void onNext(ParkingModel parkingModel) {

                        reservedorNot(false);

                    }


                });

    }

    public void reservedorNot(Boolean bool){

        if(bool){

            Toast.makeText((getApplicationContext()),"Location already Reserved",Toast.LENGTH_LONG).show();

        }

        else {
            Toast.makeText((getApplicationContext()),"Location Reserved",Toast.LENGTH_LONG).show();
            button2.setText("CHECK RESERVATION");
        }

    }


}





