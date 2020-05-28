package com.chanchuan.serivce;

import com.chanchuan.bean.CartBean;
import com.chanchuan.bean.DailyListBean;
import com.chanchuan.bean.GankBean;
import com.chanchuan.bean.GirlBean;
import com.chanchuan.bean.SectionsBean;
import com.chanchuan.bean.ZhihuDetailBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    String zhidhuUrl = "http://news-at.zhihu.com/";

    @GET("api/4/news/latest")
    Observable<DailyListBean> getDailayData();

    @GET("api/4/sections")
    Observable<SectionsBean> getSections();

    //    http://news-at.zhihu.com/api/4/news/9723727
    @GET("api/4/news/{id}")
    Observable<ZhihuDetailBean> getZhihuDetail(@Path("id") int id);

    String cartUrl = "http://cdwan.cn/";

    /**
     * 获取以往数据
     *
     * @param date
     * @return
     */
    @GET("api/4/news/before/{date}")
    Observable<DailyListBean> getBeforeData(@Path("date") String date);

    @GET("api/cart/index")
    Observable<CartBean> getCartListData();

    String gankUrl = "https://gank.io/";

    /**
     * @param type 类型 如：Android ios
     * @param num  每页的总数
     * @return
     */
    //    https://gank.io/api/data/Android/10/1
    @GET("api/data/{type}/{num}/10")
    Observable<GankBean> getGankData(@Path("type") String type, @Path("num") String num);

    @GET("api/data/%E7%A6%8F%E5%88%A9/20/3")
//    https://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1
    Observable<GirlBean> getGirlData();
}
