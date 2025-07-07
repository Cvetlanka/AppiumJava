package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Тесты для проверки поисковых запросов")
public class SearchTests extends CoreTestCase {

    @Test // Первый тест из обучающего урока
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Первый тест из обучающего урока (поиск слова)")
    @Description("Вводим слово 'Java' в поисковую статью, проверяем результат поиска статьи с описанием 'Java (programming language)'")
    @Step("Стартуем testSearch...")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch(){
        SearchPageObject SearhPageObject = SearchPageObjectFactory.get(driver);

        SearhPageObject.initSearchInput();
        SearhPageObject.typeSearchLine("Java");
        SearhPageObject.waitForSearchResult("Java (programming language)");

        System.out.println("Первый тест из урока завершён!");
    }

    @Test // Второй тест из обучающего урока
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Второй тест из обучающего урока (отмена поиска)")
    @Description("Отменяем поиск и проверяем, что кнопка отмены поиска исчезла")
    @Step("Стартуем testCancelSearch...")
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();

        System.out.println("Второй тест из урока завершён!");
    }

    @Test // Тест из обучающего урока (проверка НЕ пустого результата поиска)
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Проверка НЕ пустого результата поиска")
    @Description("Ищем результаты поиска слова Nirvana, проверяем количество найденных статей")
    @Step("Стартуем testAmountOfNotEmptySearch...")
    public void testAmountOfNotEmptySearch() {
        String search_line = "Nirvana";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        int count_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "Не найдено ни одного результата поиска!",
                count_search_results > 0
        );

        System.out.println("Тест из обучающего урока (проверка НЕ пустого результата поиска) завершён!");
    }

    @Test // Тест из обучающего урока (проверка пустого результата поиска)
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Проверка пустого результата поиска")
    @Description("Ищем результаты введенной абракадабры 'dgdgdfsgdsfg', проверяем количество найденных статей")
    @Step("Стартуем testAmountOfEmptySearch...")
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
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Поиск текста элемента строки ввода")
    @Description("Проверяем появление начального текста в строке поиска 'Search Wikipedia'")
    @Step("Стартуем testElementHasText_Ex2...")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testElementHasText_Ex2() {
        String hasText = "Search Wikipedia";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        Assert.assertEquals(
                "Искомый текст элемента строки ввода '" + hasText + "' не найден!",
                hasText,
                SearchPageObject.getTextSearchInput()
        );
        System.out.println("Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex2: Создание метода) завершён!");
    }

    @Test // Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex3: Тест: отмена поиска)
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Отмена поиска")
    @Description("Ищем результаты поиска слова 'Sport', считаем их и отменяем поиск")
    @Step("Стартуем testCancelSearchWord_Ex3...")
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
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Проверка слов в поиске")
    @Description("Проверяем количество статей поиска со словом 'Sport'")
    @Step("Стартуем testCheckSearchWordInArticle_Ex4...")
    public void testCheckSearchWordInArticle_Ex4() {
        String search_line = "Sport";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        Assert.assertTrue(
                "Ни один заголовок статьи НЕ содержит слово '" + search_line + "'",
                SearchPageObject.assertHasTextSearchTitle(search_line)
        );

        System.out.println("Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex4*: Тест: проверка слов в поиске) завершён!");
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Поиск статьи с описанием (рефакторинг темплейта)")
    @Description("Проверяем кол-во найденых статей (<=3) с конкретным заголовком 'port' и описанием 'club'")
    @Step("Стартуем testTemplateRefactoring_Ex9...")
    @Severity(value = SeverityLevel.MINOR)
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

        Assert.assertTrue(
                "Найдено менее " + need_match + " статей с заголовком '" + search_title + "' и описанием '" + search_description + "'",
                find_match >= need_match
        );
        System.out.println("Всего статей с заголовком '" + search_title + "' и описанием '" + search_description + "' = " + find_match);

        System.out.println("Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex9*: Рефакторинг темплейта) завершён!");
    }
}
