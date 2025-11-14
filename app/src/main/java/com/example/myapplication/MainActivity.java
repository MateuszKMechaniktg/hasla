package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText haslo;
    private EditText potwierdz;
    private Button sprawdz;
    private TextView wiadomosc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        haslo = findViewById(R.id.haslo);
        potwierdz = findViewById(R.id.potwierdz);
        sprawdz = findViewById(R.id.sprawdz);
        wiadomosc = findViewById(R.id.wiadomosc);

        sprawdz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String temail = String.valueOf(email.getText());
                        String thaslo = String.valueOf(haslo.getText());
                        String tpotwierdz = String.valueOf(potwierdz.getText());
                        int silaHasla = 0;
                        boolean znakiSpecjalne = false;
                        boolean znakiLiczby = false;
                        boolean znakiUppercase = false;
                        boolean znakiLowercase = false;
                        String blad = "";
                        String dodajDoHaslo = "";
                        if(!temail.contains("@")){
                            blad = "Niepoprawny adres e-mail";
                        } else if (!thaslo.equals(tpotwierdz)) {
                            blad = "Hasła się różnią";
                        }else{
                            String znaki = "!@#$%^&*()_+-=?/.,<>{}|[]";
                            String liczby = "1234567890";
                            String uppercase = "QWERTYUIOPASDFGHJKLZXCVBNM";
                            String lowercase = "qwertyuiopasdfghjklzxcvbnm";
                            for (int i = 0; i < thaslo.length(); i++){
                                if(lowercase.contains(thaslo.charAt(i)+"")){
                                    znakiLowercase = true;
                                    dodajDoHaslo += "\nBrak małej litery";
                                }
                                if(znaki.contains(thaslo.charAt(i)+"")){
                                    znakiSpecjalne = true;
                                    dodajDoHaslo += "\nBrak dużej litery";
                                }
                                if(liczby.contains(thaslo.charAt(i)+"")){
                                    znakiLiczby = true;
                                    dodajDoHaslo += "\nBrak liczby";
                                }
                                if(uppercase.contains(thaslo.charAt(i)+"")){
                                    znakiUppercase = true;
                                    dodajDoHaslo += "\nZnaku specjalnego";
                                }
                            }
                            if(thaslo.length() >= 10){
                                silaHasla++;
                            }
                            if (znakiSpecjalne){
                                silaHasla++;
                            }
                            if(znakiLiczby){
                                silaHasla++;
                            }
                            if(znakiLowercase){
                                silaHasla++;
                            }
                            if(znakiUppercase){
                                silaHasla++;
                            }
                            blad = "Witaj "+temail+"\n Siła hasła = "+silaHasla+dodajDoHaslo;
                        }
                        wiadomosc.setText(blad);
                    }
                }
        );
    }
}