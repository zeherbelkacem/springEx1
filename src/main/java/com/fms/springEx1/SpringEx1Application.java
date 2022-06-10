package com.fms.springEx1;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fms.springEx1.Entities.Article;
import com.fms.springEx1.Entities.Category;
import com.fms.springEx1.Entities.CategoryEnum;
import com.fms.springEx1.Service.IArticleService;
import com.fms.springEx1.Service.ICategoryService;

@SpringBootApplication
public class SpringEx1Application implements CommandLineRunner {

	/*
	 * Scanner
	 */
	private static Scanner scanner = new Scanner(System.in);

	/*
	 * Repositories Dependency Injections
	 */
	@Autowired
	private IArticleService articleService;
	@Autowired
	private ICategoryService categoryService;

	public static void main(String[] args) {
		SpringApplication.run(SpringEx1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*
		 * Save some categories
		 */
		/*
		 * Category pc = new Category(CategoryEnum.PC); Category smartphone = new
		 * Category(CategoryEnum.SMARTPHONE); Category tablet = new
		 * Category(CategoryEnum.TABLET); Category hardware = new
		 * Category(CategoryEnum.HARDWARE); Category other = new
		 * Category(CategoryEnum.OTHER); categoryRepository.save(pc);
		 * categoryRepository.save(smartphone); categoryRepository.save(tablet);
		 * categoryRepository.save(hardware); categoryRepository.save(other);
		 */

		/*
		 * Save some Articles
		 */
		/*
		 * articleRepository.save(new Article(null, "S10", "Samsung", (double) 350,
		 * smartphone)); articleRepository.save(new Article(null, "S7", "Samsung", 300.,
		 * smartphone)); articleRepository.save(new Article(null, "MI10", "Xiomi", 250D,
		 * smartphone)); articleRepository.save(new Article(null, "GalaxyTab",
		 * "Samsung", (double) 150, tablet)); articleRepository.save(new Article(null,
		 * "EliteBook 16G", "HP", (double) 1350, pc)); articleRepository.save(new
		 * Article(null, "Ipad", "Apple", (double) 100, tablet));
		 * articleRepository.save(new Article(null, "Chargeur PC", "hp", (double) 80,
		 * hardware));
		 * 
		 * articleRepository.save(new Article(null, "S11", "Samsung", (double) 350,
		 * smartphone)); articleRepository.save(new Article(null, "S12", "Samsung",
		 * 300., smartphone)); articleRepository.save(new Article(null, "MI09", "Xiomi",
		 * 250D, smartphone)); articleRepository.save(new Article(null, "tab enfant",
		 * "Gulli", (double) 150, tablet)); articleRepository.save(new Article(null,
		 * "EliteBook 16G", "HP", (double) 1350, pc)); articleRepository.save(new
		 * Article(null, "Ipad", "Apple", (double) 150, tablet));
		 * articleRepository.save(new Article(null, "casque ", "hp", (double) 80,
		 * hardware)); articleRepository.save(new Article(null, "S11", "Samsung",
		 * (double) 350, other)); articleRepository.save(new Article(null, "S12",
		 * "Samsung", 300., other)); articleRepository.save(new Article(null, "MI09",
		 * "Xiomi", 250D, other)); articleRepository.save(new Article(null,
		 * "tab enfant", "Gulli", (double) 150, other)); articleRepository.save(new
		 * Article(null, "EliteBook 16G", "HP", (double) 1350, other));
		 * articleRepository.save(new Article(null, "Ipad", "Apple", (double) 100,
		 * other)); articleRepository.save(new Article(null, "casque ", "hp", (double)
		 * 80, other));
		 */
		
		while (true) {			
			System.out.println("Bienvenue dans votre LIBRAIRIE. Voici la liste de nos livres ");
			welcomeMenu();
		}
		/*
		 * Some requests TEST
		 */
//		System.out.println("\n-------------------------Read all articles------------------");
//		articleService.realAll().forEach(a -> {
//			System.out.println(a);
//		});
//		// OR
////		for (Article article : articleRepository.findAll()) 
////			System.out.println(article);
//
//		System.out.println("\n------------------------Read article by Id------------------");
//		System.out.println(articleService.readById(2L));
//
//		System.out.println("\n----------------------Delete article by Id------------------");
////		if(articleRepository.findById(2L).get() != null)
////			articleRepository.deleteById(2L);
//
//		System.out.println("\n----------------------Update article ------------------------");
//
//		System.out.println("\n----------------------Article by category id ------------------------");
//		articleService.readArticleByCatgoryId(2L).forEach(a -> {
//			System.out.println(a);
//		});
//
//		System.out.println("\n----------------------Article by category name ------------------------");
//		articleService.readArticleByCatgoryName(CategoryEnum.PC).forEach(a -> {
//			System.out.println(a);
//		});
//
//		System.out.println("\n----------------------Article page by page ------------------------");
//		int size = 5;// Each page contains "size" elements
//		int page = 0; // First page start with "page" (0 in this case) index
//
//		/*
//		 * Number of pages
//		 */
//		int articleTotalPages = articleService.readArticlesPageByPage(page, size).getTotalPages();
//
//		/*
//		 * Number of total elements
//		 */
//		long totalArticlesElements = articleService.readArticlesPageByPage(page, size).getTotalElements();
//
//		/*
//		 * Pagination show
//		 */
//		for (page = 0; page < articleTotalPages; page++) {
//			int pageNbElmts = articleService.readArticlesPageByPage(page, size).getNumberOfElements(); // number of
//																										// items on the
//																										// current page
//			// Elements Page
//			System.out.println("\nPage Number " + page + " contains " + pageNbElmts + " elements:");
//			articleService.readArticlesPageByPage(page, size).forEach(a -> {
//				System.out.println(a);
//			});
//		}
//
//		System.out.println("\n---------Article page by page and with brand key word -----------");
//		articleService.findByPageByPageAndKeyWord("sam", PageRequest.of(0, 10)).forEach(a -> {
//			System.out.println(a);
//		});
	}

	/**
	 * 
	 */
	private void welcomeMenu() {
		int menuChoice = -1;
		while (menuChoice != 0) { // (0) to exit menu
			try {
				System.out.println("\n----------------------------- SHOP APP -------------------------" + "\n"
						+ "ADMIN                                                  enter (1)\n"
						+ "USERS                                                  enter (2)\n"
						+ "----------------------------------------------------------------");

				/** Only integer entries accepted */
				menuChoice = (int) getPositiveIntegerInput(scanner, "\nFaite votre choix!");
				switch (menuChoice) {
				case 1:
					adminMenu();
					break;

				case 2:
					usersMenu();

					break;

				default:
					System.out.println("Wrong entry: ONLY INTEGERS ENTRIES ( 0 to 5)");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void usersMenu() {
		// TODO Auto-generated method stub

	}

	private void adminMenu() {
		int menuChoice = -1;
		while (menuChoice != 0) { // (0) to exit menu
			try {
				System.out.println("\n------------------------ ADMIN CONSOLE -------------------------" + "\n"
						+ "Pour afficher les ARTICLES,                             enter (1)\n"
						+ "Pour afficher tous les article page par page,          enter (2)\n"
						+ "Pour Afficher toutes les CATEGORIES                    enter (3)\n"
						+ "Pour AJOUTER un ARTICLE,                               enter (4)\n"
						+ "Pour SUPPRIMER un article         ,                    enter (5)\n"
						+ "Pour METTRE A JOUR un ARTICLE,                         enter (6)\n"
						+ "Tous les articles d’une catégorie                      enter (7)\n"
						+ "SORTIR de l'application,                               enter (0)\n"
						+ "----------------------------------------------------------------");

				/** Only integer entries accepted */
				menuChoice = (int) getPositiveIntegerInput(scanner, "\nFaites votre choix!");
				switch (menuChoice) {
				case 1:
					showArticles(articleService.realAll());
					break;

				case 2:
					showArticlesPageByPage();
					break;

				case 3:
					showAllCategories(categoryService.readAllCategories());
					;
					break;

				case 4:
					addArticle();
					break;

				case 5:
					deleteArticle();
					break;

				case 6:
					updateArticle();
					break;

				case 7:
					articlesByCategoryId();
					break;

				case 0:
					menuChoice = 0;
					break;

				default:
					System.out.println("Wrong entry: ONLY INTEGERS ENTRIES ( 0 to 5)");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * 
	 */
	private void updateArticle() {
		String description = null;
		String brand = null;
		Double price = null;
		CategoryEnum catName = null;
		String answer = "";
		Long idCat = null;
		showArticles(articleService.realAll());
		int idArticle = (int) getPositiveIntegerInput(scanner,
				"\nEntrez l'ID de l'article que vous souhaitez- modifier!");
		Article article = articleService.readById((long) idArticle);

		System.out.println("DESCRPTION: " + article.getDescription());
		System.out.println("Voudriez-vous la changer: ? yes/no");
		answer = yesOrNoChoice();
		if (answer.equalsIgnoreCase("yes")) {
			description = scanner.nextLine();
			description = scanner.nextLine();

		} else
			description = article.getDescription();

		System.out.println("MARQUE: " + article.getBrand());
		System.out.println("Voudriez-vous la changer: ? yes/no");
		answer = yesOrNoChoice();
		if (answer.equalsIgnoreCase("yes")) {
			brand = scanner.nextLine();
			brand = scanner.nextLine();

		} else
			brand = article.getBrand();

		System.out.println("PRIX: " + article.getPrice());
		System.out.println("Voudriez-vous le changer: ? yes/no");
		answer = yesOrNoChoice();
		if (answer.equalsIgnoreCase("yes"))
			price = scanner.nextDouble();
		else
			price = article.getPrice();

		System.out.println("CATEGORIE: " + article.getCategory().getName());
		System.out.println("Voudriez-vous la changer: ? yes/no");
		answer = yesOrNoChoice();
		if (answer.equalsIgnoreCase("yes")) {
			categoryService.readAllCategories().forEach(c -> {
				System.out.print(c.getId() + ": " + c.getName() + "  /  ");
			});
			System.out.println("Choisissez la categorie en tapant l'ID correspondant ?");
			idCat = scanner.nextLong();
		} else
			catName = article.getCategory().getName();

		articleService.saveArticle(
				new Article((long) idArticle, description, brand, price, categoryService.getCategoryById(idCat)));
	}

	private String yesOrNoChoice() {
		String choice = scanner.next();
		while (!choice.equalsIgnoreCase("yes") && !choice.equalsIgnoreCase("no")) {
			System.out.println("Sorry, answer yes or no : ?(yes/no)");
			choice = scanner.next();
		}
		return choice;
	}

	/**
	 * 
	 */
	private void articlesByCategoryId() {
		showAllCategories(categoryService.readAllCategories());
		int idCat = (int) getPositiveIntegerInput(scanner, "\nEntrez l'ID de la categorie que vous souhaitez!");
		showArticles(articleService.readArticleByCatgoryId((long) idCat));
	}

	/**
	 * 
	 * @param readArticleByCatgoryId
	 */
	private void showAllCategories(List<Category> categories) {
		System.out.println("------------------------------------------");
		System.out.println("|ID  | CATEGORY NAME                     |");
		System.out.println("-----+-----------------------------------+");//

		/* * Display the table body: Browse the training HashMap */
		for (Category c : categories) {
			System.out.println(String.format("|%-4s|%-35s|", c.getId(), c.getName()));
			System.out.println("-----+-----------------------------------+");
		}

	}

	private void addArticle() {

		System.out.println("Description de l'article ?");
		String description = scanner.nextLine();
		description = scanner.nextLine();
		
		System.out.println("Marque de l'article ?");
		String brand = scanner.next();
		// brand = scanner.next();
		System.out.println("Prix de l'article ?");
		Double price = scanner.nextDouble();
		System.out.println("Associer une categorie à l'article ?");
		categoryService.readAllCategories().forEach(c -> {
			System.out.print(c.getId() + ": " + c.getName() + "  /  ");
		});
		System.out.println("Choisissez la categorie en tapant l'ID correspondant ?");
		Long idCat = scanner.nextLong();
		articleService
				.saveArticle(new Article(null, description, brand, price, categoryService.getCategoryById(idCat)));
	}

	private void deleteArticle() {
		showArticles(articleService.realAll());
		int idArticle = (int) getPositiveIntegerInput(scanner, "\nEntrez l'ID de l'article que vous souhaitez!");
		articleService.deleteArticleById((long) idArticle);

	}

	private void showArticlesPageByPage() {
		int size = 5;// Each page contains "size" elements
		int page = 0; // First page start with "page" (0 in this case) index

		/*
		 * Number of pages
		 */
		int articleTotalPages = articleService.readArticlesPageByPage(page, size).getTotalPages();

		/*
		 * Number of total elements
		 */
//		long totalArticlesElements = articleService.readArticlesPageByPage(page, size).getTotalElements();

		/*
		 * Pagination show
		 */
		for (page = 0; page < articleTotalPages; page++) {
			int pageNbElmts = articleService.readArticlesPageByPage(page, size).getNumberOfElements(); // number of
																										// items on the
																										// current page
			// Elements Page
			System.out.println("\nPage Number " + page + " contains " + pageNbElmts + " elements:");
			showArticles(articleService.readArticlesPageByPage(page, size).getContent());
//			articleService.readArticlesPageByPage(page, size).forEach(a -> {
//				System.out.println(a);
//			});
		}

	}

	/**
	 * Gestion d'erreurs de flux entrée sortie
	 * 
	 * @param scanner
	 * @param string
	 * @return un nombre positif (>0)
	 */
	private static long getPositiveIntegerInput(Scanner scanner, String string) {
		String menuresponse = "";
		while (true) {
			System.out.println(string);
			try {
				menuresponse = scanner.next();
				if (Integer.parseInt(menuresponse) >= 0)
					break;
				System.out.println("La réponse doit être numerique et positive >= 0");
			} catch (NumberFormatException e) {
				System.out.println("La réponse doit être numerique et positive >= 0");
			}
		}
		return Long.parseLong(menuresponse);
	}

	/**
	 * Affiche tous les livre à l'ouverture de l'appli
	 * 
	 * @param books
	 */
	private static void showArticles(List<Article> articles) {
		// Build the table header
		System.out.println(
				"--------------------------------------------------------------------------------------------------------");
		System.out.println(
				"|ID  | DESCRIPTION                       | BRAND                     | CATEGORY             |PRICE (€) |");
		System.out.println(
				"-----+-----------------------------------+---------------------------+----------------------+----------+");//

		/* * Display the table body: Browse the training HashMap */
		for (Article a : articles) {
			System.out.println(String.format("|%-4s|%-35s|%-27s|%-22s|%-10s|", a.getId(), a.getDescription(),
					a.getBrand(), a.getCategory().getName(), a.getPrice()));
			System.out.println(
					"-----+-----------------------------------+---------------------------+----------------------+----------+");
		}

	}

}
