package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Тесты для проверки работы сос статьями")
public class ArticleTests extends CoreTestCase {

    @Test // Третий тест из обучающего урока
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Третий тест из обучающего урока (проверка заголовка статьи)")
    @Description("Ищем в поиске статью со словом 'Java', проверяем ее заголовок 'Java (programming language)' ")
    @Step("Стартуем testCompareArticleTitle..")
    public void testCompareArticleTitle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Неожиданный заголовок!",
                "Java (programming language)",
                article_title
        );
        System.out.println("Третий тест из урока завершён!");
    }

    @Test // Четвертый тест из обучающего урока
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Четвертый тест из обучающего урока (свайп до конца статьи)")
    @Description("Ищем и открываем статью со словом 'Appium'; свайпаем вверх до конца статьи")
    @Step("Стартуем testSwipeArticle..")
    public void testSwipeArticle() {
        String search_line = "Appium";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(search_line);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();

        System.out.println("Четвертый тест из урока завершён!");
    }

    @Test // Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex6: Тест: assert title)
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Проверка заголовка статьи с подстрокой")
    @Description("Ищем статью с заголовком по слову 'Sport', кликаем и проверяем ее описание 'porting CP'")
    @Step("Стартуем testAssertTitle_Ex6..")
    public void testAssertTitle_Ex6() {
        String search_line = "Sport";
        String article  = "porting CP";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.clickByArticleWithSubstring(article);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.assertTitleArticleText(article);

        System.out.println("Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex6: Тест: assert title) завершён!");
    }
}
