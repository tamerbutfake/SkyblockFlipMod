package profit_calculator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class item {
    public  String item_name = "";
    public  Map<String, Integer> enchants = new HashMap<>();
    public  String reforge = "";
    public  int star_count = 0;
    public  int master_star_count = 0;
    public  boolean rarity_upgrade = false;
    public  int hot_count = 0;
    public  int fuming_count = 0;
    public  String uuid = "";
    public  int cost = 0;
}
