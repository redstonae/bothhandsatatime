package cu.redstonae.bothhandsatatime.config;

import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// note for anyone wanting to use this. Don't.

public class config {
    static String configName = "bothhandsatatime";
    static String roseFile = FabricLoader.getInstance().getConfigDir()+"/"+configName+".rose";
    // "why the FUCK are you even using a non-standard file type redstonae?" you might ask. well, you see, im stupid :3
    // ^ i am double stupid, because i now have owo-lib as a dependency and i could use that as my config, but i like my piece of shit code enough to keep it
    static String defaultConfig = "bothHand:true";

    public static String[] configs = new String[69];
    // give me a break, alright. it was between "69" and signed 32 bit int limit, and i chose the funny option
    // just realised that the way i do configs may lead to crashes, due to trying to reach an out of bounds address. egh, to fix when it actually becomes a problem/someone is cheeky enough to report it (i see you...)

    public static void setupConfig(){
        try {
            File config = new File(roseFile);
            if (config.createNewFile()) {
                try{
                    FileWriter setup = new FileWriter(roseFile);
                    setup.write(defaultConfig);
                    setup.close();
                } catch (IOException e) {throw new RuntimeException(e);}
            }
        } catch (IOException e) {throw new RuntimeException(e);}
        getConfig();
    }

    public static void getConfig(){
        File config = new File(roseFile);
        try (Scanner scanner = new Scanner(config)){
            int i = 0;
            while (scanner.hasNextLine()) {
                configs[i] = scanner.nextLine();
                i += 1;
            }
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    public static void setConfig(String setting, String value){
        File config = new File(roseFile);
        try (Scanner scanner = new Scanner(config)){
            String newConfig = "";
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] match = line.split(":");
                if(match[0].equals(setting)){
                    newConfig += match[0]+":"+value+"\n";
                } else{
                    newConfig += match[0]+":"+match[1]+"\n";
                }
            }
            try{
                FileWriter change = new FileWriter(roseFile);
                change.write(newConfig);
                change.close();
            } catch (IOException e) {throw new RuntimeException(e);}
        } catch (IOException e) {throw new RuntimeException(e);}
        getConfig();
    }

    // sam fakt tego że kod zwraca wartości boolean-owe, liczbowe i inne jako string jest zjebany. powinnam to naprawić, ale jako iż używam własnego rozszerzenia pliku, nie muszę się martwić o "standardy" czy inne pierdoły, więc zostaje tak jak jest
    // ^ i am not translating that into english
    public static String returnConfig(String setting){
        int i = 0;
        while(i< configs.length){
            String[] match = configs[i].split(":");
            i += 1;
            if(match[0].equals(setting)) {
                return match[1];
            }
        }
        return null;
    }
}

