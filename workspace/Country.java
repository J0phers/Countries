/*
Author: Jophie Chan
Date: 1/13/25
Description: This class adds the private instance vars, constructors, getters, and setters. Ends with a toString()
*/
public class Country
{
  // add private instance variables for the name, capital, language, and image file.
  private String name;  //Current name of the country
  private String capital; //Current capital of the country
  private String language;  //Current language of the country
  private String imageFile; //Current image(file) of the country
  public Object String;

  // add constructors
  public Country(String name, String capital, String language, String imageFile){
    this.name = name; //Sets the initial name of the country
    this.capital = capital; //Sets the initial capital of the country
    this.language = language; //Sets the initial language of the country
    this.imageFile = imageFile; //Sets the initial image(file) of the country
  }
  
  // Write accessor/get methods for each instance variable that returns it.
  public String getName() {
    return name;
  }

  public String getCapital() {
    return capital;
  }

  public String getLanguage() {
    return language;
  }

  public String getImageFile() {
    return imageFile;
  }

  public void setName(String name){
    this.name = name;
  }

  public void setCaptial(String capital) {
    this.capital = capital;
  }

  public void setLanguage (String language) {
    this.language = language;
  }
  
  // Write a toString() method that returns a concatenated String of 3 of the instance variables in a sentence like "..'s capital is .. and its primary language is ..."
  public String toString() {
    return "Country Name: " + name + " | " + "\nCapital: " + capital + " | " + "\nLanguage: " + language +  " | " + "\nImage File: " + imageFile;
  }
  
}