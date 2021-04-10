package Model.DAO.Impl;

import Model.DAO.VideoDAO;
import Model.Entity.Video;
import Model.tools.jsonFileProcessor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class VideoDAOImpl implements VideoDAO {

    public boolean insert(Video video) {
        jsonFileProcessor file = new jsonFileProcessor("./records/Video.json");
        StringBuffer buffer = file.read();
        ArrayList<Video> records = new ArrayList<Video>();
        if (!buffer.toString().equals("")) {
            records = new Gson().fromJson(buffer.toString(), new TypeToken<ArrayList<Video>>() {}.getType());
        }
        records.add(video);
        return file.rewrite(records);
    }

    public boolean delete(String name) {
        jsonFileProcessor file = new jsonFileProcessor("./records/Video.json");
        StringBuffer buffer = file.read();
        if (!buffer.toString().equals("")) {
            ArrayList<Video> records = new Gson().fromJson(buffer.toString(), new TypeToken<ArrayList<Video>>(){}.getType());
            for (Video v : records)
                if (v.getName().equals(name)){
                    records.remove(v);
                    return file.rewrite(records);
                }
        }
        return false;
    }

    public boolean update(Video video, String name) {
        if (delete(name))
            return insert(video);
        return false;
    }

    public Video selectByName(String name) {
        StringBuffer buffer = new jsonFileProcessor("./records/Video.json").read();
        if (!buffer.toString().equals("")) {
            ArrayList<Video> records = new Gson().fromJson(buffer.toString(), new TypeToken<ArrayList<Video>>(){}.getType());
            for (Video v : records)
                if (v.getName().equals(name))
                    return v;
        }
        return null;
    }
}
