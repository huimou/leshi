package com.wangjia.yijiale.entity;

import java.util.List;

/**
 * Created by kevin on 2016/10/26
 */

public class CommentListBean {


    /**
     * code : 200
     * msg : 店铺评论列表！
     * datas : {"eval_list":[{"geval_addtime":"2017-04-12 14:13:01","geval_frommemberid":"13","geval_frommembername":"13272950223","geval_content":"不错不错哦~~","geval_userimg":"http://cs.j.cqxueao.cn/data/upload/shop/common/default_user_portrait.gif"},{"geval_addtime":"2017-04-12 11:49:25","geval_frommemberid":"15","geval_frommembername":"feifeixi","geval_content":"不错哦","geval_userimg":"http://cs.j.cqxueao.cn/data/upload/shop/common/default_user_portrait.gif"},{"geval_addtime":"2017-04-12 11:49:25","geval_frommemberid":"15","geval_frommembername":"feifeixi","geval_content":"一把鼻涕一把泪今年终于买包了","geval_userimg":"http://cs.j.cqxueao.cn/data/upload/shop/common/default_user_portrait.gif"},{"geval_addtime":"2017-04-11 18:09:26","geval_frommemberid":"15","geval_frommembername":"feifeixi","geval_content":"这个是好包包，我有测试完全不耐烧！！！","geval_userimg":"http://cs.j.cqxueao.cn/data/upload/shop/common/default_user_portrait.gif"},{"geval_addtime":"2017-04-11 17:08:57","geval_frommemberid":"15","geval_frommembername":"feifeixi","geval_content":"有时候人品决定一切！!","geval_userimg":"http://cs.j.cqxueao.cn/data/upload/shop/common/default_user_portrait.gif"},{"geval_addtime":"2017-04-11 15:04:46","geval_frommemberid":"15","geval_frommembername":"feifeixi","geval_content":"不错不错,下次一定来","geval_userimg":"http://cs.j.cqxueao.cn/data/upload/shop/common/default_user_portrait.gif"}]}
     */

    private int code;
    private String msg;
    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        private List<EvalListBean> eval_list;

        public List<EvalListBean> getEval_list() {
            return eval_list;
        }

        public void setEval_list(List<EvalListBean> eval_list) {
            this.eval_list = eval_list;
        }

        public static class EvalListBean {
            /**
             * geval_addtime : 2017-04-12 14:13:01
             * geval_frommemberid : 13
             * geval_frommembername : 13272950223
             * geval_content : 不错不错哦~~
             * geval_userimg : http://cs.j.cqxueao.cn/data/upload/shop/common/default_user_portrait.gif
             */

            private String geval_addtime;
            private String geval_frommemberid;
            private String geval_frommembername;
            private String geval_content;
            private String geval_userimg;

            public String getGeval_addtime() {
                return geval_addtime;
            }

            public void setGeval_addtime(String geval_addtime) {
                this.geval_addtime = geval_addtime;
            }

            public String getGeval_frommemberid() {
                return geval_frommemberid;
            }

            public void setGeval_frommemberid(String geval_frommemberid) {
                this.geval_frommemberid = geval_frommemberid;
            }

            public String getGeval_frommembername() {
                return geval_frommembername;
            }

            public void setGeval_frommembername(String geval_frommembername) {
                this.geval_frommembername = geval_frommembername;
            }

            public String getGeval_content() {
                return geval_content;
            }

            public void setGeval_content(String geval_content) {
                this.geval_content = geval_content;
            }

            public String getGeval_userimg() {
                return geval_userimg;
            }

            public void setGeval_userimg(String geval_userimg) {
                this.geval_userimg = geval_userimg;
            }
        }
    }
}
