package dp.schoolandroid.service.repository.remotes;

import dp.schoolandroid.service.model.request.ChangePasswordRequest;
import dp.schoolandroid.service.model.request.ForgetPasswordRequest;
import dp.schoolandroid.service.model.request.ParentRequest;
import dp.schoolandroid.service.model.request.ResetPasswordRequest;
import dp.schoolandroid.service.model.request.StudentRequest;
import dp.schoolandroid.service.model.request.TeacherRequest;
import dp.schoolandroid.service.model.response.AboutUsResponse;
import dp.schoolandroid.service.model.response.ContactUsResponse;
import dp.schoolandroid.service.model.response.FeedsResponse;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.model.response.GalleryResponse;
import dp.schoolandroid.service.model.response.VideosResponse;
import dp.schoolandroid.service.model.response.parentresponse.ParentResponse;
import dp.schoolandroid.service.model.response.studentresponse.StudentResponse;
import dp.schoolandroid.service.model.response.teacherresponse.TeacherResponse;
import dp.schoolandroid.service.model.response.teacherresponse.TeacherScheduleResponse;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterfaces {

    //*********** Global endpoints *********
    @GET("/api/feed")
    Observable<Response<FeedsResponse>> getNewsFeed(@Header("x-api-key") String xApiKey,@Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept);

    @GET("/api/gallery")
    Observable<Response<GalleryResponse>> getGallleryImages(@Header("x-api-key") String xApiKey,@Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept,@Query("page") int pageNumber);

    @GET("/api/video")
    Observable<Response<VideosResponse>> getAllTheVideos(@Header("x-api-key") String xApiKey, @Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Query("page") int pageNumber);

    @GET("/api/about-us")
    Observable<Response<AboutUsResponse>> getAboutUsData(@Header("x-api-key") String xApiKey, @Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept);

    //*********** Teacher endpoints *********
    @POST("/api/teacher/login")
    Observable<Response<TeacherResponse>> loginAsTeacher(@Header("x-api-key") String xApiKey,@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body TeacherRequest teacherLoginRequest);

    @GET("/api/teacher/schedule")
    Observable<Response<TeacherScheduleResponse>> getTeacherSchedule(@Header("x-api-key") String xApiKey,@Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept);

    @POST("/api/teacher/forget")
    Observable<Response<ForgetPasswordResponse>> forgetPasswordTeacher(@Header("x-api-key") String xApiKey,@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ForgetPasswordRequest forgetPasswordRequest);

    @POST("/api/teacher/change-password")
    Observable<Response<ForgetPasswordResponse>> changePasswordTeacher(@Header("x-api-key") String xApiKey,@Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ChangePasswordRequest changePasswordRequest);

    @POST("/api/teacher/forget/token")
    Observable<Response<ForgetPasswordResponse>> generatePasswordResetTokenTeacher(@Header("x-api-key") String xApiKey,@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ForgetPasswordRequest forgetPasswordRequest);

    @POST("/api/teacher/forget/reset/{token}")
    Observable<Response<ForgetPasswordResponse>> resetPasswordTeacher(@Header("x-api-key") String xApiKey,@Header("Content-Type") String contentType, @Header("Accept") String accept, @Path("token") String token, @Body ResetPasswordRequest resetPasswordRequest);

    @GET("/api/teacher/contact")
    Observable<Response<ContactUsResponse>> getContactInfoForTeacher(@Header("x-api-key") String xApiKey,@Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept);

    //*********** Student endpoints *********
    @POST("/api/student/login")
    Observable<Response<StudentResponse>> loginAsStudent(@Header("x-api-key") String xApiKey,@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body StudentRequest studentLoginRequest);

    @POST("/api/student/forget")
    Observable<Response<ForgetPasswordResponse>> forgetPasswordStudent(@Header("x-api-key") String xApiKey,@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ForgetPasswordRequest forgetPasswordRequest);

    @POST("/api/student/forget/token")
    Observable<Response<ForgetPasswordResponse>> generatePasswordResetTokenStudent(@Header("x-api-key") String xApiKey,@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ForgetPasswordRequest forgetPasswordRequest);

    @POST("/api/student/forget/reset/{token}")
    Observable<Response<ForgetPasswordResponse>> resetPasswordStudent(@Header("x-api-key") String xApiKey,@Header("Content-Type") String contentType, @Header("Accept") String accept, @Path("token") String token, @Body ResetPasswordRequest resetPasswordRequest);

    //*********** Parent endpoints *********
    @POST("/api/parent/login")
    Observable<Response<ParentResponse>> loginAsParent(@Header("x-api-key") String xApiKey,@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ParentRequest parentLoginRequest);

    @POST("/api/parent/forget")
    Observable<Response<ForgetPasswordResponse>> forgetPasswordParent(@Header("x-api-key") String xApiKey,@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ForgetPasswordRequest forgetPasswordRequest);

    @POST("/api/parent/forget/token")
    Observable<Response<ForgetPasswordResponse>> generatePasswordResetTokenParent(@Header("x-api-key") String xApiKey,@Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ForgetPasswordRequest forgetPasswordRequest);

    @POST("/api/parent/forget/reset/{token}")
    Observable<Response<ForgetPasswordResponse>> resetPasswordParent(@Header("x-api-key") String xApiKey,@Header("Content-Type") String contentType, @Header("Accept") String accept, @Path("token") String token, @Body ResetPasswordRequest resetPasswordRequest);
}
