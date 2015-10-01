package org.aktsa.grocerease.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Data model for a single product from the Factual API
 *
 * @author cheek
 * @version 1, 9/26/2015
 */
public class Product implements Parcelable {

    public Product(Double avgPrice, String brand, Double calcium, Double calories, String category,
                   Double cholesterol, Double dietaryFiber, Double fatCalories, ArrayList<String> imageUrls,
                   ArrayList<String> ingredients, Double iron, String manufacturer, Double potassium,
                   String productName, Double protein, Double satFat, String servingSize, Double servings,
                   String size, Double sodium, Double sugars, Double totalCarb, Double totalFat, Double transFat,
                   Double vitaminA, Double vitaminC) {
        this.avgPrice = avgPrice;
        this.brand = brand;
        this.calcium = calcium;
        this.calories = calories;
        this.category = category;
        this.cholesterol = cholesterol;
        this.dietaryFiber = dietaryFiber;
        this.fatCalories = fatCalories;
        this.imageUrls = imageUrls;
        this.ingredients = ingredients;
        this.iron = iron;
        this.manufacturer = manufacturer;
        this.potassium = potassium;
        this.productName = productName;
        this.protein = protein;
        this.satFat = satFat;
        this.servingSize = servingSize;
        this.servings = servings;
        this.size = size;
        this.sodium = sodium;
        this.sugars = sugars;
        this.totalCarb = totalCarb;
        this.totalFat = totalFat;
        this.transFat = transFat;
        this.vitaminA = vitaminA;
        this.vitaminC = vitaminC;
    }

    private Double avgPrice;
    private String brand;
    private Double calcium;
    private Double calories;
    private String category;
    private Double cholesterol;
    private Double dietaryFiber;
    private Double fatCalories;
    private ArrayList<String> imageUrls;
    private ArrayList<String> ingredients;
    private Double iron;
    private String manufacturer;
    private Double potassium;
    private String productName;
    private Double protein;
    private Double satFat;
    private String servingSize;
    private Double servings;
    private String size;
    private Double sodium;
    private Double sugars;
    private Double totalCarb;
    private Double totalFat;
    private Double transFat;
    private Double vitaminA;
    private Double vitaminC;

    protected Product(Parcel in) {
        setAvgPrice(in.readByte() == 0x00 ? null : in.readDouble());
        setBrand(in.readString());
        setCalcium(in.readByte() == 0x00 ? null : in.readDouble());
        setCalories(in.readByte() == 0x00 ? null : in.readDouble());
        setCategory(in.readString());
        setCholesterol(in.readByte() == 0x00 ? null : in.readDouble());
        setDietaryFiber(in.readByte() == 0x00 ? null : in.readDouble());
        setFatCalories(in.readByte() == 0x00 ? null : in.readDouble());
        if (in.readByte() == 0x01) {
            setImageUrls(new ArrayList<String>());
            in.readList(getImageUrls(), String.class.getClassLoader());
        } else {
            setImageUrls(null);
        }
        if (in.readByte() == 0x01) {
            setIngredients(new ArrayList<String>());
            in.readList(getIngredients(), String.class.getClassLoader());
        } else {
            setIngredients(null);
        }
        setIron(in.readByte() == 0x00 ? null : in.readDouble());
        setManufacturer(in.readString());
        setPotassium(in.readByte() == 0x00 ? null : in.readDouble());
        setProductName(in.readString());
        setProtein(in.readByte() == 0x00 ? null : in.readDouble());
        setSatFat(in.readByte() == 0x00 ? null : in.readDouble());
        setServingSize(in.readString());
        setServings(in.readByte() == 0x00 ? null : in.readDouble());
        setSize(in.readString());
        setSodium(in.readByte() == 0x00 ? null : in.readDouble());
        setSugars(in.readByte() == 0x00 ? null : in.readDouble());
        setTotalCarb(in.readByte() == 0x00 ? null : in.readDouble());
        setTotalFat(in.readByte() == 0x00 ? null : in.readDouble());
        setTransFat(in.readByte() == 0x00 ? null : in.readDouble());
        setVitaminA(in.readByte() == 0x00 ? null : in.readDouble());
        setVitaminC(in.readByte() == 0x00 ? null : in.readDouble());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (getAvgPrice() == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(getAvgPrice());
        }
        dest.writeString(getBrand());
        if (getCalcium() == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(getCalcium());
        }
        if (getCalories() == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(getCalories());
        }
        dest.writeString(getCategory());
        if (getCholesterol() == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(getCholesterol());
        }
        if (getDietaryFiber() == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(getDietaryFiber());
        }
        if (getFatCalories() == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(getFatCalories());
        }
        if (getImageUrls() == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(getImageUrls());
        }
        if (getIngredients() == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(getIngredients());
        }
        if (getIron() == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(getIron());
        }
        dest.writeString(getManufacturer());
        if (getPotassium() == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(getPotassium());
        }
        dest.writeString(getProductName());
        if (getProtein() == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(getProtein());
        }
        if (getSatFat() == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(getSatFat());
        }
        dest.writeString(getServingSize());
        if (getServings() == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(getServings());
        }
        dest.writeString(getSize());
        if (getSodium() == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(getSodium());
        }
        if (getSugars() == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(getSugars());
        }
        if (getTotalCarb() == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(getTotalCarb());
        }
        if (getTotalFat() == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(getTotalFat());
        }
        if (getTransFat() == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(getTransFat());
        }
        if (getVitaminA() == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(getVitaminA());
        }
        if (getVitaminC() == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(getVitaminC());
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getCalcium() {
        return calcium;
    }

    public void setCalcium(Double calcium) {
        this.calcium = calcium;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public Double getDietaryFiber() {
        return dietaryFiber;
    }

    public void setDietaryFiber(Double dietaryFiber) {
        this.dietaryFiber = dietaryFiber;
    }

    public Double getFatCalories() {
        return fatCalories;
    }

    public void setFatCalories(Double fatCalories) {
        this.fatCalories = fatCalories;
    }

    public ArrayList<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Double getIron() {
        return iron;
    }

    public void setIron(Double iron) {
        this.iron = iron;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Double getPotassium() {
        return potassium;
    }

    public void setPotassium(Double potassium) {
        this.potassium = potassium;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getSatFat() {
        return satFat;
    }

    public void setSatFat(Double satFat) {
        this.satFat = satFat;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public Double getServings() {
        return servings;
    }

    public void setServings(Double servings) {
        this.servings = servings;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getSodium() {
        return sodium;
    }

    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }

    public Double getSugars() {
        return sugars;
    }

    public void setSugars(Double sugars) {
        this.sugars = sugars;
    }

    public Double getTotalCarb() {
        return totalCarb;
    }

    public void setTotalCarb(Double totalCarb) {
        this.totalCarb = totalCarb;
    }

    public Double getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(Double totalFat) {
        this.totalFat = totalFat;
    }

    public Double getTransFat() {
        return transFat;
    }

    public void setTransFat(Double transFat) {
        this.transFat = transFat;
    }

    public Double getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(Double vitaminA) {
        this.vitaminA = vitaminA;
    }

    public Double getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(Double vitaminC) {
        this.vitaminC = vitaminC;
    }
}
