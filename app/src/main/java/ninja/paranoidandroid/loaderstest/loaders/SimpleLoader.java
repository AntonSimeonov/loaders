package ninja.paranoidandroid.loaderstest.loaders;

import android.content.AsyncTaskLoader;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import java.util.ArrayList;

/**
 * Created by anton on 22.11.16.
 */

public class SimpleLoader extends AsyncTaskLoader<ArrayList<String>> {


    private ArrayList<String> cachedData;
    public static final String ACTION = "LOAD_ACTION";

    public SimpleLoader(Context context) {
        super(context);
    }

    @Override
    public ArrayList<String> loadInBackground() {

        ArrayList<String> array = new ArrayList<String>();
        array.add("kjjjdhfjkdhg");
        array.add("7547652875");
        array.add("hjerguyt7idsjkjfsklj");
        array.add("ysggughf7767676767");


        return array;
    }

    @Override
    public void deliverResult(ArrayList<String> data) {

        cachedData = data;
        super.deliverResult(cachedData);

    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        LocalBroadcastManager localBroadcastManager =  LocalBroadcastManager.getInstance(getContext());
        IntentFilter filter = new IntentFilter(ACTION);
        localBroadcastManager.registerReceiver(receiver, filter);

        if(cachedData == null){
            forceLoad();
        }else{
            super.deliverResult(cachedData);
        }


    }


    @Override
    protected void onReset() {
        super.onReset();

        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(receiver);
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            forceLoad();
        }
    };

}
