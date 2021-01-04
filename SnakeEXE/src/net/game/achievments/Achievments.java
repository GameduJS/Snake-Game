package net.game.achievments;

import java.util.List;

public enum Achievments {

	
	EAT10("Eat 10 Apples", 1);
	
	
	private static List<Achievments> completedAchievments;
	private static List<Achievments> uncompletedAchievments;
	private String achievmentName;
	private int index;
	
	private Achievments(String name, int index) {
		this.achievmentName = name;
		this.index = index;
	}
	
	public String getAchievmentName() {
		return this.achievmentName;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	//Later on important for Configuration
	public static Achievments getAchievmentWithIndex(int index) {
		for(Achievments achievments : values()) {
			if(achievments.getIndex() == index) {
				return achievments;
			}
		}
		return null;
	}
	
	public static void setAchievmentCompleted(int index) {
		completedAchievments.add(getAchievmentWithIndex(index));
	}
	
	public static void setAchievmentCompleted(Achievments achievment) {
		completedAchievments.add(achievment);
	}
	
	public static List<Achievments> getCompletedAchievments(){
		return completedAchievments;
	}
	
	public static List<Achievments> getUncompletedAchievments(){
		for(Achievments achievment : values()){
			if(!completedAchievments.contains(achievment)) {
				uncompletedAchievments.add(achievment);
			}
		}
		return uncompletedAchievments;
	}
}
