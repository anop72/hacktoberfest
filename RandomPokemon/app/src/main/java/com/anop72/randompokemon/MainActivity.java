package com.anop72.randompokemon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.Random;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    PokemonGateway gateway;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.name);

        gateway = ServerAPI.createRetrofitService(PokemonGateway.class);

    }

    public void random(View v) {
        Random r = new Random();
        int randomId = r.nextInt(100) + 1;
        getPokemon(randomId);
    }

    private void getPokemon(int pokemonId) {
        Observable<Pokemon> observable = gateway.pokemon(pokemonId);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Pokemon>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Pokemon pokemon) {
                        editText.setText(pokemon.name);
                    }
                });
    }
}
