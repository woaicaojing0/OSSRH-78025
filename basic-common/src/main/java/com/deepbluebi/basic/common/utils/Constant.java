package com.deepbluebi.basic.common.utils;

/**
 * 描述：常量
 *
 * @author zhanghao
 * @create 2018-11-19-14:48
 */
public interface Constant {

    String PALMSET_NAME_PREFIX = "AIP_IOT_DEVICE_AUTH_";

    public static final int VEIN_POSITION = 20000;

    public static final int VEIN_CAPTUREPOSITION = 10924;

    public static final String PALMSET_N = "10000";


    String DATABASE_NAME = "aipyun";

    //数据库备份保存位置
    String BACKUP_PATH = "/opt/data/backup/sql/";

    // By wangshuai 2019/1/3 拼接后的长度
    int PALMSET_NAME_LENGTH = 255;

    //临时授权的palmset name
    String USER_TEMP_AUTH_PALMSET_NAME = "AIP_TEMP_USER_AUTH";

    String USER_TEMP_AUTH_PALMSET_TAG = "临时权限";

    //redis 设备状态key值前缀
    String REDIS_DEVICE_KEY_PRE = "deviceSn_";

    //redis 设备状态心跳次数key值前缀
    String REDIS_DEVICE_KEY_HEART_PRE = "deviceIdHeatBeatTimes_";

    //redis 设备palm channel 状态key值前缀
    String REDIS_DEVICE_KEY_PALM_CHANNEL_STATUS_PRE = "deviceSnChannelPalmStatus_";

    //redis 保存设备最近在线时间
    String REDIS_DEVICE_KEY_LAST_ONLINE_TIME = "deviceSnLastOnline_";

    //保存设备请求状态
    String REDIS_DEVICE_OPERATE_CONTEXT_PRE = "device_in_operation";

    //保存每次请求的id
    String REDIS_DEVICE_OPERATE_REQUEST_PRE = "device_requestId_";


    String REDIS_ADD_DEVICE_INFO = "add_device_info_";

    String REDIS_BP_REQUEST_WEBSOCKET_MAP = "bp_request_id_";

    long REDIS_ADD_DEVICE_INFO_EXPIRE = 90000L;

    //设备状态过期时间60000 ms
    long REDIS_DEVICE_EXPIRE_TIME = 60000L;

    // 该目录对应springboot默认的静态资源的目录规范
    String SYS_WEB_UPLOAD_IMAGE_URL = "/static/upload/file";

    /**
     * redis 保存bluepalm token 的key
     */
    String REDIS_KEY_BLUE_PALM_TOKEN = "bluePalmToken";

    /**
     * token有效时间 7200秒 7200000ms
     */
    Long REDIS_KEY_BLUE_PALM_TOKEN_EXPIRE_TIME = 7200000L;


    String BP_SUCCESS = "0";

    String OTA_BASE_PATH = "/opt/iot/ota/";

    /**
     * 访客的palmset
     */
    String VISITOR_PALMSET_NAME = "AIP_VISITOR_PALMSET";
    /**
     * redis 访客邀约结束时间key值前缀
     */
    String REDIS_INVITATION_BEGIN_KEY_PRE = "invitation_begin_";

    /**
     * redis 访客邀约结束时间key值前缀
     */
    String REDIS_INVITATION_END_KEY_PRE = "invitation_end_";


    /**
     * 设备状态 在线
     */
    String DEVICE_STATUS_ONLINE = "online";
    /**
     * 设备状态 离线
     */
    String DEVICE_STATUS_OFFLINE = "offline";
    /**
     * 设备状态 故障
     */
    String DEVICE_STATUS_FAULT = "fault";

    /**
     * 设备状态 未知
     */
    String DEVICE_STATUS_UNKNOWN = "unknown";

    /**
     * 设备状态 正常
     */
    String DEVICE_STATUS_NORMAL = "normal";

    /**
     * 设备状态 没有
     */
    String DEVICE_STATUS_NONE = "none";


    /**
     * 设备日志中设备操作的状态 开始
     */
    String DEVICE_OPERATE_STATUS_START = "start";


    /**
     * 设备日志中设备操作的状态 结束
     */
    String DEVICE_OPERATE_STATUS_FINISH = "finish";

    /**
     * 设备日志中设备操作的状态 失败
     */
    String DEVICE_OPERATE_STATUS_FAILURE = "failure";

    /**
     * 记录访客palmset是否已经创建
     */
    String REDIS_INVITATION_PALMSET = "invitation_palmset";


    /**
     * 上班/休息开始
     */
    String ON_DUTY = "OnDuty";
    /**
     * 下班/休息结束
     */
    String OFF_DUTY = "OffDuty";

    /**
     * 正常
     */
    String Normal = "Normal";

    /**
     * 早退
     */
    String Early = "Early";

    /**
     * 迟到
     */
    String Late = "Late";

    /**
     * 缺卡
     */
    String NotSigned = "NotSigned";

    /**
     * 整个一天的考勤结果 正常
     */
    String ATTENDANCE_NORMAL = "Normal";

    /**
     * 整个一天的考勤结果  上班异常
     */
    String ATTENDANCE_ON_DUTY_ABNORMAL = "OnDutyAbnormal";

    /**
     * 整个一天的考勤结果 下班异常
     */
    String ATTENDANCE_OFF_DUTY_ABNORMAL = "OffDutyAbnormal";

    /**
     * 整个一天的考勤结果 上下班都异常
     */
    String ATTENDANCE_ABNORMAL = "Abnormal";

    /**
     * 整个一天的考勤结果  矿工
     */
    String ATTENDANCE_ABSENT = "Absent";

    /**
     * 整个一天的考勤结果  休息
     */
    String ATTENDANCE_REST = "rest";

    /**
     * 整个一天的考勤结果  休息并打卡
     */
    String ATTENDANCE_REST_BUT_CHECK = "restButCheck";

    /**
     * 整个一天的考勤结果  休息并打卡
     */
    String ATTENDANCE_NOT_IN_GROUP_BUT_CHECK = "notInGroupButCheck";


    /**
     * 整个一天的考勤结果  无
     */
    String ATTENDANCE_NONE = "none";

    String ATTENDANCE_COLOR_RED = "red";


}
