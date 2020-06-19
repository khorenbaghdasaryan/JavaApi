package com.company;//1. Download JSON library(http://www.java2s.com/Code/Jar/j/Downloadjavajsonjar.htm).
//2. To import library go to build path, Libraries, Add External JARs(Add your jar file).


//////////////////////////////
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class Getjson {
    String urlString;// = "https://api.nytimes.com/svc/books/v3/lists/";
    String jsonՕverview;
    String list_data;
    String list_required;
    String part1_url;// = ".json?";
    String offset;
    String part2_url;// = "&api-key=";
    String your_key;
    String best_sellers;
    String age_group;
    String author;
    String contributor;
    String price;
    String publisher;
    String published_date;
    String jsonName;
    String fullUrl;
    URL url;

    public Getjson(String fullUrl) {
        this.fullUrl = fullUrl;
        System.out.println(fullUrl);
    }

    public Getjson(String urlString, String list_data, String list_required,String part1_url,
                   String offset, String part2_url, String your_key) {
        this.urlString = urlString;
        this.list_data = list_data;
        this.list_required = list_required;
        this.part1_url = part1_url;
        this.offset = offset;
        this.part2_url = part2_url;
        this.your_key = your_key;
        this.fullUrl = urlString + list_data + list_required + part1_url + offset + part2_url + your_key;
        System.out.println(fullUrl);
    }

    public Getjson(String urlString, String best_sellers, String age_group, String author,
                   String contributor, String price, String publisher, String part3_url, String your_key){
        this.urlString = urlString;
        this.best_sellers = best_sellers;
        this.age_group = age_group;
        this.author = author;
        this.contributor = contributor;
        this.price = price;
        this.publisher = publisher;
        this.part2_url = part3_url;
        this.your_key = your_key;
        this.fullUrl = urlString + best_sellers + age_group + author + contributor + price + publisher + part3_url + your_key;
        System.out.println(fullUrl);

    }

    public Getjson(String urlString, String jsonName, String part2_url, String your_key) {
        this.urlString = urlString;
        this.jsonName = jsonName;
        this.part2_url = part2_url;
        this.your_key = your_key;
        this.fullUrl = urlString + jsonName + part2_url + your_key;
        System.out.println(fullUrl);
    }

    public Getjson(String urlString, String jsonՕverview, String published_date, String part2_url, String your_key) {
        this.urlString = urlString;
        this.jsonՕverview = jsonՕverview;
        this.published_date = published_date;
        this.part2_url = part2_url;
        this.your_key = your_key;
        this.fullUrl = urlString + jsonՕverview + published_date + part2_url + your_key;
        System.out.println(fullUrl);

    }

    public JSONObject readJson() {
        try {
            url = new URL(fullUrl);
            HttpsURLConnection conn = null;

            conn = (HttpsURLConnection) url.openConnection();
            conn.setConnectTimeout(25000);
            conn.setReadTimeout(25000);

            conn.connect();

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            int cp;
            while((cp = rd.read()) != -1) {
                sb.append((char)cp);
            }

            JSONObject json = new JSONObject(sb.toString());
            return json;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        while (true){
            System.out.print("Pleas input:\n 1-for first API get /lists.json,\n 2-for first API get /lists/best-sellers/history.json,\n"
                    + " 3-for first API get /lists/names.json, \n 4-for first API get /lists/overview.json,\n ");
            System.out.print("Input :");
            Scanner scanneri = new Scanner(System. in);
            String inputNumber = scanneri. nextLine();
            String urlString = "https://api.nytimes.com/svc/books/v3/lists/";
            String part2_url = "&api-key=";
            String your_key = "Bv8vlHTpYojIUmqmA36wu9ELG8QgZyAP";
            String offset = "offset=";
            switch (inputNumber){
                case "1":
                    String list_data = "current/";//"2019-01-20/";
                    System.out.print("Enter a list data this format by YYYY-MM-DD or input current, best-sellers: ");
                    Scanner scanner = new Scanner(System. in);
                    list_data = scanner. nextLine();
                    list_data = list_data + "/";

                    System.out.print("Enter a list required. For example hardcover-fiction, paperback-nonfiction, e-book-fiction: ");
                    String list_required = "hardcover-fiction";//"paperback-nonfiction";//"e-book-fiction";
                    list_required = scanner. nextLine();

                    String part1_url = ".json?";

                    System.out.print("Enter a off set number. !Attention must be a multiple of 20 : ");
                    offset += scanner. nextLine();

                    Getjson json1 = new Getjson(urlString, list_data, list_required, part1_url, offset, part2_url, your_key);
                    JSONObject mainJson1 = json1.readJson();
                    try {
                        System.out.println();
                        System.out.println("Copyright: " + mainJson1.get("copyright"));
                        System.out.println("Number of results: " + mainJson1.get("num_results"));
                        System.out.println("Last modified: " + mainJson1.get("last_modified"));
                        JSONObject results = mainJson1.getJSONObject("results");
                        System.out.println();
                        System.out.println("Results");
                        System.out.println("List name: " + results.get("list_name"));
                        System.out.println("Bestsellers date: " + results.get("bestsellers_date"));
                        System.out.println("Published date: " + results.get("published_date"));
                        System.out.println("Previous published date: " + results.get("previous_published_date"));
                        System.out.println("Published date description: " + results.get("published_date_description"));
                        System.out.println("Normal list ends at: " + results.get("normal_list_ends_at"));
                        System.out.println("Updated: " + results.get("updated"));
                        System.out.println();
                        System.out.println("Books");
                        JSONArray arrayBooks = results.getJSONArray("books");
                        for (int i = 0; i < arrayBooks.length(); i++) {
                            System.out.println();
                            System.out.println(i);
                            System.out.println("Publisher: " + arrayBooks.getJSONObject(i).get("publisher"));
                            System.out.println("Description: " + arrayBooks.getJSONObject(i).get("description"));
                            System.out.println("Price: " + arrayBooks.getJSONObject(i).get("price"));
                            System.out.println("Title: " + arrayBooks.getJSONObject(i).get("title"));
                            System.out.println("Author: " + arrayBooks.getJSONObject(i).get("author"));
                            System.out.println("Contributor: " + arrayBooks.getJSONObject(i).get("contributor"));
                            System.out.println("Book image: " + arrayBooks.getJSONObject(i).get("book_image"));
                            JSONArray arrayBuyLinks = arrayBooks.getJSONObject(i).getJSONArray("buy_links");
                            for (int j = 0; j < arrayBuyLinks.length() ; j++) {
                                System.out.println("Buy from :" + arrayBuyLinks.getJSONObject(j).get("name")+ " links: " + arrayBuyLinks.getJSONObject(j).get("url"));
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                case "2":
                    String best_sellers = "best-sellers/history.json?";

                    String  age_group = "age-group=";
                    System.out.print("Enter a age group. The target age group for the best seller: ");
                    Scanner scanner2 = new Scanner(System. in);
                    age_group += scanner2. nextLine() + "&";

                    String   author  = "author=";
                    System.out.print("Enter a author of the best seller. For example Philip%20C%20Stead: ");
                    author += scanner2. nextLine() + "&";

                    String contributor = "contributor=";
                    System.out.print("Enter a contributor of the best seller. For example  by%20Philip%20C.%20Stead.%20Illustrated%20by%20Erin%20E.%20Stead: ");
                    contributor += scanner2. nextLine() + "&";

                    System.out.print("Enter a off set number. !Attention must be a multiple of 20 : ");
                    offset += scanner2. nextLine();

                    String price = "price=";
                    System.out.print("The publisher's list price of the best seller, including decimal point. Enter a book price example 16.99: ");
                    price += scanner2. nextLine() + "&";

                    String publisher = "publisher=";
                    System.out.print("Enter a publisher for example Neal%20Porter%2FRoaring%20Brook: ");
                    publisher += scanner2. nextLine() + "&";

                    String title = "title=";
                    System.out.print("Enter a the title of the best seller for example BEAR%20HAS%20A%20STORY%20TO%20TELL: ");
                    title += scanner2. nextLine() +"&";

                    Getjson json2 = new Getjson(urlString, best_sellers, age_group, author, contributor, price, publisher, part2_url, your_key);
                    JSONObject mainJson2 = json2.readJson();
                    try {
                        System.out.println();
                        System.out.println("Copyright: " + mainJson2.get("copyright"));
                        System.out.println("Number of results: " + mainJson2.get("num_results"));
                        System.out.println("Results");
                        JSONArray arrayResults = mainJson2.getJSONArray("results");
                        System.out.println();
                        for (int i = 0; i < arrayResults.length(); i++) {
                            System.out.println();
                            System.out.println(i);
                            System.out.println("Title: " + arrayResults.getJSONObject(i).get("title"));
                            System.out.println("Description: " + arrayResults.getJSONObject(i).get("description"));
                            System.out.println("Publisher: " + arrayResults.getJSONObject(i).get("publisher"));
                            System.out.println("Author: " + arrayResults.getJSONObject(i).get("author"));
                            System.out.println("Contributor note: " + arrayResults.getJSONObject(i).get("contributor_note"));
                            System.out.println("Price: " + arrayResults.getJSONObject(i).get("price"));
                            System.out.println("Age group: " + arrayResults.getJSONObject(i).get("age_group"));
                            System.out.println("Publisher: " + arrayResults.getJSONObject(i).get("publisher"));
                            System.out.println();
                            JSONArray arrayRanksHistory = arrayResults.getJSONObject(i).getJSONArray("ranks_history");
                            for (int j = 0; j < arrayRanksHistory.length() ; j++) {
                                System.out.println("Rank:" + arrayRanksHistory.getJSONObject(j).get("rank"));
                                System.out.println("List name:" + arrayRanksHistory.getJSONObject(j).get("list_name"));
                                System.out.println("Display name:" + arrayRanksHistory.getJSONObject(j).get("display_name"));
                                System.out.println("Published date:" + arrayRanksHistory.getJSONObject(j).get("published_date"));
                                System.out.println("Bestsellers date:" + arrayRanksHistory.getJSONObject(j).get("bestsellers_date"));
                                System.out.println("Weeks on list:" + arrayRanksHistory.getJSONObject(j).get("weeks_on_list"));
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                case "3":
                    String jsonName = "names.json?";
                    Getjson json3 = new Getjson(urlString, jsonName, part2_url, your_key);
                    JSONObject mainJson3 = json3.readJson();
                    try {
                        System.out.println();
                        System.out.println("Copyright: " + mainJson3.get("copyright"));
                        System.out.println("Number of results: " + mainJson3.get("num_results"));
                        System.out.println("Results");
                        JSONArray arrayResults = mainJson3.getJSONArray("results");
                        System.out.println();
                        for (int i = 0; i < arrayResults.length(); i++) {
                            System.out.println();
                            System.out.println(i);
                            System.out.println("List name: " + arrayResults.getJSONObject(i).get("list_name"));
                            System.out.println("Display name: " + arrayResults.getJSONObject(i).get("display_name"));
                            System.out.println("Oldest published date: " + arrayResults.getJSONObject(i).get("oldest_published_date"));
                            System.out.println("Newest published date: " + arrayResults.getJSONObject(i).get("newest_published_date"));
                            System.out.println("Updated: " + arrayResults.getJSONObject(i).get("updated"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                case "4":
                    String jsonՕverview = "overview.json?";
                    Scanner scanner4 = new Scanner(System. in);
                    String published_date = "published_date=";
                    System.out.print("Enter a published data this format by YYYY-MM-DD: ");
                    published_date += scanner4. nextLine() + "&";

                    Getjson json4 = new Getjson(urlString, jsonՕverview, published_date, part2_url, your_key);
                    JSONObject mainJson4 = json4.readJson();
                    try {
                        System.out.println();
                        System.out.println("Copyright: " + mainJson4.get("copyright"));
                        System.out.println("Number of results: " + mainJson4.get("num_results"));
                        System.out.println("Results");
                        JSONObject objectResults = mainJson4.getJSONObject("results");
                        System.out.println("Bestsellers date: " + objectResults.get("bestsellers_date"));
                        System.out.println("Published date: " + objectResults.get("published_date"));
                        System.out.println("Published date description: " + objectResults.get("published_date_description"));
                        System.out.println("Previous published date: " + objectResults.get("previous_published_date"));
                        System.out.println("Next published date: " + objectResults.get("next_published_date"));

                        JSONArray arrayList = mainJson4.getJSONArray("lists");;
                        for (int i = 0; i < arrayList.length(); i++) {
                            System.out.println();
                            System.out.println(i);
                            System.out.println("List id: " + arrayList.getJSONObject(i).get("list_id"));
                            System.out.println("List name: " + arrayList.getJSONObject(i).get("list_name"));
                            System.out.println("Display name: " + arrayList.getJSONObject(i).get("display_name"));
                            System.out.println("Updated: " + arrayList.getJSONObject(i).get("updated"));
                            System.out.println("List image: " + arrayList.getJSONObject(i).get("list_image"));
                            JSONArray arrayBooks = arrayList.getJSONObject(i).getJSONArray("books");
                            for (int j = 0; j < arrayBooks.length(); j++){
                                System.out.println("Age group: " + arrayBooks.getJSONObject(j).get("age_group"));

                            }

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


            }



            //String bestsellers_date = "";//Pleas imput YYYY-MM-DD
            //String published_date = ""; //Pleas imput YYYY-MM-DD
            //System.out.print("Enter a string : ");
            //Scanner scanner = new Scanner(System. in);
            //String inputString = scanner. nextLine();
            //System.out.println("String read from console is : \n"+inputString);
            //System.out.println("Amazon product url: " + arrayBooks.getJSONObject(i).get("amazon_product_url"));
            //System.out.println("Book image: " + arrayBooks.getJSONObject(i).get("book_image"));
            //System.out.println("Book image: " + arrayBooks.getJSONObject(i).get("book_image"));

            //System.out.println(a);
            //System.out.println("Books: " + results.get("books"));
            //JSONObject forecast = mainJson.get("status");
            //JSONArray forecastday = forecast.getJSONArray("forecastday");
            //System.out.println(forecastday.get(0));

        }

    }

}

