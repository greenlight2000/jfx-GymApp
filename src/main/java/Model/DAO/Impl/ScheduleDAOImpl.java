package Model.DAO.Impl;

import Model.DAO.ScheduleDAO;
import Model.Entity.Schedule;
import Model.tools.jsonFileProcessor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ScheduleDAOImpl implements ScheduleDAO{

    public static void main(String[] args) {
        ScheduleDAOImpl s = new ScheduleDAOImpl();
        Schedule schedule1 = new Schedule("","123","123","123","123");
        s.insert(schedule1);
    }

    public boolean insert(Schedule schedule){
        jsonFileProcessor file = new jsonFileProcessor("./records/Schedule.json");
        StringBuffer buffer = file.read();
        ArrayList<Schedule> records = new ArrayList<Schedule>();
        if (!buffer.toString().equals("")) {
            records = new Gson().fromJson(buffer.toString(), new TypeToken<ArrayList<Schedule>>() {}.getType());
            if (records.size()!=0)
                schedule.setScheduleID(String.valueOf(Integer.parseInt(records.get(records.size() - 1).getScheduleID())+1));
        }
        else
            schedule.setScheduleID("1");
        records.add(schedule);
        return file.rewrite(records);
    }

    public Schedule selectByID(String scheduleID){
        StringBuffer buffer = new jsonFileProcessor("./records/Schedule.json").read();
        if (!buffer.toString().equals("")) {
            ArrayList<Schedule> records = new Gson().fromJson(buffer.toString(), new TypeToken<ArrayList<Schedule>>(){}.getType());
            for (Schedule s : records)
                if (s.getScheduleID().equals(scheduleID))
                    return s;
        }
        return null;
    }

    public boolean delete(String scheduleID){
        jsonFileProcessor file = new jsonFileProcessor("./records/Schedule.json");
        StringBuffer buffer = file.read();
        if (!buffer.toString().equals("")) {
            ArrayList<Schedule> records = new Gson().fromJson(buffer.toString(), new TypeToken<ArrayList<Schedule>>(){}.getType());
            for (Schedule s : records)
                if (s.getScheduleID().equals(scheduleID)){
                    records.remove(s);
                    return file.rewrite(records);
                }
        }
        return false;
    }

    public boolean update(Schedule schedule, String scheduleID){
        if (delete(scheduleID))
            return insert(schedule);
        return false;
    }
}