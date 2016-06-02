package st.cs.uni.saarland.de;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import soot.jimple.Stmt;

public class MudflowHelper {
	private static List<String> adLibs;
	private static Set<Stmt> collectedSources;
	private static Set<Stmt> collectedSinks;
	private static String packagePrefix = "";
	
	public static List<String> getAdLibs(){
		if(adLibs == null){
			loadAdLibs();
		}
		return adLibs;
	}
	
	private static void loadAdLibs(){
		adLibs = new ArrayList<>();
		File f = new File("adlibs.txt");
		if(!f.exists()){
			return;
		}
		try(BufferedReader br = new BufferedReader(new FileReader("adlibs.txt"))) {
			for(String line; (line = br.readLine()) != null; ) {
				if(line.trim().length() > 0){
					adLibs.add(line.trim());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setCollectedSources(Set<Stmt> sources){
		collectedSources = new HashSet<>();
		collectedSources.addAll(sources);
	}
	public static void setCollectedSinks(Set<Stmt> sinks){
		collectedSinks = new HashSet<>();
		collectedSinks.addAll(sinks);
	}
	
	public static Set<Stmt> getCollectedSources(){
		return collectedSources;
	}
	
	public static Set<Stmt> getCollectedSinks(){
		return collectedSinks;
	}
	
	public static void setPackagePrefix(String packageName){
		String[] splittedPackageName = packageName.split("\\.");
		if(splittedPackageName.length == 1){
			packagePrefix = packageName;
		}
		else {
			packagePrefix = splittedPackageName[0] + "." + splittedPackageName[1];
		}
	}
	
	public static boolean isClassInPackage(String className){
		return className.startsWith(packagePrefix);
	}
	
}
