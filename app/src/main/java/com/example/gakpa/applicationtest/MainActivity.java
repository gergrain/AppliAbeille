package com.example.gakpa.applicationtest;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Log.d("app","I am here");
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        SocketTask socketTask = new SocketTask("10.0.2.2", 11000);
        socketTask.execute();



       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
       /* GraphView graph = (GraphView) findViewById(R.id.graph);

        ClassDiagramme c =new ClassDiagramme();
        while(true){
            long l =  500;
            try{
                int lower = 0;
                int higher = 12;

                int x = (int)(Math.random() * (higher-lower)) + lower;
                int y = (int)(Math.random() * (higher-lower)) + lower;
                c.addPoint(x,y);
                LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(c.generateTabDataPoint());


                graph.addSeries(series);

                Thread.sleep(l);
            }catch(InterruptedException i){

            }

        }*/


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_SECTION_NAME = "section_name";
        private final Handler mHandler = new Handler();
        private LineGraphSeries<DataPoint> mSeries1;
        private Runnable mTimer1;
        List<DataPoint> points = new ArrayList<>();
        private Runnable mTimer2;
        private double graph2LastXValue = 5d;
        private int inc =0;
        public PlaceholderFragment() {
        }
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(String nom) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();


            args.putString(ARG_SECTION_NAME, nom);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.capteur);
            textView.setText(getString(R.string.section_format, getArguments().getString(ARG_SECTION_NAME)));
            GraphView graph = (GraphView) rootView.findViewById(R.id.graph);
            mSeries1 = new LineGraphSeries<DataPoint>();
            graph.addSeries(mSeries1);




            return rootView;
        }
        @Override
        public void onResume() {
            super.onResume();
            mTimer1 = new Runnable() {
                @Override
                public void run() {

                    mSeries1.resetData(generateTabDataPoint());
                    mHandler.postDelayed(this, 1000);
                }
            };
            mHandler.postDelayed(mTimer1, 0);
        }

        private DataPoint[] generateData() {
            int count = 30;
            DataPoint[] values = new DataPoint[count];
            for (int i=0; i<count; i++) {
                double x = i;
                double f = mRand.nextDouble()*0.15+0.3;
                double y = Math.sin(i*f+2) + mRand.nextDouble()*0.3;
                DataPoint v = new DataPoint(x, y);
                values[i] = v;
            }
            return values;
        }
        public DataPoint[] generateTabDataPoint(){
            points.add(generateOne());
            DataPoint[] d = new DataPoint[points.size()];

            for(int i = 0; i< (points.size()); i++){
                d[i]= points.get(i);
            }

            return d;
        }
        private DataPoint generateOne() {

            double x = inc;
            double f = mRand.nextDouble()*0.15+0.3;
            double y = Math.sin(inc*f+2) + mRand.nextDouble()*0.3;
            DataPoint v = new DataPoint(x, y);
            inc++;
            return v;
        }
        double mLastRandom = 2;
        Random mRand = new Random();
        private double getRandom() {
            return mLastRandom += mRand.nextDouble()*0.5 - 0.25;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            // switch qui crera les fragment en fonction du numero

            return PlaceholderFragment.newInstance((String)getPageTitle(position));
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Temperature intérieur";
                case 1:
                    return "Temperature extérieur";
                case 2:
                    return "Poid";
                case 3:
                    return "Humidité";
                case 4:
                    return "Mouvement";
                case 5:
                    return "Pression atmospherique";

            }
            return null;
        }
    }
}
