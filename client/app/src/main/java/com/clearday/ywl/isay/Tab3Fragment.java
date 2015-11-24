package com.clearday.ywl.isay;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.clearday.ywl.isay.adapter.Tab3Adapter;
import com.clearday.ywl.isay.models.User;
import com.clearday.ywl.isay.models.UserItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tab3Fragment extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;
    private MainActivity mActivity;
    private Tab3Adapter mAdapter;
    private User mUser;

    private View rootView;
    private ImageView avatarImageView;
    private TextView nameTextView;
    private TextView signatureTextView;
    private LinearLayout infoLinearLayout;
    private LinearLayout isayLinearLayout;
    private TextView isayCountTextView;
    private LinearLayout followingLinearLayout;
    private TextView followingCountTextView;
    private LinearLayout followedLinearLayout;
    private TextView followedCountTextView;

    private ListView navListView;

    public Tab3Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();

        List<UserItem> mUserItemList = personalItem();
        mAdapter = new Tab3Adapter(mActivity, mUserItemList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initView();
        //loadData();

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_say:
                // TODO 我说
                break;
            case R.id.my_following:
                // TODO 关注
                break;
            case R.id.my_followed:
                // TODO 粉丝
                break;
            default:
                break;
        }
    }

    protected List<UserItem> personalItem() {
        List<UserItem> data = new ArrayList<>();

        data.add(new UserItem(true, R.mipmap.ic_explore_black_24dp, "我的赞同", ""));
        data.add(new UserItem(false, R.mipmap.ic_explore_black_24dp, "我的收藏", "Lv13"));
        data.add(new UserItem(false, R.mipmap.ic_explore_black_24dp, "我的地点", "(17)"));
        data.add(new UserItem(false, R.mipmap.ic_explore_black_24dp, "我的轨迹", ""));
        data.add(new UserItem(true, R.mipmap.ic_explore_black_24dp, "编辑资料", ""));
        data.add(new UserItem(false, R.mipmap.ic_explore_black_24dp, "服务平台", ""));
        data.add(new UserItem(true, R.mipmap.ic_explore_black_24dp, "草稿箱", ""));
        return data;
    }

    public void initView() {
        rootView = View.inflate(mActivity, R.layout.fragment_tab3, null);
        avatarImageView = (ImageView) rootView.findViewById(R.id.my_avatar);
        nameTextView = (TextView) rootView.findViewById(R.id.my_name);
        signatureTextView = (TextView) rootView.findViewById(R.id.my_signature);

        navListView = (ListView) rootView.findViewById(R.id.my_nav);
        navListView.setAdapter(mAdapter);

        infoLinearLayout = (LinearLayout) rootView.findViewById(R.id.include_personal_info);

        isayLinearLayout = (LinearLayout) rootView.findViewById(R.id.my_say);
        isayCountTextView = (TextView) infoLinearLayout.findViewById(R.id.my_say_count);
        isayLinearLayout.setOnClickListener(this);

        followingLinearLayout = (LinearLayout) rootView.findViewById(R.id.my_following);
        followingCountTextView = (TextView) infoLinearLayout.findViewById(R.id.my_following_count);
        followingLinearLayout.setOnClickListener(this);

        followedLinearLayout = (LinearLayout) rootView.findViewById(R.id.my_followed);
        followedCountTextView = (TextView) infoLinearLayout.findViewById(R.id.my_followed_count);
        followedLinearLayout.setOnClickListener(this);
    }

    private void loadData(){
//        String uri = Uri.USER_SHOW;
//        uri += "?access_token=" + mAccessToken.getToken();
//        uri += "&uid=" + mAccessToken.getUid();
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, uri, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            if (response.has("error")) {
//                                int errorCode = response.getInt("error_code");
//                                String error = response.getString("error");
//                                ToastUtils.showToast(mActivity,
//                                        "错误码：" + errorCode + "-" + error,
//                                        Toast.LENGTH_SHORT);
//                            } else {
//                                mUser = new User().parseJson(response);
//                                setData();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                ToastUtils.showToast(mActivity, "网络发生异常", Toast.LENGTH_SHORT);
//            }
//        });
//
//        mActivity.mRequestQueue.add(jsonObjectRequest);
    }

    private void setData(){
        nameTextView.setText(mUser.getNickName());
        signatureTextView.setText(mUser.getSignature());
        isayCountTextView.setText(mUser.getIsayCount());
        followingCountTextView.setText(mUser.getFollowingCount());
        followedCountTextView.setText(mUser.getFollowedCount());
    }
}
