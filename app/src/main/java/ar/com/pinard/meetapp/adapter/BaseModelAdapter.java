package ar.com.pinard.meetapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by agustin on 13/07/15.
 * Generic Adapter for Models
 */
public abstract class BaseModelAdapter<T> extends BaseAdapter {
    protected final List<T> mModelList;
    private final int mListRowLayout;
    private LayoutInflater mLayoutInflater;
    protected final Context mContext;

    public BaseModelAdapter(Context context, List<T> modelList, int listRowLayout){
        mContext = context;
        mModelList = modelList;
        mListRowLayout = listRowLayout;
    }

    public BaseModelAdapter(Context context, T[] modelList, int listRowLayout){
        mContext = context;
        mModelList = Arrays.asList(modelList);
        mListRowLayout = listRowLayout;
    }

    protected abstract View buildView(View convertView, T item);

    @Override
    public int getCount() {
        return mModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return mModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mLayoutInflater == null)
            mLayoutInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = buildView(mLayoutInflater.inflate(mListRowLayout, null),
                    mModelList.get(position));
        }

        return convertView;
    }

    public List<T> getModelList() {
        return mModelList;
    }
}