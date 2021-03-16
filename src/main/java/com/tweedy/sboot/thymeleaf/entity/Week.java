package com.tweedy.sboot.thymeleaf.entity;

import java.util.ArrayList;

import java.util.List;

public class Week {
	List<Day> weekDays;

	public Week() {
		weekDays = new ArrayList<Day>();
	}
	
	public void add(Day day) {
		weekDays.add(day);
	}
	
	public String toString() {
		
		String rtn="";
		
		for(Day d: weekDays )
			rtn += d.getDayOfMonth() + " ";
		
		return rtn;
		
	}
	
	public int size() {
		return weekDays.size();
	}

	public List<Day> getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(List<Day> weekDays) {
		this.weekDays = weekDays;
	}
	
}
