package Model.DAO.Impl;

import Model.DAO.CustomerDAO;
import Model.Entity.Customer;
import Model.tools.jsonFileProcessor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    public boolean insert(Customer customer) {
        jsonFileProcessor file = new jsonFileProcessor("./records/Customer.json");
        StringBuffer buffer = file.read();
        ArrayList<Customer> records = new ArrayList<Customer>();
        if (!buffer.toString().equals("")) {
            records = new Gson().fromJson(buffer.toString(), new TypeToken<ArrayList<Customer>>() {}.getType());
            if (records.size()!=0)
                customer.setCustomerID(String.valueOf(Integer.parseInt(records.get(records.size() - 1).getCustomerID())+1));
        }
        else
            customer.setCustomerID("1");
        records.add(customer);
        return file.rewrite(records);
    }

    public boolean delete(String customerID) {
        jsonFileProcessor file = new jsonFileProcessor("./records/Customer.json");
        StringBuffer buffer = file.read();
        if (!buffer.toString().equals("")) {
            ArrayList<Customer> records = new Gson().fromJson(buffer.toString(), new TypeToken<ArrayList<Customer>>(){}.getType());
            for (Customer c : records)
                if (c.getCustomerID().equals(customerID)){
                    records.remove(c);
                    return file.rewrite(records);
                }
        }
        return false;
    }

    public boolean update(Customer customer, String customerID) {
        if (delete(customerID))
            return insert(customer);
        return false;
    }

    public Customer selectByID(String customerID) {
        StringBuffer buffer = new jsonFileProcessor("./records/Customer.json").read();
        if (!buffer.toString().equals("")) {
            ArrayList<Customer> records = new Gson().fromJson(buffer.toString(), new TypeToken<ArrayList<Customer>>(){}.getType());
            for (Customer c : records)
                if (c.getCustomerID().equals(customerID))
                    return c;
        }
        return null;
    }
}
