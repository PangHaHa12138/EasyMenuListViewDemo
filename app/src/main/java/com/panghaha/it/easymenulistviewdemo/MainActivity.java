package com.panghaha.it.easymenulistviewdemo;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private SwipeMenuListView swiplistView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View footview;
    private SlideAdapter1 slideAdapter1;
    //测试服务器地址
    private static final String Sever = "http://123.56.97.229:6080/Server/task/recevielist.do";
    private static final String zhiding = "http://123.56.97.229:6080/Server/task/stickieService.do";
    private static final String quxiaozd = "http://123.56.97.229:6080/Server//task/stickieDelete.do";
    private String userid = "02774bc536964386a68bd2b64145c910",taskid;
    //bean
    private Data_chaxunliebiao_shoudaorenwu shoudaorenwu;
    private List<Data_chaxunliebiao_shoudaorenwu.ListBean> list1;
    private List<Data_chaxunliebiao_shoudaorenwu.ListBean> list2 = new ArrayList<>();
    private Data_chaxunliebiao_shoudaorenwu.ListBean data1;
    private int iszhiding1;
    private int footheight;
    private int bootm;
    private static final int sizedown = 10; //上拉加载更多
    private static final int sizeup = 15; //下拉刷新

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        initdata();
        LoadMenu();

        //解决listview与SwipeRefreshLayout滑动冲突问题
        swiplistView.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {
                swipeRefreshLayout.setEnabled(false);
            }

            @Override
            public void onMenuClose(int position) {

            }
        });
        swiplistView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    int lastposition = swiplistView.getLastVisiblePosition();//最后一个item的位置
                    if (lastposition == swiplistView.getCount() - 1) {
                        footview.setPadding(0, 0, 0, footheight);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                onLoadMore();
                                footview.setPadding(0, -footheight, 0, 0);
//                                Toast.makeText(MainActivity.this,"加载完成",Toast.LENGTH_SHORT).show();
                            }
                        }, 2000);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                boolean enable = false; //这里做判断，只有滑到顶部才出来下拉进度条
                if (swiplistView != null && swiplistView.getChildCount() > 0) {
                    // check if the first item of the list is visible
                    boolean firstItemVisible = swiplistView.getFirstVisiblePosition() == 0;
                    // check if the top of the first item is visible
                    boolean topOfFirstItemVisible = swiplistView.getChildAt(0).getTop() == 0;
                    // enabling or disabling the refresh layout
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                swipeRefreshLayout.setEnabled(enable);

            }
        });
    }

    private void initview() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        swiplistView = (SwipeMenuListView) findViewById(R.id.swiplistView);
        //脚布局
        footview = View.inflate(MainActivity.this, R.layout.listview_footer, null);

        footview.measure(0, 0);
        footheight = footview.getMeasuredHeight();
//        bootm = dp2px(45);
        footview.setPadding(0, -footheight, 0, 0);

        swiplistView.addFooterView(footview);
        //上拉加载更多


        swiplistView.setDividerHeight(0);//去掉分割线
        swiplistView.setCacheColorHint(0);

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_green_light,
                android.R.color.holo_orange_light);
        //下拉刷新
        try {
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    swipeRefreshLayout.setRefreshing(true);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showmore();
                            Toast.makeText(MainActivity.this,"刷新完成",Toast.LENGTH_SHORT).show();
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    },2000);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //下拉刷新
    private void showmore() {
        //联网刷新listview
        try {
            OkHttpUtils.get(Sever)
                    .params("pn", 1)
                    .params("size", sizeup)
                    .params("userid", userid)
                    .params("status", 3)
                    .params("sort", 1)
                    .params("type",1)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            shoudaorenwu = JsonUtil.parseJsonToBean(s, Data_chaxunliebiao_shoudaorenwu.class);
                            if (shoudaorenwu != null) {
                                if (list1 != null) {//还有数据
                                    list1.clear();
                                }
                                list1 = shoudaorenwu.getList();
                                slideAdapter1 = new SlideAdapter1(MainActivity.this, list1);
                                swiplistView.setAdapter(slideAdapter1);
                                slideAdapter1.notifyDataSetChanged();
                            }else {
                                Toast.makeText(MainActivity.this,"对不起，没有最新数据了",Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initdata() {
        //网络请求
        try {
            OkHttpUtils.get(Sever)
                    .params("pn", 1)
                    .params("size", 15)
                    .params("userid", userid)
                    .params("status", 3)
                    .params("sort", 1)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {

    //                        LogUtil.d("json-----", s);
    //                        ToastUtil.showToast("联网成功");
                            shoudaorenwu = JsonUtil.parseJsonToBean(s, Data_chaxunliebiao_shoudaorenwu.class);
                            list1 = shoudaorenwu.getList();
                            if (list1!=null){
                                slideAdapter1 = new SlideAdapter1(MainActivity.this, list1);
                                swiplistView.setAdapter(slideAdapter1);
                                slideAdapter1.notifyDataSetChanged();
                            }else {
                                Toast.makeText(MainActivity.this,"对不起，没有任务",Toast.LENGTH_SHORT).show();
    //                            ToastUtil.showToast("对不起，没有任务");
    //                            no_renwuphoto.setVisibility(View.VISIBLE);
                            }
                        }
                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
    //                        ToastUtil.showToast("联网失败");
                            Toast.makeText(MainActivity.this,"联网失败",Toast.LENGTH_SHORT).show();
                        }
                    });

            swiplistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(MainActivity.this,"他山之石，可以攻玉",Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void LoadMenu(){

        //侧拉swipmenulistview  构建侧拉菜单
        SwipeMenuCreator creator1 = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {

                switch (menu.getViewType()) {

                    case 0:
                        createMenu1(menu);
                        break;
                    case 1:
                        createMenu2(menu);
                        break;

                }
            }

            private void createMenu1(SwipeMenu menu) {
                //创建置顶项
                SwipeMenuItem zditem1 = new SwipeMenuItem(MainActivity.this);
                //设置item width
                zditem1.setWidth(dp2px(60));
                //设置背景
                zditem1.setBackground(R.color.listview_item_right_bg);
                //图标
                zditem1.setIcon(R.drawable.list_slide_but_stick2x);
                // 添加到菜单
                menu.addMenuItem(zditem1);
            }

            private void createMenu2(SwipeMenu menu) {
                //创建置顶项
                SwipeMenuItem zditem2 = new SwipeMenuItem(MainActivity.this);
                //设置item width
                zditem2.setWidth(dp2px(60));
                //设置背景
                zditem2.setBackground(R.color.listview_item_right_bg);
                //图标
                zditem2.setIcon(R.drawable.list_slide_but_canstick2x);
                // 添加到菜单
                menu.addMenuItem(zditem2);
            }
        };
        //设置创建者
        swiplistView.setMenuCreator(creator1);
        //step 2. listener item click event
        swiplistView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                data1 = list1.get(position);
                taskid = data1.getTaskid();
                iszhiding1 = data1.getStick();//置顶
                //显示置顶
                if (iszhiding1 == 0) {
                    try {
                        OkHttpUtils.post(zhiding)
                                .params("userid", userid)
                                .params("taskid", taskid)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        Data_uploadBack_tag back_tag = JsonUtil.parseJsonToBean(s, Data_uploadBack_tag.class);
                                        if (back_tag != null) {
//                                            ToastUtil.showToast("置顶");
                                            Toast.makeText(MainActivity.this,"置顶",Toast.LENGTH_SHORT).show();
                                            showmore();//置顶服务器排序后从新获取数据刷新页面
                                        } else {
//                                            ToastUtil.showToast("置顶失败");
                                            Toast.makeText(MainActivity.this,"置顶失败",Toast.LENGTH_SHORT).show();
                                        }

                                    }

                                    @Override
                                    public void onError(Call call, Response response, Exception e) {
                                        super.onError(call, response, e);
//                                        ToastUtil.showToast("置顶失败，头皮发麻");
                                        Toast.makeText(MainActivity.this,"置顶失败，头皮发麻",Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (iszhiding1 == 1) {
                    try {
                        OkHttpUtils.post(quxiaozd)
                                .params("userid", userid)
                                .params("taskid", taskid)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        Data_uploadBack_tag back_tag = JsonUtil.parseJsonToBean(s, Data_uploadBack_tag.class);

                                        if (back_tag != null) {
//                                            ToastUtil.showToast("取消置顶");
                                            Toast.makeText(MainActivity.this,"取消置顶",Toast.LENGTH_SHORT).show();
                                            showmore();
                                        } else {
//                                            ToastUtil.showToast("取消失败");
                                            Toast.makeText(MainActivity.this,"取消失败",Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onError(Call call, Response response, Exception e) {
                                        super.onError(call, response, e);
                                        Toast.makeText(MainActivity.this,"联网失败",Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

        swiplistView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);//设置滑动朝向 左

    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
    /**上拉加载更多
     *
     * 分页加载主要逻辑
     * 两个集合，一个用来接收最新的数据，他是小集合，因为每次联网对集合长度做了限制，最多10条，
     * 然后服务器有单独一个字段是服务器总共有多少数据，每次把小集合addll到大集合里，
     * 再判断大集合的长度是否小于服务器返回总条目数，小于就继续添加，等于或大于，就提示没有更多数据了
     *
     *
     * */
    int m = 1;
    private void onLoadMore() {
        //联网刷新listview
        m++;
        try {
            OkHttpUtils.get(Sever)
                    .params("pn", m)
                    .params("size", sizedown)
                    .params("userid", userid)
                    .params("status", 3)
                    .params("sort", 1)
                    .params("type",1)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            shoudaorenwu = JsonUtil.parseJsonToBean(s, Data_chaxunliebiao_shoudaorenwu.class);
                            if (shoudaorenwu.getList()!= null){
                                list2.clear();
                            }
                            list2 = shoudaorenwu.getList();
                            int totle = shoudaorenwu.getTotal();//服务器返回总数
                            int totle2 =  list1.size();//加载的数据
                            if (totle2 < totle) {
                                list1.addAll(list2);
                                slideAdapter1.notifyDataSetChanged();
                            } else {
                                m = 1;
//                                Toast.makeText(MainActivity.this,"没有更多数据了",Toast.LENGTH_SHORT).show();
                            }

                        }


                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
