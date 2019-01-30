package dp.schoolandroid.application;

import java.util.ArrayList;
import java.util.List;

import dp.schoolandroid.di.component.DaggerNetworkComponent;
import dp.schoolandroid.di.component.NetworkComponent;
import dp.schoolandroid.di.modules.AppModule;
import dp.schoolandroid.service.model.global.AbsentStudentModel;
import dp.schoolandroid.service.model.request.TeachingSessionRequest;

public class MyApp extends android.app.Application {

    private NetworkComponent daggerNetworkComponent;
    private List<Integer> absentStudentIds = new ArrayList<>();
    private int studentOfDay;
    private AbsentStudentModel absentStudentModel;
    private TeachingSessionRequest teachingSessionRequests = new TeachingSessionRequest();
    private List<AbsentStudentModel> absentStudentModels;

    @Override
    public void onCreate() {
        super.onCreate();
        absentStudentModels = new ArrayList<>();
        initializeDaggerNetworkComponent();
    }

    public void initializeDaggerNetworkComponent() {
        daggerNetworkComponent = DaggerNetworkComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public NetworkComponent getDaggerNetworkComponent() {
        return daggerNetworkComponent;
    }

    public void addAbsentStudent(Integer absentStudentId) {
        if (!absentStudentIds.contains(absentStudentId)) {
            absentStudentIds.add(absentStudentId);
            addAbsentStudentModel(absentStudentId);
        }
    }

    public void addAbsentStudentModel(Integer absentStudentId) {
        AbsentStudentModel absentStudentModel = new AbsentStudentModel();
        absentStudentModel.setAbsentStudentId(absentStudentId);
        this.absentStudentModels.add(absentStudentModel);
    }

    public void addPermissionForAbsentStudent(Integer absentStudentId, boolean permission) {
        for (int i = 0; i < absentStudentModels.size(); i++) {
            if (absentStudentModels.get(i).getAbsentStudentId() == absentStudentId) {
                absentStudentModels.get(i).setHasPermission(permission);
            }
        }
    }


    public boolean getPermissionForAbsentStudent(Integer absentStudentId) {
        boolean hasPermission = false;
        for (int i = 0; i < absentStudentModels.size(); i++) {
            if (absentStudentModels.get(i).getAbsentStudentId() == absentStudentId) {
                hasPermission = absentStudentModels.get(i).isHasPermission();
            }
        }
        return hasPermission;
    }

    public void removeAbsentStudent(Integer absentStudentId) {
        for (int i = 0; i < absentStudentModels.size(); i++) {
            if (absentStudentModels.get(i).getAbsentStudentId() == absentStudentId) {
                absentStudentModels.remove(i);
                absentStudentIds.remove(absentStudentId);
            }
        }
    }

    public TeachingSessionRequest getTeachingSessionRequest() {
        teachingSessionRequests.setAbsentStudentModel(absentStudentModels);
        if (getStudentOfDay() !=0){
            teachingSessionRequests.setStudentOfDayId(String.valueOf(getStudentOfDay()));
        }else {
            teachingSessionRequests.setStudentOfDayId(null);
        }
        return teachingSessionRequests;
    }

    public List<Integer> getAbsentStudentIds() {
        return absentStudentIds;
    }

    public void clearAbsentStudents() {
        absentStudentIds.clear();
    }

    public int getStudentOfDay() {
        return this.studentOfDay;
    }

    public void setStudentOfDay(int studentId) {
        this.studentOfDay = studentId;
    }

    public void removeStudentOfDay(Integer studentId) {
        if (studentOfDay == studentId) {
            this.setStudentOfDay(0);
        }
    }

    public void clearStudentOfDay() {
        this.setStudentOfDay(0);
    }


    public List<AbsentStudentModel> getAbsentStudentModels() {
        return absentStudentModels;
    }
}
