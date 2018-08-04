package com.test.test.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.test.test.R;
import com.test.test.ui.fragments.CommentFragment;
import com.test.test.ui.fragments.CommentsListFragment;

import static com.test.test.Constants.Extras.EXTRA_COMMENT_ID;
import static com.test.test.Constants.Extras.EXTRA_COUNTER_ID;

public class CommentActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFragmentManager = getSupportFragmentManager();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            if (bundle.containsKey(EXTRA_COUNTER_ID)) {
                mFragmentManager.beginTransaction()
                        .replace(R.id.container, CommentsListFragment.newInstance(bundle.getLong(EXTRA_COUNTER_ID)))
                        .commit();
            } else if (bundle.containsKey(EXTRA_COMMENT_ID)){
                mFragmentManager.beginTransaction()
                        .replace(R.id.container, CommentFragment.newInstance())
                        .commit();
            }
        }
    }

    public static Intent createShowAllCommentsIntent(Context context, long counterId) {
        Intent intent = new Intent(context, CommentActivity.class);
        intent.putExtra(EXTRA_COUNTER_ID, counterId);
        return intent;
    }

    public static Intent createShowCommentIntent(Context context, long commentId) {
        Intent intent = new Intent(context, CommentActivity.class);
        intent.putExtra(EXTRA_COMMENT_ID, commentId);
        return intent;
    }

}
