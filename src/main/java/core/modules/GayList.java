package core.modules;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class GayList {

    private static String path = "src\\main\\resources\\gaygameusers";

    private List<Integer> gayList = readFile(path);
    private static LinkedHashMap<Integer, Integer> chatsList = new LinkedHashMap<Integer, Integer>();
    private static List<Integer> waitingChatList = new ArrayList<>();
    private static DataBaseManager dataBaseManager = new DataBaseManager();

    private  ArrayList<Integer> readFile(String path) {
        ArrayList<Integer> list = new ArrayList<>();
        File file = new File(path);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            if (line != null) {
                while (line != null) {
                    list.add(Integer.valueOf(line));
                    line = bufferedReader.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
        //return dataBaseManager.getGayBase();
    }

    private void writeToFile(String id, String path) {
        File file = new File(path);
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            StringBuilder sb = new StringBuilder();
            sb.append(id);
            sb.append("\n");
            bufferedWriter.write(sb.toString());
            bufferedWriter.close();
            //dataBaseManager.addToGayBase(Integer.parseInt(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addGay(int id) {
        if (!gayList.contains(id)) {
            gayList.add(id);
            writeToFile(String.valueOf(id), path);
        }
    }

    public  Integer getGay(int indx) {
        return gayList.get(indx);
    }

    public  int size() {
        return gayList.size();
    }

    public  List<Integer> getGayList() {
        return gayList;
    }

    public void printUserNames(){
        StringBuilder sb = new StringBuilder();
        for(int gay: gayList){
            sb.append(MyJSONParser.getUserRealNameById(gay+"")).append("\n");
        }
        System.out.println(sb);
    }

    public ArrayList<String> getUserNames(){
        ArrayList<String> list = new ArrayList<>();
        for(int gay: gayList){
            list.add(MyJSONParser.getUserRealNameById(gay+""));
        }
        return list;
    }

    public static void addUserToWaitingChatList(Integer id){
       // waitingChatList.add(220839826); //Sandler
        if (!waitingChatList.contains(id)) waitingChatList.add(id);
    }

    public static boolean userInChat(int id){
        return chatsList.containsKey(id) || chatsList.containsValue(id);
    }

    public static ArrayList<Integer> getWaitingChatList(){
        return (ArrayList<Integer>) waitingChatList;
    }

    public static void addPairToChat(int thisId, int friendId){
        chatsList.put(thisId, friendId);
        waitingChatList.remove((Integer) thisId);
        waitingChatList.remove((Integer) friendId);
    }

    public static int getValuePairFromChat(int keyId){
        return chatsList.get(keyId);
    }

    public static int getKeyPairFromChat(int valueId){
        for(int id: chatsList.keySet()){
            if (chatsList.get(id) == valueId) return id;
        }
        throw new IllegalArgumentException("чаты пусты");
    }

    public static LinkedHashMap<Integer, Integer> getChatsList(){
        return chatsList;
    }

    public static void exitFromChat(int id, int friendId){
        //int friendId = getValuePairFromChat(id);
        chatsList.remove(id, friendId);
    }
}
