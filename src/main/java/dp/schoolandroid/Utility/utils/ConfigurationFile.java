package dp.schoolandroid.Utility.utils;

import android.text.format.DateFormat;

import java.util.Date;

public class ConfigurationFile {

    public static class UrlConstants {
        public static final String BASE_URL = "http://dp-itc.com:4321/";//"http://dp-itc.com:8888/";
        public static final String COUNTRIES_URL = "api/utilities/countries";
        public static final String CATEGORIES_URL = "api/utilities/categories";
        public static final String LOGIN_URL = "api/login";
        public static final String OFFERS = "api/search/offers";
        public static final String CLIENT_REGISTER_URL = "api/register";
        public static final String CHANGE_PASSWORD_URL = "api/change-password";
        public static final String FORGET_PASSWORD_URL = "api/forget";
        public static final String UPDATE_METADATA = "api/companies/company-meta-data";
        public static final String CHECK_MAIL = "api/check/email";
        public static final String CHECK_PHONE = "api/check/phone";
        public static final String CHECK_ACTIVATION_STATUS = "api/activate/check";
        public static final String SEND_ACTIVATION_MAIL = "api/activate/email/send";
        public static final String PHONE_ACTIVATION = "api/activate/phone";
        public static final String SEND_ACTIVATION_CODE = "api/activate/phone/send";
        public static final String CHECK_ACTIVATION_CODE = "api/activate/phone/sent";
        public static final String CREATE_CLIENT_RESERVATION = "api/clients/reservation";
        public static final String COMPANY_APPROVE_RESERVATION = "api/companies/reservation";
        public static final String GET_RESERVATION_URL = "api/reservations/{status}";
        public static final String GET_RESERVATION_DETAIL_URL = "api/reservation/{id}";
        public static final String GET_COMPANY_DETAIL = "api/company/{id}/data";
        public static final String GET_COMPANY_COMMENTS = "api/company/{id}/comments";
        public static final String COMPANY_SEARCH = "api/search";
        public static final String CREATE_COMMENT = "api/clients/comments";
        public static final String GET_SPECIALIZATION_URL = "api/utilities/specializations";
        public static final String DELETE_RESERVATION_URL = "api/companies/reservation/{id}";
    }

    public static class Constants {
        public static final String API_KEY = "MUaJWwkyZOZKnbvZczGoFDt0sLyeS0eCkoKXtam00nobixvPC2BV2rcP3TKJSLYU";
        public static final String CONTENT_TYPE = "application/json";
        public static final String ACCEPT = "application/json";
        public static final String ACTIVITY_NUMBER = "ACTIVITY_NAME";
        public static final String PHONE_NUMBER = "PHONE_NUMBER";
        public static final String API_TOKEN = "APITOKEN";
        public static final String BEARER = "Bearer ";
        public static final String TYPE = "TYPE";
        public static final String DATA = "DATA";
        public static final String STUDENTS = "Students";
        public static final String ANNONCEMENTS = "Announcements";
        public static final String QUIZZES = "Quizzes";
        public static final String MEMBER_Key = "MEMBER";
        public static final String SESSIONTIMEMODEL = "sectionTimeModel";
        public static final String SESSIONTYPE = "SessionType";
        public static final String NEWSESSION = "2";
        public static final String HISTORYSESSION = "1";
        public static final String SESSIONID = "sessionId";
        public static final String ASSIGNMENTData = "Assignment";
        public static final String QUIZDETAILS = "QuizDetails";
        public static final String STUDENT_Key_VALUE = "2";
        public static final String TEACHER_Key_VALUE = "1";
        static final String name = DateFormat.format("yyyy-MM-dd_hhmmss", new Date()).toString();
        public static final String QUIZZES_DIRECTORY_NAME = name+"_Quizz";
        public static final String ANNONCEMENTS_DIRECTORY_NAME = name+"_Annoncement";
            public static final String PAGE_ID = "0";
        public static final int SUCCESS_CODE = 200;
        public static final int UNAUTHANTICATED_CODE = 401;
        public static final int TEACHER_ACTIVITY_CODE = 1;
        public static final int PARENT_ACTIVITY_CODE = 2;
        public static final int STUDENT_ACTIVITY_CODE = 3;
        public static final String STUDENT_PHONE_NUMBER_MESSAGE = "Your phone Number :)";
        public static final int SUCCESS_CODE_SECOND = 201;
        public static final int INVALED_DATA_CODE = 422;
        public static final int INVALED_EMAIL_PASSWORD = 401;
        public static final int SUSBENDED = 417;
        public static final int ALREADY_ACTIVATED = 403;
        public static final int TRY_LATER = 429;
        //////////////////////////////////////////////////////////////////////////
        public static final int FILL_ALL_DATA_ERROR_CODE = -601;
        public static final int INVALED_EMAIL = -602;
        public static final int PASSWORD_LENGTH_ERROR = -603;
        public static final int PASSWORD_CONFIRMATION_ERROR = -604;
        public static final int EXISET_MAIL_CODE = -605;
        public static final int NO_INTERNET_CONNECTION_CODE = -606;
        public static final int EXISET_PHONE_CODE = -607;
        public static final int ACTIVE_ACCOUNT_ERROR = -608;


        ////////////////////////////////////////////////////////////////////
        public static final int GETIMAGE = 1;
        public static final int PICK_IMAGE_REQUEST = 2;
        public static final int SELECT_COUNTRY = 3;
        public static final int MOVE_TO_COUNTRY_ACT = 4;
        public static final int MOVE_TO_CITY_ACT = 5;
        public static final int COUNTRY_ADAPTER = 6;
        public static final int MOVE_TO_MAP_ACT = 7;
        public static final int MOVE_TO_SPECIALIZATION_ACT = 8;
        public static final int SPECIALIZATION_ADAPTER = 9;
        public static final int SKIP = 10;
        public static final int REGISTER_STEP2 = 11;

    }

    public static class SharedPrefConstants {
        public static final String SHARED_PREF_NAME = "TADAWAY_SHARED_PREF";
    }

    public static class IntentConstants {
        public static final String CLIENT_REQUEST = "CLIENTREGISTERREQUEST";
        public static final String COUNTRY_DATA = "COUNTRY";
        public static final String CITY_DATA = "CITY";
        public static final String REGISTER_STEP1_DATA = "REGISTERSTEP1DATA";
        public static final String CONTAINER_RESERVATION = "RESERVATION";
        public static final String COMPANY_REQUEST = "REQUEST";
        public static final String ADDRESS_REQUEST = "ADDRESSREQUEST";
        public static final String LONGITUDE_REQUEST = "longitude";
        public static final String LATITUDE_REQUEST = "latitude";
        public static final String SPECIALIZATION_DATA = "SPECIALIZATION";
        public static final String TYPE = "type";
        public static final String REQUEST_ITEM_DATA = "ITEMREQUEST";
        public static final int REQUEST_CODE_COUNTRY = 111;
        public static final int REQUEST_CODE_CITY = 222;
        public static final int REQUEST_CODE_MAP = 333;
        public static final int REQUEST_CODE_SPECIALIZATION = 444;


    }
}
