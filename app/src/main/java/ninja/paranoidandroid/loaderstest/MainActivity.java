package ninja.paranoidandroid.loaderstest;

//import android.support.v4.app.LoaderManager;
//import android.support.v4.content.Loader;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import ninja.paranoidandroid.loaderstest.loaders.SimpleLoader;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<String>>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLoaderManager().initLoader(5, null, this);
    }

    @Override
    public Loader<ArrayList<String>> onCreateLoader(int id, Bundle args) {

        return new SimpleLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<String>> loader, ArrayList<String> data) {

        for (String item: data){
            Log.i("Simple loader tag: ", item);
        }

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<String>> loader) {

    }

    private void eventSendinCaseOfDataChangeDEMO(){

        Intent intent = new Intent(SimpleLoader.ACTION);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

    }
}
