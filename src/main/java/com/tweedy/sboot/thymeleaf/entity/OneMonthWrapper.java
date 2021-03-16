package com.tweedy.sboot.thymeleaf.entity;

import java.util.List;
/**
 * this class is used to pass params
 * to One Month Calendar page 
 * @author mikeba
 *
 */
public class OneMonthWrapper {
	private int year;
	private int month;
	List<Day> week1;
	List<Day> week2;
	List<Day> week3;
	List<Day> week4;
	List<Day> week5;
	List<Day> week6;
	List<String> header;
	
	public OneMonthWrapper(int year, int month, List<Day> week1, List<Day> week2, List<Day> week3, List<Day> week4,
			List<Day> week5, List<Day> week6, List<String> header) {
		
		this.year = year;
		this.month = month;
		this.week1 = week1;
		this.week2 = week2;
		this.week3 = week3;
		this.week4 = week4;
		this.week5 = week5;
		this.week6 = week6;
		this.header = header;
	}
	public List<String> getHeader() {
		return header;
	}
	public void setHeader(List<String> header) {
		this.header = header;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public List<Day> getWeek1() {
		return week1;
	}
	public void setWeek1(List<Day> week1) {
		this.week1 = week1;
	}
	public List<Day> getWeek2() {
		return week2;
	}
	public void setWeek2(List<Day> week2) {
		this.week2 = week2;
	}
	public List<Day> getWeek3() {
		return week3;
	}
	public void setWeek3(List<Day> week3) {
		this.week3 = week3;
	}
	public List<Day> getWeek4() {
		return week4;
	}
	public void setWeek4(List<Day> week4) {
		this.week4 = week4;
	}
	public List<Day> getWeek5() {
		return week5;
	}
	public void setWeek5(List<Day> week5) {
		this.week5 = week5;
	}
	public List<Day> getWeek6() {
		return week6;
	}
	public void setWeek6(List<Day> week6) {
		this.week6 = week6;
	}
	
	
}
