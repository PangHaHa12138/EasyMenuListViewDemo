package com.panghaha.it.easymenulistviewdemo;

import java.util.ArrayList;

/**
 * Created by pang on 2017/4/1.
 *  查询列表-我收到的任务
 */
public class Data_chaxunliebiao_shoudaorenwu {


    /**
     * total : 18
     * list : [{"taskid":"d2a004ab353b4cdc8f2dd9d0490b5449",
     * "cuserid":"ac31532c3b954a0a9d57fb1e65884775",
     * "cusername":"薄晓军","taskname":"肥肠粉地方大幅度",
     * "yendtime":"2017-03-27","sendtime":null,
     * "createtime":"2017-03-22 00:05:03.0",
     * "publishTime":"03-22","taskcontent":null,
     * "taskstatus":3,"tasklevel":3,"taskcycle":null,
     * "cycletype":0,"tasktype":0,"childcount":0,"progress":0,
     * "score":0,"assessed":0,"accepted":0,"executors":"[战乃国]",
     * "from":1,"imageUrl":"http://123.56.97.229/upload/1469698050614.jpg",
     * "timeInfo":"-9","updateTime":null,"updatetime":null,"readed":0,"stick":1}]
     */

    private int total;
    private ArrayList<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<ListBean> getList() {
        return list;
    }

    public void setList(ArrayList<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * taskid : d2a004ab353b4cdc8f2dd9d0490b5449
         * cuserid : ac31532c3b954a0a9d57fb1e65884775
         * cusername : 薄晓军
         * taskname : 肥肠粉地方大幅度
         * yendtime : 2017-03-27
         * sendtime : null
         * createtime : 2017-03-22 00:05:03.0
         * publishTime : 03-22
         * taskcontent : null
         * taskstatus : 3
         * tasklevel : 3
         * taskcycle : null
         * cycletype : 0
         * tasktype : 0
         * childcount : 0
         * progress : 0
         * score : 0
         * assessed : 0
         * accepted : 0
         * executors : [战乃国]
         * from : 1
         * imageUrl : http://123.56.97.229/upload/1469698050614.jpg
         * timeInfo : -9
         * updateTime : null
         * updatetime : null
         * readed : 0
         * stick : 1
         */

        private String taskid;
        private String cuserid;
        private String cusername;
        private String taskname;
        private String yendtime;
        private Object sendtime;
        private String createtime;
        private String publishTime;
        private Object taskcontent;
        private int taskstatus;
        private int tasklevel;
        private Object taskcycle;
        private int cycletype;
        private int tasktype;
        private int childcount;
        private int progress;
        private int score;
        private int assessed;
        private int accepted;
        private String executors;
        private int from;
        private String imageUrl;
        private String timeInfo;
        private Object updateTime;
        private Object updatetime;
        private int readed;
        private int stick;

        public String getTaskid() {
            return taskid;
        }

        public void setTaskid(String taskid) {
            this.taskid = taskid;
        }

        public String getCuserid() {
            return cuserid;
        }

        public void setCuserid(String cuserid) {
            this.cuserid = cuserid;
        }

        public String getCusername() {
            return cusername;
        }

        public void setCusername(String cusername) {
            this.cusername = cusername;
        }

        public String getTaskname() {
            return taskname;
        }

        public void setTaskname(String taskname) {
            this.taskname = taskname;
        }

        public String getYendtime() {
            return yendtime;
        }

        public void setYendtime(String yendtime) {
            this.yendtime = yendtime;
        }

        public Object getSendtime() {
            return sendtime;
        }

        public void setSendtime(Object sendtime) {
            this.sendtime = sendtime;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public Object getTaskcontent() {
            return taskcontent;
        }

        public void setTaskcontent(Object taskcontent) {
            this.taskcontent = taskcontent;
        }

        public int getTaskstatus() {
            return taskstatus;
        }

        public void setTaskstatus(int taskstatus) {
            this.taskstatus = taskstatus;
        }

        public int getTasklevel() {
            return tasklevel;
        }

        public void setTasklevel(int tasklevel) {
            this.tasklevel = tasklevel;
        }

        public Object getTaskcycle() {
            return taskcycle;
        }

        public void setTaskcycle(Object taskcycle) {
            this.taskcycle = taskcycle;
        }

        public int getCycletype() {
            return cycletype;
        }

        public void setCycletype(int cycletype) {
            this.cycletype = cycletype;
        }

        public int getTasktype() {
            return tasktype;
        }

        public void setTasktype(int tasktype) {
            this.tasktype = tasktype;
        }

        public int getChildcount() {
            return childcount;
        }

        public void setChildcount(int childcount) {
            this.childcount = childcount;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getAssessed() {
            return assessed;
        }

        public void setAssessed(int assessed) {
            this.assessed = assessed;
        }

        public int getAccepted() {
            return accepted;
        }

        public void setAccepted(int accepted) {
            this.accepted = accepted;
        }

        public String getExecutors() {
            return executors;
        }

        public void setExecutors(String executors) {
            this.executors = executors;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getTimeInfo() {
            return timeInfo;
        }

        public void setTimeInfo(String timeInfo) {
            this.timeInfo = timeInfo;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(Object updatetime) {
            this.updatetime = updatetime;
        }

        public int getReaded() {
            return readed;
        }

        public void setReaded(int readed) {
            this.readed = readed;
        }

        public int getStick() {
            return stick;
        }

        public void setStick(int stick) {
            this.stick = stick;
        }
    }
}
