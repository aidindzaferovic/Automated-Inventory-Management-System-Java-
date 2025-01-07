import java.util.Scanner;
import java.time.*;

public class Group2Project {

	public static class EmployeeID { 
		/* 
		Manager ID: 1 , PW: manager
		Employee ID: 1234 , PW: password
		*/
		
		private String name;
		private int id;
		private String password;

		public EmployeeID(String name, int id, String password) {
			this.name = name;
			this.id = id;
			this.password = password;
		}

		// Checks if password in the code matches the entered password
		public boolean logIn(String enteredPassword) {
			return this.password.equals(enteredPassword);
		}

		/*
		 * Does the same thing as logIn(), but also checks if ID=1, as its managers ID
		 * in this case
		 */
		public boolean managerLogIn(String enteredPassword) {
			return this.id == 1 && this.password.equals(enteredPassword);
		}
	}

	public abstract static class Item {
		private double pricePer;
		private int openStock;
		private int stockIn;
		private int sales;

		public Item(double pricePer, int openStock, int stockIn, int sales) {
			this.pricePer = pricePer;
			this.openStock = openStock;
			this.stockIn = stockIn;
			this.sales = sales;
		}

		// Stores the value of total amount of the items in stock
		public double valueOfRemainingStock() {
			return pricePer * openStock;
		}

		// Returns current stock
		public int getOpenStock() {
			return openStock;
		}

		// Returns the updated stock after a note has been resolved for an item
		public void stockResolved(int quantity) {
			openStock += quantity;
		}

		// Lists all the items when log in info has been confirmed
		public abstract void listAll();

		// Lists all the information of an item when the item is looked up
		public abstract void lookUp();

		// Looks up items based on the keyword searchKey
		public abstract boolean productSearch(String searchKey);
	}

	public static class ItemInfoBook extends Item {
		private String isbn;
		private String bookTitle;
		private String author;
		private String publisher;
		private String description;

		public ItemInfoBook(String isbn, double pricePer, int openStock, int stockIn, int sales, String bookTitle,
				String author, String publisher, String description) {
			super(pricePer, openStock, stockIn, sales);
			this.isbn = isbn;
			this.bookTitle = bookTitle;
			this.author = author;
			this.publisher = publisher;
			this.description = description;
		}

		public void listAll() {
			System.out.println("Book Title: " + bookTitle + ", Author: " + author + ", Stock: " + getOpenStock()); // stock
		}

		public void lookUp() {
			System.out.println("ISBN: " + isbn + ", Title: " + bookTitle + ", Author: " + author + ", Publisher: "
					+ publisher + ", Description: " + description);
		}

		/*
		 * Looks for if the searchKey contains any part of any attribute of the book
		 * This also removes any problems with Case-sensitive writing as all string
		 * fields are set with .toLowerCase()
		 */
		public boolean productSearch(String searchKey) {
			return isbn.contains(searchKey) || bookTitle.toLowerCase().contains(searchKey.toLowerCase())
					|| author.toLowerCase().contains(searchKey.toLowerCase())
					|| publisher.toLowerCase().contains(searchKey.toLowerCase());
		}
	}

	public static class ItemInfoClothing extends Item {
		private String size;
		private String material;
		private String manufacturer;

		public ItemInfoClothing(double pricePer, int openStock, int stockIn, int sales, String size, String material,
				String manufacturer) {
			super(pricePer, openStock, stockIn, sales);
			this.size = size;
			this.material = material;
			this.manufacturer = manufacturer;
		}

		public void listAll() {
			System.out.println("Clothing Size: " + size + ", Material: " + material + ", Stock: " + getOpenStock()); // stock
		}

		public void lookUp() {
			System.out.println("Size: " + size + ", Material: " + material + ", Manufacturer: " + manufacturer);
		}

		/*
		 * Looks for if the searchKey contains any part of any attribute of the clothing
		 * This also removes any problems with Case-sensitive writing as all string
		 * fields are set with .toLowerCase()
		 */
		public boolean productSearch(String searchKey) {
			return size.toLowerCase().contains(searchKey.toLowerCase())
					|| material.toLowerCase().contains(searchKey.toLowerCase())
					|| manufacturer.toLowerCase().contains(searchKey.toLowerCase());
		}
	}

	public static class Note {
		private String description;
		private int amount;
		private String datePlaced;
		private int stockRemaining;

		public Note(String description, int amount, String datePlaced, int stockRemaining) {
			this.description = description;
			this.amount = amount;
			this.datePlaced = datePlaced;
			this.stockRemaining = stockRemaining;

		}

		/*
		 * When a note is resolved, it lists what item was ordered, how much was ordered
		 * and when it was resolved
		 */
		public void resolve() {
			System.out.println("Note resolved for " + description + " with amount: " + amount);
			System.out.println("Updated stock remaining: " + stockRemaining); // Displays remaining stock
			System.out.println("Stock Updated at " + datePlaced);
		}

		// When a note is denied, it lets you know which order was denied and when
		public void deny() {
			System.out.println("Resolution denied for " + description + ". No action taken.");
			System.out.println("Denial recieved at " + datePlaced);
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// Sample Employee and Manager logins
		EmployeeID employee = new EmployeeID("Employee", 1234, "password");
		EmployeeID manager = new EmployeeID("Manager", 1, "manager");

		System.out.print("Enter ID: ");
		int enteredID = scan.nextInt();
		scan.nextLine();

		System.out.print("Enter password: ");
		String enteredPassword = scan.nextLine();

		// Boolean value to see if entered ID and password match the sample
		boolean isManager = manager.id == enteredID && manager.managerLogIn(enteredPassword);
		boolean isEmployee = employee.id == enteredID && employee.logIn(enteredPassword);

		// Adds a greetings message to the user
		if (isManager || isEmployee) {
			if (isManager) {
				System.out.println("Login successful! Welcome " + manager.name + "!");
			} else {
				System.out.println("Login successful! Welcome " + employee.name + "!");
			}

			// Sample book and clothing data
			ItemInfoBook book1 = new ItemInfoBook("1273819035731", 9.99, 50, 30, 20, "St. John's History",
					"Adnan & Aidin", "St. John's", "History of St. Johns");
			ItemInfoBook book2 = new ItemInfoBook("8392384648324", 49.99, 100, 70, 30, "Intro To Java", "Prof. Doc",
					"Puffin", "Textbook for Java");
			ItemInfoBook book3 = new ItemInfoBook("5346846984563", 15.99, 25, 15, 10, "Intro To CyberSecurity",
					"Prof.Doc", "TechBooks", "Textbook for CyberSecurity");
			ItemInfoClothing clothing1 = new ItemInfoClothing(19.99, 100, 75, 25, "L", "Cotton", "SJU Co.");
			ItemInfoClothing clothing2 = new ItemInfoClothing(24.99, 125, 80, 15, "M", "Dri-Fit", "SJU Co.");
			ItemInfoClothing clothing3 = new ItemInfoClothing(29.99, 150, 85, 35, "S", "Wool", "SJU Co.");

			// Array that contains all items
			Item[] itemsStored = { book1, book2, book3, clothing1, clothing2, clothing3 };

			// Lists out every item within the array
			System.out.println("Items in Stock:");
			for (Item item : itemsStored) {
				item.listAll();
			}
			/*
			 * When the code is running the session is considered active until the exit
			 * option is selected
			 */
			boolean sessionActive = true;
			Note note = null;

			while (sessionActive) {
				System.out.println("\nOptions:");
				System.out.println("1. Search for an item");
				System.out.println("2. Place an order");
				// Only the manager gets the option to look at and resolve/deny any note
				if (isManager) {
					System.out.println("3. Resolve/Deny a note");
				}
				System.out.println("4. Exit");
				System.out.print("Select an option: ");
				int selection = scan.nextInt();
				scan.nextLine();

				switch (selection) {
				/*
				 * Option 1 searches for an item using a user-inputed keyword
				 * with the productedSearch method
				 */
				case 1:
					System.out.print("Enter item to search: ");
					String searchKey = scan.nextLine();
					boolean itemIsFound = false;
					// Sets the boolean itemIsFound to true if the keyword matches any item
					for (Item item : itemsStored) {
						if (item.productSearch(searchKey)) {
							item.lookUp();
							itemIsFound = true;
						}
					}

					if (!itemIsFound) {
						System.out.println("Item not found.");
					}
					break;

				// Option 2 can place an order for both manager and employee
				case 2:
					System.out.print("Enter the item you want to order: ");
					String orderedItem = scan.nextLine();

					itemIsFound = false;
					// This uses the item found from a keyword and asks how much more you want to order
					for (Item item : itemsStored) {
						if (item.productSearch(orderedItem)) {
							System.out.println("Enter the quantity you want to order: ");
							int quantity = scan.nextInt();
							scan.nextLine();

							if (quantity > 0) {
								// Updates the stock after user inputed quantity
								item.stockResolved(quantity);
								System.out.println("Order placed for " + quantity + " units of:");
								item.lookUp();

								// Get current date for note creation
								String datePlaced = LocalDate.now().toString();

								// Creates the note with the item ordered
								note = new Note("Order more of " + orderedItem, quantity, datePlaced,
										item.getOpenStock());
								System.out.println("Note created.");
							} else {
								System.out.println(
										"Invalid quantity. Order should be a positive number greater than zero.");
							}

							itemIsFound = true;
							break;
						}
					}

					if (!itemIsFound) {
						System.out.println("No matching item found.");
					}

					break;
				/*
				 * Option 3 contains the ability to resolve or deny the note, 
				 * only when on the manager's account
				 */
				case 3:
					if (isManager && note != null) {
						System.out.println("Resolve the following note:");
						System.out.println("Description: " + note.description);
						System.out.println("Would you like to (1) Resolve or (2) Deny?");
						int action = scan.nextInt();
						scan.nextLine();

						/*
						 * When an acceptable action is given, the note is either resolved or denied,
						 * and the note gets reset to null once an action is done
						 */
						if (action == 1) {
							note.resolve();
							note = null;
						} else if (action == 2) {
							note.deny();
							note = null;
						} else {
							System.out.println("Action not recognized, please select (1) or (2).");
						}
					} else if (note == null) {
						System.out.println("No notes available to resolve or deny.");
					} else {
						// This occurs when you are trying to access Option 3 from the employee account
						System.out.println("This option is only available to managers.");
					}
					break;
				// Option 4 provides a way to exit which ends the program
				case 4:
					sessionActive = false;
					System.out.println("Goodbye!");
					break;

				default:
					System.out.println("Invalid option. Please try again.");
				}
			}
		} else {
			System.out.println("Invalid credentials.");
		}

		scan.close();
	}
}