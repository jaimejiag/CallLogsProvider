package com.examle.jaime.calllogsprovider;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.CallLog;

/**
 * Created by usuario on 9/02/18.
 */

public class CallLogPresenter implements LoaderManager.LoaderCallbacks<Cursor>, CallLogContract.Presenter {
    private static final int CALLLOG = 0;
    private CallLogContract.View mView;


    public CallLogPresenter(CallLogContract.View view) {
        mView = view;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        CursorLoader loader = null;

        switch (i) {
            case CALLLOG:
                String sortOrder = CallLog.Calls.DATE + " DESC";
                String[] projection = new String[] {
                        BaseColumns._ID, CallLog.Calls.NUMBER, CallLog.Calls.DATE, CallLog.Calls.DURATION, CallLog.Calls.TYPE
                };

                Uri uri = Uri.parse(CallLog.CONTENT_URI + "/calls");

                loader = new CursorLoader(mView.getContext(), uri, projection,
                        null, null, sortOrder);
                break;
        }

        return loader;
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mView.setCursor(cursor);
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mView.setCursor(null);
    }


    @Override
    public void getCallLogs() {
        ((Activity)mView.getContext()).getLoaderManager().restartLoader(CALLLOG, null, this);
    }
}
