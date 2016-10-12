//package Repositories;
//
//import Entities.User;
//import Interfaces.UserRepository;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.List;
//
///**
// * Created by ilmaz on 07.10.16.
// */
//public class jsonUserRepository implements UserRepository{
//    private final String NAME_OF_SAVE = "saves.saves";
//    private JSONObject saves;
//    private JSONArray users;
//
//    public jsonUserRepository() {
//       try{
//           JSONParser parser = new JSONParser();
//           Object obj = parser.parse(new FileReader(NAME_OF_SAVE));
//           saves = (JSONObject) obj;
//           users = (JSONArray) saves.get("users");
//       } catch (ParseException e) {
//           e.printStackTrace();
//       } catch (FileNotFoundException e) {
//           saves = new JSONObject();
//           users = new JSONArray();
//           saves.put("users", users);
//           try(FileWriter fileWriter = new FileWriter(NAME_OF_SAVE)) {
//               fileWriter.write(saves.toJSONString());
//           } catch (IOException e1) {
//               e1.printStackTrace();
//           }
//       } catch (IOException e) {
//           e.printStackTrace();
//       }
//    }
//
//    @Override
//    public boolean addUser(User user) {
//        if(users.contains(user)){
//            return false;
//        } else {
//            users.add(user);
//        }
//        return true;
//    }
//
//    @Override
//    public User findByEmail(String email, String password) {
//        return null;
//    }
//
//    @Override
//    public List<User> getUsersList() {
//        return null;
//    }
//
//    @Override
//    public boolean containsUser(User user) {
//        return false;
//    }
//}
