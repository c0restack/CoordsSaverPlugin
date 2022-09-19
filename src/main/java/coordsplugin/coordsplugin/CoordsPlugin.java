package coordsplugin.coordsplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public final class CoordsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){


        if (command.getName().equalsIgnoreCase("missä")){
            if (sender instanceof Player){
                Player p =(Player) sender;
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader("C:/Users/eeron/Desktop/papertestserver/plugins/logi.txt"));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                try {
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();

                    while (line != null) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
                    }
                    String everything = sb.toString();
                    //System.out.println(everything);
                    p.sendMessage(ChatColor.BLUE + everything);
                } catch (IOException e) {
                throw new RuntimeException(e);
                } finally {
                    try {
                        br.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }


            }

        }
        if (command.getName().equalsIgnoreCase("tässä")){
            if (sender instanceof Player){
                Player p =(Player) sender;

                String name = p.getName();

                Location sijainti = p.getLocation();
                double x = sijainti.getX();
                double y = sijainti.getY();
                double z = sijainti.getZ();
                int xInt = (int)x;
                int yInt = (int)y;
                int zInt = (int)z;

                String coordsString = String.format("%d,%d,%d",xInt, yInt, zInt);

                if (args.length == 0){
                    p.sendMessage(ChatColor.RED + "Pitää laittaa et mikä mesta son siihen kommandin perään: /tässä <mikäpaikka>");
                } else if (args.length > 1) {
                    p.sendMessage(ChatColor.RED + "Kuvaile yhdellä sanalla mikä paikka koska työmaa käyttää taulukoita");
                } else {
                    String viesti = args[0];
                    logaa(viesti, coordsString, name);
                    p.sendMessage(ChatColor.GOLD +  "Tallennettiin: " + viesti + " coordeille " + coordsString);

                }


            }

        }

        return true;
    }
    private void logaa(String tag, String coords, String name) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/C:/Users//eeron//Desktop//papertestserver//plugins//logi.txt", true)));
            out.println(name + ": " + tag + " @: " + coords);
            out.close();
        } catch (IOException e) {

        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
