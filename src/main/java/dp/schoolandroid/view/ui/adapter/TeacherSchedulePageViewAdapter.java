package dp.schoolandroid.view.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.google.gson.Gson;

import java.util.ArrayList;

import dp.schoolandroid.service.model.global.SectionTimeModel;
import dp.schoolandroid.service.model.global.TeacherSchedule;
import dp.schoolandroid.view.ui.fragment.DayFragment;

public class TeacherSchedulePageViewAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    private TeacherSchedule weekData;
    private static ArrayList<SectionTimeModel> dayData = new ArrayList<>();

    public TeacherSchedulePageViewAdapter(FragmentManager fm , TeacherSchedule weekData) {
        super(fm);
        this.weekData = weekData;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                dayData = weekData.getSat();
                break;
            case 1:
                dayData = weekData.getSun();
                break;
            case 2:
                dayData = weekData.getMon();
                break;
            case 3:
                dayData = weekData.getTue();
                break;
            case 4:
                dayData = weekData.getWed();
                break;
            case 5:
                dayData = weekData.getThu();
                break;
            case 6:
                dayData = weekData.getFri();
                break;
        }
        return new DayFragment(dayData);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        return tabTitles[position];
    }
}
