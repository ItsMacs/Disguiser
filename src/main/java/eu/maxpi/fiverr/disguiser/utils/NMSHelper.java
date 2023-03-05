package eu.maxpi.fiverr.disguiser.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import eu.maxpi.fiverr.disguiser.Disguiser;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo;
import net.minecraft.network.protocol.game.PacketPlayOutRespawn;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.network.PlayerConnection;
import net.minecraft.world.level.EnumGamemode;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftWolf;
import org.bukkit.entity.Player;


import java.lang.reflect.Field;
import java.util.HashMap;

public class NMSHelper {

    public static HashMap<Player, String> fakeNames = new HashMap<>();
    public static HashMap<Player, SkinURLObject> skins = new HashMap<>();

    public static void setName(Player p, String name) throws IllegalAccessException, NoSuchFieldException {
        String realname = p.getName();
        fakeNames.put(p, name);

        EntityPlayer ep = ((CraftPlayer) p).getHandle();
        GameProfile playerProfile = ep.fq();
        Field ff = playerProfile.getClass().getDeclaredField("name");
        ff.setAccessible(true);
        ff.set(playerProfile, name);

        if(skins.containsKey(p)){
            playerProfile.getProperties().removeAll("textures");
            playerProfile.getProperties().put("textures", new Property("textures", skins.get(p).properties[0].value, skins.get(p).properties[0].signature));
        }

        PacketPlayOutPlayerInfo destroyInfo = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.b, ep);
        PacketPlayOutEntityDestroy destroyEntity = new PacketPlayOutEntityDestroy(ep.aH);
        PacketPlayOutNamedEntitySpawn spawnEntity = new PacketPlayOutNamedEntitySpawn(ep);
        PacketPlayOutPlayerInfo spawnInfo = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, ep);
        PacketPlayOutRespawn respawn = new PacketPlayOutRespawn(((CraftWorld)p.getWorld()).getHandle().Z(), ((CraftWorld)p.getWorld()).getHandle().aa(), 0L, EnumGamemode.valueOf(p.getGameMode().name()), null, false, false, false);

        for(int i = 0; i < 3; i++){
            Bukkit.getScheduler().scheduleSyncDelayedTask(Disguiser.getInstance(), () -> {
                for(Player all : Bukkit.getOnlinePlayers()) {
                    if(all == p) continue;
                    PlayerConnection connection = ((CraftPlayer) all).getHandle().b;

                    connection.a(destroyInfo);
                    connection.a(destroyEntity);
                    connection.a(spawnEntity);
                    connection.a(spawnInfo);
                    //connection.a(respawn);

                    all.hidePlayer(Disguiser.getInstance(), p);
                    all.showPlayer(Disguiser.getInstance(), p);
                }
            }, i);
        }

        p.hidePlayer(Disguiser.getInstance(), p);
        p.showPlayer(Disguiser.getInstance(), p);

        p.setPlayerListName(realname);
        p.setDisplayName(realname);
        p.setCustomName(realname);

        Bukkit.getScheduler().scheduleSyncDelayedTask(Disguiser.getInstance(), () -> {
            try {
                setProfileName(p, realname);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }, 5L);
    }

    public static void setName(Player p, String name, Player target) throws IllegalAccessException, NoSuchFieldException {
        String realname = p.getName();

        EntityPlayer ep = ((CraftPlayer) p).getHandle();
        GameProfile playerProfile = ep.fq();
        Field ff = playerProfile.getClass().getDeclaredField("name");
        ff.setAccessible(true);
        ff.set(playerProfile, name);

        PacketPlayOutPlayerInfo destroyInfo = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.b, ep);
        PacketPlayOutEntityDestroy destroyEntity = new PacketPlayOutEntityDestroy(ep.aH);
        PacketPlayOutNamedEntitySpawn spawnEntity = new PacketPlayOutNamedEntitySpawn(ep);
        PacketPlayOutPlayerInfo spawnInfo = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, ep);

        for(int i = 0; i < 3; i++){
            Bukkit.getScheduler().scheduleSyncDelayedTask(Disguiser.getInstance(), () -> {
                PlayerConnection connection = ((CraftPlayer) target).getHandle().b;

                connection.a(destroyInfo);
                connection.a(destroyEntity);
                connection.a(spawnEntity);
                connection.a(spawnInfo);

                target.hidePlayer(Disguiser.getInstance(), p);
                target.showPlayer(Disguiser.getInstance(), p);
            }, i);
        }

        p.setPlayerListName(realname);
        p.setDisplayName(realname);
        p.setCustomName(realname);

        Bukkit.getScheduler().scheduleSyncDelayedTask(Disguiser.getInstance(), () -> {
            try {
                setProfileName(p, realname);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }, 5L);
    }

    public static void setSkin(Player p, String name) throws NoSuchFieldException, IllegalAccessException {
        String uuid = URLUtils.getUUID(name);
        if(uuid.length() == 0) {
            p.sendMessage(PluginLoader.lang.get("no-skin-available"));
            return;
        }

        EntityPlayer ep = ((CraftPlayer) p).getHandle();
        GameProfile playerProfile = ep.fq();

        if(!URLUtils.setSkin(p, playerProfile, uuid)){
            p.sendMessage(PluginLoader.lang.get("no-skin-available"));
            return;
        }
        setName(p, fakeNames.getOrDefault(p, p.getName()));
    }

    public static void setProfileName(Player p, String name) throws IllegalAccessException, NoSuchFieldException {
        EntityPlayer ep = ((CraftPlayer) p).getHandle();
        GameProfile playerProfile = ep.fq();
        Field ff = playerProfile.getClass().getDeclaredField("name");
        ff.setAccessible(true);
        ff.set(playerProfile, name);
    }

}
