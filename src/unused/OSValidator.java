package unused;

public final class OSValidator {

    private static String osName = System.getProperty("os.name").toLowerCase();

    public static boolean isWindows(){
        return osName.contains("win");
    }

    public static boolean isMac(){
        return osName.contains("mac");
    }

    public static boolean isUnix(){
        return osName.contains("nix") || osName.contains("nux") || osName.contains("aix");
    }

    public static String getOS(){
        if(isWindows()){
            return "win";
        }
        else if(isMac()){
            return "mac";
        }
        else if(isUnix()){
            return "unix";
        }
        else{
            return "error";
        }
    }
}
