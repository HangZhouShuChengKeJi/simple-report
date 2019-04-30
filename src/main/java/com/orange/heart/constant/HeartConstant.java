package com.orange.heart.constant;

public interface HeartConstant {

    /**
     * 默认编码
     */
    String DEFALUT_CHARSET = "UTF-8";

    /**
     * 登录常量
     *
     * @author yeyj
     */
    public interface HEART_LOGIN_CONSTANTS {

        /**
         * Session
         */
        String COOKIE_HEART_SESSION_ID = "heart_cookie_session_id";

        /**
         * Cookie中的用户名
         */
        String COOKIE_HEART_USER_NICK = "heartUserNick";
    }

    /**
     * 短信签名
     *
     * @author Administrator
     */
    public static interface SIGN {
        // 橙果错题本
        String JUBAOYUN = "【橙果错题本】";
    }

    /**
     * 返回值
     *
     * @author Administrator
     */
    public static interface SMS_RETURN_CODE {
        // 成功
        String SUCCESS = "success";
        // 失败
        String FAILURE = "failure";
    }

    /**
     * @author Administrator
     */
    public static interface PAGE_BASE {
        // 成功
        String OFFSET  = "success";
        // 失败
        String FAILURE = "failure";
    }

    public static interface TASK_STATUS {
        /**
         * 任务状态初始化
         */
        String TASK_STATUS_0 = "0";

        /**
         * 任务状态成功
         */
        String TASK_STATUS_1 = "1";

        /**
         * 任务状态异常
         */
        String TASK_STATUS_2 = "2";
    }

    public static interface TASK_YES_NO {
        // 开启
        int TASK_YES = 1;

        // 关闭
        int TASK_NO = 0;
    }

    public static interface YES_NO {
        int YES = 1;

        int NO = 0;
    }

    public static interface CHART_X_INFO {
        String X_KEY = "xKey";

        String X_KEY_VALUE = "xKeyValue";
    }

    public static interface CHART_PARAM_KEY {
        String HTML_DATAINFO = "html_dataInfo";

        String HTML_XAXISTICKS = "html_xaxisTicks";

        String HTML_TITLE = "html_title";
    }

    public static interface DATA_SOURCE {
        String CTB = "ctb";

    }

    public static interface QUARTZ {

        String JOB_GROUP = "hiveWork";
    }

    public static interface PARAM_TYPE {
        // 字符串
        String STR       = "0";
        // 整型
        String NUMBER    = "1";
        /**
         * 日期
         */
        String DATE_TIME = "2";
        /**
         * 日期区间
         */
        String DATE_TIME_RANGE = "4";

        // 整型（区间）
        String NUMBER_RANGE    = "5";

    }
}
