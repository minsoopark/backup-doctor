package sgen.backup.dr.models;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String id;
    private String name;
    private String gender;
    private int birth;
    private String phone;
    private int height;
    private int weight;
    private String blood;
    private String smoking;
    private String drinking;
    private String disease;
    private String medicine;
    private String allergy;

    public User(String id, String name, String gender, int birth, String phone, int height, int weight, String blood, String smoking, String drinking, String disease, String medicine, String allergy) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.phone = phone;
        this.height = height;
        this.weight = weight;
        this.blood = blood;
        this.smoking = smoking;
        this.drinking = drinking;
        this.disease = disease;
        this.medicine = medicine;
        this.allergy = allergy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getSmoking() {
        return smoking;
    }

    public void setSmoking(String smoking) {
        this.smoking = smoking;
    }

    public String getDrinking() {
        return drinking;
    }

    public void setDrinking(String drinking) {
        this.drinking = drinking;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public Map<String, String> getMapFromUser() {
        Map<String, String> map = new HashMap<String, String>();

        map.put("id", getId());
        map.put("name", getName());
        map.put("gender", getGender());
        map.put("birth", getBirth() + "");
        map.put("phone", getPhone());
        map.put("height", getHeight() + "");
        map.put("weight", getWeight() + "");
        map.put("blood", getBlood());
        map.put("smoking", getSmoking());
        map.put("drinking", getDrinking());
        map.put("disease", getDisease());
        map.put("medicine", getMedicine());
        map.put("allergy", getAllergy());

        return map;
    }
}
