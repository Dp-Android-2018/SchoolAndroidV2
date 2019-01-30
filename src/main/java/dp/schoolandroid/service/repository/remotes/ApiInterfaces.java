package dp.schoolandroid.service.repository.remotes;

import dp.schoolandroid.service.model.request.ChangePasswordRequest;
import dp.schoolandroid.service.model.request.ForgetPasswordRequest;
import dp.schoolandroid.service.model.request.GradeRequest;
import dp.schoolandroid.service.model.request.ParentRequest;
import dp.schoolandroid.service.model.request.QuizRequest;
import dp.schoolandroid.service.model.request.ResetPasswordRequest;
import dp.schoolandroid.service.model.request.SessionRequest;
import dp.schoolandroid.service.model.request.StudentRequest;
import dp.schoolandroid.service.model.request.SuggestionRequest;
import dp.schoolandroid.service.model.request.TeacherRequest;
import dp.schoolandroid.service.model.request.TeachingSessionRequest;
import dp.schoolandroid.service.model.response.AboutUsResponse;
import dp.schoolandroid.service.model.response.ContactUsResponse;
import dp.schoolandroid.service.model.response.FeedsResponse;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.model.response.GalleryResponse;
import dp.schoolandroid.service.model.response.SessionResponse;
import dp.schoolandroid.service.model.response.SessionResponseData;
import dp.schoolandroid.service.model.response.VideosResponse;
import dp.schoolandroid.service.model.response.parentresponse.ParentResponse;
import dp.schoolandroid.service.model.response.studentresponse.StudentResponse;
import dp.schoolandroid.service.model.response.teacherresponse.AssignmentHistoryResponse;
import dp.schoolandroid.service.model.response.teacherresponse.QuizHistoryResponse;
import dp.schoolandroid.service.model.response.teacherresponse.SessionHistoryResponse;
import dp.schoolandroid.service.model.response.teacherresponse.TeacherResponse;
import dp.schoolandroid.service.model.response.teacherresponse.TeacherScheduleResponse;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterfaces {

    //*********** Global endpoints *********
    @GET("/api/feed")
    Observable<Response<FeedsResponse>> getNewsFeed(@Header("x-api-key") String xApiKey, @Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept);

    @GET("/api/gallery")
    Observable<Response<GalleryResponse>> getGallleryImages(@Header("x-api-key") String xApiKey, @Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Query("page") int pageNumber);

    @GET("/api/video")
    Observable<Response<VideosResponse>> getAllTheVideos(@Header("x-api-key") String xApiKey, @Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Query("page") int pageNumber);

    @GET("/api/about-us")
    Observable<Response<AboutUsResponse>> getAboutUsData(@Header("x-api-key") String xApiKey, @Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept);

    @POST("/api/suggest")
    Observable<Response<AboutUsResponse>> createNewSuggestion(@Header("x-api-key") String xApiKey, @Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body SuggestionRequest suggestionRequest);

    //*********** Teacher endpoints *********
    @POST("/api/teacher/login")
    Observable<Response<TeacherResponse>> loginAsTeacher(@Header("x-api-key") String xApiKey, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body TeacherRequest teacherLoginRequest);

    @GET("/api/teacher/schedule")
    Observable<Response<TeacherScheduleResponse>> getTeacherSchedule(@Header("x-api-key") String xApiKey, @Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept);

    @POST("/api/teacher/forget")
    Observable<Response<ForgetPasswordResponse>> forgetPasswordTeacher(@Header("x-api-key") String xApiKey, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ForgetPasswordRequest forgetPasswordRequest);

    @POST("/api/teacher/change-password")
    Observable<Response<ForgetPasswordResponse>> changePasswordTeacher(@Header("x-api-key") String xApiKey, @Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ChangePasswordRequest changePasswordRequest);

    @POST("/api/teacher/forget/token")
    Observable<Response<ForgetPasswordResponse>> generatePasswordResetTokenTeacher(@Header("x-api-key") String xApiKey, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ForgetPasswordRequest forgetPasswordRequest);

    @POST("/api/teacher/forget/reset/{token}")
    Observable<Response<ForgetPasswordResponse>> resetPasswordTeacher(@Header("x-api-key") String xApiKey, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Path("token") String token, @Body ResetPasswordRequest resetPasswordRequest);

    @GET("/api/teacher/contact")
    Observable<Response<ContactUsResponse>> getContactInfoForTeacher(@Header("x-api-key") String xApiKey, @Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept);

    @POST("/api/teacher/session")
    Observable<Response<SessionResponse>> startNewSession(@Header("x-api-key") String xApiKey, @Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body SessionRequest sessionRequest);

    @GET("/api/teacher/session/{session}")
    Observable<Response<SessionResponseData>> getDataforSession(@Header("x-api-key") String xApiKey, @Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Path("session") int sessionId);

    @PUT("/api/teacher/session/{session}")
    Observable<Response<SessionResponseData>> updateTeachingSession(@Header("x-api-key") String xApiKey, @Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Path("session") int sessionId, @Body TeachingSessionRequest teachingSessionRequest);

    @POST("/api/teacher/{session}/quiz")
    Observable<Response<QuizRequest>> createNewQuiz(@Header("x-api-key") String xApiKey, @Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Path("session") int sessionId, @Body QuizRequest quizRequest);

    @GET("/api/teacher/session")
    Observable<Response<SessionHistoryResponse>> getAllSessionsForSpecificSession(@Header("x-api-key") String xApiKey, @Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Query("semester_session") int sessionId);

    @GET("/api/teacher/{session}/quiz")
    Observable<Response<AssignmentHistoryResponse>> listQuizzesHistory(@Header("x-api-key") String xApiKey, @Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Path("session") int sessionId);

    @GET("/api/teacher/quiz/{quiz}/response")
    Observable<Response<QuizHistoryResponse>> getQuizResponses(@Header("x-api-key") String xApiKey, @Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Path("quiz") int quizId);

    @POST("/api/teacher/quiz/{quiz}/response/{quiz_response}")
    Observable<Response<SessionResponseData>> givePointsToQuizResponse(@Header("x-api-key") String xApiKey, @Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Path("quiz") int quizId, @Path("quiz_response") int quizResponseId,@Body GradeRequest gradeRequest);

    //*********** Student endpoints ********************************************************************************************************************************************
    @POST("/api/student/login")
    Observable<Response<StudentResponse>> loginAsStudent(@Header("x-api-key") String xApiKey, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body StudentRequest studentLoginRequest);

    @POST("/api/student/forget")
    Observable<Response<ForgetPasswordResponse>> forgetPasswordStudent(@Header("x-api-key") String xApiKey, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ForgetPasswordRequest forgetPasswordRequest);

    @POST("/api/student/forget/token")
    Observable<Response<ForgetPasswordResponse>> generatePasswordResetTokenStudent(@Header("x-api-key") String xApiKey, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ForgetPasswordRequest forgetPasswordRequest);

    @POST("/api/student/forget/reset/{token}")
    Observable<Response<ForgetPasswordResponse>> resetPasswordStudent(@Header("x-api-key") String xApiKey, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Path("token") String token, @Body ResetPasswordRequest resetPasswordRequest);

    @POST("/api/student/change-password")
    Observable<Response<ForgetPasswordResponse>> changePasswordStudent(@Header("x-api-key") String xApiKey, @Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ChangePasswordRequest changePasswordRequest);

    @GET("/api/student/contact")
    Observable<Response<ContactUsResponse>> getContactInfoForStudent(@Header("x-api-key") String xApiKey, @Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Header("Accept") String accept);

    //*********** Parent endpoints *********
    @POST("/api/parent/login")
    Observable<Response<ParentResponse>> loginAsParent(@Header("x-api-key") String xApiKey, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ParentRequest parentLoginRequest);

    @POST("/api/parent/forget")
    Observable<Response<ForgetPasswordResponse>> forgetPasswordParent(@Header("x-api-key") String xApiKey, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ForgetPasswordRequest forgetPasswordRequest);

    @POST("/api/parent/forget/token")
    Observable<Response<ForgetPasswordResponse>> generatePasswordResetTokenParent(@Header("x-api-key") String xApiKey, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ForgetPasswordRequest forgetPasswordRequest);

    @POST("/api/parent/forget/reset/{token}")
    Observable<Response<ForgetPasswordResponse>> resetPasswordParent(@Header("x-api-key") String xApiKey, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Path("token") String token, @Body ResetPasswordRequest resetPasswordRequest);
}
