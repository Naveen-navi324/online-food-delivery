package com.tap.model;

public class Menu {
	 private int menuId;
	    private int restaurantId; // Foreign key referencing the Restaurant
	    private String itemName;
	    private String description;
	    private double price;
	    private double ratings;
	    private boolean isAvailable; // Availability of the item
	    private String imagePath; 
	    
	    public Menu(int i, int j, String string, double d, boolean b) {
			// TODO Auto-generated constructor stub
		}

		public Menu(int menuId, int restaurantId, String itemName, String description, double price, double ratings,
				boolean isAvailable, String imagePath) {
			super();
			this.menuId = menuId;
			this.restaurantId = restaurantId;
			this.itemName = itemName;
			this.description = description;
			this.price = price;
			this.ratings = ratings;
			this.isAvailable = isAvailable;
			this.imagePath = imagePath;
		}

		public int getMenuId() {
			return menuId;
		}

		public void setMenuId(int menuId) {
			this.menuId = menuId;
		}

		public int getRestaurantId() {
			return restaurantId;
		}

		public void setRestaurantId(int restaurantId) {
			this.restaurantId = restaurantId;
		}

		public String getItemName() {
			return itemName;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public double getRatings() {
			return ratings;
		}

		public void setRatings(double ratings) {
			this.ratings = ratings;
		}

		public boolean isAvailable() {
			return isAvailable;
		}

		public void setAvailable(boolean isAvailable) {
			this.isAvailable = isAvailable;
		}

		public String getImagePath() {
			return imagePath;
		}

		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}
	    
	    
}