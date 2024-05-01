package edu.utsa.cs3443.botanicalbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

import edu.utsa.cs3443.botanicalbuddy.model.DestinationList;

public class MapActivity extends AppCompatActivity {

    /**
     * Holds the list of destinations loaded into the application.
     */
    DestinationList list;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_map);

            list = new DestinationList();
        Log.i("load","load");
             try {
                list.loadDestinations(this); //load destination data
                Log.i("load","load");
            } catch (IOException e) {
                throw new RuntimeException(e); // handle exception
            }

        // **Button Click Listeners**

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MapActivity","Button1 pressed");
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 1); // passing id for destination 1
                startActivity(intent);


            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MapActivity","Button2 pressed");
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 2); // passing id for destination 2
                startActivity(intent);


            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                    intent.putExtra("descriptionId", 3);
                    startActivity(intent);
                }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 4);
                startActivity(intent);
            }
        });

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 5);
                startActivity(intent);
            }
        });

        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 6);
                startActivity(intent);
            }
        });

        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 7);
                startActivity(intent);
            }
        });

        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 8);
                startActivity(intent);
            }
        });

        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 9);
                startActivity(intent);
            }
        });

        Button button10 = findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 10);
                startActivity(intent);
            }
        });

        Button button11 = findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 11);
                startActivity(intent);
            }
        });

        Button button12 = findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 12);
                startActivity(intent);
            }
        });

        Button button13 = findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 13);
                startActivity(intent);
            }
        });

        Button button14 = findViewById(R.id.button14);
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 14);
                startActivity(intent);
            }
        });

        Button button15 = findViewById(R.id.button15);
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 15);
                startActivity(intent);
            }
        });

        Button button16 = findViewById(R.id.button16);
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 16);
                startActivity(intent);
            }
        });

        Button button17 = findViewById(R.id.button17);
        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 17);
                startActivity(intent);
            }
        });

        Button button18 = findViewById(R.id.button18);
        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 18);
                startActivity(intent);
            }
        });

        Button button19 = findViewById(R.id.button19);
        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 19);
                startActivity(intent);
            }
        });

        Button button20 = findViewById(R.id.button20);
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 20);
                startActivity(intent);
            }
        });

        Button button21 = findViewById(R.id.button21);
        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 21);
                startActivity(intent);
            }
        });

        Button button22 = findViewById(R.id.button22);
        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 22);
                startActivity(intent);
            }
        });

        Button button23 = findViewById(R.id.button23);
        button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 23);
                startActivity(intent);
            }
        });

        Button button24 = findViewById(R.id.button24);
        button24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 24);
                startActivity(intent);
            }
        });

        Button button25 = findViewById(R.id.button25);
        button25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 25);
                startActivity(intent);
            }
        });

        Button button26 = findViewById(R.id.button26);
        button26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 26);
                startActivity(intent);
            }
        });

        Button button27 = findViewById(R.id.button27);
        button27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 27);
                startActivity(intent);
            }
        });

        Button button28 = findViewById(R.id.button28);
        button28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 28);
                startActivity(intent);
            }
        });

        Button button29 = findViewById(R.id.button29);
        button29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 29);
                startActivity(intent);
            }
        });

        Button button30 = findViewById(R.id.button30);
        button30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 30);
                startActivity(intent);
            }
        });

        Button button31 = findViewById(R.id.button31);
        button31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 31);
                startActivity(intent);
            }
        });

        Button button32 = findViewById(R.id.button32);
        button32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 32);
                startActivity(intent);
            }
        });

        Button button33 = findViewById(R.id.button33);
        button33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 33);
                startActivity(intent);
            }
        });

        // **Main Logo Click Listener**
        ImageView logo = findViewById(R.id.main_logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapActivity.this, MainActivity.class)); // log's user out
            }
        });

        /**
         * Initializes the dropdown menu to handle navigation.
         * */
        ImageView menu = findViewById(R.id.dropdown_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    /**
     * Displays and handles interactions with the dropdown menu.
     *
     * @param v The view (the menu icon) that was clicked.
     */
    public void showMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(MapActivity.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.menuMap) {
                    startActivity(new Intent(MapActivity.this, MapActivity.class));
                }
                if(item.getItemId() == R.id.menuConserve) {
                    startActivity(new Intent(MapActivity.this, ConserveLandingActivity.class));
                }
                if(item.getItemId() == R.id.menuLogin) {
                    Intent logout = new Intent(getApplicationContext(), LoginActivity.class);
                    finishAffinity();
                    startActivity(logout);
                }

                return true;
            }
        });
        popupMenu.show();
    }
}