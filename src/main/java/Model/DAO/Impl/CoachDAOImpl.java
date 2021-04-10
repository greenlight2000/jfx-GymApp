package Model.DAO.Impl;

import Model.DAO.CoachDAO;
import Model.Entity.Coach;
import Model.tools.jsonFileProcessor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class CoachDAOImpl implements CoachDAO {
    //dao会维护entity的id属性
    public boolean insert(Coach coach) {
        jsonFileProcessor file = new jsonFileProcessor("./records/Coach.json");
        StringBuffer buffer = file.read();
        ArrayList<Coach> records = new ArrayList<Coach>();
        if (!buffer.toString().equals("")) {
            records = new Gson().fromJson(buffer.toString(), new TypeToken<ArrayList<Coach>>() {}.getType());
            if (records.size()!=0)
                coach.setCoachID(String.valueOf(Integer.parseInt(records.get(records.size() - 1).getCoachID())+1));
            else
                coach.setCoachID("1");
        }
        else
            coach.setCoachID("1");
        records.add(coach);
        return file.rewrite(records);
    }

    public boolean delete(String coachID) {
        jsonFileProcessor file = new jsonFileProcessor("./records/Coach.json");
        StringBuffer buffer = file.read();
        if (!buffer.toString().equals("")) {
            ArrayList<Coach> records = new Gson().fromJson(buffer.toString(), new TypeToken<ArrayList<Coach>>(){}.getType());
            for (Coach c : records)
                if (c.getCoachID().equals(coachID)){
                    records.remove(c);
                    return file.rewrite(records);
                }
        }
        return false;
    }

    public boolean update(Coach coach, String coachID) {
        if (delete(coachID))
            return insert(coach);
        return false;
    }

    public Coach selectByID(String coachID) {
        StringBuffer buffer = new jsonFileProcessor("./records/Coach.json").read();
        if (!buffer.toString().equals("")) {
            ArrayList<Coach> records = new Gson().fromJson(buffer.toString(), new TypeToken<ArrayList<Coach>>(){}.getType());
            for (Coach c : records)
                if (c.getCoachID().equals(coachID))
                    return c;
        }
        return null;
    }
}
