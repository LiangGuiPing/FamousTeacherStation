package com.util.page;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public enum ResponseCode
{
	SUCCESS(0, "成功", "Success"),
	FAILURE(999, "服务器异常", ""),
    LOGIN_FAILD(20001, "登录失败",""),
    USER_NOT_REGISTER(20002, "用户未注册",""),
    SEND_SMS_OVER_TIMES(20003, "验证码超过次数",""),
    SEND_SMS_CHECKOUT_FAILURE(20004, "发送短信失败",""),
    MESSAGE_ALREADY_SEND(20005, "消息已发送",""),
    USER_NOT_EXIST(20006, "用户不存在",""),
    USER_NOT_LOGIN(20007, "用户未登录",""),
    VERIFICATION_CODE_ERROR(20008, "验证码有误",""),
    MOBILE_IS_NULL(20009, "手机号为空",""),
    CODE_IS_NULL(20010, "验证码为空",""),
	PASSWORD_IS_NULL(20011, "登录密码为空",""),
	USERNAME_ERROR_OR_PASSWORD_ERROR(20012, "用户名或密码有误",""),
    SMS_SEND_FAILD(20013, "发送短信验证码失败",""),
    SMS_REDIS_NULL(20014, "请重新获取验证码",""),
    SMS_EQUALS_FALSE(20015, "验证码错误，请重新输入",""),
    SMS_MOBILE_EMPTY(20016, "手机号不能为空",""),
    SMS_MOBILE_VALID(20017, "非有效的手机号格式",""),
    LOGIN_TOKEN_IS_NULL(20018, "登录Token为空",""),
    PLATFORM_TYPE_IS_NULL(20019, "平台类型为空",""),
    LOGIN_TOKEN_INVALID(20020, "登录Token无效",""),
    LOGOUT_SUCCESS(20021, "用户登出成功",""),
    SUBJECT_ID_IS_NULL(20022, "主题ID为空",""),
    USER_EXIST(20023, "手机号已存在",""),
    FILE_URL_IS_NULL(20024, "文件URL为空",""),
    EMAIL_ADDRESS_IS_NULL(20025, "收件人邮箱为空",""),
    USER_ID_IS_NULL(20026, "用户ID为空",""),
    QUESTION_TITLE_IS_NULL(20027, "问题title为空",""),
    QUESTION_DESC_IS_NULL(20028, "问题description为空",""),
    QUESTION_IS_EXIST(20029, "该问题已经存在",""),
    QUESTION_SEARCH_KEYWORD_IS_NULL(20030, "搜索问题关键字为空",""),
    OLDPASSWORD_IS_NULL(20031, "旧密码为空",""),
    NEWPASSWORD_IS_NULL(20032, "新密码为空",""),
    QUESTION_ID_IS_NULL(20033, "问题ID为空", ""),
    ANSWER_CONTENT_IS_NULL(20034, "回答内容为空", ""),
    ANSWER_ID_IS_NULL(20035, "回答ID为空", ""),
    HAS_LIKE_BY_YOURSELF(20036, "你已经点赞过了", ""),
    DEVICE_INFO_IS_NULL(20037, "设备号信息为空", ""),
    USER_CONTENT_NULL(20038, "修改内容不能为空", ""),
    DEVICE_INFO_IS_EXIST(20039, "设备号信息已经存在", ""),
    THIRD_TOKEN_IS_NULL(20040, "第三方token为空", ""),
    DEVICE_TYPE_IS_NULL(20041, "设备类型为空", ""),
    PARAMS_IS_NULL(20042, "参数为空", ""),
    NICKNAME_IS_EXISTS(20043, "该昵称已被占用", ""),
    THIRD_PARTY_RETURN_EXCEPTION(20044, "第三方返回异常", ""),
    TOOL_FACE_PIC_LIST_SIZE_0(20045, "图片数量为0", ""),
    BIRTHDAY_IS_NOT_RIGHT(20046, "生日不符合标准",""),
    TOOL_FACE_FACEID_IS_ERROR(20047, "faceId无效", ""),
    TOOL_FACE_PICID_IS_ERROR(20048, "picId无效", ""),
    PAGE_MODULE_ID_IS_NULL(20049, "模块ID为空", ""),
    TOOL_FACE_TOKEN_TEACHERID_IS_ERROR(20050, "token取teacherId失败",""),
    TOOL_FACE_TOKEN_CAMPUSID_IS_ERROR(20050, "token取campusId失败",""),
    TOOL_FACE_LEAVE_MSG_LONG(20051, "留言不能超过300个字符",""),

    TEACH_QA_QESTION_TITLE_SHORT(20052, "问题标题不能少于5个字符",""),
    TEACH_QA_QESTION_TITLE_LONG(20053, "问题标题不能多于15个字符",""),
    TEACH_QA_QESTION_TITLE_QUESTION_MARK(20054, "问题标题需以问号结尾",""),
    TEACH_QA_QESTION_TITLE_ERROR_CHARACTER(20055, "问题标题中不能含有*\\/",""),
    TEACH_QA_QESTION_DESCRIPTION_SHORT(20056, "问题描述不能少于3个字符",""),
    TEACH_QA_QESTION_DESCRIPTION_LONG(20057, "问题描述不能多于200个字符",""),
    TEACH_QA_QESTION_DESCRIPTION_ERROR_CHARACTER(20058, "问题描述中不能含有*\\/",""),
    TEACH_QA_ANSWER_CONTENT_SHORT(20059, "回答内容不能少于3个字符",""),
    TEACH_QA_ANSWER_CONTENT_LONG(20060, "回答内容不能多于1000个字符",""),
    TEACH_QA_QESTIONID_IS_ERROR(20061, "questionId无效",""),
    TEACH_QA_COLLECTION_IS_DUPLICATE(20062, "不可重复收藏",""),
    TEACH_QA_COLLECTION_IS_NULL(20063, "还未收藏，不可取消",""),
    TEACH_QA_ANSWERID_IS_ERROR(20064, "answerId无效",""),
    TEACH_QA_ANSWER_LIKE_IS_DUPLICATE(20065, "不可重复点赞同一回答",""),
    TEACH_QA_ANSWER_LIKE_IS_NULL(20066, "还未点赞此回答，不可取消",""),
    TEACH_QA_KA_COLLECTION_TYPE_IS_ERROR(20067, "type无效",""),

    TEACH_SEARCH_OPTIONS_TYPE_IS_ERROR(20068, "type无效",""),
    TEACH_SEARCH_OPTIONS_TITLE_IS_NULL(20069, "title为空",""),
    TEACH_SEARCH_OPTIONS_NUMBER_IS_ERROR(20070, "number需大于0",""),
    
    IDS_SET_IS_NULL(20071, "ID集合字符为空",""),
    GET_GARDEN_SERVER_DATA_IS_NULL(20072, "从garden服务取数据为空或服务已宕机",""),

    ;
    public final Integer code;
    public final String message;
    public final String message_en;
    ResponseCode(Integer code, String message, String message_en)
    {
        this.code = code;
        this.message = message;
        this.message_en = message_en;
    }

    public Integer getCode() 
    {
		return code;
	}

	public String getMessage() 
	{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String lang=request.getHeader("language");
        if(lang!=null&&lang.equals("en")&&!message_en.equals("")){
            return message_en;
        }
        else{
            return message;
        }
	}

	public static ResponseCode toEnum(String name) 
	{
        for (ResponseCode res : ResponseCode.values()) 
        {
            if (res.toString().equalsIgnoreCase(name)) 
            {
                return res;
            }
        }
        return null;
    }

}