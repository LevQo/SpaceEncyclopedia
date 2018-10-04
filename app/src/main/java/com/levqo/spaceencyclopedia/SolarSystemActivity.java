package com.levqo.spaceencyclopedia;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class SolarSystemActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private DatabaseReference mDataBaseUsersActions;
    private DatabaseReference mDataBaseUsersActions1;
    private FirebaseAuth mAuth;

    private ImageView mainImage;
    private TextView titleArticle;
    private TextView descriptionArticle;

    private String userScores;
    int mercury_Price_int;
    int venus_Price_int;
    int earth_Price_int;
    int mars_Price_int;
    int jupiter_Price_int;
    int saturn_Price_int;
    int uranus_Price_int;
    int neptune_Price_int;
    int sun_Price_int;
    int userScores_int;

    boolean alreadyChecked = false;
    boolean alreadyChecked1 = false;
    boolean alreadyChecked2 = false;
    boolean alreadyChecked3 = false;
    boolean alreadyChecked4 = false;
    boolean alreadyChecked5 = false;
    boolean alreadyChecked6 = false;
    boolean alreadyChecked7 = false;
    boolean alreadyChecked8 = false;
    boolean alreadyChecked9 = false;


    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solar_system);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);

        mediaPlayer.start();


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        final ImageView mercury_Buy = (ImageView) findViewById(R.id.btn_Mercury);
        final ImageView venus_Buy = (ImageView) findViewById(R.id.btn_Venus);
        final ImageView earth_Buy = (ImageView) findViewById(R.id.btn_Earth);
        final ImageView mars_Buy = (ImageView) findViewById(R.id.btn_Mars);
        final ImageView jupiter_Buy = (ImageView) findViewById(R.id.btn_Jupiter);
        final ImageView saturn_Buy = (ImageView) findViewById(R.id.btn_Saturn);
        final ImageView uranus_Buy = (ImageView) findViewById(R.id.btn_Uranus);
        final ImageView neptune_Buy = (ImageView) findViewById(R.id.btn_Neptune);
        final ImageView sun_Buy = (ImageView) findViewById(R.id.btn_Sun);

        ImageView backgroundImage = (ImageView) findViewById(R.id.background);
        //Glide.with(this).load(R.drawable.background).into(backgroundImage);

        final ImageView solarSystemImage = (ImageView) findViewById(R.id.solarSystem);

        mDatabase.child("SolarSystem").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String mercury_Image = (String) dataSnapshot.child("Mercury").child("Image").getValue();
                final String mercury_Price = (String) dataSnapshot.child("Mercury").child("Price").getValue();
                String venus_Image = (String) dataSnapshot.child("Venus").child("Image").getValue();
                String venus_Price = (String) dataSnapshot.child("Venus").child("Price").getValue();
                String earth_Image = (String) dataSnapshot.child("Earth").child("Image").getValue();
                String earth_Price = (String) dataSnapshot.child("Earth").child("Price").getValue();
                String mars_Price = (String) dataSnapshot.child("Mars").child("Price").getValue();
                String jupiter_Price = (String) dataSnapshot.child("Jupiter").child("Price").getValue();
                String saturn_Price = (String) dataSnapshot.child("Saturn").child("Price").getValue();
                String uranus_Price = (String) dataSnapshot.child("Uranus").child("Price").getValue();
                String neptune_Price = (String) dataSnapshot.child("Neptune").child("Price").getValue();
                String sun_Price = (String) dataSnapshot.child("Sun").child("Price").getValue();

                mercury_Price_int = Integer.parseInt(mercury_Price);
                venus_Price_int = Integer.parseInt(venus_Price);
                earth_Price_int = Integer.parseInt(earth_Price);
                mars_Price_int = Integer.parseInt(mars_Price);
                jupiter_Price_int = Integer.parseInt(jupiter_Price);
                saturn_Price_int = Integer.parseInt(saturn_Price);
                uranus_Price_int = Integer.parseInt(uranus_Price);
                neptune_Price_int = Integer.parseInt(neptune_Price);
                sun_Price_int = Integer.parseInt(sun_Price);


                //titleArticle.setText(title);
                //descriptionArticle.setText(Html.fromHtml(description));

                mDatabase.child("UsersScores").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        userScores = (String) dataSnapshot.getValue();
                        userScores_int = Integer.parseInt(userScores);

                        TextView scores_txtView = (TextView) findViewById(R.id.scores);
                        scores_txtView.setText(userScores);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                mDatabase.child("UserSolarSystem").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                            String mercury_String_fromApp = "Mercury";
                            String mercury_String_fromDB = (String) dataSnapshot.child(mAuth.getCurrentUser().getUid()).getValue();

                            String planet_String_fromDB = (String) dataSnapshot.child(mAuth.getCurrentUser().getUid()).getValue();

                            String venus_String_fromApp = "Venus";
                            String venus_String_fromDB = (String) dataSnapshot.child(mAuth.getCurrentUser().getUid()).getValue();

                            String earth_String_fromApp = "Earth";
                            String earth_String_fromDB = (String) dataSnapshot.child(mAuth.getCurrentUser().getUid()).getValue();

                            String mars_String_fromApp = "Mars";
                            String mars_String_fromDB = (String) dataSnapshot.child(mAuth.getCurrentUser().getUid()).getValue();

                            String jupiter_String_fromApp = "Jupiter";
                            String jupiter_String_fromDB = (String) dataSnapshot.child(mAuth.getCurrentUser().getUid()).getValue();

                            String saturn_String_fromApp = "Saturn";
                            String saturn_String_fromDB = (String) dataSnapshot.child(mAuth.getCurrentUser().getUid()).getValue();

                            String uranus_String_fromApp = "Uranus";
                            String uranus_String_fromDB = (String) dataSnapshot.child(mAuth.getCurrentUser().getUid()).getValue();

                            String neptune_String_fromApp = "Neptune";
                            String neptune_String_fromDB = (String) dataSnapshot.child(mAuth.getCurrentUser().getUid()).getValue();

                            String sun_String_fromApp = "Sun";
                            String sun_String_fromDB = (String) dataSnapshot.child(mAuth.getCurrentUser().getUid()).getValue();

                            if (alreadyChecked == false) {

                                if (dataSnapshot.hasChild(mAuth.getCurrentUser().getUid())) {
                                    if (planet_String_fromDB.equals(mercury_String_fromApp) || planet_String_fromDB.equals(venus_String_fromApp) || planet_String_fromDB.equals(earth_String_fromApp)
                                            || planet_String_fromDB.equals(mars_String_fromApp) || planet_String_fromDB.equals(jupiter_String_fromApp) || planet_String_fromDB.equals(saturn_String_fromApp)
                                            || planet_String_fromDB.equals(uranus_String_fromApp) || planet_String_fromDB.equals(neptune_String_fromApp) || planet_String_fromDB.equals(sun_String_fromApp)) {
                                    } else {
                                        Glide.with(getApplicationContext()).load(R.drawable.solar_system_default).into(solarSystemImage);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mercury_not).into(mercury_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_venus_not).into(venus_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_earth_not).into(earth_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mars_not).into(mars_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_jupiter_not).into(jupiter_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_saturn_not).into(saturn_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_uranus_not).into(uranus_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_neptune).into(neptune_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_sun_not).into(sun_Buy);
                                    }

                                    if (planet_String_fromDB.equals(mercury_String_fromApp)) {
                                        Glide.with(getApplicationContext()).load(R.drawable.solar_system_mercury).into(solarSystemImage);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mercury_ok).into(mercury_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_venus_ok).into(venus_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_earth_ok).into(earth_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mars_ok).into(mars_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_jupiter_ok).into(jupiter_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_saturn_ok).into(saturn_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_uranus_ok).into(uranus_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_neptune_ok).into(neptune_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_sun).into(sun_Buy);
                                    }

                                    if (planet_String_fromDB.equals(venus_String_fromApp)) {
                                        Glide.with(getApplicationContext()).load(R.drawable.solar_system_venus).into(solarSystemImage);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mercury).into(mercury_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_venus_ok).into(venus_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_earth_ok).into(earth_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mars_ok).into(mars_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_jupiter_ok).into(jupiter_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_saturn_ok).into(saturn_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_uranus_ok).into(uranus_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_neptune_ok).into(neptune_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_sun_not).into(sun_Buy);
                                    }

                                    if (planet_String_fromDB.equals(earth_String_fromApp)) {
                                        Glide.with(getApplicationContext()).load(R.drawable.solar_system_earth).apply(new RequestOptions().placeholder(new ColorDrawable(0xFF1c0055))).into(solarSystemImage);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mercury_not).into(mercury_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_venus).into(venus_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_earth_ok).into(earth_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mars_ok).into(mars_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_jupiter_ok).into(jupiter_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_saturn_ok).into(saturn_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_uranus_ok).into(uranus_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_neptune_ok).into(neptune_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_sun_not).into(sun_Buy);
                                    }

                                    if (planet_String_fromDB.equals(mars_String_fromApp)) {
                                        Glide.with(getApplicationContext()).load(R.drawable.solar_system_mars).apply(new RequestOptions().placeholder(new ColorDrawable(0xFF1c0055))).into(solarSystemImage);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mercury_not).into(mercury_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_venus_not).into(venus_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_earth).into(earth_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mars_ok).into(mars_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_jupiter_ok).into(jupiter_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_saturn_ok).into(saturn_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_uranus_ok).into(uranus_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_neptune_ok).into(neptune_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_sun_not).into(sun_Buy);
                                    }

                                    if (planet_String_fromDB.equals(jupiter_String_fromApp)) {
                                        Glide.with(getApplicationContext()).load(R.drawable.solar_system_jupiter).apply(new RequestOptions().placeholder(new ColorDrawable(0xFF1c0055))).into(solarSystemImage);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mercury_not).into(mercury_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_venus_not).into(venus_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_earth_not).into(earth_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mars).into(mars_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_jupiter_ok).into(jupiter_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_saturn_ok).into(saturn_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_uranus_ok).into(uranus_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_neptune_ok).into(neptune_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_sun_not).into(sun_Buy);
                                    }

                                    if (planet_String_fromDB.equals(saturn_String_fromApp)) {
                                        Glide.with(getApplicationContext()).load(R.drawable.solar_system_saturn).apply(new RequestOptions().placeholder(new ColorDrawable(0xFF1c0055))).into(solarSystemImage);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mercury_not).into(mercury_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_venus_not).into(venus_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_earth_not).into(earth_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mars_not).into(mars_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_jupiter).into(jupiter_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_saturn_ok).into(saturn_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_uranus_ok).into(uranus_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_neptune_ok).into(neptune_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_sun_not).into(sun_Buy);
                                    }

                                    if (planet_String_fromDB.equals(uranus_String_fromApp)) {
                                        Glide.with(getApplicationContext()).load(R.drawable.solar_system_uranus).apply(new RequestOptions().placeholder(new ColorDrawable(0xFF1c0055))).into(solarSystemImage);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mercury_not).into(mercury_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_venus_not).into(venus_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_earth_not).into(earth_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mars_not).into(mars_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_jupiter_not).into(jupiter_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_saturn).into(saturn_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_uranus_ok).into(uranus_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_neptune_ok).into(neptune_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_sun_not).into(sun_Buy);
                                    }

                                    if (planet_String_fromDB.equals(neptune_String_fromApp)) {
                                        Glide.with(getApplicationContext()).load(R.drawable.solar_system_neptune).apply(new RequestOptions().placeholder(new ColorDrawable(0xFF1c0055))).into(solarSystemImage);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mercury_not).into(mercury_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_venus_not).into(venus_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_earth_not).into(earth_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mars_not).into(mars_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_jupiter_not).into(jupiter_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_saturn_not).into(saturn_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_uranus).into(uranus_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_neptune_ok).into(neptune_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_sun_not).into(sun_Buy);
                                    }

                                    if (planet_String_fromDB.equals(sun_String_fromApp)) {
                                        Glide.with(getApplicationContext()).load(R.drawable.solar_system_sun).apply(new RequestOptions().placeholder(new ColorDrawable(0xFF1c0055))).into(solarSystemImage);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mercury_ok).into(mercury_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_venus_ok).into(venus_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_earth_ok).into(earth_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mars_ok).into(mars_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_jupiter_ok).into(jupiter_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_saturn_ok).into(saturn_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_uranus_ok).into(uranus_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_neptune_ok).into(neptune_Buy);
                                        Glide.with(getApplicationContext()).load(R.drawable.buy_sun_ok).into(sun_Buy);
                                    }
                                }else{
                                    Glide.with(getApplicationContext()).load(R.drawable.solar_system_default).into(solarSystemImage);
                                    Glide.with(getApplicationContext()).load(R.drawable.buy_mercury_not).into(mercury_Buy);
                                    Glide.with(getApplicationContext()).load(R.drawable.buy_venus_not).into(venus_Buy);
                                    Glide.with(getApplicationContext()).load(R.drawable.buy_earth_not).into(earth_Buy);
                                    Glide.with(getApplicationContext()).load(R.drawable.buy_mars_not).into(mars_Buy);
                                    Glide.with(getApplicationContext()).load(R.drawable.buy_jupiter_not).into(jupiter_Buy);
                                    Glide.with(getApplicationContext()).load(R.drawable.buy_saturn_not).into(saturn_Buy);
                                    Glide.with(getApplicationContext()).load(R.drawable.buy_uranus_not).into(uranus_Buy);
                                    Glide.with(getApplicationContext()).load(R.drawable.buy_neptune).into(neptune_Buy);
                                    Glide.with(getApplicationContext()).load(R.drawable.buy_sun_not).into(sun_Buy);
                                }
                                alreadyChecked = true;
                            }




                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                mDatabase.child("UserSolarSystem").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(mAuth.getCurrentUser().getUid())){
                            final String planet_String_fromDB = (String) dataSnapshot.child(mAuth.getCurrentUser().getUid()).getValue();

                            if(planet_String_fromDB.equals("Venus")) {
                                alreadyChecked = false;
                                mercury_Buy.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        mDatabase.child("UserSolarSystem").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                            //Подключаемся к элементу базы данных, отвечающего за Солнечный системы пользователей
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (planet_String_fromDB.equals("Venus")) {
                                                    //Если куплена предудущая планета
                                                    if (alreadyChecked7 == false) {
                                                        if (userScores_int >= mercury_Price_int) {
                                                            //Если у пользователя больше очков, чем стоимость планеты
                                                            int scoresAfterPayment = userScores_int - mercury_Price_int;
                                                            //Покупка планеты
                                                            mDatabase.child("UserSolarSystem").child(mAuth.getCurrentUser().getUid()).setValue("Mercury");
                                                            //Обновляем планету пользователя
                                                            mDatabase.child("UsersScores").child(mAuth.getCurrentUser().getUid()).setValue(String.valueOf(scoresAfterPayment));
                                                            //Обновляем очки
                                                            Glide.with(getApplicationContext()).load(R.drawable.solar_system_mercury).into(solarSystemImage);
                                                            //Загружаем фон
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_mercury_ok).into(mercury_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_venus_ok).into(venus_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_earth_ok).into(earth_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_mars_ok).into(mars_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_jupiter_ok).into(jupiter_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_saturn_ok).into(saturn_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_uranus_ok).into(uranus_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_neptune_ok).into(neptune_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_sun).into(sun_Buy);
                                                            //Загружаем кнопки
                                                        } else {
                                                            Toast.makeText(getApplicationContext(), "Недостаточно очков", Toast.LENGTH_LONG).show();
                                                            //Выводим сообщение об очках
                                                        }
                                                        alreadyChecked7 = true;
                                                    }
                                                }


                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });

                                    }
                                });
                            }

                            if(planet_String_fromDB.equals("Earth")) {
                                alreadyChecked = false;
                                venus_Buy.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        mDatabase.child("UserSolarSystem").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if(alreadyChecked6 == false) {
                                                    if (userScores_int >= venus_Price_int) {
                                                        if (planet_String_fromDB.equals("Earth")) {
                                                            int scoresAfterPayment = userScores_int - venus_Price_int;

                                                            mDatabase.child("UserSolarSystem").child(mAuth.getCurrentUser().getUid()).setValue("Venus");

                                                            mDatabase.child("UsersScores").child(mAuth.getCurrentUser().getUid()).setValue(String.valueOf(scoresAfterPayment));
                                                            Glide.with(getApplicationContext()).load(R.drawable.solar_system_venus).into(solarSystemImage);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_mercury).into(mercury_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_venus_ok).into(venus_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_earth_ok).into(earth_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_mars_ok).into(mars_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_jupiter_ok).into(jupiter_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_saturn_ok).into(saturn_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_uranus_ok).into(uranus_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_neptune_ok).into(neptune_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_sun_not).into(sun_Buy);
                                                        } else {
                                                            // Toast.makeText(getApplicationContext(), userScores, Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                    alreadyChecked6 = true;
                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });

                                    }
                                });
                            }

                            if(planet_String_fromDB.equals("Mars")) {
                                alreadyChecked = false;
                                earth_Buy.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        mDatabase.child("UserSolarSystem").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if(alreadyChecked5 == false) {
                                                    if (userScores_int >= earth_Price_int) {
                                                        if (planet_String_fromDB.equals("Mars")) {
                                                            int scoresAfterPayment = userScores_int - earth_Price_int;
                                                            mDatabase.child("UserSolarSystem").child(mAuth.getCurrentUser().getUid()).setValue("Earth");
                                                            mDatabase.child("UsersScores").child(mAuth.getCurrentUser().getUid()).setValue(String.valueOf(scoresAfterPayment));
                                                            Glide.with(getApplicationContext()).load(R.drawable.solar_system_earth).into(solarSystemImage);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_mercury_not).into(mercury_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_venus).into(venus_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_earth_ok).into(earth_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_mars_ok).into(mars_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_jupiter_ok).into(jupiter_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_saturn_ok).into(saturn_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_uranus_ok).into(uranus_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_neptune_ok).into(neptune_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_sun_not).into(sun_Buy);
                                                        } else {
                                                            //Toast.makeText(getApplicationContext(), userScores, Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                    alreadyChecked5 = true;
                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });

                                    }
                                });
                            }


                            if(planet_String_fromDB.equals("Jupiter")) {
                                alreadyChecked = false;
                                mars_Buy.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        mDatabase.child("UserSolarSystem").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if(alreadyChecked4 == false) {
                                                    if (userScores_int >= mars_Price_int) {
                                                        if (planet_String_fromDB.equals("Jupiter")) {
                                                            int scoresAfterPayment = userScores_int - mars_Price_int;

                                                            mDatabase.child("UserSolarSystem").child(mAuth.getCurrentUser().getUid()).setValue("Mars");

                                                            mDatabase.child("UsersScores").child(mAuth.getCurrentUser().getUid()).setValue(String.valueOf(scoresAfterPayment));
                                                            Glide.with(getApplicationContext()).load(R.drawable.solar_system_mars).into(solarSystemImage);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_mercury_not).into(mercury_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_venus_not).into(venus_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_earth).into(earth_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_mars_ok).into(mars_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_jupiter_ok).into(jupiter_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_saturn_ok).into(saturn_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_uranus_ok).into(uranus_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_neptune_ok).into(neptune_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_sun_not).into(sun_Buy);
                                                        } else {
                                                            // Toast.makeText(getApplicationContext(), userScores, Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                    alreadyChecked4 = true;
                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });

                                    }
                                });
                            }

                            if(planet_String_fromDB.equals("Saturn")) {
                                jupiter_Buy.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        mDatabase.child("UserSolarSystem").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {

                                                if (alreadyChecked3 == false) {
                                                    if (userScores_int >= jupiter_Price_int) {
                                                        if (planet_String_fromDB.equals("Saturn")) {
                                                            int scoresAfterPayment = userScores_int - jupiter_Price_int;
                                                            mDatabase.child("UserSolarSystem").child(mAuth.getCurrentUser().getUid()).setValue("Jupiter");
                                                            mDatabase.child("UsersScores").child(mAuth.getCurrentUser().getUid()).setValue(String.valueOf(scoresAfterPayment));
                                                            Glide.with(getApplicationContext()).load(R.drawable.solar_system_jupiter).into(solarSystemImage);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_mercury_not).into(mercury_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_venus_not).into(venus_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_earth_not).into(earth_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_mars).into(mars_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_jupiter_ok).into(jupiter_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_saturn_ok).into(saturn_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_uranus_ok).into(uranus_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_neptune_ok).into(neptune_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_sun_not).into(sun_Buy);
                                                        }
                                                    } else {
                                                        // Toast.makeText(getApplicationContext(), userScores, Toast.LENGTH_LONG).show();
                                                    }
                                                    alreadyChecked3 = true;
                                                }
                                            }


                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });

                                    }
                                });
                            }

                            if(planet_String_fromDB.equals("Uranus")) {

                                saturn_Buy.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mDatabase.child("UserSolarSystem").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                //alreadyChecked1 = false;
                                                if(alreadyChecked2 == false) {
                                                    if (userScores_int >= saturn_Price_int) {
                                                        if (planet_String_fromDB.equals("Uranus")) {
                                                            int scoresAfterPayment = userScores_int - saturn_Price_int;

                                                            mDatabase.child("UserSolarSystem").child(mAuth.getCurrentUser().getUid()).setValue("Saturn");

                                                            mDatabase.child("UsersScores").child(mAuth.getCurrentUser().getUid()).setValue(String.valueOf(scoresAfterPayment));
                                                            Glide.with(getApplicationContext()).load(R.drawable.solar_system_saturn).into(solarSystemImage);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_mercury_not).into(mercury_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_venus_not).into(venus_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_earth_not).into(earth_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_mars_not).into(mars_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_jupiter).into(jupiter_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_saturn_ok).into(saturn_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_uranus_ok).into(uranus_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_neptune_ok).into(neptune_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_sun_not).into(sun_Buy);

                                                        } else {
                                                            // Toast.makeText(getApplicationContext(), userScores, Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                    alreadyChecked2 = true;
                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });

                                    }
                                });
                            }

                            if(planet_String_fromDB.equals("Neptune")) {
                                alreadyChecked = false;
                                uranus_Buy.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        mDatabase.child("UserSolarSystem").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (alreadyChecked1 == false) {
                                                    if (userScores_int >= uranus_Price_int) {
                                                        int scoresAfterPayment = userScores_int - uranus_Price_int;
                                                        mDatabase.child("UserSolarSystem").child(mAuth.getCurrentUser().getUid()).setValue("Uranus");
                                                        mDatabase.child("UsersScores").child(mAuth.getCurrentUser().getUid()).setValue(String.valueOf(scoresAfterPayment));
                                                        Glide.with(getApplicationContext()).load(R.drawable.solar_system_uranus).into(solarSystemImage);
                                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mercury_not).into(mercury_Buy);
                                                        Glide.with(getApplicationContext()).load(R.drawable.buy_venus_not).into(venus_Buy);
                                                        Glide.with(getApplicationContext()).load(R.drawable.buy_earth_not).into(earth_Buy);
                                                        Glide.with(getApplicationContext()).load(R.drawable.buy_mars_not).into(mars_Buy);
                                                        Glide.with(getApplicationContext()).load(R.drawable.buy_jupiter_not).into(jupiter_Buy);
                                                        Glide.with(getApplicationContext()).load(R.drawable.buy_saturn).into(saturn_Buy);
                                                        Glide.with(getApplicationContext()).load(R.drawable.buy_uranus_ok).into(uranus_Buy);
                                                        Glide.with(getApplicationContext()).load(R.drawable.buy_neptune_ok).into(neptune_Buy);
                                                        Glide.with(getApplicationContext()).load(R.drawable.buy_sun_not).into(sun_Buy);
                                                    } else {
                                                        //  Toast.makeText(getApplicationContext(), userScores, Toast.LENGTH_LONG).show();
                                                    }

                                                    alreadyChecked1 = true;
                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });

                                    }
                                });
                            }
                                if (planet_String_fromDB.equals("Mercury")) {
                                    alreadyChecked = false;
                                    sun_Buy.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mDatabase.child("UserSolarSystem").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {

                                                        if (userScores_int >= sun_Price_int) {
                                                            String sun_String_fromDB = (String) dataSnapshot.getValue();
                                                            if (sun_String_fromDB.equals("Mercury")) {
                                                                int scoresAfterPayment = userScores_int - sun_Price_int;

                                                                mDatabase.child("UserSolarSystem").child(mAuth.getCurrentUser().getUid()).setValue("Sun");

                                                                mDatabase.child("UsersScores").child(mAuth.getCurrentUser().getUid()).setValue(String.valueOf(scoresAfterPayment));
                                                                Glide.with(getApplicationContext()).load(R.drawable.solar_system_sun).into(solarSystemImage);
                                                                Glide.with(getApplicationContext()).load(R.drawable.buy_mercury_ok).into(mercury_Buy);
                                                                Glide.with(getApplicationContext()).load(R.drawable.buy_venus_ok).into(venus_Buy);
                                                                Glide.with(getApplicationContext()).load(R.drawable.buy_earth_ok).into(earth_Buy);
                                                                Glide.with(getApplicationContext()).load(R.drawable.buy_mars_ok).into(mars_Buy);
                                                                Glide.with(getApplicationContext()).load(R.drawable.buy_jupiter_ok).into(jupiter_Buy);
                                                                Glide.with(getApplicationContext()).load(R.drawable.buy_saturn_ok).into(saturn_Buy);
                                                                Glide.with(getApplicationContext()).load(R.drawable.buy_uranus_ok).into(uranus_Buy);
                                                                Glide.with(getApplicationContext()).load(R.drawable.buy_neptune_ok).into(neptune_Buy);
                                                                Glide.with(getApplicationContext()).load(R.drawable.buy_sun_ok).into(sun_Buy);
                                                            } else {
                                                              //  Toast.makeText(getApplicationContext(), userScores, Toast.LENGTH_LONG).show();
                                                            }
                                                        }


                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {

                                                }
                                            });

                                        }
                                    });
                                }

                        }else{
                            neptune_Buy.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    mDatabase.child("UserSolarSystem").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            if(userScores_int >= neptune_Price_int) {
                                                String neptune_String_fromDB = (String) dataSnapshot.getValue();
                                                mDatabase.child("UserSolarSystem").addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        if(!dataSnapshot.hasChild(mAuth.getCurrentUser().getUid())){
                                                            int scoresAfterPayment = userScores_int - neptune_Price_int;
                                                            mDatabase.child("UserSolarSystem").child(mAuth.getCurrentUser().getUid()).setValue("Neptune");
                                                            mDatabase.child("UsersScores").child(mAuth.getCurrentUser().getUid()).setValue(String.valueOf(scoresAfterPayment));
                                                            Glide.with(getApplicationContext()).load(R.drawable.solar_system_neptune).into(solarSystemImage);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_mercury_not).into(mercury_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_venus_not).into(venus_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_earth_not).into(earth_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_mars_not).into(mars_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_jupiter_not).into(jupiter_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_saturn_not).into(saturn_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_uranus).into(uranus_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_neptune_ok).into(neptune_Buy);
                                                            Glide.with(getApplicationContext()).load(R.drawable.buy_sun_not).into(sun_Buy);
                                                        }else{
                                                           // Toast.makeText(getApplicationContext(), "20", Toast.LENGTH_LONG).show();
                                                        }

                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });
                                    /*
                                    if (!neptune_String_fromDB.equals("Venus") && !neptune_String_fromDB.equals("Mercury")
                                            && !neptune_String_fromDB.equals("Earth") && !neptune_String_fromDB.equals("Sun") && !neptune_String_fromDB.equals("Jupiter")
                                            && !neptune_String_fromDB.equals("Saturn") && !neptune_String_fromDB.equals("Uranus") && !neptune_String_fromDB.equals("Neptune")){

                                    }

                                    else{
                                        Toast.makeText(getApplicationContext(), userScores, Toast.LENGTH_LONG).show();
                                    }
                                    */
                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });

                                }
                            });

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ImageView backImage = (ImageView) findViewById(R.id.back_button);
        Glide.with(this).load(R.drawable.left_arrow).into(backImage);

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mediaPlayer.stop();
        mediaPlayer.release();

    }
}
