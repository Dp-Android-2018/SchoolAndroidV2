package dp.schoolandroid.service.repository.remotes;

import android.arch.lifecycle.MutableLiveData;

import dp.schoolandroid.service.model.request.ForgetPasswordRequest;
import dp.schoolandroid.service.model.request.ParentRequest;
import dp.schoolandroid.service.model.request.StudentRequest;
import dp.schoolandroid.service.model.request.TeacherRequest;
import dp.schoolandroid.service.model.response.FeedsResponse;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.model.response.parentresponse.ParentResponse;
import dp.schoolandroid.service.model.response.studentresponse.StudentResponse;
import dp.schoolandroid.service.model.response.teacherresponse.TeacherResponse;
import dp.schoolandroid.service.model.response.teacherresponse.TeacherScheduleResponse;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterfaces {

    @POST("/api/teacher/login")
    Call<TeacherResponse> loginAsTeacher(@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body TeacherRequest teacherLoginRequest);

    @POST("/api/student/login")
    Call<StudentResponse> loginAsStudent(@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body StudentRequest studentLoginRequest);

    @POST("/api/parent/login")
    Call<ParentResponse> loginAsParent(@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ParentRequest parentLoginRequest);

    @GET("/api/teacher/schedule")
    Call<TeacherScheduleResponse> getTeacherSchedule(@Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept);

    @GET("/api/feed")
    Call<FeedsResponse> getNewsFeed(@Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept);

    @POST("/api/teacher/forget")
    Call<ForgetPasswordResponse> forgetPasswordTeacher(@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ForgetPasswordRequest forgetPasswordRequest);

    @POST("/api/teacher/forget/token")
    Call<ForgetPasswordResponse> generatePasswordResetTokenTeacher(@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ForgetPasswordRequest forgetPasswordRequest);

    @POST("/api/parent/forget")
    Call<ForgetPasswordResponse> forgetPasswordParent(@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ForgetPasswordRequest forgetPasswordRequest);

    @POST("/api/parent/forget/token")
    Call<ForgetPasswordResponse> generatePasswordResetTokenParent(@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ForgetPasswordRequest forgetPasswordRequest);

    @POST("/api/student/forget")
    Call<ForgetPasswordResponse> forgetPasswordStudent(@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ForgetPasswordRequest forgetPasswordRequest);

    @POST("/api/student/forget/token")
    Call<ForgetPasswordResponse> generatePasswordResetTokenStudent(@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ForgetPasswordRequest forgetPasswordRequest);

}
