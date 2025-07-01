package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test // Первый тест из обучающего урока
    public void testSearch(){
        SearchPageObject SearhPageObject = SearchPageObjectFactory.get(driver);

        SearhPageObject.initSearchInput();
        SearhPageObject.typeSearchLine("Java");
        SearhPageObject.waitForSearchResult("Java (programming language)");

        System.out.println("Первый тест из урока завершён!");
    }

    @Test // Второй тест из обучающего урока
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();

        System.out.println("Второй тест из урока завершён!");
    }

    @Test // Тест из обучающего урока (проверка НЕ пустого результата поиска)
    public void testAmountOfNotEmptySearch() {
        String search_line = "Nirvana";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        int count_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "Не найдено ни одного результата поиска!",
                count_search_results > 0
        );

        System.out.println("Тест из обучающего урока (проверка НЕ пустого результата поиска) завершён!");
    }

    @Test // Тест из обучающего урока (проверка пустого результата поиска)
    public void testAmountOfEmptySearch() {
        String search_line = "dgdgdfsgdsfg";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();

        System.out.println("Тест из обучающего урока (проверка пустого результата поиска) завершён!");
    }

    @Test // Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex2: Создание метода)
    public void testElementHasText_Ex2() {
        String hasText = "Search Wikipedia";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        assertEquals(
                "Искомый текст элемента строки ввода '" + hasText + "' не найден!",
                hasText,
                SearchPageObject.getTextSearchInput()
        );
        System.out.println("Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex2: Создание метода) завершён!");
    }

    @Test // Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex3: Тест: отмена поиска)
    public void testCancelSearchWord_Ex3() {
        String search_line = "Sport";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        System.out.println("Найдено " + SearchPageObject.getAmountOfFoundArticles() + " статей в поиске");

        SearchPageObject.clearSearchLine();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();

        System.out.println("Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex3: Тест: отмена поиска) завершён!");
    }

    @Test // Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex4*: Тест: проверка слов в поиске)
    public void testCheckSearchWordInArticle_Ex4() {
        String search_line = "Sport";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        assertTrue(
                "Ни один заголовок статьи НЕ содержит слово '" + search_line + "'",
                SearchPageObject.assertHasTextSearchTitle(search_line)
        );

        System.out.println("Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex4*: Тест: проверка слов в поиске) завершён!");
    }

    @Test
    public void testTemplateRefactoring_Ex9() { // Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex9*: Рефакторинг темплейта)
        int need_match = 3;

        String search_line = "Sport",
               search_title = "port",
               search_description = "club";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult(search_line);

        int find_match = SearchPageObject.waitForElementByTitleAndDescription(search_title, search_description);

        assertTrue(
                "Найдено менее " + need_match + " статей с заголовком '" + search_title + "' и описанием '" + search_description + "'",
                find_match >= need_match
        );
        System.out.println("Всего статей с заголовком '" + search_title + "' и описанием '" + search_description + "' = " + find_match);

        System.out.println("Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex9*: Рефакторинг темплейта) завершён!");
    }
}
