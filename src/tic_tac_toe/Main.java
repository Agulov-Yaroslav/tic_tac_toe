package tic_tac_toe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	final static String MOVE = "Выберите номер ячейки: ";
	static Scanner scanner = new Scanner(System.in);
	static int numberMoves = 1;

	public static void main(String[] arg) {
		boolean game = true;
		while (game) {
			char[][] matrix = createArr();

			String crosses = "Сейчас ходит игрок ставящий крестики. \n";
			String zeroes = "Сейчас ходит игрок ставящий нолики.\n";

			int xOdero;

			while (numberMoves < 9 && !victorySearch(matrix, 'X') && !victorySearch(matrix, 'O')) {

				xOdero = numberMoves % 2;
				switch (xOdero) {
				case 1:
					System.out.print(crosses);
					break;
				case 0:
					System.out.print(zeroes);
					break;
				}
				pruf(matrix, chooseNumber(matrix), xOdero);
			}

			if (victorySearch(matrix, 'X')) {
				System.out.println("Крестики победили!!!");
			} else if (victorySearch(matrix, 'O')) {
				System.out.println("Нолики победили!!!");
			} else
				System.out.println("Ничья");

			showField(matrix);
			game = nextGame();
		}
		System.out.println("Спасибо за игру!");

		scanner.close();
	}

	public static char[][] createArr() {
		char[][] matrix = new char[3][3];
		int a = 1;
		for (int i = 0; i < matrix.length; i++) {

			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = (char) (a + '0');
				a++;
			}
		}
		return matrix;
	}

	public static int chooseNumber(char[][] matrix) {
		System.out.println(MOVE);
		int choise;

		do {
			showField(matrix);
			try {
				choise = scanner.nextInt();

				if (choise > 0 && choise < 10)
					return choise;
				else
					System.out.println(MOVE);

			} catch (InputMismatchException e) {
				System.out.println(MOVE);
				scanner.next();
			}
		} while (true);

	}

	public static void showField(char[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static boolean pruf(char[][] matrix, int num, int xOdero) {
		int[] address = new int[2];
		boolean pruf = true;
		String fail = "Эта ячейка уже занята. ";

		switch (num) {

		case 1:
			address = new int[] { 0, 0 };
			break;

		case 2:
			address = new int[] { 0, 1 };
			break;
		case 3:
			address = new int[] { 0, 2 };
			break;
		case 4:
			address = new int[] { 1, 0 };
			break;
		case 5:
			address = new int[] { 1, 1 };
			break;
		case 6:
			address = new int[] { 1, 2 };
			break;
		case 7:
			address = new int[] { 2, 0 };
			break;
		case 8:
			address = new int[] { 2, 1 };
			break;
		case 9:
			address = new int[] { 2, 2 };
			break;

		}

		if (matrix[address[0]][address[1]] == 'X' || matrix[address[0]][address[1]] == 'O') {
			System.out.println(fail);
		} else {

			if (xOdero == 1)
				matrix[address[0]][address[1]] = 'X';
			else
				matrix[address[0]][address[1]] = 'O';
			numberMoves++;

		}

		return pruf;
	}

	public static boolean victorySearch(char[][] matrix, char symbol) {
		boolean victorySearch = false;

		for (int i = 0; i < 3; i++) {
			if (matrix[i][0] == symbol && matrix[i][1] == symbol && matrix[i][2] == symbol) {
				return true;
			}
		}

		// Проверка по столбцам
		for (int j = 0; j < 3; j++) {
			if (matrix[0][j] == symbol && matrix[1][j] == symbol && matrix[2][j] == symbol) {
				return true;
			}
		}

		// Проверка по диагоналям
		if (matrix[0][0] == symbol && matrix[1][1] == symbol && matrix[2][2] == symbol) {
			return true;
		}
		if (matrix[0][2] == symbol && matrix[1][1] == symbol && matrix[2][0] == symbol) {
			return true;
		}

		return victorySearch;

	}

	public static boolean nextGame() {
		boolean nextGame = true;
		String offer = "Хотите сыграть еще раз? (да/нет): ";
		System.out.println(offer);
		String answer;

		while (true) {
			answer = scanner.next();
			switch (answer) {
			case "да":
				return nextGame;

			case "нет":
				nextGame = false;
				return nextGame;
			default:
				System.out.println(offer);

			}

		}

	}

}
