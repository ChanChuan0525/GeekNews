package com.chanchuan.bean;

import java.util.List;

public class DailyListBean {
    /**
     * date : 20200426
     * stories : [{"image_hue":"0x3b2e42","title":"如果当时钟南山没说话，武汉确诊人数可能就翻倍了","url":"https://daily.zhihu.com/story/9723102","hint":"杨书航 · 11 分钟阅读","ga_prefix":"042607","images":["https://pic2.zhimg.com/v2-89434353c979af839ce84190317b0d51.jpg"],"type":0,"id":9723102},{"image_hue":"0xb37d9c","title":"如何从小到大保护好牙齿？","url":"https://daily.zhihu.com/story/9723132","hint":"Edwin · 16 分钟阅读","ga_prefix":"042607","images":["https://pic3.zhimg.com/v2-e23c5d118ee565c2ef2414635413784a.jpg"],"type":0,"id":9723132},{"image_hue":"0x192424","title":"张三对男朋友说「不给 30 万就分手」是否构成敲诈勒索罪？","url":"https://daily.zhihu.com/story/9723149","hint":"猴子判官 · 2 分钟阅读","ga_prefix":"042607","images":["https://pic2.zhimg.com/v2-3042783f9b246d9444dc56dcf0351bc5.jpg"],"type":0,"id":9723149},{"image_hue":"0x173e53","title":"鲨鱼和鲸鱼哪个厉害？","url":"https://daily.zhihu.com/story/9723110","hint":"五莲花开 · 3 分钟阅读","ga_prefix":"042607","images":["https://pic2.zhimg.com/v2-d46c87776e09c6c34566709dbe84b1f5.jpg"],"type":0,"id":9723110},{"image_hue":"0xb3927d","title":"为什么心理咨询师需要面谈 3、4 次才给建议？","url":"https://daily.zhihu.com/story/9723129","hint":"倪慢慢 · 2 分钟阅读","ga_prefix":"042607","images":["https://pic2.zhimg.com/v2-8fc3f20f448235eae679097800f6f271.jpg"],"type":0,"id":9723129},{"image_hue":"0x955426","title":"瞎扯 · 如何正确地吐槽","url":"https://daily.zhihu.com/story/9723155","hint":"VOL.2382","ga_prefix":"042606","images":["https://pic1.zhimg.com/v2-868c2020bd64a4fe17c488e05cfe91e4.jpg"],"type":0,"id":9723155}]
     * top_stories : [{"image_hue":"0x687854","hint":"作者 / 时间规划局","url":"https://daily.zhihu.com/story/9723139","image":"https://pic4.zhimg.com/v2-976e6c061aed1a39998992f3dbc8b71b.jpg","title":"小事 · 博士生学历真的很重要吗？","ga_prefix":"042507","type":0,"id":9723139},{"image_hue":"0x6e5a49","hint":"作者 / 芝麻酱","url":"https://daily.zhihu.com/story/9723068","image":"https://pic2.zhimg.com/v2-076656312e4d6c67a46e026931d13bbd.jpg","title":"喝牛奶的人和不喝牛奶的人差别在哪？","ga_prefix":"042407","type":0,"id":9723068},{"image_hue":"0x454030","hint":"作者 / 星球研究所","url":"https://daily.zhihu.com/story/9723052","image":"https://pic4.zhimg.com/v2-9760bb365083e93202f825dcd9d40b37.jpg","title":"为什么我们要保护地球？","ga_prefix":"042307","type":0,"id":9723052},{"image_hue":"0xb39059","hint":"作者 / Mr.X","url":"https://daily.zhihu.com/story/9722986","image":"https://pic2.zhimg.com/v2-57e94f61938e15bd9d44e4365b0cdabd.jpg","title":"原油价格为什么会跌到变负值？","ga_prefix":"042207","type":0,"id":9722986},{"image_hue":"0xb3907d","hint":"作者 / 吴升知","url":"https://daily.zhihu.com/story/9722967","image":"https://pic1.zhimg.com/v2-72bd97d568340d9767a5131f8fb29610.jpg","title":"下一个革命性的人机交互方式会是什么？","ga_prefix":"042107","type":0,"id":9722967}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * image_hue : 0x3b2e42
         * title : 如果当时钟南山没说话，武汉确诊人数可能就翻倍了
         * url : https://daily.zhihu.com/story/9723102
         * hint : 杨书航 · 11 分钟阅读
         * ga_prefix : 042607
         * images : ["https://pic2.zhimg.com/v2-89434353c979af839ce84190317b0d51.jpg"]
         * type : 0
         * id : 9723102
         */

        private String image_hue;
        private String title;
        private String url;
        private String hint;
        private String ga_prefix;
        private int type;
        private int id;
        private List<String> images;

        public String getImage_hue() {
            return image_hue;
        }

        public void setImage_hue(String image_hue) {
            this.image_hue = image_hue;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image_hue : 0x687854
         * hint : 作者 / 时间规划局
         * url : https://daily.zhihu.com/story/9723139
         * image : https://pic4.zhimg.com/v2-976e6c061aed1a39998992f3dbc8b71b.jpg
         * title : 小事 · 博士生学历真的很重要吗？
         * ga_prefix : 042507
         * type : 0
         * id : 9723139
         */

        private String image_hue;
        private String hint;
        private String url;
        private String image;
        private String title;
        private String ga_prefix;
        private int type;
        private int id;

        public String getImage_hue() {
            return image_hue;
        }

        public void setImage_hue(String image_hue) {
            this.image_hue = image_hue;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
