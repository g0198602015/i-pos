package jerome.i_pos;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.CenterFragment.TabFragment.BaseFragment;
import com.LeftFragement.LeftFragment;
import com.LeftFragement.ListViewData;
import com.RightFragment.RightFragment;
import com.CenterFragment.SlidingMenu;
import com.CenterFragment.CenterFragmenet;

public class MainActivity extends AppCompatActivity implements LeftFragment.OnLeftFragmentEventListener, BaseFragment.BaseFragmentEventListener
{
    SlidingMenu mSlidingMenu;
    LeftFragment mLeftFragment;
    RightFragment mRightFragment;
    CenterFragmenet mCenterFragmenet;
//    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initListener();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        return super.onOptionsItemSelected(item);
    }

    private void initListener()
    {
        mSlidingMenu.setCanSliding(false,false);
    }
    private void init()
    {
        mSlidingMenu = (SlidingMenu) findViewById(R.id.slidingMenu);
        mSlidingMenu.setLeftView(getLayoutInflater().inflate(
                R.layout.left_fragment, null));
        mSlidingMenu.setRightView(getLayoutInflater().inflate(
                R.layout.right_fragment, null));
        mSlidingMenu.setCenterView(getLayoutInflater().inflate(
                R.layout.center_frame, null));

        FragmentTransaction t = this.getSupportFragmentManager()
                .beginTransaction();

        mLeftFragment = new LeftFragment();
        t.replace(R.id.left_frame, mLeftFragment);

        mRightFragment = new RightFragment();
        t.replace(R.id.right_frame, mRightFragment);

        Bundle extras = getIntent().getExtras();
        //int type = extras.getInt("Type", -1);
        mCenterFragmenet = new CenterFragmenet();
        mCenterFragmenet.setArguments(extras);
        t.replace(R.id.center_frame, mCenterFragmenet);
        t.commit();
    }

    public void showLeft() {
        mSlidingMenu.showLeftView();
    }

    public void showRight() {
        mSlidingMenu.showRightView();
    }

    public void onListViewClickChanged() {
        // The user selected the headline of an article from the HeadlinesFragment
        // Do something here to display that article

        CenterFragmenet articleFrag = (CenterFragmenet)
                getSupportFragmentManager().findFragmentById(R.id.center_frame);

        if (articleFrag != null) {
            // If article frag is available, we're in two-pane layout...

            // Call a method in the ArticleFragment to update its content
            articleFrag.updateFragmentData("");
        }
    }
    private boolean bInitial = true;
    public void onListViewDataChanged(ListViewData items) {
        // The user selected the headline of an article from the HeadlinesFragment
        // Do something here to display that article
        if (bInitial) {
            LeftFragment articleFrag = (LeftFragment)
                    getSupportFragmentManager().findFragmentById(R.id.left_frame);

            if (articleFrag != null) {
                // If article frag is available, we're in two-pane layout...

                // Call a method in the ArticleFragment to update its content
                articleFrag.setListViewData(items);
            }
            bInitial = false;
        }
    }

}
