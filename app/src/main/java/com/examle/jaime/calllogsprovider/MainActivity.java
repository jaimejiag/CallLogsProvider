package com.examle.jaime.calllogsprovider;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends ListActivity implements CallLogContract.View {
    private CallLogContract.Presenter mPresenter;
    private CallLogAdapter mAdapter;


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new CallLogAdapter(getContext());
        setListAdapter(mAdapter);
        mPresenter = new CallLogPresenter(this);
        mPresenter.getCallLogs();
    }


    @Override
    public void setCursor(Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }


    @Override
    public Context getContext() {
        return this;
    }
}
