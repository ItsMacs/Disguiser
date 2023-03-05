package eu.maxpi.fiverr.disguiser.utils;

import com.google.gson.Gson;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.entity.Player;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class URLUtils {

    public static String getUUID(String name){
        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL(String.format("https://api.mojang.com/users/profiles/minecraft/" + name)).openConnection();
            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                String reply = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
                String uuid = reply.split("\"id\":\"")[1].split("\"")[0];
                return uuid;
            } else {
                System.out.println("Connection could not be opened (Response code " + connection.getResponseCode() + ", " + connection.getResponseMessage() + ")");
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean setSkin(Player p, GameProfile profile, String uuid) {
        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false").openConnection();
            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                StringBuilder reply = new StringBuilder();
                new BufferedReader(new InputStreamReader(connection.getInputStream())).lines().forEach(reply::append);
                SkinURLObject obj = new Gson().fromJson(reply.toString(), SkinURLObject.class);
                String skin = obj.properties[0].value;
                String signature = obj.properties[0].signature;
                profile.getProperties().put("textures", new Property("textures", skin, signature));
                NMSHelper.skins.put(p, obj);
                return true;
            } else {
                System.out.println("Connection could not be opened (Response code " + connection.getResponseCode() + ", " + connection.getResponseMessage() + ")");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
